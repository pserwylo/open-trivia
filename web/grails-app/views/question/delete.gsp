<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Delete question</title>
	<r:require module="adminDelete" />
	<meta name="layout" content="admin"></head>
<body>

	<triv:panel class="delete" title="Really delete?">
		Are you sure you want to delete the question:
		<div class='info'>
			<span class='label'>Question</span>
			${question.question}
		</div>
		<div class='info'>
			<span class='label'>Answer</span>
			${question.answer}
		</div>
		<div class='buttons'>
			<button
				class='cancel'
				type='button'
				onclick='document.location = "${createLink( action : 'list' )}"'>
				Cancel
			</button>
			<button
				class='delete'
				type='button'
				onclick='document.location = "${createLink( action : 'deleteConfirmed', params : [ id : question.id ] )}"'>
				Delete
			</button>
		</div>
	</triv:panel>

</body>
</html>