package com.serwylo.trivia.questions

import com.serwylo.trivia.auth.User

class Template {

	static constraints = {
	}

	static mapping = {
		questionTemplate( type : 'text' )
		answerTemplate( type : 'text' )
	}

	static hasMany = [ questions : TemplateQuestion ]

	String name
	String questionTemplate
	String answerTemplate

	Date dateCreated
	Date lastUpdated
	User createdBy
	User modifiedBy

	public List<String> getRequiredPlaceholders() {
		def placeholders = []
		placeholders.addAll( extractPlaceholders( questionTemplate ) )
		placeholders.addAll( extractPlaceholders( answerTemplate ) )
		return placeholders
	}

	private static List<String> extractPlaceholders( String template ) {
		(List<String>)( template =~ /\[(.+?)\]/ ).collect { match ->
			match[ 1 ]
		}
	}

}
