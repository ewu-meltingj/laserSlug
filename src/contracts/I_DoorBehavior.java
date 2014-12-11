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

package contracts;

import model.Coordinate;
import model.door.Door;
import model.player.Player;

/**
 * The Interface I_DoorState is used to switch behavior of the door entities
 * during runtime.
 */
public interface I_DoorBehavior {

	/**
	 * Gets the symbol that is displayed for the end user
	 * 
	 * @return the symbol
	 */
	public int getSymbol();

	/**
	 * Gets the symbol simple that is used for the maze traversal algorithm.
	 * 
	 * @return the symbol simple
	 */
	public int getSymbolSimple();

	/**
	 * Interact. This is called via a visitor pattern. The door has three
	 * different states that will affect the player uniquely.
	 * 
	 * @param player
	 *            the player
	 * @param direction
	 *            the direction
	 * @param parentDoor
	 *            the parent door
	 */
	public void interact(Player player, Coordinate direction, Door parentDoor);
}
