package com.serwylo.trivia.web

import com.serwylo.trivia.auth.Role
import com.serwylo.trivia.auth.User
import com.serwylo.trivia.auth.UserRole

class UserService {

	def getAllUsers() {
		User.list()
	}

	def getAllRoles() {
		Role.list()
	}

	def getRolesForUser( User user ) {
		UserRole.findAllByUser( user )*.role
	}
}
