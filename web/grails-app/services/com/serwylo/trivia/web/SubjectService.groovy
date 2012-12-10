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

	/**
	 * Returns a list of subjects, with each element being a tree including its sub-subjects.
	 * @return
	 */
    List<Subject> getSubjectTrees() {

		return Subject.list()

	}
}
