package model.region;

import model.point.Point;

public abstract class A_Region {

	protected int _width;

	protected int _height;

	protected Point _origin;

	protected int _id;

	public A_Region(int id) {
		_width = 0;
		_height = 0;
		_origin = new Point(0, 0);
		_id = id;
	}

	public A_Region(int height, int width, Point origin, int id) {
		_width = width;
		_height = height;
		_origin = origin;
		_id = id;
	}

	public Point center() {
		int y = _origin.getY() + _height / 2;
		int x = _origin.getX() + _width / 2;
		return new Point(y, x);
	}

	public boolean contains(Point point) {
		int x = point.getX();
		int y = point.getY();

		if (x >= _origin.getX() + 1 && x < _origin.getX() + getWidth() - 1) {
			if (y >= _origin.getY() + 1 && y < _origin.getY() + getHeight() - 1) {
				return true;
			}
		}
		return false;
	}

	public int getHeight() {
		return _height;
	}

	public int getId() {
		return _id;
	}

	public Point getOrigin() {
		return _origin;
	}

	public int getWidth() {
		return _width;
	}

	public void setHeight(int height) {
		_height = height;
	}

	public void setWidth(int width) {
		_width = width;
	}

}
