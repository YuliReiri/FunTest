package factories;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.util.ArrayList;
import java.util.Random;

public abstract class PrimitiveFactory {

	protected Random _rnd = new Random();
	protected ArrayList<Color> _colors = new ArrayList<Color>();
	protected Double _area;
	protected java.awt.geom.Point2D.Double _startSpeed;
	protected java.awt.geom.Point2D.Double _deltaSpeed;

	protected PrimitiveFactory(Rectangle2D.Double area, Point2D.Double start_speed, Point2D.Double delta_speed) {
		super();
		_area = area;
		_startSpeed = start_speed;
		_deltaSpeed = delta_speed;
		_colors.add(Color.RED);
		_colors.add(Color.GREEN);
		_colors.add(Color.BLUE);
		_colors.add(Color.YELLOW);
	}

	protected Color getColor(){
		return _colors.get((int)Math.round( _rnd.nextFloat()*(_colors.size()-1)));
	}
	protected Point2D.Double getPos(){
		return new Point2D.Double(
				generateRandomDouble(_area.x, _area.width), 
				generateRandomDouble(_area.y, _area.height));
	}
	protected Point2D.Double getVelocity(){
		return new Point2D.Double(
				generateRandomDouble (_startSpeed.x, _startSpeed.x + _deltaSpeed.x),
				generateRandomDouble (_startSpeed.y, _startSpeed.y + _deltaSpeed.y));
	}

	protected double generateRandomDouble(double min, double max) {
		return  min + (max - min) * _rnd.nextDouble();
	}

}