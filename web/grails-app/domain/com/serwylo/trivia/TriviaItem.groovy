package com.serwylo.trivia

/**
 * A TriviaItem is something which gets read out to the participants.
 * The most obvious format is a question which requires an answer, but it could also 
 * be a challange which is just something the participants need to do.
 */
abstract class TriviaItem {

    static constraints = {
    }

	static mapping = {
		commentToHost( type: "text" )
	}

	static hasOne = [
		difficulty: Difficulty
	]

	/**
	 * Some questions may have potential points of dispute, so we put a comment here for the host to help prevent any
	 * arguments. For example, if a question is directed at
	 */
	String commentToHost = ""

	/**
	 * Not Safe For Work.
	 * If you want to have a trivia night for a school or a church or somehting, then 
	 * make sure NOT to include any nsfw questions.
	 * Defaults to false (i.e. IS safe for work/children/etc).
	 */
	Boolean nsfw = false

}
