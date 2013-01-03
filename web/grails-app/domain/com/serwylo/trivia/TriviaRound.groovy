package com.serwylo.trivia

/**
 * There are different types of rounds available, most revolving around a list of questions.
 */
abstract class TriviaRound {

    static constraints = {
	}

	TriviaNight triviaNight

	Integer roundNumber

	/**
	 * Return an identifier for this type of trivia round. It will be used both internally to identify the type of
	 * round (e.g. by the factory classes), but also to show to the user.
	 * @return
	 */
	abstract String getTypeName()

}
