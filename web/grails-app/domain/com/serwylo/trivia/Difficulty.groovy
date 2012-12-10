package com.serwylo.trivia

class Difficulty {

	static Integer DIFF_EASY = 1
	static Integer DIFF_MEDIUM = 2
	static Integer DIFF_HARD = 2

	static constraints = {

		value( inList: [ DIFF_EASY, DIFF_MEDIUM, DIFF_HARD ] )

	}

	static hasMany = [

		questions: Question

	]

	String label

	Integer value

	String toString() {
		return label
	}

}
