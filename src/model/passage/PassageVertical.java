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
 * The Class PassageVertical.
 */
public class PassageVertical extends A_Passage {

	/**
	 * Instantiates a new passage vertical.
	 * 
	 * @param topRoom
	 *            the top room
	 * @param bottomRoom
	 *            the bottom room
	 */
	public PassageVertical(Room topRoom, Room bottomRoom) {
		super(topRoom, bottomRoom);
		_firstDoor.setOrigin(originBottom(topRoom));
		_secondDoor.setOrigin(originTop(bottomRoom));
	}

	/**
	 * Origin bottom.
	 * 
	 * @param room
	 *            the room
	 * @return the point
	 */
	private Point originBottom(Room room) {
		int xCoord = room.getOrigin().getX() + room.getWidth() / 2;
		int yCoord = room.getOrigin().getY() + room.getHeight() - 1;
		Point point = new Point(yCoord, xCoord);
		_passageOrigin = point;
		return point;
	}

	/**
	 * Origin top.
	 * 
	 * @param room
	 *            the room
	 * @return the point
	 */
	private Point originTop(Room room) {
		int xCoord = room.getOrigin().getX() + room.getWidth() / 2;
		int yCoord = room.getOrigin().getY();
		Point point = new Point(yCoord, xCoord);
		_passageEnd = point;
		return point;
	}
}
