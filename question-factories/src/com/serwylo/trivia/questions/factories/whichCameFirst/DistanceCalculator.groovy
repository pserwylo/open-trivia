package com.serwylo.trivia.questions.factories.whichCameFirst

import java.text.DateFormat
import java.text.SimpleDateFormat

/**
 * Calculate the maximum time we wish to explore to find events to compare with.
 *
 * For more recent events, we only want to explore other recent events. That is, it is silly to ask:
 *   Which came first: "The first world wide web or the first iPod"
 *
 * However, as we go further back, it makes sense to explore greater distances, such as:
 *   Which came first: "The light bulb or the telegraph" which are actually a couple of decades apart.
 *
 * In addition, the closer the dates are together, the harder the question should be.
 * Therefore, we will allow for varying degrees of difficulty for the questions depending on the distance between
 * events.
 *
 *
 */
class DistanceCalculator {

	Map<Integer,Integer> intervals = [:]

	Event event

	Integer maxYearsAway = 0

	public DistanceCalculator( Event event ) {

		this.event = event

		this.intervals.put( 1, 1 );
		this.intervals.put( 100, 10 );
		this.intervals.put( 250, 35 );
		this.intervals.put( 1000, 200 );

		this.maxYearsAway = this.calcMaxDistance()

		// SimpleDateFormat format = new SimpleDateFormat( "yyyy" )
		// println "Event: $event.name (${format.format( event.date )}) will accept dates within ${maxYearsAway}yrs"

	}

	/**
	 * Based on the maximum allowed distance, decides whether or not to allow these two events to be compared in a
	 * question.
	 * @param event
	 * @return
	 */
	public Boolean accept( Event event ) {

		Integer diff = Math.abs( event.date.year - this.event.date.year )
		return diff < this.maxYearsAway && diff != 0

	}

	public Integer calcMaxDistance() {

		Date eventDate = (Date)event.date.clone()
		Date nowDate   = new Date()

		Map.Entry<Integer, Integer> previousEntry = null
		Map.Entry<Integer, Integer> nextEntry = null

		for ( Map.Entry<Integer, Integer> entry : this.intervals ) {

			nextEntry = entry

			Date pastDate = nowDate.clone()
			pastDate.year -= entry.getKey()

			if ( eventDate > pastDate ) {

				break

			}

			previousEntry = nextEntry
		}

		Integer distance = 0

		if ( previousEntry == null ) {

			distance = nextEntry.getValue()

		} else if ( previousEntry == nextEntry ) {

			distance = previousEntry.getValue()

		} else {

			// Interpolate between next and previous entry.
			Integer lowEventYear = previousEntry.getKey()
			Integer middle = Math.abs( eventDate.year - nowDate.year )
			Integer highEventYear = nextEntry.getKey()
			Double ratio = middle / ( highEventYear - lowEventYear )

			Integer lowDistanceYear = previousEntry.getValue()
			Integer highDistanceYear = nextEntry.getValue()

			distance = lowDistanceYear + ( highDistanceYear - lowDistanceYear ) * ratio

		}

		return distance

	}

}
