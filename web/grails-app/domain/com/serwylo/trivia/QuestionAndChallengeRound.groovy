package com.serwylo.trivia

class QuestionAndChallengeRound extends QuestionRound {

	static final String TYPE = "Question round with a challenge at the end"

    static constraints = {
    }

	static hasOne = [
		challenge: Challenge
	]

	String getTypeName() {
		return QuestionAndChallengeRound.TYPE
	}

}
