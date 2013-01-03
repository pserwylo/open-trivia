modules = {

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

		resource( url: "css/views/answerSheet.css" )

	}

}