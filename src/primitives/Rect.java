package primitives;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class Rect extends Primitive{
	
	private java.awt.geom.Rectangle2D.Double _rect2d;

	public Rect(double x, double y, double w, double h, Color fill){
		super(fill);
		_rect2d = new Rectangle2D.Double(x, y, w, h);
	}

	@Override
	public Rectangle getBounds() {
		return _rect2d.getBounds();
	}

	@Override
	public Shape getShape() {
		return _rect2d;
	}
}
