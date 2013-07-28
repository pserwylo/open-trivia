package com.serwylo.trivia.admin

import com.serwylo.trivia.Question
import com.serwylo.trivia.web.QuestionService

class QuestionsTagLib {

	static namespace = "triv"

	QuestionService questionService

	/**
	 * @attr questions
	 */
	def questionList = { attrs ->

		List<Question> questions
		if ( attrs.containsKey( 'questions' ) ) {
			questions = attrs.remove( 'questions' )
		} else {
			questions = questionService.allQuestions
		}

		out << """
			<table id='list-questions' class='list'>
				<thead>
					<tr>
						<th class='questions'>Question</th>
						<th class='answers'>Answer</th>
						<th class='answers'>Subject</th>
						<th class='actions'></th>
					</tr>
				</thead>
				<tbody>
					${triv.questionListItems( [ questions : questions ] )}
				</tbody>
			</table>
"""
	}

	static String truncate(String string, int length) {
		if ( string.length() > length ) {
			String start = string.substring(0, length - 3)
			String end   = string.substring(length - 3)
			return "$start<span class='continuation'>...</span><span class='remainder'>$end</span>"
		} else {
			return string
		}
	}

	/**
	 * @attr questions REQUIRED
	 */
	def questionListItems = { attrs ->

		List<Question> questionList = attrs.questions

		questionList.eachWithIndex { question, index ->

			String clazz = index % 2 == 0 ? "even" : "odd"
			out << """
				<tr class='$clazz'>
					<td>${truncate(question.question, 60)}</td>
					<td>${truncate(question.answer, 20)}</td>
					<td>$question.subject.name</td>
					<td>
						<button name='edit'   value='${question.id}'>Edit  </button>
						<button name='delete' value='${question.id}'>Delete</button>
					</td>
				</tr>
"""
		}

	}

}
