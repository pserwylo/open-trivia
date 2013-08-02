navigation = {

	publicScope {

		home( controller : 'page', action : 'home' )

	}

	adminScope {

		questions ( controller : 'question', action : 'list', actionAliases : [ 'edit' ] )
		subjects ( controller : 'subject', action : 'list', actionAliases : [ 'edit' ] )
		users ( controller : 'user', action : 'list', actionAliases : [ 'index', 'edit' ] )

	}
}