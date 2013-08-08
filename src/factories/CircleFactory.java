package factories;


import interfaces.SceneObject;
import interfaces.SceneObjectFactory;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import physics.SimpleMovingObj;
import primitives.Circle;
/**
 * creates scene object with circle primitive in it 
 * @author yuli
 *
 */
public class CircleFactory extends PrimitiveFactory implements SceneObjectFactory {

	public CircleFactory(Rectangle2D.Double area, Point2D.Double start_speed, Point2D.Double delta_speed){
		super(area, start_speed, delta_speed);
	}
	@Override
	public SceneObject createSceneObect() {
		
		return new SimpleMovingObj(
				new Circle(0, 0, 20 ,getColor()) ,getPos(),getVelocity());
	}

}
