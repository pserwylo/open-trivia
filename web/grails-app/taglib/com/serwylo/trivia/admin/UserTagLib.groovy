package com.serwylo.trivia.admin

import com.serwylo.trivia.auth.User

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

			String clazz = index % 2 == 0 ? "even" : "odd"
			String editLink = createLink( controller : 'user', action : 'edit', params : [ id : user.id ] )
			out << """
				<tr class='$clazz'>
					<td>$user.username</td>
					<td>
						<button
							name='edit'
							onclick='document.location = "$editLink"'
							value='${user.id}'>Edit
						</button>
						<button name='delete' value='${user.id}'>Delete</button>
					</td>
				</tr>
"""
		}

	}

}
