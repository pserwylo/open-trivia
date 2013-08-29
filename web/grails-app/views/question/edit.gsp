<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>Question details</title>
	<r:require module="adminQuestionForm" />
	<meta name="layout" content="admin"></head>

	<g:javascript>
		$(function() {
			$( '#btn-add-source' ).click( function() {
				$( this ).hide();
				$( '.input.source' ).show( 'fast' );
			});

			/* TODO: Deselect 'Unknown' if we select another value.
			$( 'select[name=subject-ids]' ).on( 'change', function( event, params ) {
				var UNKNOWN_ID = '${com.serwylo.trivia.Subject.unknown.id}';
				var value = $( this ).val();
				if ( value != null && value.length > 0 && value.indexOf( UNKNOWN_ID ) != -1 ) {
					$( this ).find( 'option[value=' + UNKNOWN_ID + ']' ).prop( 'selected', false );
				}
			});
			*/
		});
	</g:javascript>

<body>

	<g:if test="${question?.hasErrors()}">
		<div class="errors">
			<g:renderErrors bean="${question}" />
		</div>
	</g:if>

	<g:form action="save">

		<g:if test="${question}">
			<g:hiddenField name="id" value="${question.id}" />
		</g:if>

		<div class="main-details">

			<h2>Question Details</h2>

			<div class="input">
				<label for="input-question">Question</label>
				<g:textArea
					id="input-question"
					class="large ${hasErrors( bean : question, field : 'question', 'errors' )}"
					name="question"
					escapeHtml="false"
					value="${fieldValue( bean : question, field : 'question' )}" />
			</div>


			<div class="input">
				<label for="input-answer">Answer</label>
				<g:textArea
					id="input-answer"
					class="small ${hasErrors( bean : question, field : 'answer', 'errors' )}"
					name="answer"
					escapeHtml="false"
					value="${fieldValue( bean : question, field : 'answer' )}" />
			</div>

			<div class="input select">
				<label for="input-subject">Subject</label>

				<g:select
					id="input-subject"
					name="subject-ids"
					from="${subjectList}"
					optionKey="id"
					multiple="true"
					data-placeholder="Enter subjects"
					value="${question?.subjects ? question?.subjects*.id : []}"
					noSelection="${['0':'']}"/>

			</div>

			<div class="input select">
				<label for="input-difficulty">Difficulty</label>

				<g:select
					id="input-difficulty"
					name="difficulty.id"
					from="${difficultyList}"
					optionKey="id"
					value="${question?.difficulty?.id}"/>

				<g:javascript>
					$( '#input-difficulty' ).chosen({
						disable_search : true
					});
				</g:javascript>

			</div>

			<div style="clear: both;"></div>

		</div>

		<div class="other-details">

			<h2>Source</h2>

			<div class="details">
				<triv:questionSources question="${question}" />
			</div>

			<div class="input buttons">
				<button id="btn-add-source" type="button">Add source</button>
			</div>

			<div class="input source hidden">

				<div class="input">
					<label for="input-source">Source</label>
					<g:textField
						id="input-source"
						name="source-location"
						value="${sourceLocation}"/>
					<div class="info">(for example "https://en.wikipedia.org/wiki/Australia" or "Oxford Dictionary")</div>
				</div>

				<div class="input">
					<label for="input-source-commentText">Source details</label>
					<g:textArea
						id="input-source-commentText"
						class="small"
						name="source-commentText"
						value="${sourceCommentText}" />
				</div>

			</div>

		</div>

		<div class="buttons">
			<g:submitButton
				name="Save"
				class="big" />
			<button
				onclick="document.location='${createLink(action: 'list')}'"
				type="button"
				class="big" >
				Cancel
			</button>
		</div>
	</g:form>

</body>
</html>