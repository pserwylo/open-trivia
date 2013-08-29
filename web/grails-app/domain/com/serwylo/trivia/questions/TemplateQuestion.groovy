package com.serwylo.trivia.questions

import com.serwylo.trivia.Question

class TemplateQuestion extends Question {

	static constraints = {

		sourceId unique : true

		placeholderValues validator: { Map val, TemplateQuestion question ->

			Template template = question?.template
			if ( template == null ) {
				return false
			}

			for ( String placeholder in template.requiredPlaceholders ) {
				if ( !question?.placeholderValues?.containsKey( placeholder ) ) {
					return false
				}
			}

			return true

		}
	}

	static hasOne = [ template : Template ]

	/**
	 * Id from the spreadsheet we imported from.
	 * Used so that we can sync the database questions with the spreadsheet, even if the values change.
	 */
	String sourceId

	Map<String,String> placeholderValues

	def beforeInsert() {
		populateQuestion()
	}

	def beforeUpdate() {
		populateQuestion()
	}

	private def populateQuestion() {
		if ( template != null && placeholderValues != null ) {
			question = template.generateQuestion( placeholderValues )
			answer   = template.generateAnswer( placeholderValues )
		}
	}

}
