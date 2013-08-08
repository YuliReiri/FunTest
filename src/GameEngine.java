import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Date;

import javax.swing.JComponent;

import factories.AnimatedSpriteFactory;
import factories.CircleFactory;
import factories.RectFactory;




public class GameEngine extends JComponent implements MouseListener, KeyListener{
	
	private final int  WIDTH_SCN = 400;
	private final int  HIEGHT_SCN = 400;
	private SceneImpl _scene;
	private int _deltaTime;

	private GraphicsContextImpl _graphicsContext;
	private long _prevTime = new Date().getTime();

	private static final long serialVersionUID = 1L;
	
/**
 * Creating primitive factories. 
 */
	
	
	
	private final String _RESOURCE1 = "res/bouncing.bmp";
	private final Rectangle2D.Double _AREA = new Rectangle2D.Double(WIDTH_SCN/4, HIEGHT_SCN/4, WIDTH_SCN - WIDTH_SCN/4, HIEGHT_SCN - HIEGHT_SCN/4);
	private final Point2D.Double _POS = new Point2D.Double(-100.0, -100.0);
	private final Point2D.Double _VEL = new Point2D.Double(100.0, 100.0);
	private RectFactory _factoryRct = new RectFactory(_AREA, _POS, _VEL);
	private CircleFactory _factoryCrl = new CircleFactory(_AREA, _POS, _VEL);
	private AnimatedSpriteFactory _factorySprite;
	
	
	public GameEngine(){
		
		
		_graphicsContext = new GraphicsContextImpl(WIDTH_SCN, HIEGHT_SCN);
		_scene = new SceneImpl(new Rectangle2D.Double(0,0,WIDTH_SCN,HIEGHT_SCN), _graphicsContext);
		
		try {
			_factorySprite = new AnimatedSpriteFactory(_RESOURCE1, _AREA, _POS, _VEL);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
		_deltaTime = (int)(cur_time.getTime()-_prevTime);
		
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
		if (e.getButton() == MouseEvent.BUTTON1){
			if (e.isShiftDown() ){
				_scene.createSceneObject(_factoryRct);
			} else if (e.isControlDown()) {
				_scene.createSceneObject(_factorySprite);
			} else {
				_scene.createSceneObject(_factoryCrl);
			}
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
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (KeyEvent.VK_SHIFT  == arg0.getKeyCode()){
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
}
