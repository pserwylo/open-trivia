package com.serwylo.trivia.web

import com.serwylo.trivia.auth.Role
import com.serwylo.trivia.auth.User
import com.serwylo.trivia.auth.UserRole
import grails.plugins.springsecurity.SpringSecurityService

class UserService extends CRUDService {

	SpringSecurityService springSecurityService

	User getCurrent() {
        (User)springSecurityService.currentUser
    }

	def updateUserRoles( User user, List<Role> roles ) {

		def currentRoles = UserRole.findAllByUser( user )
		List<UserRole> toRemove = currentRoles.findAll { !roles.contains( it.role ) }
		List<Role> toAdd = roles.findAll { !currentRoles*.role.contains( it ) }

		toRemove.each {
			it.delete( flush : true )
		}

		toAdd.each {
			new UserRole( user : user, role : it ).save( flush : true )
		}

	}

	def getAllRoles() {
		Role.list()
	}

	List<Role> getRolesForUser( User user ) {
		UserRole.findAllByUser( user )*.role
	}

	List<Role> getRoles( List<Long> roleIds ) {
		Role.findAllByIdInList( roleIds )
	}
}