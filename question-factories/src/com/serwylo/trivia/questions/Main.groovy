package com.serwylo.trivia.questions

import com.serwylo.trivia.questions.factories.QuestionFactory

Integer total = 0

QuestionFactory.factories.each {

	List<GeneratedQuestion> questions = it.getQuestions()

	println()
	println "===================================="
	println " ${it.name} (${questions.size()} Questions)"
	println "===================================="
	println()

	questions.each {
		println it.hash + "\n"
		println it.toString() + "\n"
	}

	total += questions.size()

}

println()
println "===================================="
println " Total: ${total} Questions"
println "===================================="
println()