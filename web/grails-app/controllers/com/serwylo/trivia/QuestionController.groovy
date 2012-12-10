package com.serwylo.trivia

import com.serwylo.trivia.web.DifficultyService
import com.serwylo.trivia.web.QuestionService
import com.serwylo.trivia.web.SubjectService

class QuestionController {

	SubjectService subjectService
	DifficultyService difficultyService
	QuestionService questionService

	def list = {

		return [
			subjects: subjectService.subjectList,
			difficulties: difficultyService.difficulties,
			questions: questionService.allQuestions
		]

	}

}
