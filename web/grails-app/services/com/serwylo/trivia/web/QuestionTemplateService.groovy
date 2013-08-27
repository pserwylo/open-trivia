package com.serwylo.trivia.web

import com.serwylo.trivia.questions.Template

class QuestionTemplateService extends CRUDService {

	List<Template> listTemplates( def params ) {
		def listParams = getListParams( params )
		Template.list( listParams )
	}

	int countTemplates( def params ) {
		Template.count()
	}

}
