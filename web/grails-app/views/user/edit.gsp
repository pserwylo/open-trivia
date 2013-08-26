<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>User details</title>
	<r:require module="adminForm" />
	<meta name="layout" content="admin"></head>
<body>

	<g:if test="${user?.hasErrors()}">
		<div class="errors">
			<g:renderErrors bean="${user}" />
		</div>
	</g:if>

	<g:form action="save">

		<g:if test="${user}">
			<g:hiddenField name="id" value="${user.id}" />
		</g:if>

		<div class="input">
			<label for="input-username">Username</label>
			<g:textField
				id="input-username"
				class="${hasErrors( bean : user, field : 'username', 'errors' )}"
				name="username"
				value="${fieldValue( bean : user, field : 'username' )}" />
		</div>

		<div class="input">
			<label for="input-password">Password</label>
			<g:passwordField
				id="input-password"
				class="small ${hasErrors( bean : user, field : 'password', 'errors' )}"
				name="password" />
		</div>

		<div class="input multi-checkboxes">
			<span class='label'>Roles</span>
			<ul>
				<g:each in="${roles}" var="role">
					<li>
						<label>
							<g:checkBox name="roles.id" checked="${userRoles*.id.contains( role.id )}" value="${role.id}" /> ${role.name}
						</label>
					</li>
				</g:each>
			</ul>
		</div>

		<div class="buttons">
			<g:submitButton
				name="Save"
				class="big" />
			<button
				onclick="document.location='${createLink(action: 'list')}'"
				type="button"
				class="big">
				Cancel
			</button>
		</div>
	</g:form>

</body>
</html>