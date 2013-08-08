

package factories;

import interfaces.SceneObject;
import interfaces.SceneObjectFactory;

import java.awt.geom.Rectangle2D.Double;

import physics.SimpleMovingObj;
import primitives.Rect;
/**
 * creates scene object with rect primitive 
 * @author yuli
 *
 */
public class RectFactory extends PrimitiveFactory implements SceneObjectFactory {

	public RectFactory(Double area, java.awt.geom.Point2D.Double start_speed,
			java.awt.geom.Point2D.Double delta_speed) {
		super(area, start_speed, delta_speed);
	}

	@Override
	public SceneObject createSceneObect() {
		
		
		
		
		return new SimpleMovingObj(
				new Rect(0, 0, 20, 20, getColor()), getPos(), getVelocity());
	}

}
