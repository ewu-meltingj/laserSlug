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
package model.room;

import java.util.ArrayList;
import java.util.List;

import model.Coordinate;
import model.Region;
import model.door.Door;
import model.maze.Interactable;
import model.player.Player;
import contracts.I_UserInteract;

/**
 * The Class Room.
 */
public class Room extends Region implements I_UserInteract {

	/** The _id. */
	private int _id;

	/** The _door list. */
	private transient List<Door> _doorList;

	/**
	 * Instantiates a new room.
	 * 
	 * @param id
	 *            the id
	 * @param origin
	 *            the origin
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 */
	public Room(int id, Coordinate origin, int width, int height) {
		super(height, width, origin, id);
		_id = id;
		_doorList = new ArrayList<Door>();
	}

	/**
	 * Adds a door to the room.
	 * 
	 * @param door
	 *            the door
	 */
	public void addDoor(Door door) {
		_doorList.add(door);
	}

	/**
	 * Gets the doors.
	 * 
	 * @return the doors
	 */
	public List<Door> getDoors() {
		return _doorList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see contracts.I_UserInteract#interactWith(model.player.Player,
	 * model.Point)
	 */
	@Override
	public void interactWith(Player player, Coordinate direction) {
		player.move(direction);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * contracts.I_UserInteract#setInteractableBounds(model.maze.Interactable)
	 */
	@Override
	public void setInteractableBounds(Interactable active) {
		int startY = _origin.getY() + 1;
		int startX = _origin.getX() + 1;
		int endY = startY + _height - 2;
		int endX = startX + _width - 2;
		for (int y = startY; y < endY; y++)
			for (int x = startX; x < endX; x++)
				active.put(new Coordinate(y, x), this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ID:" + _id + " Height:" + _height + " Width:" + _width;
	}
}
