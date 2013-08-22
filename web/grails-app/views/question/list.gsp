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

			var filterBar = $( '.filter-bar' );
			filterBar.find( "select" ).change( function() {
				filterBar.find( "form" ).submit();
			});

		});
	</g:javascript>

	<r:require module="adminQuestionList" />

	<meta name="layout" content="admin"></head>
<body>

	<triv:notify />

	<triv:filterBar>
		<g:form
			action="list"
			method="get">
			<button
				id="btn-add"
				type="button"
				onclick="document.location='${createLink(action: 'edit')}'"
				class="big" >
				New question
			</button>
			<g:select
				name="subject.id"
				from="${subjects}"
				optionKey="id"
				value="${params['subject.id']}"
				noSelection="${[ 'null' : 'All Subjects' ]}"/>
			<g:select
				class='hide-on-mobile'
				name="difficulty.id"
				from="${difficulties}"
				optionKey="id"
				value="${params['difficulty.id']}"
				noSelection="${[ 'null' : 'All Difficulties' ]}" />
		</g:form>
	</triv:filterBar>

	<table class='list'>
		<thead>
			<tr>
				<g:sortableColumn
					property="question"
					title="Question"/>
				<g:sortableColumn
					property="answer"
					title="Answer" />
				<g:sortableColumn
					property="subject"
					title="Subject" />
				<th class='actions'></th>
			</tr>
		</thead>
		<tbody>
			<g:each in="${questions}" var="question">
				<tr>
					<td><triv:truncate string="${question.question}" length="60" /></td>
					<td><triv:truncate string="${question.answer}"   length="20" /></td>
					<td>${question.subject.name}</td>
					<td class="actions">
						<triv:actionButton action="edit"   id="${question.id}" />
						<triv:actionButton action="delete" id="${question.id}" />
					</td>
				</tr>
			</g:each>
		</tbody>
	</table>

	<div class="pages">
		<g:paginate total="${count}" action="list" offset="${params?.offset}" max="5" />
	</div>

</body>
</html>