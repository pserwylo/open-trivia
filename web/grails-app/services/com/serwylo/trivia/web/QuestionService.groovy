package com.serwylo.trivia.web

import com.serwylo.trivia.Difficulty
import com.serwylo.trivia.Question
import com.serwylo.trivia.Subject

class QuestionService {

	List<Question> getAllQuestions() {

		return Question.list()

	}

	List<Question> getQuestions(long subjectId, long difficultyId ) {

		Question.withCriteria {

			if ( subjectId > 0 ) {
				subject {
					eq ( 'id', subjectId )
				}
			}

			if ( difficultyId > 0 ) {
				difficulty {
					eq ( 'id', difficultyId )
				}
			}

		}

	}
}
