package com.serwylo.trivia.admin

import com.serwylo.trivia.Question
import com.serwylo.trivia.Subject
import com.serwylo.trivia.web.DifficultyService
import com.serwylo.trivia.web.QuestionService
import com.serwylo.trivia.web.SubjectService
import com.serwylo.trivia.web.UserService

class QuestionController extends CRUDController {

	static defaultAction = 'list'

	SubjectService subjectService
	DifficultyService difficultyService
	QuestionService questionService
	UserService userService

	def list() {

		def questions = questionService.list( params )
		def count     = questionService.count( params )
		def subjects  = Subject.list()

		return [
			subjects     : subjects,
			difficulties : difficultyService.difficulties,
			questions    : questions,
			count        : count,
			params       : params,
		]

	}

	def ajaxList = {

		List<Question> questions = questionService.list( params )
		render triv.questionListItems( questions : questions )

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

		long id = idParam
		Question question = null
		if ( id ) {
			question = Question.get( id )
		}

		if ( question == null ) {
			redirect( [ action : 'list' ] )
		} else {
			String questionText = question.question
			question.delete()
			success( "Question \"$questionText\" deleted." )
		}

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
			params.createdBy = userService.current
			question = new Question( params )
		}

		question?.modifiedBy = userService.current
		if ( question?.validate() ) {
			question.save()
			redirect( action : 'list' )
		} else {
			flash.question = question
			forward( action : 'edit' )
		}
	}

}
