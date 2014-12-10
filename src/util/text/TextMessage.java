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
package util.text;

// TODO: Auto-generated Javadoc
/**
 * The Class TextMessage.
 */
public class TextMessage {

	/** The Constant END_MESSAGE. */
	public static final String END_MESSAGE = "Thanks for rocking with us\n"
			+ "============================================================================\n"
			+ "We worked hard to show you guys an awesome trivia maze\n"
			+ "The UI Library used to handle all GUI stuff is Called Blacken.\n"
			+ "This application does need his library to work. All the models/control\n"
			+ "was written by these handsome fellas before y'all.\n"
			+ "\n"
			+ "If you were unaware, any time a user quits prior to the maze saying it\n"
			+ "is no longer traversable, the state of the game saves for the next play.\n"
			+ "\n" + "\n" + "Press 'q' to close this screen\n" + "\n";;

	/** The Constant START_MESSAGE. */
	public static final String START_MESSAGE = "GAME Commands\n"
			+ "============================================================================\n"
			+ "LEFT : keyboard 'a'                  | RIGHT : keyboard 'd'\n"
			+ "UP : keyboard 'w'                    | DOWN : keyboard 's'\n"
			+ "QUIT :  keyboard 'X'                 \n"
			+ "\n"
			+ "Team Lazer Slugs invites you to a good time.\n\n"
			+ "\n"
			+ "Any time a user quits prior to the maze saying it\n"
			+ "is no longer traversable, the state of the game saves for the next play.\n"
			+ "\n" + "\n" + "Press 'q' to close this screen\n" + "\n";

	/**
	 * Instantiates a new text message.
	 */
	private TextMessage() {
	}
}
