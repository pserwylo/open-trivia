package com.serwylo.trivia.admin

import com.serwylo.trivia.Question
import com.serwylo.trivia.Subject
import com.serwylo.trivia.auth.User

class SubjectTagLib {

	static namespace = "triv"

	def subjectService

	/**
	 * @attr subjects
	 */
	def subjectList = { attrs ->

		List<Subject> subject
		if ( attrs.containsKey( 'subjects' ) ) {
			subject= attrs.remove( 'subjects' )
		} else {
			subject = subjectService.subjectList
		}

		out << """
			<table id='list-subject' class='list'>
				<thead>
					<tr>
						<th class='name'>Subject</th>
						<th class='questions'>Questions</th>
						<th class='actions'></th>
					</tr>
				</thead>
				<tbody>
					${triv.subjectListItems( [ subjects : subject ] )}
				</tbody>
			</table>
"""
	}

	/**
	 * @attr subjects REQUIRED
	 */
	def subjectListItems = { attrs ->

		List<Subject> subjects = attrs.subjects

		subjects.eachWithIndex { subject, index ->

			String editLink     = createLink( controller : 'subject',  action : 'edit',   params : [ id : subject.id ] )
			String deleteLink   = createLink( controller : 'subject',  action : 'delete', params : [ id : subject.id ] )
			String questionLink = createLink( controller : 'question', action : 'list',   params : [ subjectId : subject.id ] )

			String clazz        = index % 2 == 0 ? "even" : "odd"
			int questionCount   = Question.countBySubject( subject )
			String plural       = questionCount == 1 ? '' : 's'

			out << """
				<tr class='$clazz'>
					<td>$subject.name</td>
					<td>
						<a href='$questionLink'>$questionCount question$plural</a>
					</td>
					<td class='actions'>
						${triv.action( type: 'edit',   link : editLink   )}
						${triv.action( type: 'delete', link : deleteLink )}
					</td>
				</tr>
"""
		}

	}

}
