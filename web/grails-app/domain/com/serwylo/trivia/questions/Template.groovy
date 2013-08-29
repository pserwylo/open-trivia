package com.serwylo.trivia.questions

import com.serwylo.trivia.auth.User

class Template {

	static final String NAME_BASIC_IMPORT = "Basic import"

	static constraints = {
		name             unique : true, blank : false
		questionTemplate blank : false, validator : validateTemplate
		answerTemplate   blank : false, validator : validateTemplate
	}

	static mapping = {
		questionTemplate type : 'text'
		answerTemplate   type : 'text'
	}

	static hasMany = [ questions : TemplateQuestion ]

	static Closure validateTemplate = { String value, Template template ->
		extractPlaceholders( value ).size() > 0
	}

	String name
	String questionTemplate
	String answerTemplate

	Date dateCreated
	Date lastUpdated
	User createdBy
	User modifiedBy

	private List<String> placeholders = null

	public void setQuestionTemplate( String value ) {
		if ( this.questionTemplate != value ) {
			this.questionTemplate = value
			placeholders = null
		}
	}

	public void setAnswerTemplate( String value ) {
		if ( this.answerTemplate != value ) {
			this.answerTemplate = value
			placeholders = null
		}
	}

	public List<String> getRequiredPlaceholders() {
		if ( placeholders == null ) {
			placeholders = []
			placeholders.addAll( extractPlaceholders( questionTemplate ) )
			placeholders.addAll( extractPlaceholders( answerTemplate ) )
		}
		return placeholders
	}

	private static List<String> extractPlaceholders( String template ) {
		(List<String>)( template =~ /\[(.+?)\]/ ).collect { match ->
			match[ 1 ]
		}
	}

	public String generateQuestion( Map<String,String> values ) {
		replacePlaceholders( questionTemplate, values )
	}

	public String generateAnswer( Map<String,String> values ) {
		replacePlaceholders( answerTemplate, values )
	}

	private String replacePlaceholders( String templateString, Map<String,String> values ) {
		String result = templateString
		placeholders.each { String placeholder ->
			result = result.replaceAll( /\[$placeholder\]/, values[ placeholder ] )
		}
		return result
	}

	boolean canEdit() {
		name != NAME_BASIC_IMPORT
	}

	public static Template getBasicImport() {
		return Template.findByName( NAME_BASIC_IMPORT )
	}

}
