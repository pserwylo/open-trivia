class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/admin" ( controller: "adminDashboard" )

		"/" ( controller: "page", action: "home" )

		"500"(view:'/error')
	}
}
