package model.point;

import java.io.Serializable;

public class Point implements Serializable {

	private static final long serialVersionUID = -5522070899577942L;

	public static Point translate(Point pointOne, Point pointTwo) {
		return new Point(pointOne.getY() + pointTwo.getY(), pointOne.getX()
				+ pointTwo.getX());
	}
	
	public static Point refuse(Point pointOne) {
		return new Point(pointOne.getY() * -1, pointOne.getX() * -1);
	}

	private int _xCoordinate;

	private int _yCoordinate;

	public Point(int yCoordinate, int xCoordinate) {
		_xCoordinate = xCoordinate;
		_yCoordinate = yCoordinate;
	}

	@Override
	public boolean equals(Object point) {
		if (this == point)
			return true;
		if (!(point instanceof Point))
			return false;
		Point pos = (Point) point;
		if (this.getY() != pos.getY())
			return false;
		if (this.getX() != pos.getX())
			return false;
		return true;
	}

	public int getX() {
		return _xCoordinate;
	}

	public int getY() {
		return _yCoordinate;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = 31 * result + _yCoordinate;
		result = 31 * result + _xCoordinate;

		return result;
	}

	public void setX(int xCoord) {
		_xCoordinate = xCoord;
	}

	public void setY(int yCoord) {
		_xCoordinate = yCoord;
	}
}
