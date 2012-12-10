package com.serwylo.trivia.questions

import org.apache.commons.codec.digest.DigestUtils

class Question {

	String question

	String answer

	List<String> subject = []

	String commentToHost

	private String hash

	/**
	 * This is some sort of unique identifier, which is based on the data in the spreadsheet.
	 * It means that we can rerun the generation of questions, without duplicating questions, and without overwritting
	 * changes to existing questions (i.e. if the admin modified them).
	 */
	public String getHash() {
		return this.hash
	}

	String toString() {
		return "Q: $question\nA: $answer"
	}

	/**
	 * Sets the {@link Question#hash} to the md5 of the question and the answer, then returns itself.
	 * @param questionType Name of the question factory which generated the question. e.g. "WhoAmI".
	 * @param toHash The value to hash for this question. If null, will just be {@link Question#question} +
	 * {@link Question#answer}.
	 * @return Reference to ourself.
	 */
	Question md5( String questionType, String toHash = null ) {

		this.hash = ""

		if ( toHash == null ) {
			toHash = question + answer
		}

		DigestUtils.md5( questionType + toHash ).each {
			hash += it
		}

		return this

	}

}
