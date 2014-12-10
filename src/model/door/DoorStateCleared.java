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
package model.door;

import model.Point;
import model.player.Player;
import contracts.I_DoorState;

// TODO: Auto-generated Javadoc
/**
 * The Class DoorStateCleared.
 */
public class DoorStateCleared implements I_DoorState {

	/*
	 * (non-Javadoc)
	 * 
	 * @see contracts.I_DoorState#getSymbol()
	 */
	@Override
	public int getSymbol() {
		return "+".codePointAt(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see contracts.I_DoorState#getSymbolSimple()
	 */
	@Override
	public int getSymbolSimple() {
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see contracts.I_DoorState#interact(model.player.Player, model.Point,
	 * model.door.Door)
	 */
	@Override
	public void interact(Player player, Point direction, Door parentDoor) {
		player.move(direction);
	}
}