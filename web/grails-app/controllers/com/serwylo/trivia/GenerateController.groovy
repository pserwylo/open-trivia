package com.serwylo.trivia

import org.grails.datastore.mapping.query.api.Criteria

class GenerateController {

	static defaultAction = "answerSheet"

	TriviaFactoryService triviaFactoryService

	def "new"() {
		return []
	}

	def questionSheet() {

		TriviaNight night = triviaFactoryService.createRandomTriviaNight( 3 )

		[ night: night ]

	}

	def answerSheet() {

		TriviaNight night = triviaFactoryService.createRandomTriviaNight( 3 )
		return [ night: night ]

	}

	def download() {

		TriviaNight night = triviaFactoryService.createRandomTriviaNight( 3 )

		render( filename: "AnswerSheet.pdf",
				contentType: "application/x-pdf",
				view: "answerSheet",
				model: [ night: night ] )

	}

	def index() {

	}

}
