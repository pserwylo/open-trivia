package com.serwylo.trivia.questions.factories

import com.serwylo.trivia.questions.Question

abstract class QuestionFactory {

	private List<Header> headers = null

	private static final defaultHeaders = [
		new Header( name: "subject", isRequired: false ),
		new Header( name: "difficulty", isRequired: false ),
		new Header( name: "commenttohost", isRequired: false ),
	]

	static List<QuestionFactory> getFactories() {

		return [
			new FictitiousSettingsFactory(),
			new WhichCameFirstFactory(),
		]

	}

	protected Map<String,String> parseLine( String line ) {

		Map<String,String> values = null

		LineParser parser = new LineParser( getHeaders(), line )
		if ( parser.parseLine() ) {
			values = parser.values
		}

		return values
	}

	protected final List<Header> getHeaders() {

		if ( headers == null ) {

			headers = []
			headers.addAll( additionalHeaders )
			headers.addAll( defaultHeaders )

		}

		return headers
	}

	/**
	 * The list of identifiers which must be present in a row of a data file to be valid.
	 * @return
	 */
	abstract protected List<Header> getAdditionalHeaders()

	abstract protected List<Question> parseQuestions( Map<String,String> values )

	abstract protected File getFile()

	/**
	 * This is the name of the factory, purely for debugging and descriptive output.
	 * @return
	 */
	abstract String getName()


	List<Question> getQuestions() {

		List<Question> questions = []

		File file = getFile()

		file.eachLine { line, i ->

			if ( i != 1 ) {

				Map<String,String> values = parseLine( line )
				if ( values ) {

					questions.addAll( parseQuestions( values ) )

				}

			}
		}

		return questions

	}


}
