package com.serwylo.trivia

import com.serwylo.trivia.auth.User

class Source {

	static constraints = {
	}

	String name
	User createdBy
	Date dateCreated
	Date lastUpdated

}
