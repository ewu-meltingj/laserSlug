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
import model.maze.Interactable;
import model.player.Player;

/**
 * The Interface I_UserInteract. This is added to passages, rooms, and doors.
 * They interact with the player and the direction intended.
 */
public interface I_UserInteract {

	/**
	 * Interact with.
	 * 
	 * @param player
	 *            the player
	 * @param direction
	 *            the direction
	 */
	public void interactWith(Player player, Coordinate direction);

	/**
	 * Sets the interactable bounds.
	 * 
	 * @param active
	 *            is the element ready to interact. It is called by its self to
	 *            draw the bounds it can interact.
	 */
	public void setInteractableBounds(Interactable active);
}
