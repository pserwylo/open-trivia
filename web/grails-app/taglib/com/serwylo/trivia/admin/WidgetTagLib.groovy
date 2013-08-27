package com.serwylo.trivia.admin

class WidgetTagLib {

	static namespace = "triv"

	private static final Map<String, String> ACTIONS = [
			"importData" : "Import",
			"edit"       : "Edit",
			"delete"     : "Delete",
	]

	def filterBar = { attrs, body ->
		out << "<div class='filter-bar'>"
		out << body()
		out << "</div>"
	}

	/**
	 * @attr action REQUIRED
	 * @attr id REQUIRED
	 */
	def actionButton = { attrs ->

		if ( !attrs.containsKey( 'action' ) ) {
			throwTagError( "Tag [actionButton] doesn't have required [action] attribute." )
		}

		if ( !attrs.containsKey( 'id' ) ) {
			throwTagError( "Tag [actionButton] doesn't have required [id] attribute." )
		}

		String action = attrs.action
		long itemId   = attrs.id as Long

		if ( !ACTIONS.containsKey( action ) ) {
			def actions = ACTIONS.keySet().join( ', ' )
			throwTagError( "Attribute [action] for tag [actionButton] has unknown type '$action'. Should be one of $actions." )
		}

		String label = ACTIONS[ action ]
		String link  = createLink( action : action, params : [ id : itemId ] )
		out << customActionButton( [ class : action, label : label, link : link ] )
	}

	/**
	 * @attr label REQUIRED
	 * @attr class REQUIRED
	 * @attr link  REQUIRED
	 */
	def customActionButton = { attrs ->

		if ( !attrs.containsKey( 'label' ) ) {
			throwTagError( "Tag [customActionButton] doesn't have required [label] attribute." )
		}

		if ( !attrs.containsKey( 'class' ) ) {
			throwTagError( "Tag [customActionButton] doesn't have required [class] attribute." )
		}

		if ( !attrs.containsKey( 'link' ) ) {
			throwTagError( "Tag [customActionButton] doesn't have required [link] attribute." )
		}

		String label = attrs.remove( 'label' )
		String clazz = attrs.remove( 'class' )
		String link  = attrs.remove( 'link' )

		out << """<a href='$link' class='action $clazz'>$label</a>"""
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