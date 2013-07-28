<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Manage questions</title>

	<g:javascript>
		$(document).ready( function() {

			$( 'tr' ).click(function() {
				if ( $( this ).hasClass( 'full' ) ) {
					$( this ).removeClass( 'full' );
				} else {
					$( this ).addClass( 'full' );
				}
			});

			var filterSubject    = $( '#filter-subject' );
			var filterDifficulty = $( '#filter-difficulty' );
			var tableBody        = $( '#list-questions' ).find( 'tbody' );

			filterSubject.change( function() {
				refreshList();
			});

			$( 'button[name=edit]' ).click( function() {
				var questionId = $( this ).val();
				document.location = '${createLink( action : 'edit' )}?id=' + questionId;
			});

			var refreshList = function() {
				var params = {};

				var subjectId = filterSubject.val();
				if ( subjectId != '0' ) {
					params.subjectId = subjectId;
				}

				var difficultyId = filterDifficulty.val();
				if (difficultyId != '0' ) {
					params.difficultyId = difficultyId;
				}

				tableBody.load( '${createLink( action : 'ajaxList' )}', params );
			};

		});
	</g:javascript>

	<r:require module="adminQuestionList" />

	<meta name="layout" content="main"></head>
<body>

	<g:if test="${flash.errors}">
		<ul class="errors">
			<g:each in="${flash.errors}" var="error">
					<li>${error}</li>
			</g:each>
		</ul>
	</g:if>

	<div class="filter-bar">
		<button
			id="btn-add"
			onclick="document.location='${createLink(action: 'edit')}'">
			New question
		</button>
		<g:select
			name="filter-subject"
			from="${subjects}"
			optionKey="id"
			noSelection="${[ 0 : 'All Subjects' ]}"/>
		<g:select
			name="filter-difficulty"
			from="${difficulties}"
			optionKey="id"
			noSelection="${[ 0 : 'All Difficulties' ]}" />
	</div>

	<triv:questionList/>

</body>
</html>