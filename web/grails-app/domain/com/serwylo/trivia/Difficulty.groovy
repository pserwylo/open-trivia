package com.serwylo.trivia

class Difficulty {

	private static Integer DIFF_UNKNOWN = -1
	private static Integer DIFF_EASY    = 1
	private static Integer DIFF_MEDIUM  = 2
	private static Integer DIFF_HARD    = 3

	static constraints = {
		value( inList: [ DIFF_EASY, DIFF_MEDIUM, DIFF_HARD ] )
	}

	String label
	Integer value
	Date dateCreated
	Date lastUpdated

	String toString() {
		return label
	}

	static final Difficulty getUnknown() {
		return findByValue( DIFF_EASY );
	}

	static final Difficulty getEasy() {
		return findByValue( DIFF_EASY );
	}

	static final Difficulty getMedium() {
		return findByValue( DIFF_MEDIUM );
	}

	static final Difficulty getHard() {
		return findByValue( DIFF_HARD );
	}

}
