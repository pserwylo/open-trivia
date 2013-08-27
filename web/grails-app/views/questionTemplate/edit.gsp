<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>Template details</title>
	<r:require module="adminQuestionTemplateForm" />
	<meta name="layout" content="admin"></head>
<body>

	<g:if test="${template?.hasErrors()}">
		<div class="errors">
			<g:renderErrors bean="${template}" />
		</div>
	</g:if>

	<g:form action="save">

		<g:if test="${template}">
			<g:hiddenField name="id" value="${template.id}" />
		</g:if>

		<div class="main-details">

			<h2>Template Details</h2>

			<div class="input">
				<label for="input-name">Name</label>
				<g:textField
					id="input-name"
					class="large ${hasErrors( bean : template, field : 'name', 'errors' )}"
					name="name"
					value="${fieldValue( bean : template, field : 'name' )}" />
			</div>

			<div class="input">
				<label for="input-questionTemplate">Question Template</label>
				<g:textArea
					id="input-questionTemplate"
					class="small ${hasErrors( bean : template, field : 'questionTemplate', 'errors' )}"
					name="questionTemplate"
					escapeHtml="false"
					value="${fieldValue( bean : template, field : 'questionTemplate' )}" />
			</div>

			<div class="input">
				<label for="input-answerTemplate">Answer Template</label>
				<g:textArea
					id="input-answerTemplate"
					class="small ${hasErrors( bean : template, field : 'answerTemplate', 'errors' )}"
					name="answerTemplate"
					escapeHtml="false"
					value="${fieldValue( bean : template, field : 'answerTemplate' )}" />
			</div>

		</div>

		<div class="other-details">

			<h2>Help</h2>

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