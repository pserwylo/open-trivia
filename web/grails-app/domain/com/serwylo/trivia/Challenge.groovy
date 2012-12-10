package com.serwylo.trivia

/**
 * A challenge for each table to perform.
 * Unlike a question, a challenge doesn't require an answer. 
 * It is simply a description of something the tables must perform.
 */
class Challenge extends TriviaItem {

    static constraints = {
    }

	String title

	String description
	
}
