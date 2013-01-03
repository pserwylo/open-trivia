package com.serwylo.trivia

class QuestionRound extends TriviaRound {

	static final String TYPE = "Simply a round of questions"

    static constraints = {
    }

	static hasMany = [
		questions: Question
	]

	List<Question> questions

	String getTypeName() {
		return QuestionRound.TYPE
	}

	String toString() {
		return getTypeName() + "\n" + questions
	}

}
