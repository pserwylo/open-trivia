<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Manage questions</title>

	<g:javascript>
		$(document).ready( function() {
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
				id="subject-filter"
				name="subject.id"
				from="${subjects}"
				optionKey="id"
				value="${params['subject.id']}"
				noSelection="${['0':'All subjects']}"/>

			<g:select
				id="difficulty-filter"
				class='hide-on-mobile'
				name="difficulty.id"
				from="${difficulties}"
				optionKey="id"
				value="${params['difficulty.id']}"
				noSelection="${['0':'All difficulties']}"/>

		</g:form>
	</triv:filterBar>

	<table class='list'>
		<thead>
			<tr>
				<g:sortableColumn
					property="question"
					title="Question"/>
				<g:sortableColumn
					property="subject"
					title="Subject" />
				<th class='actions'></th>
			</tr>
		</thead>
		<tbody>
			<g:each in="${questions}" var="question">
				<tr>
					<td>
						<div class="question">
							<triv:truncate string="${question.question}" length="80" />
						</div>
						<div class="answer">
							<triv:truncate string="${question.answer}"   length="100" />
						</div>
					</td>
					<td>${question.subjects*.name.join( ", " )}</td>
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