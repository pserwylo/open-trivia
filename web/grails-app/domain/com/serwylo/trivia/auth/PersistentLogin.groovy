package com.serwylo.trivia.auth

class PersistentLogin {

	String id
	String username
	String token
	Date lastUsed

	static constraints = {
		username maxSize: 64
		token maxSize: 64
		id maxSize: 64
	}

	static transients = ['series']

	void setSeries(String series) { id = series }
	String getSeries() { id }

	static mapping = {
		id column: 'series', generator: 'assigned'
		version false
	}
}
