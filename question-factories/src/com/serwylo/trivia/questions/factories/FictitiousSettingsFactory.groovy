package com.serwylo.trivia.questions.factories

import com.serwylo.trivia.questions.Question

class FictitiousSettingsFactory extends QuestionFactory {

	final static HEADER_SOURCE = "source"
	final static HEADER_PLACE = "place"
	final static HEADER_CAN_REVERSE = "canreverse"

	protected File getFile() {
		return new File( "/home/pete/code/open-trivia/data/FictitiousSettings.tsv" )
	}

	@Override
	String getName() {
		return "Fictitious Settings"
	}

	protected List<Header> getAdditionalHeaders() {

		return [
			new Header( name: "source", isRequired: true ),
			new Header( name: "place", isRequired: true ),
			new Header( name: "canreverse", isRequired: true ),
		]

	}

	protected List<Question> parseQuestions( Map<String,String> lineValues ) {

		List<Question> questions = []

		String source = lineValues[ HEADER_SOURCE ]
		String place = lineValues[ HEADER_PLACE ]

		if ( lineValues[ HEADER_CAN_REVERSE ]?.toLowerCase()?.equals( "yes" ) ) {
			questions.add(
				new Question(
					question: "What TV show is set in $place?",
					answer: source
				).md5( getName(), place + source )
			)
		}

		questions.add(
			new Question(
				question: "Where is $source set?",
				answer: place
			).md5( getName(), source + place )
		)

		return questions

	}

}
