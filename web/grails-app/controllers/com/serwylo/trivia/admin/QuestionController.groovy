package com.serwylo.trivia.admin

import com.serwylo.trivia.Question
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

		def subjectId = 0
		if ( params.containsKey( 'subjectId' ) ) {
			try {
				subjectId = Long.parseLong( params.subjectId )
			} catch ( NumberFormatException nfe ) {}
		}

		def questions = questionService.getQuestions( subjectId )

		return [
			subjects     : subjectService.subjectList,
			difficulties : difficultyService.difficulties,
			subjectId    : subjectId,
			questions    : questions,
		]

	}

	def ajaxList = { QuestionListFilterCommand cmd ->

		List<Question> questions = questionService.getQuestions( cmd.subjectId, cmd.difficultyId )
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

class QuestionListFilterCommand {

	int subjectId
	int difficultyId

}