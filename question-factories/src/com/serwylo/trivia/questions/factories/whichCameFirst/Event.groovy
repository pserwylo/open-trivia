package com.serwylo.trivia.questions.factories.whichCameFirst

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat

class Event implements Comparable<Event> {

	static final DateFormat DD_MM_YYYY = new SimpleDateFormat( "dd/MM/yyyy" )
	static final DateFormat YYYY = new SimpleDateFormat( "yyyy" )

	String name
	Date date

	String toString() {

		return name + " (" + YYYY.format( date ) + ")"

	}

	/**
	 * Only compares the year component of the date.
	 * @param event
	 * @return
	 */
	int compareTo( Event event ) {

		return this.date.year.compareTo( event.date.year )

	}

	Boolean equals( Event event ) {

		return this.date.year == event.date.year && this.name == event.name

	}

	static Event create( String name, String dateString ) {

		Date date = null

		try {
			date = DD_MM_YYYY.parse( dateString )
		} catch ( ParseException e1 ) {
			try {
				date = YYYY.parse( dateString )
			} catch ( ParseException e2 ) {
				throw new IllegalArgumentException( "Cannot parse date value '$dateString'" )
			}
		}

		return new Event(
			name: name,
			date: date
		)

	}

}
