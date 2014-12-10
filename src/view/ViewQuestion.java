package view;

import model.question.QuestionTF;

import com.googlecode.blacken.terminal.BlackenMouseEvent;
import com.googlecode.blacken.terminal.BlackenWindowEvent;
import com.googlecode.blacken.terminal.TerminalInterface;
import com.googlecode.blacken.terminal.TerminalView;
import com.googlecode.blacken.terminal.TerminalViewInterface;
import com.googlecode.blacken.terminal.editing.CodepointCallbackInterface;
import com.googlecode.blacken.terminal.editing.SingleLine;

public class ViewQuestion implements CodepointCallbackInterface {

	private final TerminalInterface term;

	private TerminalViewInterface view;

	private QuestionTF _question;

	public ViewQuestion(TerminalInterface term, QuestionTF question) {
		this.term = term;
		_question = question;
		view = new TerminalView(term);
	}

	@Override
	public int handleCodepoint(int codepoint) {
		return codepoint;
	}

	@Override
	public boolean handleMouseEvent(BlackenMouseEvent mouse) {
		return false;
	}

	@Override
	public void handleResizeEvent() {
		term.clear();
	}

	@Override
	public boolean handleWindowEvent(BlackenWindowEvent window) {
		return false;
	}

	public boolean run() {
		term.clear();
		SingleLine.putString(view, 4, 3, _question.getQuestion(), 50, 0);
		String answer = SingleLine.getString(term, 20, 3, 22, null);

		return _question.checkAnswer(answer);
	}

}
