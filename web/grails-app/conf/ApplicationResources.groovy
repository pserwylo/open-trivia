modules = {

	libChosen {
		resource( url: "js/lib/chosen/chosen.jquery.min.js" )
		resource( url: "js/lib/chosen/chosen.min.css" )
		resource( url: "js/lib/chosen/choose.all.js" )
	}

	misc {
		resource( url: "css/misc.css" )
		resource( url: "css/main.css" )
		dependsOn( [ "jquery", "libChosen" ] )
	}

	login {
		resource( url: "css/login.css" )
		dependsOn( [ "widget", "form" ] )
	}

	publicPage {
		resource( url: "css/public.css" )
		resource( url: "css/mobile.css" )
		dependsOn( [ 'misc' ] )
	}

	home {
		resource( url: "css/publicHome.css" )
		dependsOn( [ 'misc', 'widget' ] )
	}

	widget {
		resource( url: "css/widget/panel.css" )
	}

	admin {
		resource( url: "css/admin.css" )
		dependsOn( [ "misc" ] )
	}

	form {
		resource( url: "css/form.css" )
	}

	adminForm {
		resource( url: "css/adminForm.css" )
		dependsOn( [ "admin", "form" ] )
	}

	adminQuestionForm {
		resource( url: "css/adminQuestionForm.css" )
		dependsOn( [ "adminForm" ] )
	}

	adminList {
		resource( url: "css/adminList.css" )
		dependsOn( [ "admin" ] )
	}

	adminQuestionList {
		resource( url: "css/adminQuestionList.css" )
		dependsOn( [ "adminList" ] )
	}

	adminUserList {
		resource( url: "css/adminUserList.css" )
		dependsOn( [ "adminList" ] )
	}

	adminSubjectList {
		resource( url: "css/adminSubjectList.css" )
		dependsOn( [ "adminList" ] )
	}

	adminDelete {
		resource( url: "css/adminDelete.css" )
		dependsOn( [ "admin", "widget" ] )
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