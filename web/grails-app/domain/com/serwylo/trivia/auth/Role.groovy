package com.serwylo.trivia.auth

class Role {

	static final ADMIN     = "ROLE_ADMIN";
	static final USER      = "ROLE_USER";
	static final MEMBER    = "ROLE_MEMBER";
	static final ANONYMOUS = "ROLE_IS_AUTHENTICATED_ANONYMOUSLY";

	String authority
	String name

	static mapping = {
	}

	static constraints = {
		authority blank: false, unique: true
	}

	String toString() { name }

}
