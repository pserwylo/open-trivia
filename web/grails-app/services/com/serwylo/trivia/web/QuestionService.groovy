package com.serwylo.trivia.web

import com.serwylo.trivia.Difficulty
import com.serwylo.trivia.Question
import com.serwylo.trivia.Subject

class QuestionService extends CRUDService {

	List<Question> list(def params) {
		def listParams = getListParams( params )
		Question.createCriteria().list( listParams, generateCriteria( params ) )
	}

	int count(def params) {
		Question.createCriteria().count( generateCriteria( params ) )
	}

	private Closure generateCriteria( def params = null ) {

		long subjectId = 0
		if ( params?.containsKey( 'subject.id' ) && params[ 'subject.id' ] != 'null' ) {
			try {
				subjectId = params[ 'subject.id' ] as Long
			} catch ( NumberFormatException e ) {}
		}

		long difficultyId = 0
		if ( params?.containsKey( 'difficulty.id' ) && params[ 'difficulty.id' ] != 'null' ) {
			try {
				difficultyId = params[ 'difficulty.id' ] as Long
			} catch ( NumberFormatException e ) {}
		}

		def criteria = {

			if ( subjectId > 0 ) {
				subjects {
					eq ( 'id', subjectId )
				}
			}

			if ( difficultyId > 0 ) {
				difficulty {
					eq ( 'id', difficultyId )
				}
			}
		}

		return criteria

	}
}
