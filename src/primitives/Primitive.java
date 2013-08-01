package primitives;

import interfaces.GraphicsObject;
import interfaces.Scene;

import java.awt.Color;
import java.awt.geom.Point2D;

public abstract class Primitive implements GraphicsObject {

	protected Color _fillColor;

	public Primitive(Color fill) {
		super();
		_fillColor= fill;
	}

	public Color getFillColor() {
		return _fillColor;
	}
	
	@Override
	public void render(Scene scene, Point2D.Double transform) {
		scene.getGraphicsContext().RenderPrimitive(this, transform);
	}

}