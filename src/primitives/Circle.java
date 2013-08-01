package primitives;



import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;


public class Circle extends Primitive {

	Ellipse2D.Double _circle;
	
	public Circle (double x, double y, double radius, Color fill) {
		super(fill);
		_circle = new Ellipse2D.Double(x, y, radius, radius);
	}
	
	
	@Override
	public Rectangle getBounds() {
		return _circle.getBounds();
	}
	public Shape getShape() {
		return _circle;
	}
}
