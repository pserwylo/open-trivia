package com.serwylo.trivia.admin

import com.serwylo.trivia.Question
import com.serwylo.trivia.Subject
import com.serwylo.trivia.web.DifficultyService
import com.serwylo.trivia.web.QuestionService
import com.serwylo.trivia.web.SourceService
import com.serwylo.trivia.web.SubjectService
import com.serwylo.trivia.web.UserService

class QuestionController extends CRUDController {

	static defaultAction = 'list'

	SubjectService    subjectService
	DifficultyService difficultyService
	QuestionService   questionService
	UserService       userService
	SourceService     sourceService

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
			question          : question,
			subjectList       : subjectService.list(),
			difficultyList    : difficultyService.difficulties,
			sourceLocation    : flash.sourceLocation,
			sourceCommentText : flash.sourceCommentText,
		]

	}

	/**
	 * If params.id points to a valid question, return the domain object, otherwise returns null.
	 */
	private Question getQuestion() {
		long id = idParam
		Question question = null
		if ( id ) {
			question = Question.get( id )
		}
		return question
	}

	// TODO: Check if the question is being used by any trivia nights, and if so, prevent deletion.
	def delete() {

		Question question = getQuestion()
		if ( !question ) {
			redirect( action : 'list' )
		}

		return [
			question : question
		]
	}

	def deleteConfirmed() {

		Question question = getQuestion()
		if ( !question ) {
			redirect( action : 'list' )
		}

		String questionText = question.question
		question.delete()
		success( "Question \"$questionText\" deleted." )

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

		// Did the user want to add a source to this question at this time?
		String sourceLocation    = params[ "source-location" ]
		String sourceCommentText = params[ "source-commentText" ]
		if ( params.containsKey( "source-location" ) && params.containsKey( "source-commentText" ) ) {
			sourceLocation    = params[ "source-location" ]?.trim()
			sourceCommentText = params[ "source-commentText" ]?.trim()
		}

		question?.modifiedBy = userService.current
		if ( question?.validate() ) {
			question.save()
			if ( sourceLocation?.size() > 0 ) {
				sourceService.addSourceToQuestion( sourceLocation, sourceCommentText, question )
			}
			redirect( action : 'list' )
		} else {
			flash.question          = question
			flash.sourceLocation    = params.sourceName
			flash.sourceCommentText = params.sourceDetails
			forward( action : 'edit' )
		}
	}

}
