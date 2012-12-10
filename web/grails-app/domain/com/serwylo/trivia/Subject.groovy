package com.serwylo.trivia

/**
 * Each Question has one or more subjects (aka categories/genres/etc).
 * Subjects can be nested inside other genres.
 */
class Subject {

	static constraints = {
		parent( nullable: true )
		description( nullable: true )
	}

	static hasMany = [ children: Subject ]

	String name
	String description
	Subject parent

	String toString() {
		return name
	}

}
