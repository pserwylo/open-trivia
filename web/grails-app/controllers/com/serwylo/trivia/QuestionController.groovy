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
			subjects     : subjectService.subjectList,
			difficulties : difficultyService.difficulties,
			questions    : questionService.allQuestions
		]

	}

	def ajaxList = { QuestionListFilterCommand cmd ->

		List<Question> questionList = questionService.getQuestions( cmd.subjectId, cmd.difficultyId )
		render triv.questionListItems( questions : questionList )

	}

	private void error( String message ) {
		flash.errors = [ message ]
		redirect( action : 'list' )
	}

	def edit = {

		Question question = null
		if ( flash.question ) {
			question = flash.question
		} else if ( params?.containsKey( 'id' ) ) {
			int questionId = params[ 'id' ] as Integer;
			if ( questionId > 0 ) {
				question = Question.get( questionId )
				if ( question == null ) {
					error( "Question $questionId not found." )
				}
			}
		}

		return [
			question       : question,
			subjectList    : subjectService.subjectList,
			difficultyList : difficultyService.difficulties,
		]

	}

	def save = {

		Question question
		if ( params.containsKey( 'id' ) ) {
			int id = params.id as Integer
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