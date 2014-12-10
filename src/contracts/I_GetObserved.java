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

package contracts;

/**
 * The Interface I_GetObserve is added to entities that can change state. this
 * is used to redraw elements that have altered state.
 */
public interface I_GetObserved {

	/**
	 * Checks if state changed.
	 * 
	 * @return true, if is changed
	 */
	public boolean isStateChanged();

	/**
	 * Manually set status of state.
	 * 
	 * @param isChanged
	 *            is true for a state change and false otherwise.
	 */
	public void isStateChanged(boolean isChanged);
}
