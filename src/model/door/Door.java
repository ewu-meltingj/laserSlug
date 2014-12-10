/**    *********************************
 *     ******* Team Lazer Slugz ********
 *     *********************************
 *              
 *  This is the current state of the third
 *  iteration for Team Lazer Slugz. The code
 *  uses some more patterns to help separate
 *  MVC elements. The ultimate goal of this 
 *  iteration is to create a game that can
 *  switch out a GUI on the fly.
 * 	
 */
package model.door;

import model.Coordinate;
import model.maze.Interactable;
import model.passage.A_Passage;
import model.player.Player;
import contracts.I_GetObserved;
import contracts.I_DoorBehavior;
import contracts.I_UserInteract;

/**
 * The Class Door is an observed entity that a player can interact. If the user
 * interacts in such a way that the door changes behavior, it's state will not
 * be changed. the doors observer will update the door to handle its current
 * state.
 */
public class Door implements I_GetObserved, I_UserInteract {

	/** The _door behavior. */
	private I_DoorBehavior _doorBehavior;

	/** The _passage. */
	private A_Passage _passage;

	/** The _origin. */
	private Coordinate _origin;

	/** The state. */
	private boolean _isStateChanged;

	/**
	 * Instantiates a new door.
	 * 
	 * @param doorBehavior
	 *            the door's behavior
	 */
	public Door(I_DoorBehavior doorBehavior, A_Passage passage) {
		_doorBehavior = doorBehavior;
		_passage = passage;
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
	public Coordinate getOrigin() {
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
		return _doorBehavior.getSymbol();
	}

	/**
	 * Gets the symbol used by the traversing algorithm
	 * 
	 * @return the simple symbol.
	 */
	public int getSymbolSimple() {
		return _doorBehavior.getSymbolSimple();
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
	public void interactWith(Player player, Coordinate direction) {
		_doorBehavior.interact(player, direction, this);
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
	 * Sets the door behavior.
	 * 
	 * @param door
	 *            sets new behavior for the door.
	 */
	public void setDoorState(I_DoorBehavior door) {
		_doorBehavior = door;
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
	public void setOrigin(Coordinate origin) {
		_origin = origin;
	}
}
