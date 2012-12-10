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
			new CapitalCitiesFactory(),
			new FictitiousSettingsFactory(),
			new WhichCameFirstFactory(),
			new WhoAmIFactory(),
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
		Map<String,Question> hashes = [:]

		File file = getFile()

		file.eachLine { line, i ->

			if ( i != 1 ) {

				Map<String,String> values = parseLine( line )
				if ( values ) {

					List<Question> result = parseQuestions( values )

					result.each {

						if ( !it.hash ) {
							it.md5( getName() )
						}

						if ( !hashes.containsKey( it.hash ) ) {
							hashes.put( it.hash, it )
						} else {
							throw new Exception( "Hash collision for question factory '" + getName() + "'." )
						}

						questions.add( it )
					}

				}

			}

		}

		return questions

	}


}
