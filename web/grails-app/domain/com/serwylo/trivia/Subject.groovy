package com.serwylo.trivia

/**
 * Each Question has one or more subjects (aka categories/genres/etc).
 * Subjects can be nested inside other genres.
 */
class Subject {

	static constraints = {
		name( nullable: false, blank: false, unique: true )
		parent( nullable: true )
		description( blank: true )
	}

	static hasMany = [ children: Subject ]

	String name
	String description = ""
	Subject parent
	Date dateCreated
	Date lastUpdated

	String toString() {
		return name
	}

}
