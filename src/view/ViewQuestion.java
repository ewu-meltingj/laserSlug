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

import java.util.EnumSet;

import model.question.Question;

import com.googlecode.blacken.terminal.BlackenEventType;
import com.googlecode.blacken.terminal.BlackenMouseEvent;
import com.googlecode.blacken.terminal.BlackenWindowEvent;
import com.googlecode.blacken.terminal.TerminalInterface;
import com.googlecode.blacken.terminal.TerminalView;
import com.googlecode.blacken.terminal.TerminalViewInterface;
import com.googlecode.blacken.terminal.editing.CodepointCallbackInterface;
import com.googlecode.blacken.terminal.editing.SingleLine;

// TODO: Auto-generated Javadoc
/**
 * The Class ViewQuestion.
 */
public class ViewQuestion implements CodepointCallbackInterface {

	/** The _term. */
	private TerminalInterface _term;

	/** The _view. */
	private TerminalViewInterface _view;

	/**
	 * Instantiates a new view question.
	 * 
	 * @param term
	 *            the term
	 */
	public ViewQuestion(TerminalInterface term) {
		_term = term;
		_view = new TerminalView(_term);
	}

	/**
	 * Answer question.
	 * 
	 * @param question
	 *            the question
	 * @return true, if successful
	 */
	public boolean answerQuestion(Question question) {
		_term.setEventNotices(EnumSet.of(BlackenEventType.MOUSE_WHEEL));
		_view = new TerminalView(_term);
		_term.clear();
		SingleLine.putString(_view, 4, 3, question.getQuestion(), 50, 0);
		String answer = SingleLine.getString(_term, 20, 3, 22, null);
		_view.clear();
		return question.checkAnswer(answer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.googlecode.blacken.terminal.editing.CodepointCallbackInterface#
	 * handleCodepoint(int)
	 */
	@Override
	public int handleCodepoint(int codepoint) {
		return codepoint;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.googlecode.blacken.terminal.editing.CodepointCallbackInterface#
	 * handleMouseEvent(com.googlecode.blacken.terminal.BlackenMouseEvent)
	 */
	@Override
	public boolean handleMouseEvent(BlackenMouseEvent mouse) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.googlecode.blacken.terminal.editing.CodepointCallbackInterface#
	 * handleResizeEvent()
	 */
	@Override
	public void handleResizeEvent() {
		_term.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.googlecode.blacken.terminal.editing.CodepointCallbackInterface#
	 * handleWindowEvent(com.googlecode.blacken.terminal.BlackenWindowEvent)
	 */
	@Override
	public boolean handleWindowEvent(BlackenWindowEvent window) {
		return false;
	}

}
