package com.serwylo.trivia.admin

class WidgetTagLib {

	static namespace = "triv"

	private static final Map<String, String> ACTIONS = [
		"edit"   : "Edit",
		"delete" : "Delete",
	]

	def filterBar = { attrs, body ->
		out << "<div class='filter-bar'>"
		out << body()
		out << "</div>"
	}

	/**
	 * @attr type REQUIRED
	 * @attr link REQUIRED
	 */
	def action = { attrs ->
		String type = attrs.remove( 'type' )
		if ( !ACTIONS.containsKey( type ) ) {
			def actions = ACTIONS.keySet().join( ', ' )
			throw new IllegalArgumentException( "Unknown action '$type'. Should be one of $actions." )
		}

		String label = ACTIONS[ type ]
		String link  = attrs.remove( 'link' )
		out << customAction( [ type : type, label : label, link : link ] )
	}

	/**
	 * @attr label REQUIRED
	 * @attr type REQUIRED
	 * @attr link  REQUIRED
	 */
	def customAction = { attrs ->
		String label = attrs.remove( 'label' )
		String clazz = attrs.remove( 'type' )
		String link  = attrs.remove( 'link' )
		out << """<a href='$link' class='action type-$clazz'>$label</a>"""
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
