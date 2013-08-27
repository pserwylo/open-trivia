<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Manage Question Templates</title>
	<r:require module="adminQuestionTemplateList" />
	<meta name="layout" content="admin"></head>
<body>

	<triv:notify />

	<triv:filterBar>
		<button
			id="btn-add"
			type="button"
			onclick="document.location='${createLink(action: 'edit')}'"
			class="big" >
			New template
		</button>
	</triv:filterBar>

	<table id="template-list" class='list'>
		<thead>
			<tr>
				<g:sortableColumn
					property="question"
					title="Template"/>
				<th>Questions</th>
				<th class='actions'></th>
			</tr>
		</thead>
		<tbody>
			<g:each in="${templates}" var="template">
				<tr>
					<td class='template'>
						${template.name}
					</td>
					<td class='questions'>
						${template.questions?.size()}
					</td>
					<td class="actions">
						<triv:actionButton action="importData" id="${template.id}" />
						<triv:actionButton action="edit"       id="${template.id}" />
						<triv:actionButton action="delete"     id="${template.id}" />
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
