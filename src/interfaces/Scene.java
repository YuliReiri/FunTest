package interfaces;
import java.awt.Rectangle;


/**
 * Scene interface 
 * @author yuli
 *
 */
public interface Scene {
	public Rectangle getBounds();
	public GraphicsContext getGraphicsContext();
	/**
	 * creates and adds object in to scene
	 * @param factory - scene primitive object factory  
	 * @return created object
	 */
	public SceneObject createSceneObject(SceneObjectFactory factory);
}
