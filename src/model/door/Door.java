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
package model.door;

import model.Point;
import model.maze.Interactable;
import model.passage.A_Passage;
import model.player.Passage;
import contracts.I_GetObserved;
import contracts.I_DoorState;
import contracts.I_UserInteract;

/**
 * The Class Door is an observed entity that can interact with a user.
 */
public class Door implements I_GetObserved, I_UserInteract {

	/** The _door state. */
	private I_DoorState _doorState;

	/** The _passage. */
	private A_Passage _passage;

	/** The _origin. */
	private Point _origin;

	/** The state. */
	private boolean _isStateChanged;

	/**
	 * Instantiates a new door.
	 * 
	 * @param doorState
	 *            the door state
	 */
	public Door(I_DoorState doorState) {
		_doorState = doorState;
		_isStateChanged = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Door door = (Door) obj;
		if (!this._origin.equals(door.getOrigin()))
			return false;

		return true;
	}

	/**
	 * Gets the height.
	 * 
	 * @return the height
	 */
	public int getHeight() {
		return 1;
	}

	/**
	 * Gets the origin.
	 * 
	 * @return the origin
	 */
	public Point getOrigin() {
		return _origin;
	}

	/**
	 * Gets the passage.
	 * 
	 * @return the passage
	 */
	public A_Passage getPassage() {
		return _passage;
	}

	/**
	 * Gets the symbol shown to the user.
	 * 
	 * @return the symbol
	 */
	public int getSymbol() {
		return _doorState.getSymbol();
	}

	/**
	 * Gets the symbol simple used by the traversable algorithm
	 * 
	 * @return the symbol simple
	 */
	public int getSymbolSimple() {
		return _doorState.getSymbolSimple();
	}

	/**
	 * Gets the width.
	 * 
	 * @return the width
	 */
	public int getWidth() {
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see contracts.I_UserInteract#interactWith(model.player.Player,
	 * model.Point)
	 */
	@Override
	public void interactWith(Passage player, Point direction) {
		_doorState.interact(player, direction, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see contracts.I_GetObserved#isStateChanged()
	 */
	@Override
	public boolean isStateChanged() {
		return _isStateChanged;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see contracts.I_GetObserved#isStateChanged(boolean)
	 */
	@Override
	public void isStateChanged(boolean isChanged) {
		_isStateChanged = isChanged;
	}

	/**
	 * Sets the door state.
	 * 
	 * @param door
	 *            sets new behavior for the door.
	 */
	public void setDoorState(I_DoorState door) {
		_doorState = door;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * contracts.I_UserInteract#setInteractableBounds(model.maze.Interactable)
	 */
	@Override
	public void setInteractableBounds(Interactable element) {
		element.put(_origin, this);
	}

	/**
	 * Sets the origin.
	 * 
	 * @param origin
	 *            the new origin
	 */
	public void setOrigin(Point origin) {
		_origin = origin;
	}

	/**
	 * Sets the passage.
	 * 
	 * @param passage
	 *            the new passage
	 */
	public void setPassage(A_Passage passage) {
		_passage = passage;
	}
}
