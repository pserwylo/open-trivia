package com.serwylo.trivia.web

import au.com.bytecode.opencsv.CSVReader
import com.serwylo.trivia.Difficulty
import com.serwylo.trivia.Subject
import com.serwylo.trivia.questions.Template
import com.serwylo.trivia.questions.TemplateQuestion
import org.grails.plugins.csv.CSVMapReader

class QuestionTemplateService extends CRUDService {

	UserService userService

	static class ImportException extends Exception {

	}

	List<Template> listTemplates( def params ) {
		def listParams = getListParams( params )
		Template.list( listParams )
	}

	int countTemplates( def params ) {
		Template.count()
	}

	void importQuestions( Template template, String text )
		throws ImportException {

		importQuestions( template, new StringReader( text ) )

	}

	def importQuestions( Template template, Reader from )
		throws ImportException {

		CSVReader csvReader = new CSVReader( from, (char)'\t' )
		CSVMapReader csv    = new CSVMapReader( csvReader )

		List<TemplateQuestion> questions = []

		csv.collect { Map map ->
			questions.add( createQuestion( template, map ) )
		}

		questions*.validate()
		def witErrors     = questions.findAll { it.hasErrors()  }
		def withoutErrors = questions.findAll { !it.hasErrors() }
		withoutErrors*.save()
		return questions
	}

	private TemplateQuestion createQuestion( Template template, Map map ) {

		String id = map.containsKey( 'id' ) ? map.id : null

		TemplateQuestion question = null

		if ( id ) {
			question = TemplateQuestion.findBySourceIdAndTemplate( id, template )
		}

		if ( question == null ) {
			question = new TemplateQuestion(
				sourceId          : id,
				template          : template,
				difficulty        : Difficulty.unknown,
				subjects          : [ Subject.unknown ],
				createdBy         : userService.current)
		}

		question.placeholderValues = map
		question.modifiedBy        = userService.current

		// TODO: Check for subjects and difficulty using special fields (like how 'id' is a special field).

		return question
	}

}
