<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Manage subjects</title>

	<r:require module="adminSubjectList" />

	<meta name="layout" content="admin"></head>
<body>

	<triv:notify />

	<triv:filterBar>
		<button
			class="big"
			onclick="document.location = '${createLink( action : 'edit' )}'">
			New subject
		</button>
	</triv:filterBar>

	<table class='list'>
		<thead>
			<tr>
				<g:sortableColumn
					property="name"
					title="Subject"
					class="name" />
				<th class='questions'>Questions</th>
				<th class='actions'></th>
			</tr>
		</thead>
		<tbody>
			<g:each in="${subjects}" var="subject">
				<tr>
					<td>${subject.name}</td>
					<td>
						<g:link controller="question" action="list" params="${[ 'subject.id' : subject.id ]}">
							${subject.numQuestions}
							question<triv:plural count="${subject.numQuestions}" />
						</g:link>
					</td>
					<td class="actions">
						<triv:actionButton action="edit"   id="${subject.id}" />
						<triv:actionButton action="delete" id="${subject.id}" />
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
