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
package model.maze;

import java.util.HashMap;
import java.util.Map;

import model.Coordinate;
import model.player.Player;
import contracts.I_UserInteract;

/**
 * The Class Interactable.
 */
public class Interactable {

	/** The _interactive grid. */
	private Map<Coordinate, I_UserInteract> _interactiveGrid;

	/**
	 * Instantiates a new interactable.
	 */
	public Interactable() {
		_interactiveGrid = new HashMap<Coordinate, I_UserInteract>();
	}

	/**
	 * Instantiates a new interactable. Used with the MazeBuilder to grab all
	 * interactable elements created.
	 * 
	 * @see util.maze.MazeBuilder#create(Maze maze)
	 * 
	 * @param interactive
	 *            the interactive
	 */
	public Interactable(Interactable interactive) {
		_interactiveGrid = interactive.getInteractables();
	}

	/**
	 * Adds an interactive element to this
	 * 
	 * @param active
	 *            the active element
	 */
	public void add(I_UserInteract active) {
		active.setInteractableBounds(this);
	}

	/**
	 * Adds an array of interactive elements
	 * 
	 * @param active
	 *            the active array of elements
	 */
	public void addArray(I_UserInteract[] active) {
		for (int i = 0; i < active.length; i++)
			add(active[i]);
	}

	/**
	 * Gets the interactables.
	 * 
	 * @return the interactables
	 */
	public Map<Coordinate, I_UserInteract> getInteractables() {
		return _interactiveGrid;
	}

	/**
	 * Interactive element interacts with a player and its possible direction.
	 * 
	 * @param player
	 *            the player
	 * @param direction
	 *            the direction
	 */
	public void interactWith(Player player, Coordinate direction) {
		_interactiveGrid.get(player.possible(direction)).interactWith(player,
				direction);
	}

	/**
	 * Checks if the players direction touches an interactive elements
	 * 
	 * @param player
	 *            the player
	 * @param direction
	 *            the direction
	 * @return true, if is interactive
	 */
	public boolean isInteractive(Player player, Coordinate direction) {
		Coordinate possibleMove = player.possible(direction);
		return _interactiveGrid.containsKey(possibleMove);
	}

	/**
	 * Put an interactive element inside a HashMap with a point value as the
	 * key. The key is used as a coordinate system. If an interactive element
	 * sits on more than one point then that object will be referenced on each
	 * point it resided.
	 * 
	 * @param point
	 *            the point an interactive element sits.
	 * @param active
	 *            the interactive element.
	 */
	public void put(Coordinate point, I_UserInteract active) {
		_interactiveGrid.put(point, active);
	}
}
