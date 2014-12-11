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

import model.door.Door;

import contracts.I_Observe;

// TODO: Auto-generated Javadoc
/**
 * The Class ViewObserveDoor.
 */
public class ViewObserveDoor implements I_Observe {

	/** The _door. */
	private Door _door;

	/** The _question viewer. */
	private ViewQuestion _questionViewer;

	/** The _drawer. */
	ViewDrawer _drawer;

	/**
	 * Instantiates a new view observe door.
	 * 
	 * @param door
	 *            the door
	 * @param drawer
	 *            the drawer
	 * @param questionViewer
	 *            the question viewer
	 */
	public ViewObserveDoor(Door door, ViewDrawer drawer,
			ViewQuestion questionViewer) {
		_door = door;
		_drawer = drawer;
		_questionViewer = questionViewer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see contracts.I_Observe#update()
	 */
	@Override
	public void update() {
		if (_door.isStateChanged()) {
			if (_questionViewer
					.answerQuestion(_door.getPassage().getQuestion()))
				_door.getPassage().clearDoors();
			else
				_door.getPassage().blockDoors();
			_drawer.drawDoor(_door);
			_drawer.drawDoor(_door.getPassage().getDoorSibling(_door));
			_door.isStateChanged(false);
		}
	}
}
