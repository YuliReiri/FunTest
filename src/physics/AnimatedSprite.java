package physics;

import interfaces.Scene;

import java.awt.geom.Point2D.Double;

import primitives.AnimatedBitmap;

/**
 * 
 * @author yuli
 * 
 * This class represents animated sprite. 
 */
public class AnimatedSprite extends SimpleMovingObj {

	private int _currentTime;
	private int _framesCount;
	private int _animationDuration;


	public AnimatedSprite(AnimatedBitmap graphics_obj, Double pos, Double velocity) {
		super(graphics_obj, pos, velocity);
		_currentTime = 0;
	}
/**
 * 
 * @param frames_count in the AnimatedBitmap
 * @param duration in milliseconds
 */
	public void SetAnimationData(int frames_count, int duration){
		_framesCount = frames_count;
		_animationDuration = duration;
	}
	/**
	 * @return current animation frame
	 */
	protected int getCurrentFrame(){
		return _currentTime * (_framesCount-1) / _animationDuration ;
	}
	/**
	 * updates animation time and sets corresponding frame to AnimatedBitmap
	 */
	@Override
	public void update(Scene scene, int delta_time) {
		super.update(scene, delta_time);
		((AnimatedBitmap)_graphicsObject).setCurrentFrame(getCurrentFrame());
		_currentTime = (_currentTime >=  _animationDuration ) ?  0 :  _currentTime + delta_time;
	}
}
