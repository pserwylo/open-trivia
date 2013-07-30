package com.serwylo.trivia.admin

import com.serwylo.trivia.auth.Role
import com.serwylo.trivia.auth.User
import com.serwylo.trivia.auth.UserRole

class UserController {

	static defaultAction = 'list'

	def userService

	def list() {
		return [
			roles : userService.allRoles
		]
	}

	def error( String message ) {
		flash.errors = [ message ]
		redirect( action : 'list' )
	}

	def edit() {

		User user = null
		List<Role> userRoles = []
		if ( flash.user ) {
			user = flash.user
			if ( user.id > 0 ) {
				userRoles = userService.getRolesForUser( user )
			}
		} else if ( params?.containsKey( 'id' ) ) {
			int userId = params[ 'id' ] as Integer;
			if ( userId > 0 ) {
				user = User.get( userId )
				if ( user != null ) {
					userRoles = userService.getRolesForUser( user )
				} else {
					error( "User $userId not found." )
				}
			}
		}

		return [
			user      : user,
			userRoles : userRoles,
			roles     : userService.allRoles,
		]
	}

	def save = {

		User user
		if ( params.containsKey( 'id' ) ) {
			int id = params.id as Integer
			user = User.get( id )
			if ( user != null ) {
				user.username = params.username
				if ( params.password?.trim()?.length() ) {
					user.password = params.password
				}
			} else {
				errors.reject( "Could not find user $id" )
			}
		} else {
			user = new User( params )
		}

		if ( user?.validate() ) {
			user.save()

			// TODO: I'm not sure if this is the most robust way to do this.
			// Is there a way to force "roles.id" to come back as an array?
			// e.g. in PHP, you'd append "[]" to the end of the checkbox name.
			List<Long> roleIds = []
			def roleIdsFromForm = params[ 'roles.id' ]
			if ( roleIdsFromForm instanceof String[] ) {
				roleIds = roleIdsFromForm.collect { it as Long }
			} else {
				Long roleId = roleIdsFromForm as Long
				if ( roleId > 0 ) {
					roleIds.add( roleId )
				}
			}

			List<Role> newRoles = userService.getRoles( roleIds )
			userService.updateUserRoles( user, newRoles )
			redirect( action : 'list' )
		} else {
			flash.user = user
			forward( action : 'edit' )
		}
	}

}
