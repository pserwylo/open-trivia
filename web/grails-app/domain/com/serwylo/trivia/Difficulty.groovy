package com.serwylo.trivia

class Difficulty {

	private static Integer DIFF_EASY = 1
	private static Integer DIFF_MEDIUM = 2
	private static Integer DIFF_HARD = 2

	static constraints = {
		value( inList: [ DIFF_EASY, DIFF_MEDIUM, DIFF_HARD ] )
	}

	String label

	Integer value

	String toString() {
		return label
	}

	static final Difficulty getEasy() {
		return Difficulty.findByValue( DIFF_EASY );
	}

	static final Difficulty getMedium() {
		return Difficulty.findByValue( DIFF_MEDIUM );
	}

	static final Difficulty getHard() {
		return Difficulty.findByValue( DIFF_HARD );
	}

}
