<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Manage users</title>
	<r:require module="adminUserList" />
	<meta name="layout" content="admin">
</head>
<body>

	<triv:notify />

	<triv:filterBar>
		<g:form action="list" method="get">
			<button
				id="btn-add"
				class="big"
				onclick="document.location='${createLink(action: 'edit')}'">
				New user
			</button>
		</g:form>
	</triv:filterBar>

	<table id='list-user' class='list'>
		<thead>
			<tr>
				<g:sortableColumn property='username' class='username' title="Username" />
				<th class='roles'>Roles</th>
				<th class='actions'></th>
			</tr>
		</thead>
		<tbody>
			<g:each var="user" in="${users}">
				<tr>
					<td>${user.username}</td>
					<td><triv:userRoles user="${user}" /></td>
					<td class="actions">
						<triv:actionButton id="${user.id}" action="edit" />
						<triv:actionButton id="${user.id}" action="delete" />
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
