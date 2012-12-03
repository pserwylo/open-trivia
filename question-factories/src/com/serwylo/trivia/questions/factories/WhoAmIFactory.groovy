package com.serwylo.trivia.questions.factories

import com.serwylo.trivia.questions.Question

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

	protected List<Question> parseQuestions( Map<String,String> lineValues ) {

		List<Question> questions = []

		String person = lineValues[ HEADER_PERSON ]
		String famousFor = lineValues[ HEADER_FAMOUS_FOR ]
		String dob = lineValues[ HEADER_DOB ]
		String pob = lineValues[ HEADER_POB ]
		String clue1 = lineValues[ HEADER_CLUE_1 ]
		String clue2 = lineValues[ HEADER_CLUE_2 ]
		String clue3 = lineValues[ HEADER_CLUE_3 ]
		String clue4 = lineValues[ HEADER_CLUE_4 ]
		String clue5 = lineValues[ HEADER_CLUE_5 ]
		String clue6 = lineValues[ HEADER_CLUE_6 ]
		String clue7 = lineValues[ HEADER_CLUE_7 ]
		String clue8 = lineValues[ HEADER_CLUE_8 ]

		questions.add(
			new Question(
				question:
"""Who Am I?
I was born on $dob $pob.

$clue1

$clue2

$clue3

$clue4

$clue5

My first name begins with ${person[0].toUpperCase(

)}""",

				answer: person
			)
		)

		return questions

	}

}
