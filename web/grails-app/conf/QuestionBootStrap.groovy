import com.serwylo.trivia.Difficulty
import com.serwylo.trivia.Question
import com.serwylo.trivia.Subject
import com.serwylo.trivia.questions.GeneratedQuestion
import com.serwylo.trivia.questions.factories.QuestionFactory
import com.serwylo.trivia.web.DifficultyService
import grails.util.Environment

class QuestionBootStrap {

	DifficultyService difficultyService

    def init = { servletContext ->

		initDifficulties();

		if ( Environment.current == Environment.DEVELOPMENT ) {

			initQuestionsFromFactories()
			initDefaultSubjects()

		}

	}

	void initDifficulties() {

		if ( Difficulty.count() == 0 ) {

			new Difficulty( label: "Easy", value:  Difficulty.DIFF_EASY ).save( flush: true, failOnError: true )
			new Difficulty( label: "Medium", value:  Difficulty.DIFF_MEDIUM ).save( flush: true, failOnError: true )
			new Difficulty( label: "Hard", value:  Difficulty.DIFF_HARD ).save( flush: true, failOnError: true )

		}

	}

	/**
	 * If no subjects were specified by the question factories, then we will init some
	 * default ones, jus so that we can have some dummy data (e.g. for drop down lists
	 * and filtering, etc).
	 */
	void initDefaultSubjects() {

		if ( Subject.count() == 0 ) {

			Subject geography = new Subject(
				name: "Geography",
				description: "Questions about physical features, such as size, as well as administrative features, such as cities.",
			).save( flush: true, failOnError: true )

			Subject entertainment = new Subject(
				name:  "Entertainment",
				description: "Entertainment industry in general, including music, TV, movies, famous faces, etc."
			).save( flush: true, failOnError: true )

			Subject music = new Subject(
				name:  "Music",
				description: "",
				parent: entertainment
			).save( flush:  true, failOnError: true )

			Subject tv = new Subject(
				name: "Television",
				description: "",
				parent: entertainment
			).save( flush: true, failOnError: true )

			Subject movies = new Subject(
				name: "Movies",
				description: "",
				parent: entertainment
			).save( flush: true, failOnError: true )


			entertainment.children = [ music, tv, movies ]
			entertainment.save( flush: true, failOnError: true )

		}

	}

	void initQuestionsFromFactories() {

		List<Question> allSavedQuestions = []
		List<GeneratedQuestion> allGeneratedQuestions = []

		QuestionFactory.factories.each { factory ->

			List<GeneratedQuestion> questions = factory.getQuestions()

			questions.each { question ->

				Question q = new Question(
					question: question.question,
					answer:  question.answer,
					difficulty: difficultyService.easy,
					hash: question.hash
				).save( failOnError: true )

				allGeneratedQuestions.add( question );
				allSavedQuestions.add( q )

			}

		}

		allSavedQuestions.each { question ->

			GeneratedQuestion q = allGeneratedQuestions.find { it.hash == question.hash }
			List<String> mutuallyExclusiveHashes = q.mutuallyExclusive*.hash

			question.mutuallyExclusiveQuestions = allSavedQuestions.findAll { mutuallyExclusiveHashes.contains( it.hash ) }
			question.save( failOnError: true );

		}

	}

	def destroy = {
    }
}
