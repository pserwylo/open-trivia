package com.serwylo.trivia.admin

import com.serwylo.trivia.auth.User

class MiscTagLib {

	static namespace = "triv"

	/**
	 * @attr dateCreated
	 * @attr lastUpdated
	 * @attr createdBy
	 * @attr lastUpdatedBy
	 */
	def createdModified = { attrs ->

		Date dateCreated
		Date lastUpdated
		User createdBy
		User lastUpdatedBy

		if ( attrs.containsKey( 'dateCreated' ) ) {
			dateCreated = attrs.dateCreated
		}

		if ( attrs.containsKey( 'lastUpdated' ) ) {
			lastUpdated = attrs.lastUpdated
		}

		if ( attrs.containsKey( 'createdBy' ) ) {
			createdBy = attrs.createdBy
		}

		if ( attrs.containsKey( 'lastUpdatedBy' ) ) {
			lastUpdatedBy = attrs.lastUpdatedBy
		}

		if ( !( dateCreated || lastUpdated || createdBy || lastUpdatedBy ) ) {
			throwTagError( "Tag [createdModified] must have one of [dateCreated | lastUpdated | createdBy | lastUpdatedBy] attributes." )
		}

		String createdString = null
		if ( dateCreated && createdBy ) {
			createdString = "Created by <span class='user'>$createdBy.username</span> on <span class='date'>$dateCreated</span>"
		} else if ( createdBy ) {
			createdString = "Created by <span class='user'>$createdBy.username</span>"
		} else if ( dateCreated ) {
			createdString = "Created on <span class='date'>$dateCreated</span>"
		}

		String updatedString = null
		if ( lastUpdated && lastUpdatedBy ) {
			updatedString = "Last updated by <span class='user'>$lastUpdatedBy.username</span> on <span class='date'>$dateCreated</span>"
		} else if ( lastUpdatedBy ) {
			updatedString = "Last updated by <span class='user'>$lastUpdatedBy.username</span>"
		} else if ( lastUpdated ) {
			updatedString = "Last updated on <span class='date'>$dateCreated</span>"
		}

		out << "<div class='createdModified'>"

		if ( createdString ) {
			out << "<div class='created'>$createdString</div>"
		}

		if ( updatedString ) {
			out << "<div class='updated'>$updatedString</div>"
		}

		out << "</div>"

	}

	/**
	 * @attr count REQUIRED
	 */
	def plural = { attrs ->

		if ( !attrs.containsKey( 'count' ) ) {
			throwTagError( "Tag [plural] missing required [count] attribute." )
		}

		int count = attrs.count as Integer
		if ( count != 1 ) {
			out << "s"
		}

	}

	/**
	 * @attr string REQUIRED
	 * @attr length REQUIRED
	 */
	def truncate = { attrs ->

		if ( !attrs.containsKey( 'string' ) ) {
			throwTagError( "Tag [truncate] doesn't have required [string] attribute." )
		}

		if ( !attrs.containsKey( 'length' ) ) {
			throwTagError( "Tag [truncate] doesn't have required [length] attribute." )
		}

		String string = attrs.string
		int length    = attrs.length as Integer

		if ( string.length() > length ) {
			String start = string.substring(0, length - 3)
			String end   = string.substring(length - 3)
			out << "$start<span class='continuation'>...</span><span class='remainder'>$end</span>"
		} else {
			out << string
		}
	}

}
