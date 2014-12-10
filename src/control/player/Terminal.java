package control.player;

import com.googlecode.blacken.colors.ColorNames;
import com.googlecode.blacken.colors.ColorPalette;
import com.googlecode.blacken.swing.SwingTerminal;
import com.googlecode.blacken.terminal.CursesLikeAPI;
import com.googlecode.blacken.terminal.TerminalInterface;

import control.player.action.ActionMoveDown;
import control.player.action.ActionMoveLeft;
import control.player.action.ActionMoveRight;
import control.player.action.ActionMoveUp;
import control.player.action.ActionQuit;
import control.player.action.ActionVoid;
import control.player.action.I_ActionHandler;

public class Terminal {

	private TerminalInterface _terminal;

	public Terminal() {
		_terminal = new CursesLikeAPI(new SwingTerminal());
		init();
	}

	public void close() {
		_terminal.quit();
	}

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

	public TerminalInterface getTerminal() {
		return _terminal;
	}

	private void init() {
		_terminal.init("Trivia Maze", 25, 80);
		setPallete();
	}

	private void setPallete() {
		ColorPalette palette = new ColorPalette();
		palette.addAll(ColorNames.XTERM_256_COLORS, false);
		palette.putMapping(ColorNames.SVG_COLORS);
		_terminal.setPalette(palette);
	}
}