package com.serwylo.trivia.admin

abstract class CRUDController {

	protected void error( String message ) {
		flash.errors = [ message ]
		redirect( action : 'list' )
	}

	protected void success( String message ) {
		flash.messages = [ message ]
		redirect( action : 'list' )
	}

	protected long getIdParam() {
		long id = 0
		if ( params.containsKey( 'id' ) ) {
			try {
				id = params.id as Long
			} catch ( NumberFormatException nfe ) {}
		}
		return id
	}

	abstract def list()
	abstract def edit()
	abstract def delete()

}
