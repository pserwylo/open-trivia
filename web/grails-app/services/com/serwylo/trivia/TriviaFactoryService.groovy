package com.serwylo.trivia

class TriviaFactoryService {

	def questionService

    TriviaNight createTriviaNight( String type = null ) {

		

    }
}


abstract class TriviaRoundFactory<T extends TriviaRound> {

	private static final FACTORIES = [
		(QUESTION_ROUND): new QuestionRoundFactory(),
		(QUESTION_AND_CHALLENGE_ROUND): new QuestionAndChallengeRoundFactory(),
		(SUBJECT_ROUND): new SubjectRoundFactory(),
	]

	/**
	 * If you want to have the option to include questions which are not safe for work/children/churches/etc, then set
	 * this to true before calling {@link TriviaRoundFactory#createRound()}.
	 */
	Boolean allowNsfw = false


	abstract T createRound()

	/**
	 * We want to prevent people from getting questions repeated in the same trivia night, but also across subsequent
	 * trivia nights.
	 */
	List<Question> questionsToExclude

}


class QuestionRoundFactory extends TriviaRoundFactory<QuestionRound> {

	QuestionRound createRound() {

		QuestionRound round = new QuestionRound()
	
		round.questions = generateQuestions()	

		return round
	
	}

	protected List<Question> generateQuestions() {
	
		return []
	
	}

}


class QuestionAndChallengeRoundFactory extends QuestionRoundFactory {

	QuestionAndChallengeRound createRound() {

		QuestionAndChallengeRound round = new QuestionAndChallengeRound()

		round.questions = generateQuestions()

		return round

	}

}


class SubjectRoundFactory extends QuestionRoundFactory {

	QuestionAndChallengeRound createRound() {

		QuestionAndChallengeRound round = new QuestionAndChallengeRound()

		round.questions = generateSubjectQuestions()

		return round

	}

	List<Question> generateSubjectQuestions() {

		return []

	}

}
