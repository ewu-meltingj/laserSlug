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

//TODO remember that it should observe passages and not doors since passages hold both doors being interacted with.
package view;

import model.player.Player;
import contracts.I_Observe;

// TODO: Auto-generated Javadoc
/**
 * The Class ViewObservePlayer.
 */
public class ViewObserverPassage implements I_Observe {

	/** The _player. */
	private Player _passage;

	/** The _drawer. */
	private ViewDrawer _drawer;

	/**
	 * Instantiates a new view observe player.
	 * 
	 * @param passage
	 *            the passage
	 * @param drawer
	 *            the drawer
	 */
	public ViewObserverPassage(Player passage, ViewDrawer drawer) {
		_passage = passage;
		_drawer = drawer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see contracts.I_Observe#update()
	 */
	@Override
	public void update() {
		if (_passage.isStateChanged()) {
			_drawer.drawPlayer(_passage);
			_passage.isStateChanged(false);
		}
	}
}
