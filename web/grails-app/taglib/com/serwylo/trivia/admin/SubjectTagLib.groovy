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

			String clazz = index % 2 == 0 ? "even" : "odd"
			String editLink     = createLink( controller : 'subject',  action : 'edit', params : [ id : subject.id ] )
			String questionLink = createLink( controller : 'question', action : 'list', params : [ subjectId : subject.id ] )
			int questionCount = Question.countBySubject( subject )
			String plural = questionCount == 1 ? '' : 's'
			out << """
				<tr class='$clazz'>
					<td>$subject.name</td>
					<td>
						<a href='$questionLink'>$questionCount question$plural</a>
					</td>
					<td>
						<button
							name='edit'
							onclick='document.location = "$editLink"'
							value='${subject.id}'>
							Edit
						</button>
						<button
							onclick='document.location = "${createLink( controller: 'subject', action : 'delete', params : [ id : subject.id ] )}"'
							name='delete'
							value='${subject.id}'>
							Delete
						</button>
					</td>
				</tr>
"""
		}

	}

}
