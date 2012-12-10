package com.serwylo.trivia

/**
 * There are different types of rounds available, most revolving around a list of questions.
 */
abstract class TriviaRound {

    static constraints = {
	}

	static hasOne = [
		triviaNight: TriviaNight
	]

	Integer roundNumber

}
