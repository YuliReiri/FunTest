import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Date;

import javax.swing.JComponent;

import primitives.CircleFactory;
import primitives.RectFactory;



public class GameEngine extends JComponent implements MouseListener, KeyListener{
	
	private final int  WIDTH_SCN = 400;
	private final int  HIEGHT_SCN = 400;
	private SceneImpl _scene;
	private double _deltaTime;

	private GraphicsContextImpl _graphicsContext;
	private long _prevTime = new Date().getTime();
	private boolean _shiftPressed = false;

	private static final long serialVersionUID = 1L;
	
/**
 * Creating primitive factories. 
 */
	private CircleFactory _factoryCrl = new CircleFactory(
			new Rectangle2D.Double(WIDTH_SCN/4, HIEGHT_SCN/4, WIDTH_SCN - WIDTH_SCN/4, HIEGHT_SCN - HIEGHT_SCN/4), 
			new Point2D.Double(-50.0, -50.0), new Point2D.Double(200.0, 200.0));
	
	private RectFactory _factoryRct = new RectFactory(
			new Rectangle2D.Double(WIDTH_SCN/4, HIEGHT_SCN/4, WIDTH_SCN - WIDTH_SCN/4, HIEGHT_SCN - HIEGHT_SCN/4), 
			new Point2D.Double(-50.0, -50.0), new Point2D.Double(200.0, 200.0));
	
	public GameEngine(){
		
		
		_graphicsContext = new GraphicsContextImpl(WIDTH_SCN, HIEGHT_SCN);
		_scene = new SceneImpl(new Rectangle2D.Double(0,0,WIDTH_SCN,HIEGHT_SCN), _graphicsContext);
		
		addMouseListener(this);
		addKeyListener(this);
		
		setFocusable(true);
		requestFocusInWindow();
	}
	
	 @Override
	public Dimension getMinimumSize() {
	    return new Dimension(WIDTH_SCN, HIEGHT_SCN);
	}
	
	@Override
	public Dimension getPreferredSize() {
	    return new Dimension(WIDTH_SCN, HIEGHT_SCN);
	}
	
	@Override
    public void paintComponent(Graphics device) {
		
// calculate time between frames  		
		Date cur_time = new Date ();
		_deltaTime = (cur_time.getTime()-_prevTime)/1000.0;
		
// update scene objects. in this test only move and collision detection
		_scene.update(_deltaTime);		
		super.paintComponent(device);
// setup graphics device to graphics context		
		_graphicsContext.setGrapicsDevice((Graphics2D)device);
		
// prepare for render 
		_graphicsContext.start();
// render all objects in scene 		
		_scene.render();
// flush back buffer to graphics device   		
		_graphicsContext.flushBuffer();
		
		_prevTime = cur_time.getTime();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (_shiftPressed){
			_scene.createSceneObject(_factoryRct);
		} else {
			_scene.createSceneObject(_factoryCrl);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (KeyEvent.VK_SHIFT  == arg0.getKeyCode()){
			_shiftPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (KeyEvent.VK_SHIFT  == arg0.getKeyCode()){
			_shiftPressed  = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
}
