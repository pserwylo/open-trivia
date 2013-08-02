modules = {

	misc {
		resource( url: "css/misc.css" )
		resource( url: "css/main.css" )
		dependsOn( [ "jquery" ] )
	}

	login {
		resource( url: "css/login.css" )
		resource( url: "css/form.css" )
		dependsOn( [ "widget" ] )
	}

	publicPage {
		resource( url: "css/public.css" )
		dependsOn( [ 'misc' ] )
	}

	home {
		resource( url: "css/home.css" )
		dependsOn( [ 'misc', 'widget' ] )
	}

	widget {
		resource( url: "css/widget/panel.css" )
	}

	admin {
		resource( url: "css/admin.css" )
		dependsOn( [ "misc" ] )
	}

	adminForm {
		resource( url: "css/form.css" )
		dependsOn( [ "admin" ] )
	}

	adminList {
		resource( url: "css/adminList.css" )
		dependsOn( [ "admin" ] )
	}

	adminQuestionList {
		resource( url: "css/adminQuestionList.css" )
		dependsOn( [ "adminList" ] )
	}

	adminSubjectList {
		resource( url: "css/adminSubjectList.css" )
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