package com.serwylo.trivia.admin

class WidgetTagLib {

	static namespace = "triv"

	def filterBar = { attrs, body ->
		out << "<div class='filter-bar'>"
		out << body()
		out << "</div>"
	}

	/**
	 * @attr title
	 * @attr titleCode
	 * @attr class
	 */
	def panel = { attrs, body ->

		String header = ""
		String clazz  = ""

		String title = null
		if ( attrs.containsKey( 'title' ) ) {
			title = attrs.remove( 'title' )
		} else if ( attrs.containsKey( 'titleCode' ) ) {
			title = g.message( [ code : attrs.remove( 'titleCode' ) ] )
		}

		if ( title ) {
			header = "<div class='header'>$title</div>"
		}

		if ( attrs.containsKey( 'class' ) ) {
			clazz = attrs.remove( 'class' )
		}

		out << """
			<div class='panel $clazz'>
				$header
				<div class='contents'>
					${body()}
				</div>
			</div>
		"""

	}

}
