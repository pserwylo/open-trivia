package com.serwylo.trivia.web

abstract class CRUDService {

	public static Map<String, Object> getListParams( def params ) {
		def listParams = [
			max    : 5,
			offset : 0,
			sort   : null,
			order  : null,
		]

		listParams.keySet().each { property ->
			if ( params.containsKey( property ) ) {
				listParams[ property ] = params[ property ]
			}
		}

		return listParams
	}

}