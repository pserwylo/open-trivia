package com.serwylo.trivia.admin

import com.serwylo.trivia.auth.Role

class UserTagLib {

	static namespace = "triv"

	def userService

	/**
	 * @attr user REQUIRED
	 */
	def userRoles = { attrs ->
		if ( !attrs.containsKey( 'user' ) ) {
			throwTagError( "Tag [userRoles] missing required [user] attribute." )
		}

		List<Role> roles = userService.getRolesForUser( attrs.user )
		out << roles*.name.join( ', ' )
	}

}
