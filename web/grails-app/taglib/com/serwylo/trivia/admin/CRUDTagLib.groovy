package com.serwylo.trivia.admin

class CRUDTagLib {

	static namespace = "triv"

	/**
	 * Notifications (errors and messages) from the CRUD controller.
	 * They live in the flash object, so will disappear on reload.
	 */
	def notify = {

		messages( flash.errors,   'errors'   )
		messages( flash.messages, 'messages' )

	}

	private void messages( def messages, def clazz ) {
		if ( messages ) {
			out << "<div class='notifications $clazz'><ul>"
			messages.each {
				out << "<li>${it}</li>"
			}
			out << "</ul></div>"
		}
	}

}
