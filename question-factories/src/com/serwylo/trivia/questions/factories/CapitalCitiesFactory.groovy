package com.serwylo.trivia.questions.factories

import com.serwylo.trivia.questions.Question

class CapitalCitiesFactory extends QuestionFactory {

	final static HEADER_COUNTRY = "country"
	final static HEADER_CAPITAL = "capital"
	final static HEADER_CAN_REVERSE = "canreverse"

	protected File getFile() {
		return new File( "/home/pete/code/open-trivia/data/CapitalCities.tsv" )
	}

	@Override
	String getName() {
		return "Capital Cities"
	}

	protected List<Header> getAdditionalHeaders() {

		return [
			new Header( name: "country", isRequired: true ),
			new Header( name: "capital", isRequired: true ),
			new Header( name: "canreverse", isRequired: false ),
		]

	}

	protected List<Question> parseQuestions( Map<String,String> lineValues ) {

		List<Question> questions = []

		String country = lineValues[ HEADER_COUNTRY ]
		String capital = lineValues[ HEADER_CAPITAL ]

		if ( ! lineValues[ HEADER_CAN_REVERSE ]?.toLowerCase()?.equals( "no" ) ) {
			questions.add(
				new Question(
					question: "What country is $capital the capital of?",
					answer: country
				).md5( getName(), capital + country )
			)
		}

		questions.add(
			new Question(
				question: "What is the capital of $country?",
				answer: capital
			).md5( getName(), country + capital )
		)

		return questions

	}

}
