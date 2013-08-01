package interfaces;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;


public interface GraphicsObject {
	public void render(Scene scene, Point2D.Double transform);
	public Rectangle getBounds();
	public Shape getShape();
}
