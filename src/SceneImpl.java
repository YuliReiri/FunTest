

import interfaces.GraphicsContext;
import interfaces.Scene;
import interfaces.SceneObject;
import interfaces.SceneObjectFactory;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;



public class SceneImpl implements Scene {
	private List<SceneObject> _objects = new LinkedList<SceneObject>();
	private Rectangle2D.Double _sceneBorders;
	private GraphicsContext _graphicsDevice;
	
	
	SceneImpl(Rectangle2D.Double scene_borders, GraphicsContext graphics_device){
		
		_graphicsDevice = graphics_device;
		setSceneBorders(scene_borders);
		
	}
	public void update(int delta_time){
// update objects positions 
		for (SceneObject object :  _objects ){
			object.update(this, delta_time);
		}
	}
	
	public void render(){
//TODO: render objects 
		for (SceneObject object :  _objects ){
			object.render(this);
		}
	}
	protected Rectangle2D.Double getSceneBorders() {
		return _sceneBorders;
	}
	protected void setSceneBorders(Rectangle2D.Double scene_borders) {
		_sceneBorders = scene_borders;
	}
	
	@Override
	public GraphicsContext getGraphicsContext() {
		return _graphicsDevice;
	}
	@Override
	public Rectangle getBounds() {
		return _sceneBorders.getBounds();
	}
	@Override
	public SceneObject createSceneObject(SceneObjectFactory factory) {
		SceneObject obj = factory.createSceneObect();
		_objects.add(obj);
		return obj;
	}
}
