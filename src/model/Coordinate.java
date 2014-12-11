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
 * The Class Point.
 */
public class Coordinate {

	/**
	 * Refuse. A static method to push a player backwards two steps.
	 * Currently it takes a point but it really should take a player
	 * interface that holds the desired move and the players move
	 * method.
	 * 
	 * @param coord
	 *            the point the user tries to move to.
	 * @return the point that the user is the pushed to.
	 */
	public static Coordinate refuse(Coordinate coord) {
		return new Coordinate(coord.getY() * -2, coord.getX() * -2);
	}

	/**
	 * Translates a new point based on two input points
	 * 
	 * @param pointOne
	 *            a point
	 * @param pointTwo
	 *            a point
	 * @return the point
	 */
	public static Coordinate translate(Coordinate pointOne, Coordinate pointTwo) {
		return new Coordinate(pointOne.getY() + pointTwo.getY(), pointOne.getX()
				+ pointTwo.getX());
	}

	/** The _x coordinate. */
	private int _xCoordinate;

	/** The _y coordinate. */
	private int _yCoordinate;

	/**
	 * Instantiates a new point.
	 * 
	 * @param yCoordinate
	 *            the y coordinate
	 * @param xCoordinate
	 *            the x coordinate
	 */
	public Coordinate(int yCoordinate, int xCoordinate) {
		_xCoordinate = xCoordinate;
		_yCoordinate = yCoordinate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object point) {
		if (this == point)
			return true;
		if (!(point instanceof Coordinate))
			return false;
		Coordinate pos = (Coordinate) point;
		if (this.getY() != pos.getY())
			return false;
		if (this.getX() != pos.getX())
			return false;
		return true;
	}

	/**
	 * Gets the x coordinate.
	 * 
	 * @return the x coordinate.
	 */
	public int getX() {
		return _xCoordinate;
	}

	/**
	 * Gets the y coordinate.
	 * 
	 * @return the y coordinate.
	 */
	public int getY() {
		return _yCoordinate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = 0;
		result = 31 * result + _yCoordinate;
		result = 31 * result + _xCoordinate;

		return result;
	}

	/**
	 * Sets the x coordinate.
	 * 
	 * @param xCoord
	 *            the new x coordinate
	 */
	public void setX(int xCoord) {
		_xCoordinate = xCoord;
	}

	/**
	 * Sets the y coordinate.
	 * 
	 * @param yCoord
	 *            the new y coordinate.
	 */
	public void setY(int yCoord) {
		_xCoordinate = yCoord;
	}
}
