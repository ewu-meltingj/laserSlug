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

import model.Coordinate;
import model.maze.Interactable;
import model.player.Player;
import util.text.TextSlug;

// TODO: Auto-generated Javadoc
/**
 * The Class ActionMoveRight.
 */
public class ActionMoveRight implements I_ActionHandler {

	/** The Constant MOVE_RIGHT. */
	private static final Coordinate MOVE_RIGHT = new Coordinate(0, +1);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * user.control.actions.I_ActionHandler#handleAction(model.player.Player,
	 * model.maze.Interactable)
	 */
	@Override
	public void handleAction(Player player, Interactable _element) {
		player.setPlayerSymbol(TextSlug.LOOKING_RIGHT);
		if (_element.isInteractive(player, MOVE_RIGHT))
			_element.interactWith(player, MOVE_RIGHT);
	};
}
