package control.player.action;

import model.player.Player;
import model.point.Point;
import util.maze.Interactive;
import util.text.TextSlug;

public class ActionMoveUp implements I_ActionHandler {

	private static final Point MOVE_UP = new Point(-1, 0);

	@Override
	public void handleAction(Player player, Interactive _element) {
		player.setPlayerSymbol(TextSlug.LOOKING_UP);
		if (_element.isInteractive(player, MOVE_UP))
			_element.interactWith(player, MOVE_UP);
	};
}
