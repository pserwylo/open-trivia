package com.serwylo.trivia

import org.grails.datastore.mapping.query.api.Criteria

class TriviaFactoryService {

	def questionService

    TriviaNight createRandomTriviaNight( int rounds = 3 ) {

		TriviaNight night = new TriviaNight()
		for ( int i in 1..rounds ) {

			TriviaRoundFactory factory = TriviaRoundFactory.getRandomFactory()
			factory.allowNsfw = false
			night.rounds.add( factory.createRound() )

		}

		return night

    }
}


abstract class TriviaRoundFactory<T extends TriviaRound> {

	public static TriviaRoundFactory getRandomFactory()
	{

		def factories = [
			new QuestionRoundFactory(),
			new QuestionAndChallengeRoundFactory(),
			new SubjectRoundFactory(),
		]

		int index = (int)( Math.random() * ( factories.size() - 1 ) )

		return factories[ index ]

	}

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
	List<Question> questionsToExclude = []

}


class QuestionRoundFactory extends TriviaRoundFactory<QuestionRound> {

	QuestionRound createRound() {

		QuestionRound round = new QuestionRound()
	
		round.questions = generateQuestions()	

		return round
	
	}

	/**
	 * Returns 10 questions of any type, as long as they are not in questionsToExclude.
	 * @return
	 */
	protected List<Question> generateQuestions() {

		List<Question> questions = []

		for ( int i in 0..9 ) {

			List<Question> q = Question.executeQuery(
				"from Question as q where q.hash not in :illegalHashes  order by rand()",
				[ illegalHashes: questionsToExclude*.hash ?: [ 'abc' ] ],
				[ max: 1 ]
			)

			assert q.size() == 1

			Question question = q.pop()

			questions.add( question )
			questionsToExclude.add( question )
			questionsToExclude.addAll( question.mutuallyExclusiveQuestions )

		}

		return questions

	}

}


class QuestionAndChallengeRoundFactory extends QuestionRoundFactory {

	QuestionAndChallengeRound createRound() {

		QuestionAndChallengeRound round = new QuestionAndChallengeRound()

		round.questions = generateQuestions()
		round.challenge = null

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

		return generateQuestions()
		/*Criteria c = Question.createCriteria()
		return c {
			not {
				inList( "questionsToExclude" )
			}
			inList( "subjects" )
			maxResults( 10 )
		}*/

	}

}
