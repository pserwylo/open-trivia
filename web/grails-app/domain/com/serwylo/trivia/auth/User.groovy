package com.serwylo.trivia.auth

class User {

	transient springSecurityService

	String username
	String password
	String salt
	boolean enabled         = true
	boolean accountExpired  = false
	boolean accountLocked   = false
	boolean passwordExpired = false

	static constraints = {
		username blank: false, unique: true
		password blank: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password, salt)
	}

	static String generateSalt() {
		def chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890`~!@#\$%^&*()\"|_+-=;:'<>,./?"
		def sb = new StringBuilder()
		for ( int i in 0..15 ) {
			def index = (int)( Math.random() * ( chars.length() - 1 ) )
			sb.append( chars[ index ] )
		}
		return sb.toString()
	}
}
