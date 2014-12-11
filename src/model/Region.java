/**    *********************************
 *     ******* Team Lazer Slugz ********
 *     *********************************
 *              
 *  This is the current state of the third
 *  iteration for Team Lazer Slugz. The code
 *  uses some more patterns to help seperate
 *  MVC elements. The ultimate goal of this 
 *  iteration is to create a game that can
 *  switch out a GUI on the fly.
 * 	
 */
package model;

/**
 * The Class Region.
 */
public abstract class Region {

	/** The width. */
	protected int _width;

	/** The height. */
	protected int _height;

	/** The origin point. */
	protected Coordinate _origin;

	/** The region identifier. */
	protected int _id;

	/**
	 * Instantiates a new region with default values.
	 * This is used by the maze which will always start
	 * at zero. The maze will then have its attributes
	 * manipulated by the maze builder.
	 * TODO It may be nice to set maze vaues then have the maze builder build from those.
	 * @param id
	 *            the identifier
	 */
	public Region(int id) {
		_width = 0;
		_height = 0;
		_origin = new Coordinate(0, 0);
		_id = id;
	}

	/**
	 * Instantiates a new region with each value set. This is used
	 * by the rooms which are given their attributes by the maze
	 * builder.
	 * 
	 * @param height
	 *            the height
	 * @param width
	 *            the width
	 * @param origin
	 *            the origin point
	 * @param id
	 *            the identifier
	 */
	public Region(int height, int width, Coordinate origin, int id) {
		_width = width;
		_height = height;
		_origin = origin;
		_id = id;
	}

	/**
	 * Gets the center point of this region.
	 * 
	 * @return the point
	 */
	public Coordinate center() {
		int y = _origin.getY() + _height / 2;
		int x = _origin.getX() + _width / 2;
		return new Coordinate(y, x);
	}

	/**
	 * Checks if this region contains a point.
	 * 
	 * @param point
	 *            the point
	 * @return true, if true the point is contained in the region.
	 */
	public boolean contains(Coordinate point) {
		int x = point.getX();
		int y = point.getY();

		if (x >= _origin.getX() + 1 && x < _origin.getX() + getWidth() - 1) {
			if (y >= _origin.getY() + 1 && y < _origin.getY() + getHeight() - 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the height.
	 * 
	 * @return the height
	 */
	public int getHeight() {
		return _height;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the identifier
	 */
	public int getId() {
		return _id;
	}

	/**
	 * Gets the origin.
	 * 
	 * @return the origin.
	 */
	public Coordinate getOrigin() {
		return _origin;
	}

	/**
	 * Gets the width.
	 * 
	 * @return the width.
	 */
	public int getWidth() {
		return _width;
	}

	/**
	 * Sets the height.
	 * 
	 * @param height
	 *            the height.
	 */
	public void setHeight(int height) {
		_height = height;
	}

	/**
	 * Sets the width.
	 * 
	 * @param width
	 *            the width.
	 */
	public void setWidth(int width) {
		_width = width;
	}

}
