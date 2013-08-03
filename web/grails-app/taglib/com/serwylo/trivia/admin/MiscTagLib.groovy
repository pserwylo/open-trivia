package com.serwylo.trivia.admin

class MiscTagLib {

	static namespace = "triv"

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
