package view.observer;

import model.player.Player;
import view.drawer.Drawer;

// TODO: Auto-generated Javadoc

public class ObservePlayer implements I_Observe {

	private Player _player;

	private Drawer _drawer;

	public ObservePlayer(Player door, Drawer drawer) {
		_player = door;
		_drawer = drawer;
	}

	@Override
	public void update() {
		if (_player.isStateChanged()) {
			_drawer.drawPlayer(_player);
			_player.isStateChanged(false);
		}
	}
}
