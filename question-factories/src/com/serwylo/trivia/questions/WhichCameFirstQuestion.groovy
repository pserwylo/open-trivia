package com.serwylo.trivia.questions

import com.serwylo.trivia.questions.factories.whichCameFirst.Event
import org.apache.commons.collections.CollectionUtils

class WhichCameFirstQuestion extends GeneratedQuestion {

	Event firstEvent
	Event secondEvent

	/**
	 * The order of event1 or event2 does not matter. We will randomly assign them an order in the question.
	 * @param event1
	 * @param event2
	 */
	public WhichCameFirstQuestion( Event event1, Event event2 ) {

		// Put them in order of date...
		firstEvent  = event1 < event2 ? event1 : event2
		secondEvent = event1 < event2 ? event2 : event1

		// Then shuffle them randomly for the actual formatting of the question, so that we know about 50% of the time
		// the first one will be right...
		Boolean swap = Math.random() > 0.5
		Event firstInQuestion  = swap ? secondEvent : firstEvent
		Event secondInQuestion = swap ? firstEvent  : secondEvent

		question = "Which came first, \"$firstInQuestion.name\" or \"$secondInQuestion.name\"?"
		answer = firstInQuestion < secondInQuestion ? "$firstInQuestion came before $secondInQuestion" : "$secondInQuestion came before $firstInQuestion"
		md5( "WhichCameFirst", firstEvent.toString() + secondEvent.toString() )
	}

	Boolean equals( WhichCameFirstQuestion question ) {

		return question.firstEvent == this.firstEvent && question.secondEvent == this.secondEvent

	}

	Boolean isMutuallyExclusive( WhichCameFirstQuestion question ) {

		( question != this ) &&
			CollectionUtils.containsAny( [ firstEvent, secondEvent ], [ question.firstEvent, question.secondEvent ] )

	}

}
