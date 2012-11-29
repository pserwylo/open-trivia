package com.serwylo.trivia.questions.factories

import com.serwylo.trivia.questions.Question

import java.text.DateFormat
import java.text.SimpleDateFormat

/**
 *
 *
 *
 */
class WhichCameFirstFactory extends BatchQuestionFactory {

	final static HEADER_NAME = "source"
	final static HEADER_DATE = "date"

	private Set<String> questionsUsed = []

	protected Question generateQuestion() {

		int index1 = (int)( Math.random() * ( this.getCachedData().size() - 1 ) )
		int index2 = index1

		while ( index1 == index2 ) {
			index2 = (int)( Math.random() * ( this.getCachedData().size() - 1 ) )
		}

		Map<String, String> data1 = this.getCachedData()[ index1 ]
		Map<String, String> data2 = this.getCachedData()[ index2 ]

		DateFormat format = new SimpleDateFormat( "dd/MM/yyyy" )

		Date date1 = format.parse( data1[ HEADER_DATE ] )
		Date date2 = format.parse( data2[ HEADER_DATE ] )

		Map<String, String> first  = date1 < date2 ? data1 : data2
		Map<String, String> second = date1 < date2 ? data2 : data1

		String identifier = first[ HEADER_NAME ] + " -> " + second[ HEADER_NAME ]
		if ( !questionsUsed.contains( identifier ) )
		{
			questionsUsed.add( identifier )
			return new Question(
				question: "Which came first, ${data1[ HEADER_NAME]} or ${data2[ HEADER_NAME ]}",
				answer: "${first[ HEADER_NAME ]} ${first[ HEADER_DATE ]} (vs ${second[ HEADER_NAME ]} ${second[ HEADER_DATE ]})"
			)
		}
		else
		{
			return null
		}

	}

	@Override
	protected List<Question> generateQuestions() {

		List<Question> questions = []

		int attempts = 0
		int i = 0
		while ( i < 10 && attempts < 100 ) {

			Question q = generateQuestion()
			if ( q != null ) {
				questions.add( q )
				i ++
			}
			attempts ++
		}

		return questions

	}

	@Override
	protected List<Header> getAdditionalHeaders() {
		return [
			new Header( name:  HEADER_NAME, isRequired: true ),
			new Header( name:  HEADER_DATE, isRequired: true ),
		]
	}

	@Override
	protected File getFile() {
		new File( "/home/pete/code/open-trivia/data/WhichCameFirst.tsv" )
	}

	@Override
	String getName() {
		return "Which Came First?"
	}

}
