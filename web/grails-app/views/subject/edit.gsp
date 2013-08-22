<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>Subject details</title>
	<r:require module="adminForm" />
	<meta name="layout" content="admin"></head>
<body>

	<g:if test="${subject?.hasErrors()}">
		<div class="errors">
			<g:renderErrors bean="${subject}" />
		</div>
	</g:if>

	<g:form action="save">

		<g:if test="${subject}">
			<g:hiddenField name="id" value="${subject.id}" />
		</g:if>

		<div class="input">
			<label for="input-name">Name</label>
			<g:textField
				id="input-name"
				class="${hasErrors( bean : subject, field : 'name', 'errors' )}"
				name="name"
				value="${fieldValue( bean : subject, field : 'name' )}" />
		</div>

		<div class="input">
			<label for="input-description">Description</label>
			<g:textArea
				id="input-description"
				class="large ${hasErrors( bean : subject, field : 'description', 'errors' )}"
				name="description"
				value="${fieldValue( bean : subject, field : 'description' )}" />
		</div>

		<div class="input select">
			<label for="input-subject">Child of</label>
			<g:select
				id="input-subject"
				name="parent.id"
				from="${parentSubjects}"
				optionKey="id"
				noSelection="${[ "null" : "None (top level subject)" ]}"
				value="${subject?.parent?.id}" />
		</div>

		<div class="buttons">
			<g:submitButton
				name="Save"
				class="big" />
			<button
				onclick="document.location = '${createLink(action: 'list')}'"
				type="button"
				class="big">
				Cancel
			</button>
		</div>
	</g:form>

</body>
</html>