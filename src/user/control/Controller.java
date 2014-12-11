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
package user.control;

import model.maze.Interactable;
import model.player.Player;
import user.control.actions.I_ActionHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
public class Controller {

	/** The _terminal. */
	private Terminal _terminal;

	/** The _player. */
	private Player _player;

	/** The _active elements. */
	private Interactable _activeElements;

	/**
	 * Instantiates a new controller that the user will interact with.
	 * 
	 * @param terminal
	 *            the terminal
	 * @param player
	 *            the player
	 * @param activeElements
	 *            the interactive elements
	 */
	public Controller(Terminal terminal, Player player,
			Interactable activeElements) {
		_terminal = terminal;
		_player = player;
		_activeElements = activeElements;
	}

	/**
	 * Check command.
	 */
	public void checkCommand() {
		doAction(_terminal.getKey());
	}

	/**
	 * Do action.
	 * 
	 * @param actionHandler
	 *            the action handler (visitor pattern)
	 */
	public void doAction(I_ActionHandler actionHandler) {
		actionHandler.handleAction(_player, _activeElements);
	}

}
