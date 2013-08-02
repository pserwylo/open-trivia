package com.serwylo.trivia.web

import com.serwylo.trivia.Subject

class SubjectService {

	/**
	 * Returns a flat list of all subjects.
	 * @return
	 */
    List<Subject> getSubjectList() {

		return Subject.list()

	}

	List<Subject> allExcept( Subject subject = null ) {
		Subject.findAllByIdNotEqual( subject?.id )
	}
}
