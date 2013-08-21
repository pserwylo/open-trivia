package com.serwylo.trivia

import com.serwylo.trivia.auth.User

class Comment implements Comparable<Comment> {

	static constraints = {
		commentText blank : false
	}

	static mapping = {
		commentText type : "text"
	}

	String commentText

	Date dateCreated
	Date lastUpdated
	User createdBy

	@Override
	int compareTo( Comment other ) {
		return dateCreated.compareTo( other.dateCreated )
	}
}
