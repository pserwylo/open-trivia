package com.serwylo.trivia.web

import com.serwylo.trivia.Subject

class SubjectService extends CRUDService {

	int count( def params ) {

		Subject.count()
	}

    List<Subject> list( def params = [:] ) {
		Subject.list( getListParams( params ) )
	}

	List<Subject> allExcept( Subject subject = null ) {
		Subject.findAllByIdNotEqual( subject?.id )
	}
}
