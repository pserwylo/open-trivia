package com.serwylo.trivia

/**
 * Questions belong to genres (one or more).
 * Categories can be nested inside other genres.
 * I would've liked to to use the term "Category", but it is in use as a groovy.lang class, which makes it a bit
 * inconvenient to have to import everywhere.
 */
class Subject {

	static constraints = {
		parent( nullable: true )
		description( nullable: true )
	}

	String name
	String description = null
	Subject parent = null

	String toString() {
		return name
	}

}
