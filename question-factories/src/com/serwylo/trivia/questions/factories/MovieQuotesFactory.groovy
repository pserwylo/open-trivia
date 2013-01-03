package com.serwylo.trivia.questions.factories

import com.serwylo.trivia.questions.GeneratedQuestion

class MovieQuotesFactory extends QuestionFactory {

	final static HEADER_PERSON = "person"
	final static HEADER_PERSON_DIFFICULTY = "personDifficulty"
	final static HEADER_MOVIE = "movie"
	final static HEADER_MOVIE_DIFFICULTY = "movieDifficulty"
	final static HEADER_QUOTE = "quote"
	final static HEADER_QUOTE_ENDING = "quoteEnding"

	protected File getFile() {
		return new File( "/home/pete/code/open-trivia/data/MovieQuotes.tsv" )
	}

	@Override
	String getName() {
		return "Movie Quotes"
	}

	protected List<Header> getAdditionalHeaders() {

		return [
			new Header( name: HEADER_PERSON, isRequired: false ),
			new Header( name: HEADER_PERSON_DIFFICULTY, isRequired: false ),
			new Header( name: HEADER_MOVIE, isRequired: true ),
			new Header( name: HEADER_MOVIE_DIFFICULTY, isRequired: false ),
			new Header( name: HEADER_QUOTE, isRequired: true ),
			new Header( name: HEADER_QUOTE_ENDING, isRequired: false ),
		]

	}

	protected List<GeneratedQuestion> parseQuestions( Map<String,String> lineValues ) {

		List<GeneratedQuestion> questions = []


		String person = lineValues[ HEADER_PERSON ]
		String personDifficulty = lineValues[ HEADER_PERSON_DIFFICULTY ]
		String movie = lineValues[ HEADER_MOVIE ]
		String movieDifficulty = lineValues[ HEADER_MOVIE_DIFFICULTY ]
		String quote = lineValues[ HEADER_QUOTE ]
		String quoteEnding = lineValues[ HEADER_QUOTE_ENDING ]

		String fullQuote = quote + ( quoteEnding ? quoteEnding : "" )

		if ( person ) {
			questions.add(
				new GeneratedQuestion(
					question: "In the movie \"$movie\", who said: \"$fullQuote\"",
					answer: person,
				).md5( getName(), "person: " + fullQuote )
			)
		}

		questions.add(
			new GeneratedQuestion(
				question: "What movie is the following quote from? \"$fullQuote\"",
				answer: movie
			).md5( getName(), "movie: " + movie + fullQuote )
		)

		if ( quoteEnding ) {
			questions.add(
				new GeneratedQuestion(
					question: "Complete the following movie quote: \"$quote\"",
					answer: quoteEnding
				).md5( getName(), "ending: " + fullQuote )
			)
		}

		return questions

	}

}
