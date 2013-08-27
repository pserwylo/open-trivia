package com.serwylo.trivia.admin

import com.serwylo.trivia.auth.Role
import com.serwylo.trivia.auth.User
import com.serwylo.trivia.auth.UserRole

class IdeaController extends CRUDController {

	static defaultAction = 'list'

	def list() {
		return [
			ideas : Idea.list( params ),
			count : Idea.count(),
		]
	}

	def delete() {

	}

	def convertToQuestion() {
		
	}

	def save() {
		Idea idea = new Idea( params )
		if ( idea?.validate() ) {
			idea.save()
			redirect( action : 'list' )
		} else {
			flash.idea = idea
			forward( action : 'edit' )
		}
	}

}
