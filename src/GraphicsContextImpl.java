import interfaces.GraphicsContext;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import primitives.Primitive;


/**
 * Graphics context represent work with low level system API
 * @author yuli
 *
 */
public class GraphicsContextImpl implements GraphicsContext, ImageObserver {

	private BufferedImage _bufferedImage;
	private Graphics2D _render;
	private Graphics2D _device;
	private AffineTransform _transform = new AffineTransform();
	
	
	public GraphicsContextImpl(int width, int height){
		
		_device = null;
		_bufferedImage = new BufferedImage(width, height,  
				BufferedImage.TYPE_INT_RGB);
	}
	/**
	 * Sets render to the context
	 * @param device
	 */
	public void setGrapicsDevice(Graphics2D device){
		_device = device;
	}

	@Override
	public void start() {
		_render = _bufferedImage.createGraphics();
		
		Composite backup=_render.getComposite();
		
		_render.setComposite(
				  AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
		Rectangle2D.Double rect =  new Rectangle2D.Double(0, 0, 
				_bufferedImage.getWidth(),
				_bufferedImage.getHeight()); 
		_render.fill(rect);
		_render.setComposite(backup);

		
		_render.setRenderingHint (RenderingHints.KEY_ANTIALIASING,   RenderingHints.VALUE_ANTIALIAS_ON);
	}

	@Override
	public void flushBuffer() {
		_render.dispose();	
		_device.drawImage(_bufferedImage, 0,0, this);
	}

	@Override
	public boolean imageUpdate(Image img, int info_flags, int x, int y, int width, int height) {
		return false;
	}
	
	/**
	 * Renders existing primitives 
	 */
	@Override
	public void RenderPrimitive(Primitive obj, Point2D.Double point) {
		_transform.setToIdentity();
		_transform.translate(point.x, point.y);
		_render.setPaint(obj.getFillColor());
		_render.setTransform(_transform);
		_render.fill(obj.getShape());
	}
	
}
