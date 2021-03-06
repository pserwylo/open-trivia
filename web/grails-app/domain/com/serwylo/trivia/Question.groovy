package com.serwylo.trivia

/**
 * Questions are simply something which is asked of the particiapnts, which requires an answer.
 * This could be a simple question/answer format, a who-am-i format (where the question is read in parts), 
 * or a series of things which need to be put in order (but still only has one answer), or something else.
 */
class Question extends TriviaItem {

    static constraints = {
		question( blank : false, nullable : false )
		answer  ( blank : false, nullable : false )
		subjects( minSize : 1 )
    }

	static mapping = {
		question( type: "text" )
		answer( type: "text" )
	}

	static hasMany = [ subjects : Subject ]

	String question
	String answer

	public List<QuestionSource> getSources() {
		QuestionSource.findAllByQuestion( this )
	}

}
