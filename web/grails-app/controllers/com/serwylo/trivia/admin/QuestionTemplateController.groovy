package com.serwylo.trivia.admin

import com.serwylo.trivia.questions.Template
import com.serwylo.trivia.web.*

class QuestionTemplateController extends CRUDController {

	static defaultAction = 'list'

	QuestionTemplateService questionTemplateService
	UserService userService

	def list() {

		def templates = questionTemplateService.listTemplates( params )
		def count     = questionTemplateService.countTemplates( params )

		return [
			templates : templates,
			count     : count,
			params    : params,
		]

	}

	def importData() {

		Template template = getTemplate()
		if ( template == null ) {
			error( "Template $idParam not found." )
		}

		[ template : template ]
	}

	def performImport() {

		Template template = getTemplate()
		if ( template == null ) {
			error( "Template $idParam not found." )
			return
		}

		String text = ""
		if ( params.containsKey( 'text' ) ) {
			text = params.text?.trim()
		}

		if ( params.containsKey( 'file' ) ) {

		}

		if ( text?.length() > 0 ) {
			questionTemplateService.importQuestions( template, text )
		} else {
			// questionTemplateService.importQuestions( template, reader )
		}

	}

	def edit() {

		long id = idParam
		Template template = null
		if ( flash.template ) {
			template = flash.template
		} else if ( id > 0 ) {
			template = getTemplate()
			if ( template == null ) {
				error( "Template $id not found." )
			} else if ( !template.canEdit() ) {
				error( "Cannot edit template $template.name" )
			}

		}

		return [
			template : template,
		]

	}

	/**
	 * If params.id points to a valid question, return the domain object, otherwise returns null.
	 */
	private Template getTemplate() {
		long id = idParam
		Template template = null
		if ( id ) {
			template = Template.get( id )
		}
		return template
	}

	// TODO: Check if the template has any questions, and if so, prevent deletion.
	def delete() {

		Template template = getTemplate()
		if ( !template ) {
			redirect( action : 'list' )
		} else if ( !template.canEdit() ) {
			error( "Cannot delete template $template.name" )
		}

		return [
			template : template
		]
	}

	def deleteConfirmed() {

		Template template = getTemplate()
		if ( !template ) {
			redirect( action : 'list' )
		} else if ( !template.canEdit() ) {
			error( "Cannot delete template $template.name" )
		}

		String templateName = template.name
		template.delete()
		success( "Template \"$templateName\" deleted." )

	}

	def save = {

		long id = idParam
		Template template
		if ( id ) {
			template = Template.get( id )
			if ( template == null ) {
				errors.reject( "Could not find template $id" )
			} else if ( !template.canEdit() ) {
				redirect( action : 'list' )
			} else {
				template.properties = params
			}
		} else {
			params.createdBy = userService.current
			template = new Template( params )
		}

		template?.createdBy  = userService.current
		template?.modifiedBy = userService.current
		if ( template?.validate() ) {
			template.save()
			redirect( action : 'list' )
		} else {
			flash.template = template
			forward( action : 'edit' )
		}
	}

}