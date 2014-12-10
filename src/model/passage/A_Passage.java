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

import model.Coordinate;
import model.door.Door;
import model.door.DoorBlocked;
import model.door.DoorCleared;
import model.door.DoorQuestion;
import model.maze.Interactable;
import model.player.Player;
import model.question.Question;
import model.room.Room;
import contracts.I_UserInteract;

/**
 * The Class A_Passage.
 */
public abstract class A_Passage implements I_UserInteract {

	/** The _first door. */
	protected Door _firstDoor;

	/** The _second door. */
	protected Door _secondDoor;

	/** The _passage end. */
	protected Coordinate _passageEnd;

	/** The _passage origin. */
	protected Coordinate _passageOrigin;

	/** The _question. */
	public Question _question;

	/**
	 * Instantiates a new a_ passage.
	 * 
	 * @param firstRoom
	 *            the first room
	 * @param secondRoom
	 *            the second room
	 */
	public A_Passage(Room firstRoom, Room secondRoom) {
		_firstDoor = new Door(new DoorQuestion(), this);

		_secondDoor = new Door(new DoorQuestion(), this);

		_question = new Question();
	}

	/**
	 * Block doors. Changed the passages door's behavior to that of blocked.
	 */
	public void blockDoors() {
		_firstDoor.setDoorState(new DoorBlocked());
		_secondDoor.setDoorState(new DoorBlocked());
	}

	/**
	 * Clear doors. Changed the passages door's behavior to that of cleared.
	 */
	public void clearDoors() {
		_firstDoor.setDoorState(new DoorCleared());
		_secondDoor.setDoorState(new DoorCleared());
	}

	/**
	 * Gets the door first.
	 * 
	 * @return the door first
	 */
	public Door getDoorFirst() {
		return _firstDoor;
	}

	/**
	 * Gets the door second.
	 * 
	 * @return the door second
	 */
	public Door getDoorSecond() {
		return _secondDoor;
	}

	/**
	 * Gets the door sibling of the door inside the method parameter.
	 * 
	 * @param door
	 *            the door
	 * @return the door sibling
	 */
	public Door getDoorSibling(Door door) {
		if (!_firstDoor.equals(door))
			return _firstDoor;
		return _secondDoor;
	}

	/**
	 * Gets the end point of the passage
	 * 
	 * @return the end
	 */
	public Coordinate getEnd() {
		return _passageEnd;
	}

	/**
	 * Gets the height.
	 * 
	 * @return the height
	 */
	public int getHeight() {
		return _passageEnd.getY() - _passageOrigin.getY();
	}

	/**
	 * Gets the origin point of the passage.
	 * 
	 * @return the origin
	 */
	public Coordinate getOrigin() {
		return _passageOrigin;
	}

	/**
	 * Gets the question shared by the passage doors
	 * 
	 * @return the question
	 */
	public Question getQuestion() {
		return _question;
	}

	/**
	 * Gets the width.
	 * 
	 * @return the width
	 */
	public int getWidth() {
		return _passageEnd.getX() - _passageOrigin.getX();
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
		Coordinate origin = _passageOrigin;
		Coordinate end = _passageEnd;
		int lenthHorizontal = end.getX() - origin.getX();
		int lenthVertical = end.getY() - origin.getY();

		for (int y = 0; y < lenthVertical; y++)
			active.put(new Coordinate(y + origin.getY(), origin.getX()), this);

		for (int x = 1; x < lenthHorizontal; x++)
			active.put(new Coordinate(origin.getY(), origin.getX() + x), this);
	}

}
