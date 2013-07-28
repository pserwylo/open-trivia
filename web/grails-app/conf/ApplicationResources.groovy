modules = {

	misc {
		resource( url: "css/misc.css" )
		resource( url: "css/main.css" )
		dependsOn( [ "jquery" ] )
	}

	publicPage {
		resource( url: "css/public.css" )
		dependsOn( [ 'misc' ] )
	}

	home {
		resource( url: "css/home.css" )
		dependsOn( 'misc' )
	}

	admin {
		resource( url: "css/admin.css" )
		dependsOn( [ "misc" ] )
	}

	adminList {
		resource( url: "css/adminList.css" )
		dependsOn( [ "admin" ] )
	}

	adminForm {
		resource( url: "css/adminForm.css" )
		dependsOn( [ "admin" ] )
	}

	adminQuestionList {
		resource( url: "css/adminQuestionList.css" )
		dependsOn( [ "adminList" ] )
	}

	views {
		dependsOn( [ "jquery" ] )
	}

	viewsGenerate {
		dependsOn( [ "views" ] );
	}

	questionList {
		dependsOn( [ "question" ] )
	}

}