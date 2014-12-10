package control.player.action;

import model.player.Player;
import util.maze.Interactive;

public class ActionQuit implements I_ActionHandler {

	@Override
	public void handleAction(Player _player, Interactive element) {
		_player.hasQuit(true);
	}

}
