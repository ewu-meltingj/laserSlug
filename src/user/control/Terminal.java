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

import user.control.actions.ActionMoveDown;
import user.control.actions.ActionMoveLeft;
import user.control.actions.ActionMoveRight;
import user.control.actions.ActionMoveUp;
import user.control.actions.ActionQuit;
import user.control.actions.ActionVoid;
import user.control.actions.I_ActionHandler;

import com.googlecode.blacken.colors.ColorNames;
import com.googlecode.blacken.colors.ColorPalette;
import com.googlecode.blacken.swing.SwingTerminal;
import com.googlecode.blacken.terminal.CursesLikeAPI;
import com.googlecode.blacken.terminal.TerminalInterface;

/**
 * The Class Terminal.
 */
public class Terminal {

	/** The _terminal. */
	private TerminalInterface _terminal;

	/**
	 * Instantiates a new terminal.
	 */
	public Terminal() {
		_terminal = new CursesLikeAPI(new SwingTerminal());
		init();
	}

	/**
	 * Close the terminal
	 */
	public void close() {
		_terminal.quit();
	}

	/**
	 * Gets the key inputed by the user and sets it to a custom object.
	 * 
	 * @return the object created by the key.
	 */
	public I_ActionHandler getKey() {
		int action = _terminal.getch();

		if (action == 'w')// up
			return new ActionMoveUp();
		else if (action == 'a')// left
			return new ActionMoveLeft();
		else if (action == 's')// down
			return new ActionMoveDown();
		else if (action == 'd')// right
			return new ActionMoveRight();
		else if (action == 'X')
			return new ActionQuit();
		else
			return new ActionVoid();
	}

	/**
	 * Gets the terminal.
	 * 
	 * @return the terminal
	 */
	public TerminalInterface getTerminal() {
		return _terminal;
	}

	/**
	 * Inits the terminal.
	 */
	private void init() {
		_terminal.init("Trivia Maze", 25, 80, "assets/VGA8.ttf");
		setPallete();
	}

	/**
	 * Sets the pallete.
	 */
	private void setPallete() {
		ColorPalette palette = new ColorPalette();
		palette.addAll(ColorNames.XTERM_256_COLORS, false);
		palette.putMapping(ColorNames.SVG_COLORS);
		_terminal.setPalette(palette);
	}
}