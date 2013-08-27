<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Quick ideas</title>
	<r:require module="adminIdeaList" />
	<meta name="layout" content="admin">
</head>
<body>

	<triv:notify />

	<g:form action="save">
		<g:textArea
			id="input-ideaText"
			placeholder="Enter new idea here then save"
			class="large ${hasErrors( bean : idea, field : 'ideaText', 'errors' )}"
			name="ideaText"
			escapeHtml="false"
			value="${fieldValue( bean : idea, field : 'ideaText' )}" />

		<button class="large">
			
		</button>
	</g:form>

	<table id='list-idea' class='list'>
		<thead>
			<tr>
				<th class='idea'>Idea</th>
				<th class='actions'></th>
			</tr>
		</thead>
		<tbody>
			<g:each var="idea" in="${ideas}">
				<tr>
					<td><triv:truncate text="${idea.comment}" length="50" /></td>
					<td class="actions">
						<triv:actionButton id="${idea.id}" action="convert-to-question" />
						<triv:actionButton id="${idea.id}" action="delete" />
					</td>
				</tr>
			</g:each>
		</tbody>
	</table>

	<div class="pages">
		<g:paginate total="${count}" action="list" offset="${params?.offset}" max="10" />
	</div>

</body>
</html>
