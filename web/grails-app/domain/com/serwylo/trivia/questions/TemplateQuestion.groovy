package com.serwylo.trivia.questions

import com.serwylo.trivia.Question

class TemplateQuestion extends Question {

	static constraints = {
	}

	static hasOne = Template

	/**
	 * Used so that we can sync the database questions with the spreadsheet, even if the values change.
	 */
	long sourceIdentifier

	Map placeholderValues

	private void replacePlaceholders() {

	}

}
