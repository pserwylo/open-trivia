import com.serwylo.trivia.auth.Role
import com.serwylo.trivia.auth.User
import com.serwylo.trivia.auth.UserRole

class BootStrap {

    def init = { servletContext ->
		bootstrapUsersRoles()
	}


	private static void bootstrapUsersRoles() {

		boolean createInitialUser = ( Role.count() == 0 )
		def roles = [
			(Role.ADMIN)  : "Admin",
			(Role.MEMBER) : "Member",
			(Role.USER)   : "User",
		]

		roles.each {

			String authority = it.key
			String name      = it.value

			Role role = Role.findByAuthority( authority )
			if ( !role ) {
				role = new Role( authority : authority, name : name )
				role.save( flush : true )
			}

			if ( authority == Role.ADMIN && createInitialUser ) {
				User admin = new User( username : "admin", password : "admin", salt : User.generateSalt() )
				admin.save( flush : true )

				UserRole adminRole = new UserRole( user : admin, role : role )
				adminRole.save()
			}

		}
	}

    def destroy = {
    }
}
