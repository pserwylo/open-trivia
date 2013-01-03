package com.serwylo.trivia.questions.factories

import com.serwylo.trivia.questions.GeneratedQuestion
import com.serwylo.trivia.questions.factories.whichCameFirst.DistanceCalculator
import com.serwylo.trivia.questions.factories.whichCameFirst.Event
import com.serwylo.trivia.questions.WhichCameFirstQuestion

/**
 * "Which came first, event 1 or event 2?".
 * This class generates questions that comapre two events, e.g. inventions, tv show start dates,
 * album release dates, etc.
 * It will make sure that events are not too close or too far apart, and will assign a higher
 * difficulty if the events are closer in time (TODO).
 */
class WhichCameFirstFactory extends BatchQuestionFactory {

	final static HEADER_NAME = "name"
	final static HEADER_DATE = "date"
	final static HEADER_CATEGORY = "category"

	private Map<String,List<Event>> eventsBySubject = [:]

	@Override
	protected List<GeneratedQuestion> generateQuestions() {

		List<WhichCameFirstQuestion> questions = []

		this.initEvents();

		eventsBySubject.each { entry ->

			questions.addAll( processEvents( entry.value, entry.key ) )

		}

		// It is easier to wait until we've created every single question before figuring out which are
		// mutually exclusive. May be a bit slower, but oh well...
		questions.each { question ->

			question.mutuallyExclusive = questions.findAll { question.isMutuallyExclusive( it ) }

		}

		return questions

	}

	/**
	 * Allows us to take a list of events and compare each to each other (where allowed).
	 * Currently enforces only comaprisons between the same subjects, but it might be a nice TODO to
	 * compare some cross-subject questions.
	 */
	protected List<WhichCameFirstQuestion> processEvents( List<Event> events, String subject ) {

		List<WhichCameFirstQuestion> questions = []

		// Not a very Groovy way to iterate, but I want to ensure that events from the inner loop are not compared
		// to events from the outer loop which have already been visited.
		for ( Integer i = 0; i < events.size(); i ++ ) {

			Event mainEvent = events.get( i )
			DistanceCalculator calc = new DistanceCalculator( mainEvent )

			for ( Integer j = i + 1; j < events.size(); j ++ ) {

				Event secondaryEvent = events.get( j )
				if ( calc.accept( secondaryEvent ) )
				{

					questions.add( new WhichCameFirstQuestion( mainEvent, secondaryEvent ) )

				}

			}

		}

		return questions

	}

	/**
	 * Iterate through each of the items from the spreadsheet (getCachedData()).
	 * Each will be turned into an event.
	 */
	protected void initEvents() {

		getCachedData().each { data ->

			String name = data[ HEADER_NAME ]
			String date = data[ HEADER_DATE ]
			String category = data[ HEADER_CATEGORY ]

			Event event = Event.create( name, date )

			if ( !this.eventsBySubject.containsKey( category ) ) {
				this.eventsBySubject.put( category, [] )
			}

			this.eventsBySubject.get( category ).add( event )

		}

	}

	@Override
	protected List<Header> getAdditionalHeaders() {
		return [
			new Header( name: HEADER_NAME, isRequired: true ),
			new Header( name: HEADER_DATE, isRequired: true ),
			new Header( name: HEADER_CATEGORY, isRequired: true ),
		]
	}

	@Override
	protected File getFile() {
		new File( "/home/pete/code/open-trivia/data/WhichCameFirst.tsv" )
	}

	@Override
	String getName() {
		return "Which Came First?"
	}

}
