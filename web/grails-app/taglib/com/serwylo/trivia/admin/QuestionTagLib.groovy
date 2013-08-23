package com.serwylo.trivia.admin

import com.serwylo.trivia.Question
import com.serwylo.trivia.QuestionSource
import com.serwylo.trivia.Subject

class QuestionTagLib {

	static namespace = "triv"

	def questionService

	/**
	 * @attr question REQUIRED
	 */
	def questionSources= { attrs ->

		if ( !attrs.containsKey( 'question' ) ) {
			throwTagError( "Tag [questionSources] doesn't have required [question] attribute." );
		}

		Question question = attrs.question

		if ( question != null ) {
			out << "<ul class='sources output-items'>"
			question.sources.each { QuestionSource source ->
				String comment = source.comment?.commentText ?: ""
				String location = source.isUrl() ? "<a href='$source.location'>$source.source.name</a>" : source.location
				out << """
					<li class='source output-item pop-out'>
						<div class='location'>$location</div>
						<div class='description'>$comment</div>
						${triv.createdModified( [ createdBy : source.createdBy, dateCreated : source.dateCreated ] )}
					</li>
				"""
			}
			out << "</ul>"
		}
	}

}
