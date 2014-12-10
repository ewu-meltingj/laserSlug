package control.player.action;

import model.player.Player;
import model.point.Point;
import util.maze.Interactive;
import util.text.TextSlug;

public class ActionMoveLeft implements I_ActionHandler {

	private static final Point MOVE_LEFT = new Point(0, -1);

	@Override
	public void handleAction(Player player, Interactive _element) {
		player.setPlayerSymbol(TextSlug.LOOKING_LEFT);
		if (_element.isInteractive(player, MOVE_LEFT))
			_element.interactWith(player, MOVE_LEFT);
	};
}
