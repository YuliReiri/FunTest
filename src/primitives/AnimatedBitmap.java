package primitives;
import interfaces.GraphicsObject;
import interfaces.Scene;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
/**
 * 
 * @author yuli
 * Simple animated bitmap graphics object. 
 * Count of frames and current frame could be set up and object will automatically render necessary frame
 */

public class AnimatedBitmap implements GraphicsObject {
	private Image _data = null;
	private int _framesCount = 0;
	private Rectangle2D.Double _rect;
	private int _currentFrame = 0;
	
	public AnimatedBitmap(Image image, int frames_count){
		_data = image;
		_framesCount = frames_count;
		_rect = new Rectangle2D.Double(0,0, getFrameSize(), image.getHeight(null));
	}
	/**
	 * 
	 * @return calculates frame size in a pixels based on frames count 
	 */
	private int getFrameSize() {
		return _data.getWidth(null)/_framesCount;
	}
	/**
	 * 
	 * @param frame current frame to render
	 */
	public void setCurrentFrame(int frame){
		_currentFrame = frame;
	}
/**
 * renders current frame of image
 */
	@Override
	public void render(Scene scene, Double transform) {
		scene.getGraphicsContext().RenderBitmap(this, transform, -getFrameSize()*_currentFrame, 0);
	}
	@Override
	public Rectangle getBounds() {
		return getShape().getBounds();
	}
	@Override
	public Shape getShape() {
		return _rect;
	}
	/**
	 * 
	 * @return image object that represents bitmap data
	 */
	
	public Image getData() {
		return _data;
	}
}
