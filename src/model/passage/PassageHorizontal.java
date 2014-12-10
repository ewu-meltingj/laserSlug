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
package model.passage;

import model.Point;
import model.room.Room;

/**
 * The Class PassageHorizontal.
 */
public class PassageHorizontal extends A_Passage {

	/**
	 * Instantiates a new passage horizontal.
	 * 
	 * @param leftRoom
	 *            the left room
	 * @param rightRoom
	 *            the right room
	 */
	public PassageHorizontal(Room leftRoom, Room rightRoom) {
		super(leftRoom, rightRoom);
		_firstDoor.setOrigin(originRight(leftRoom));
		_secondDoor.setOrigin(originLeft(rightRoom));
	}

	/**
	 * Origin left.
	 * 
	 * @param room
	 *            the room
	 * @return the point
	 */
	private Point originLeft(Room room) {
		int xCoord = room.getOrigin().getX();
		int yCoord = room.getOrigin().getY() + room.getHeight() / 2;
		Point point = new Point(yCoord, xCoord);
		_passageEnd = point;
		return point;
	}

	/**
	 * Origin right creates an origin point for the room.
	 * 
	 * @param room
	 *            the room
	 * @return the point
	 */
	private Point originRight(Room room) {
		int xCoord = room.getOrigin().getX() + room.getWidth() - 1;
		int yCoord = room.getOrigin().getY() + room.getHeight() / 2;
		Point point = new Point(yCoord, xCoord);
		_passageOrigin = point;
		return point;
	}

}
