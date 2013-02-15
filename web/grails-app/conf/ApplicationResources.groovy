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

	views {
		dependsOn( [ "klass", "jquery" ] )
		resource( url: "js/views/views.js" );
	}

	viewsGenerate {
		dependsOn( [ "views" ] );
		resource( url: "js/views/generate/generate.js" );
		resource( url: "js/views/generate/options/options.js" );
		resource( url: "js/views/generate/options/night.js" );
		resource( url: "js/views/generate/options/round.js" );
		resource( url: "js/views/generate/options/subject.js" );
	}

	questionList {
		dependsOn( [ "question" ] )
	}

	answerSheet {
		dependsOn( [ "klass", "misc", "jquery" ] )
		resource( url: "css/views/answerSheet.css" )
		resource( url: "js/views/answerSheet/answerSheet.js" )
	}

	answerSheet {
		dependsOn( [ "klass", "misc", "jquery" ] )
		resource( url: "css/views/generate.css" )
		resource( url: "js/views/generate/generate.js" )
	}

}