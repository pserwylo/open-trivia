package com.serwylo.trivia.questions.factories

import com.serwylo.trivia.questions.GeneratedQuestion

class WhoAmIFactory extends QuestionFactory {

	final static HEADER_PERSON = "source"
	final static HEADER_FAMOUS_FOR = "famousFor"
	final static HEADER_DOB = "dob"
	final static HEADER_POB = "placeOfBirth"
	final static HEADER_CLUE_1 = "clue1"
	final static HEADER_CLUE_2 = "clue2"
	final static HEADER_CLUE_3 = "clue3"
	final static HEADER_CLUE_4 = "clue4"
	final static HEADER_CLUE_5 = "clue5"
	final static HEADER_CLUE_6 = "clue6"
	final static HEADER_CLUE_7 = "clue7"
	final static HEADER_CLUE_8 = "clue8"

	protected File getFile() {
		return new File( "/home/pete/code/open-trivia/data/WhoAmI.tsv" )
	}

	@Override
	String getName() {
		return "Who Am I"
	}

	protected List<Header> getAdditionalHeaders() {

		return [
			new Header( name: HEADER_PERSON, isRequired: true ),
			new Header( name: HEADER_FAMOUS_FOR, isRequired: false ),
			new Header( name: HEADER_DOB, isRequired: true ),
			new Header( name: HEADER_POB, isRequired: true ),
			new Header( name: HEADER_CLUE_1, isRequired: true ),
			new Header( name: HEADER_CLUE_2, isRequired: true ),
			new Header( name: HEADER_CLUE_3, isRequired: true ),
			new Header( name: HEADER_CLUE_4, isRequired: false ),
			new Header( name: HEADER_CLUE_5, isRequired: false ),
			new Header( name: HEADER_CLUE_6, isRequired: false ),
			new Header( name: HEADER_CLUE_7, isRequired: false ),
			new Header( name: HEADER_CLUE_8, isRequired: false ),
		]

	}

	protected List<GeneratedQuestion> parseQuestions( Map<String,String> lineValues ) {

		List<GeneratedQuestion> questions = []

		String person = lineValues[ HEADER_PERSON ]
		String famousFor = lineValues[ HEADER_FAMOUS_FOR ]

		List<String> clueKeys = [
		   HEADER_CLUE_1,
		   HEADER_CLUE_2,
		   HEADER_CLUE_3,
		   HEADER_CLUE_4,
		   HEADER_CLUE_5,
		   HEADER_CLUE_6,
		   HEADER_CLUE_7,
		   HEADER_CLUE_8,
		]

		List<String> clues = [ "I was born on ${lineValues[ HEADER_DOB ]} ${lineValues[ HEADER_POB ]}." ]

		for ( String key in clueKeys ) {
			if ( lineValues.containsKey( key ) ) {
				clues.add( lineValues[ key ] )
			}
		}

		clues.add( "My first name begins with ${person[0].toUpperCase()}" )

		questions.add(
			new GeneratedQuestion(
				question: "Who Am I?\n\n" + clues.join( "\n\n" ),
				answer: person + ( famousFor ? "($famousFor)" : "" ),
			)
		)

		return questions

	}

}
