package com.serwylo.trivia

import com.serwylo.trivia.auth.Role
import com.serwylo.trivia.auth.User

class UserController {

	static defaultAction = 'list'

	def userService
	def springSecurityService

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
			user      = flash.user
			userRoles = flash.userRoles
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

		println params
		return

		User user
		List<Role> currentRoles = []

		if ( params.containsKey( 'id' ) ) {
			int id = params.id as Integer
			user = User.get( id )
			if ( user != null ) {
				user.username = params.username
				currentRoles  = userService.getRolesForUser( user )
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

			List<Role> newRoles = Role.findAllByIdInList( params[ 'roles' ] )

			redirect( action : 'list' )
		} else {
			flash.user = user
			forward( action : 'edit' )
		}
	}

}
