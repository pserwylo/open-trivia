<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>Question details</title>
	<r:require module="adminForm" />
	<meta name="layout" content="admin"></head>
<body>

	<g:if test="${question?.hasErrors()}">
		<div class="errors">
			<g:renderErrors bean="${question}" />
		</div>
	</g:if>

	<g:form action="save">

		<g:if test="${question}">
			<g:hiddenField name="id" value="${question.id}" />
		</g:if>

		<div class="input">
			<label for="input-question">Question</label>
			<g:textArea
				id="input-question"
				class="large ${hasErrors( bean : question, field : 'question', 'errors' )}"
				name="question"
				value="${fieldValue( bean : question, field : 'question' )}" />
		</div>


		<div class="input">
			<label for="input-answer">Answer</label>
			<g:textArea
				id="input-answer"
				class="small ${hasErrors( bean : question, field : 'answer', 'errors' )}"
				name="answer"
				value="${fieldValue( bean : question, field : 'answer' )}" />
		</div>

		<div class="input select">
			<label for="input-subject">Subject</label>
			<g:select
				id="input-subject"
				name="subject.id"
				from="${subjectList}"
				optionKey="id"
				value="${question?.subject?.id}" />
		</div>

		<div class="input select">
			<label for="input-difficulty">Difficulty</label>
			<g:select
				id="input-difficulty"
				name="difficulty.id"
				from="${difficultyList}"
				optionKey="id"
				value="${question?.difficulty?.id}"/>
		</div>

		<div class="buttons">
			<g:submitButton
				name="Save" />
			<button
				id="btn-cancel"
				onclick="document.location='${createLink(action: 'list')}'">
				Cancel
			</button>
		</div>
	</g:form>

</body>
</html>