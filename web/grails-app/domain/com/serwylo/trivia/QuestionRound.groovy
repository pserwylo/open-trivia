package com.serwylo.trivia

class QuestionRound extends TriviaRound {

    static constraints = {
    }

	static hasMany = [
		questions: Question
	]

	List<Question> questions

}
