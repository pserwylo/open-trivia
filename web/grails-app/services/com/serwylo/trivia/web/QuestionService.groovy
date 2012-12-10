package com.serwylo.trivia.web

import com.serwylo.trivia.Question

class QuestionService {

	def getAllQuestions() {

		return Question.list()

	}
}
