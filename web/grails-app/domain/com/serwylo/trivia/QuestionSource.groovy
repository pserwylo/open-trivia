package com.serwylo.trivia

import com.serwylo.trivia.auth.User

class QuestionSource implements Comparable<QuestionSource> {

	static constraints = {
		comment  nullable : true
	}

	static belongsTo = [ question  : Question ]

	String location
	Source source
	Comment comment
	Date dateCreated
	User createdBy

	public boolean isUrl() {
		boolean isUrl = false
		try {
			location.toURL()
			isUrl = true
		} catch ( MalformedURLException e ) {}
		return isUrl
	}

	@Override
	int compareTo( QuestionSource other ) {
		return comment.compareTo( other.comment )
	}
}
