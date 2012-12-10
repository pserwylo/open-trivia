package com.serwylo.trivia

class QuestionAndChallengeRound extends QuestionRound {

    static constraints = {
    }

	static hasOne = [
		challenge: Challenge
	]	

}
