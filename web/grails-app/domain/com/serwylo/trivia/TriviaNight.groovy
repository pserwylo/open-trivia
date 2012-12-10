package com.serwylo.trivia

class TriviaNight {

    static constraints = {
	}

	static hasMany = [
		rounds: TriviaRound,
	]

	List<TriviaRound> rounds = []

}
