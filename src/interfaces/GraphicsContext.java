package interfaces;
import java.awt.geom.Point2D.Double;

import primitives.Primitive;

public interface GraphicsContext {
/**
 * has to be called before scene render starts	
 */
	public void start();
/**
 * flush data to the buffer
 */
	public void flushBuffer();
	
/**
 *  if we need rotation in future than it has to be transform matrix, 
 *  but for our simple task we will use 2d point
 * @param obj - circle object  
 * @param transform - transform point 
 */
	public void RenderPrimitive(Primitive obj, Double point);
}
