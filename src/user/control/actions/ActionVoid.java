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
 * The Class ActionVoid.
 */
public class ActionVoid implements I_ActionHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * user.control.actions.I_ActionHandler#handleAction(model.player.Player,
	 * model.maze.Interactable)
	 */
	@Override
	public void handleAction(Player _player, Interactable element) {

	}

}
