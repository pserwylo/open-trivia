package com.serwylo.trivia

/**
 * Questions are simply something which is asked of the particiapnts, which requires an answer.
 * This could be a simple question/answer format, a who-am-i format (where the question is read in parts), 
 * or a series of things which need to be put in order (but still only has one answer), or something else.
 * TODO: Map the question/answer fields to something in the base class, so that Question and ChallengeItem can use the same table more efficiently.
 */
class Question extends TriviaItem {

    static constraints = {
    }

	static hasMany = [
		subjects: Subject,
		mutuallyExclusiveQuestions: Question
	]

	static mapping = {
		question( type: "text" )
		answer( type: "text" )
	}

	String question

	String answer

	String hash

}
