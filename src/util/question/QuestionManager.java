package util.question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// TODO: Auto-generated Javadoc

public class QuestionManager {

	private final int _trivialQuestionAmount = 19; // used as placeholder to demonstrate concept

	private Random _random;

	private int _questionTotalClipped;

	private int _questionTotal;

	private int _questionPerSide;

	private List<Integer> _usedRandomQuestionIDs;

	public QuestionManager() {
		_questionTotal = getTotalFromDatabase();
		_questionPerSide = questionTotalRoot(_questionTotal);
		_questionTotalClipped = _questionPerSide * _questionPerSide;
		_random = new Random();
		_usedRandomQuestionIDs = new ArrayList<Integer>();
	}

	public int getNextRandomID() {
		if (_usedRandomQuestionIDs.size() == _questionTotal)
			throw new RuntimeException("ID larger that number of quesitons.");

		Integer questionID = _random.nextInt(_questionTotal);
		while (_usedRandomQuestionIDs.contains(questionID))
			questionID = _random.nextInt(_questionTotal);

		_usedRandomQuestionIDs.add(questionID);
		return questionID;
	}

	private int getTotalFromDatabase() {
		return _trivialQuestionAmount;
	}

	public List<Integer> getUsedIDs() {
		return _usedRandomQuestionIDs;
	}

	public int lendthSide() {
		return _questionPerSide;
	}

	public int length() {
		return _questionTotal;
	}

	public int lengthCleaned() {
		return _questionTotalClipped;
	}

	private int questionTotalRoot(int total) {
		return ((int) Math.sqrt(total));
	}
}
