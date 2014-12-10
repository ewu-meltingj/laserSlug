package control.player;

import model.player.Player;
import util.maze.Interactive;
import control.player.action.I_ActionHandler;

public class Controller {

	private Terminal _terminal;

	private Player _player;

	private Interactive _activeElements;

	public Controller(Terminal terminal, Player player,
			Interactive activeElements) {
		_terminal = terminal;
		_player = player;
		_activeElements = activeElements;
	}

	public void checkCommand() {
		doAction(_terminal.getKey());
	}

	public void doAction(I_ActionHandler actionHandler) {
		actionHandler.handleAction(_player, _activeElements);
	}

}
