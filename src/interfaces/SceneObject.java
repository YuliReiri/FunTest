package interfaces;

import java.awt.geom.Point2D;

/**
 * 
 * @author yuli
 *
 * Object should have some position in scene, so made this class abstract 
 */

public abstract class SceneObject {
	
	private Point2D.Double _pos;	
	
	public SceneObject(Point2D.Double pos){
		setPos(pos);
	}
	public abstract void update ( Scene scene, double delta_time );
	public abstract void render ( Scene scene );
	
	public Point2D.Double getPos() {
		return _pos;
	}
	
	public void setPos(Point2D.Double pos) {
		_pos = pos;
	}
}
