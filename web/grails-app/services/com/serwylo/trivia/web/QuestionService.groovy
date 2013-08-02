package com.serwylo.trivia.web

import com.serwylo.trivia.Difficulty
import com.serwylo.trivia.Question
import com.serwylo.trivia.Subject

class QuestionService {

	List<Question> getQuestions(long subjectId = 0, long difficultyId = 0) {

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
