package com.serwylo.trivia

/**
 * CMS-like page controller, for rendering templated content.
 * All of the pages are going to be defined once, and there will not be scope for arbitrary pages (at this point).
 * Rather, this just does the dirty work of displaying the templates.
 */
class PageController {

	static layout = "page"

	/**
	 * Either display the home page, or decide which other page needs to be rendered.
	 * If not found, handle the 404 appropriately.
	 */
	def index() {

		this.home()

	}

	def home() {

	}

}

