package model.question;

public class QuestionTF {

	private String _question;

	private String _answer;

	public QuestionTF() {
		_question = "Hamburgers are Better than hotdogs. (true)";
		_answer = "true";
	}

	public boolean checkAnswer(String userAnswer) {
		return userAnswer.equalsIgnoreCase(_answer);
	}

	public String getAnswer() {
		return _answer;
	}

	public String getQuestion() {
		return _question;
	}

}
