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
package view;

import model.player.Player;
import contracts.I_Observe;

// TODO: Auto-generated Javadoc
/**
 * The Class ViewObservePlayer.
 */
public class ViewObservePlayer implements I_Observe {

	/** The _player. */
	private Player _player;

	/** The _drawer. */
	private ViewDrawer _drawer;

	/**
	 * Instantiates a new view observe player.
	 * 
	 * @param door
	 *            the door
	 * @param drawer
	 *            the drawer
	 */
	public ViewObservePlayer(Player door, ViewDrawer drawer) {
		_player = door;
		_drawer = drawer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see contracts.I_Observe#update()
	 */
	@Override
	public void update() {
		if (_player.isStateChanged()) {
			_drawer.drawPlayer(_player);
			_player.isStateChanged(false);
		}
	}
}
