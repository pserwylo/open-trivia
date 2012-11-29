package com.serwylo.trivia.questions

import com.serwylo.trivia.questions.factories.QuestionFactory

QuestionFactory.factories.each {

	List<Question> questions = it.getQuestions()

	println "===================================="
	println " ${it.name} (${questions.size()} Questions)"
	println "===================================="
	println()

	questions.each {
		println it.toString() + "\n"
	}

}