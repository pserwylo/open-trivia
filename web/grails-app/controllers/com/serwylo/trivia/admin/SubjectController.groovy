package com.serwylo.trivia.admin

import com.serwylo.trivia.Subject
import com.serwylo.trivia.web.SubjectService

class SubjectController extends CRUDController {

	static defaultAction = 'list'

	SubjectService subjectService

	def list() {

		return []

	}

	def edit() {

		long id = idParam
		Subject subject = null
		if ( flash.subject ) {
			subject = flash.subject
		} else if ( id ) {
			subject = Subject.get( id )
			if ( subject == null ) {
				error( "Subject $id not found." )
			}
		}

		return [
			subject        : subject,
			parentSubjects : subjectService.allExcept( subject )
		]

	}

	def save = {

		Subject subject
		long id = idParam
		if ( id ) {
			subject = Subject.get( id )
			if ( subject == null ) {
				errors.reject( "Could not find subject $id" )
			} else {
				subject.properties = params
			}
		} else {
			subject = new Subject( params )
		}

		if ( subject?.validate() ) {
			subject.save()
			success( "Saved subject $subject.name." )
		} else {
			flash.subject = subject
			forward( action : 'edit' )
		}
	}

	def delete() {
		Subject subject
		long id = idParam
		if ( id ) {
			subject = Subject.get( id )
			if ( subject == null ) {
				error( "Could not find subject $id." )
			} else {
				String subjectName = subject.name
				subject.delete()
				success( "Deleted subject $subjectName." )
			}
		} else {
			redirect( action : 'list' )
		}
	}

}

