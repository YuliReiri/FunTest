package physics;


import interfaces.GraphicsObject;
import interfaces.Scene;
import interfaces.SceneObject;

import java.awt.Rectangle;
import java.awt.geom.Point2D;


public class SimpleMovingObj extends SceneObject {

	
	private Point2D.Double _velocity;
	private GraphicsObject _graphicsObject;


	public SimpleMovingObj(GraphicsObject graphics_obj, Point2D.Double pos, Point2D.Double velocity) {
		super(pos);
		_velocity = velocity;
		_graphicsObject = graphics_obj;
	}

	@Override
	public void update(Scene scene, double delta_time) {
// in case of complex object behavior and collision between them we need to introduce physics scene
			Rectangle scn_rct = scene.getBounds();
			Rectangle obj_rct = _graphicsObject.getBounds();
			if ( getPos().x < 0.0 && _velocity.x < 0.0 )
				_velocity.x *= -1.0;
			
			if ( (getPos().x + obj_rct.width) > scn_rct.width && _velocity.x > 0.0)
				_velocity.x *= -1.0;
			
			if ( getPos().y < 0.0 && _velocity.y < 0.0)
				_velocity.y *= -1.0;
			
			if ( ( getPos().y + obj_rct.height) > scn_rct.height && _velocity.y > 0.0 )  
				_velocity.y *= -1.0;
		
	
		getPos().setLocation(getPos().getX()+_velocity.getX()*delta_time, getPos().getY()+_velocity.getY()*delta_time);
	}

	@Override
	public void render(Scene scene) {
		_graphicsObject.render(scene, getPos());
	}

}
