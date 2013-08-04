package com.serwylo.trivia.web

import com.serwylo.trivia.Subject

class SubjectService {

	int count( def params ) {

		Subject.count()
	}

    List<Subject> list( def params = [:] ) {
		Subject.list( params )
	}

	List<Subject> allExcept( Subject subject = null ) {
		Subject.findAllByIdNotEqual( subject?.id )
	}
}
