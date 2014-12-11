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
package user.control.actions;

import model.maze.Interactable;
import model.player.Player;

// TODO: Auto-generated Javadoc
/**
 * The Interface I_ActionHandler.
 */
public interface I_ActionHandler {

	/**
	 * Handle action. Handles the players action with it's desired direction
	 * with an interactive element. There may or may not be an interactive
	 * element. It depends on the point that the player moves.
	 * 
	 * @param _player
	 *            the _player
	 * @param element
	 *            the interactive element.
	 */
	public void handleAction(Player _player, Interactable element);
}
