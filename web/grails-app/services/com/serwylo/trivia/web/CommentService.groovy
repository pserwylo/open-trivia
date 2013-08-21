package com.serwylo.trivia.web

import com.serwylo.trivia.Comment

class CommentService {

	UserService userService

	Comment create( String commentText ) {
		Comment comment = new Comment(
			commentText : commentText,
			createdBy   : userService.current,
		)

		log.info "Creating comment for user $userService.current.id: '$commentText'."

		comment.save( flush : true )

		log.debug( "Comment $comment.id created." )

		return comment
	}

}