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
package model.player;

import model.Point;
import model.room.Room;
import util.text.TextSlug;
import contracts.I_GetObserved;

/**
 * The Class Player.
 */
public class Player implements I_GetObserved{

	/** The _x coordinate. */
	private int _xCoordinate;

	/** The _y coordinate. */
	private int _yCoordinate;

	/** The _old position. */
	private Point _oldPosition;

	/** The _symbol player. */
	private int _symbolPlayer;

	/** The _current room. */
	private transient Room _currentRoom;

	/** The _current room id. */
	private int _currentRoomID;

	/** The _has quit. */
	private boolean _hasQuit;

	/** The _is state changed. */
	private boolean _isStateChanged;

	/**
	 * Instantiates a new player.
	 *
	 * @param newRoom the room the player starts in
	 */
	public Player(Room newRoom) {
		_currentRoom = newRoom;
		_currentRoomID = newRoom.getId();
		this.setPlayerPosition(newRoom.center());
		_oldPosition = new Point(_yCoordinate, _xCoordinate);
		_hasQuit = false;
		_isStateChanged = true;
		_symbolPlayer = TextSlug.LOOKING_DOWN;
	}

	/**
	 * Gets the player position prev.
	 *
	 * @return the player position prev
	 */
	public Point getPlayerPositionPrev() {
		return _oldPosition;
	}

	/**
	 * Gets the player symbol.
	 *
	 * @return the player symbol
	 */
	public int getPlayerSymbol() {
		return _symbolPlayer;
	}

	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Point getPosition() {
		return new Point(_yCoordinate, _xCoordinate);
	}

	/**
	 * Gets the room current id.
	 *
	 * @return the room current id
	 */
	public int getRoomCurrentID() {
		return _currentRoomID;
	}

	/**
	 * Gets the x coordinate.
	 *
	 * @return the x
	 */
	public int getX() {
		return _xCoordinate;
	}

	/**
	 * Gets the y coordinate.
	 *
	 * @return the y
	 */
	public int getY() {
		return _yCoordinate;
	}

	/**
	 * Checks for user quit.
	 *
	 * @return true, if successful application ends.
	 */
	public boolean hasQuit() {
		return _hasQuit;
	}

	/**
	 * Sets quit.
	 *
	 * @param hasQuit the user just informed the application that it quit.
	 */
	public void hasQuit(boolean hasQuit) {
		_hasQuit = hasQuit;
	}

	/* (non-Javadoc)
	 * @see contracts.I_GetObserved#isStateChanged()
	 */
	@Override
	public boolean isStateChanged() {
		return _isStateChanged;
	}

	/* (non-Javadoc)
	 * @see contracts.I_GetObserved#isStateChanged(boolean)
	 */
	@Override
	public void isStateChanged(boolean isChanged) {
		_isStateChanged = isChanged;
	}

	/**
	 * Move. moves player and informs the application that
	 * its' state changed.
	 *
	 * @param direction the direction moved to.
	 */
	public void move(Point direction) {
		_oldPosition = getPosition();
		this.setPlayerPosition(_yCoordinate + direction.getY(), _xCoordinate
				+ direction.getX());
		_isStateChanged = true;
	}

	/**
	 * Possible. creates a new point that the user wants to move to
	 *
	 * @param direction the direction
	 * @return the point
	 */
	public Point possible(Point direction) {
		return Point.translate(this.getPosition(), direction);
	}

	/**
	 * Sets the player position.
	 *
	 * @param yCoord the y coord
	 * @param xCoord the x coord
	 */
	public void setPlayerPosition(int yCoord, int xCoord) {
		_xCoordinate = xCoord;
		_yCoordinate = yCoord;
	}

	/**
	 * Sets the player position.
	 *
	 * @param position the new player position
	 */
	public void setPlayerPosition(Point position) {
		_xCoordinate = position.getX();
		_yCoordinate = position.getY();
	}

	/**
	 * Sets the player position prev.
	 *
	 * @param prev the new player position prev
	 */
	public void setPlayerPositionPrev(Point prev) {
		_oldPosition = prev;
	}

	/**
	 * Sets the player symbol.
	 *
	 * @param symbolPlayer the new player symbol
	 */
	public void setPlayerSymbol(int symbolPlayer) {
		_symbolPlayer = symbolPlayer;
	}

	/**
	 * Sets the room.
	 *
	 * @param newRoom the new room
	 */
	public void setRoom(Room newRoom) {
		_currentRoom = newRoom;
		_currentRoomID = _currentRoom.getId();
	}

}
