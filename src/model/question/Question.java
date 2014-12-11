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
package model.question;

/**
 * The Class Question.
 */
public class Question {

	/** The _question. */
	private String _question;

	/** The _answer. */
	private String _answer;

	/**
	 * Instantiates a new question.
	 */
	public Question() {
		_question = "Hamburgers are Better than hotdogs. (true)";
		_answer = "true";
	}

	/**
	 * Check answer.
	 * 
	 * @param userAnswer
	 *            the user answer
	 * @return true, if successful
	 */
	public boolean checkAnswer(String userAnswer) {
		return userAnswer.equalsIgnoreCase(_answer);
	}

	/**
	 * Gets the answer.
	 * 
	 * @return the answer
	 */
	public String getAnswer() {
		return _answer;
	}

	/**
	 * Gets the question.
	 * 
	 * @return the question
	 */
	public String getQuestion() {
		return _question;
	}

}
