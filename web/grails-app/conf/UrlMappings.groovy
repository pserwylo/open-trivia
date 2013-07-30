class UrlMappings {

	static mappings = {

		"/$controller/$action?/$id?" ( )

		"/admin" ( controller: "adminDashboard" )

		"/" ( controller: "page", action: "home" )

		"500"(view:'/error')
	}
}
