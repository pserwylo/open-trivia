package com.serwylo.trivia.web

import com.serwylo.trivia.Comment
import com.serwylo.trivia.Question
import com.serwylo.trivia.QuestionSource
import com.serwylo.trivia.Source

class SourceService extends CRUDService {

	UserService    userService
	CommentService commentService

	/**
	 * @param location
	 * @return
	 */
	Source getOrCreate( String location ) {
		String sourceName = locationToName( location )
		Source source = Source.findByName( sourceName )
		if ( source == null ) {
			source = new Source(
				createdBy : userService.current,
				name      : sourceName,
			)

			log.info "Creating new source '$sourceName'."

			source.save( flush : true )

			log.debug "Source $source.id created."
		}
		return source
	}

	/**
	 * The location could be any string, e.g. a URL, a book title, "common sense", or something else.
	 * If it is a URL, we will try to extract the host name and just use that, otherwise we'll stick
	 * to whatever is passed in.
	 * @param location
	 * @return
	 */
	private String locationToName( String location ) {
		String sourceName = location
		URL url = attemptUrl( location )
		if ( url ) {
			sourceName = url.host
		}
		return sourceName
	}

	private URL attemptUrl( String location ) {
		URL url = null
		try {
			url = new URL( location )
		} catch ( MalformedURLException urlException ) {

			// Hmm... doesn't look like a URL...
			if ( urlException.message.contains( "no protocol" ) ) {
				// but what the hell, let's shoot a request there anyway and see what happens...

				try {
					URL mungedUrl = new URL( "http://$location" )

					// This may be a bad idea timing out for 1 second, but if it is, we can always
					// revert back to just statically parsing the URL...
					HttpURLConnection connection = (HttpURLConnection)mungedUrl.openConnection()
					connection.setConnectTimeout( 1000 )
					int responseCode = connection.responseCode

					// Seemed okay, I wont bother following any 3** redirects, we'll guess that
					// they are okay.
					if ( responseCode >= 200 && responseCode <= 400 ) {
						url = mungedUrl
					}
				} catch ( IOException ioException ) {
					// Either still didn't look like a URL, or didn't get a proper response code.
					// let's give up here...
				}
			}
		}
		return url
	}

	void addSourceToQuestion( String location, String commentText, Question question ) {
		Comment comment = null
		if ( commentText?.trim()?.size() > 0 ) {
			comment = commentService.create( commentText )
		}

		QuestionSource questionSource = new QuestionSource(
			source    : getOrCreate( location ),
			location  : location,
			comment   : comment,
			createdBy : userService.current,
			question  : question,
		)

		log.info "Adding source '$location' to question $question.id"

		questionSource.save( flush : true, failOnError : true )

		log.info "Source added to question (join $questionSource.id)"
	}
}