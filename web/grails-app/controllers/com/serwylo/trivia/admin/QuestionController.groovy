package com.serwylo.trivia.admin

import com.serwylo.trivia.Question
import com.serwylo.trivia.web.DifficultyService
import com.serwylo.trivia.web.QuestionService
import com.serwylo.trivia.web.SubjectService

class QuestionController extends CRUDController {

	static defaultAction = 'list'

	SubjectService subjectService
	DifficultyService difficultyService
	QuestionService questionService

	def list() {

		return [
			subjects     : subjectService.subjectList,
			difficulties : difficultyService.difficulties,
			questions    : questionService.allQuestions
		]

	}

	def ajaxList = { QuestionListFilterCommand cmd ->

		render triv.questionListItems( questions : questionList )

	}

	def edit() {

		long id = idParam
		Question question = null
		if ( flash.question ) {
			question = flash.question
		} else if ( id ) {
			if ( id > 0 ) {
				question = Question.get( id )
				if ( question == null ) {
					error( "Question $id not found." )
				}
			}
		}

		return [
			question       : question,
			subjectList    : subjectService.subjectList,
			difficultyList : difficultyService.difficulties,
		]

	}

	def delete() {

	}

	def save = {

		long id = idParam
		Question question
		if ( id ) {
			question = Question.get( id )
			if ( question == null ) {
				errors.reject( "Could not find question $id" )
			} else {
				question.properties = params
			}
		} else {
			question = new Question( params )
		}

		if ( question?.validate() ) {
			question.save()
			redirect( action : 'list' )
		} else {
			flash.question = question
			forward( action : 'edit' )
		}
	}

}

class QuestionListFilterCommand {

	int subjectId
	int difficultyId

}