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

import primitives.AnimatedBitmap;
import primitives.Primitive;


/**
 * Graphics context representwork with low level system API
 * @author yuli
 *
 */
public class GraphicsContextImpl implements GraphicsContext, ImageObserver {

	private BufferedImage _bufferedImage;
	private Graphics2D _render;
	private Graphics2D _device;
	private AffineTransform _transform = new AffineTransform();
	private int _width;
	private int _height;
	
	
	public GraphicsContextImpl(int width, int height){
		_width = width;
		_height = height;
		_device = null;
	}
	/**
	 * Sets render to the context
	 * @param device
	 */
	public void setGrapicsDevice(Graphics2D device){
		_device = device;
		//_bufferedImage = _device.getDeviceConfiguration().createCompatibleImage(_width, _height, Transparency.OPAQUE);
		_bufferedImage = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_ARGB);
	}

	@Override
	public void start() {
		_render = _bufferedImage.createGraphics();
		_render.setRenderingHint (RenderingHints.KEY_ANTIALIASING,   RenderingHints.VALUE_ANTIALIAS_ON);
		
		Composite backup=_render.getComposite();
		Rectangle2D.Double rect =  new Rectangle2D.Double(0, 0, 
				_bufferedImage.getWidth(),
				_bufferedImage.getHeight()); 

		_render.setComposite(AlphaComposite.Src);
		_render.fill(rect);
		_render.setComposite(backup);
		
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
		setTransform(point);
		_render.setPaint(obj.getFillColor());
		_render.fill(obj.getShape());
	}
	@Override
	public void RenderBitmap(AnimatedBitmap bitmap, Point2D.Double point, int offset_x, int offset_y) {
		setTransform(point);
		_render.setClip(bitmap.getShape());
		_render.drawImage(bitmap.getData(), offset_x, offset_y, null);
		_render.setClip(null);
	}
	private void setTransform(Point2D.Double point) {
		_transform.setToIdentity();
		_transform.translate(point.x, point.y);
		_render.setTransform(_transform);
	}
}
