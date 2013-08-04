package com.serwylo.trivia.admin

import com.serwylo.trivia.auth.Role
import com.serwylo.trivia.auth.User
import com.serwylo.trivia.auth.UserRole

class UserTagLib {

	static namespace = "triv"

	def userService

	/**
	 * @attr users
	 */
	def userList = { attrs ->

		List<User> users
		if ( attrs.containsKey( 'users' ) ) {
			users= attrs.remove( 'users' )
		} else {
			users = userService.allUsers
		}

		out << """
			<table id='list-user' class='list'>
				<thead>
					<tr>
						<th class='username'>Username</th>
						<th class='roles'>Roles</th>
						<th class='actions'></th>
					</tr>
				</thead>
				<tbody>
					${triv.userListItems( [ users : users ] )}
				</tbody>
			</table>
"""
	}

	/**
	 * @attr users REQUIRED
	 */
	def userListItems = { attrs ->

		List<User> users = attrs.users

		users.eachWithIndex { user, index ->

			String clazz      = index % 2 == 0 ? "even" : "odd"
			String editLink   = createLink( controller : 'user', action : 'edit',   params : [ id : user.id ] )
			String deleteLink = createLink( controller : 'user', action : 'delete', params : [ id : user.id ] )
			List<Role> roles  = userService.getRolesForUser( user )
			out << """
				<tr class='$clazz'>
					<td>$user.username</td>
					<td>${roles*.name.join( ', ' )}</td>
					<td class="actions">
						${triv.actionButton( action: 'edit'   )}
						${triv.actionButton( action: 'delete' )}
					</td>
				</tr>
"""
		}

	}

}
