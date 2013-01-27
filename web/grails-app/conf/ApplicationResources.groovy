modules = {

	misc {
		resource( url: "css/misc.css" )
		resource( url: "js/utils.js" )
	}

	publicPage {
		resource( url: "css/public.css" )
	}

	klass {
		resource( url: 'js/lib/klass/klass.min.js' )
	}

	question {
		dependsOn( [ "klass" ] )
	}

	questionList {
		dependsOn( [ "question" ] )
	}

	answerSheet {
		dependsOn( [ "klass", "misc", "jquery" ] )
		resource( url: "css/views/answerSheet.css" )
		resource( url: "js/answerSheet.js" )
	}

}