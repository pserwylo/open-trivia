package com.serwylo.trivia

/**
 * A subject round has all questions which belong to one and only one subject (although the questions will be of
 * different types).
 */
class SubjectRound extends QuestionRound {

	static final String TYPE = "Themed round, where questions are only from a single subject"

    static constraints = {
    }

	static hasOne = [
		subject: Subject
	]

	String getTypeName() {
		return SubjectRound.TYPE
	}
	
}
