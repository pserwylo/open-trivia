package com.serwylo.trivia

/**
 * Each Question has one or more subjects (aka categories/genres/etc).
 * Subjects can be nested inside other genres.
 */
class Subject {

	public static final String NAME_UNKNOWN = "Unknown"

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

	boolean canEdit() {
		name != NAME_UNKNOWN
	}

	int getNumQuestions() {
		def count = Question.withCriteria {
			projections {
				count( 'id' )
			}
			subjects {
				eq( 'id', id )
			}
		}

		count.size() > 0 ? count[ 0 ] : 0
	}

	public static Subject getUnknown() {
		findByName( NAME_UNKNOWN )
	}

}
