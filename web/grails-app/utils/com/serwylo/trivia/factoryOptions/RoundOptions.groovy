package com.serwylo.trivia.factoryOptions

import com.serwylo.trivia.Difficulty

/**
 * A configurable object which is understood by the trivia round factories.
 * Specify a set of options (e.g. number of questions, type, dificulty, etc,
 */
class RoundOptions {

	String label = "Question time"

	/**
	 * If you only specify one difficulty, then that will be used for all questions.
	 * If you specify multiple difficulties, then it will divide the round into as many
	 * segments as you specify, each segment having the difficulty specified.
	 * So, for example:
	 *  [ medium, hard ] would produce medium questions for the first 50%, and hard for the latter 50%,
	 *  whereas [ easy, medium, hard ] would produce 33% easy, 33% medium, and then 33% hard.
	 */
	List<Difficulty> difficulty = [ Difficulty.easy, Difficulty.medium, Difficulty.hard ]

	Integer numQuestions = 10


}
