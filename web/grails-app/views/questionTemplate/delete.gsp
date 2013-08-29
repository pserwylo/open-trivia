<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Delete template</title>
	<r:require module="adminDelete" />
	<meta name="layout" content="admin"></head>
<body>

	<triv:panel class="delete" title="Really delete?">
		Are you sure you want to delete the template:
		<div class='info'>
			<span class='label'>Name</span>
			${template.name}
		</div>
		<div class='info'>
			<span class='label'>Question Template</span>
			${template.questionTemplate}
		</div>
		<div class='info'>
			<span class='label'>Answer Template</span>
			${template.answerTemplate}
		</div>
		<div class='buttons'>
			<button
				class='cancel big'
				type='button'
				onclick='document.location = "${createLink( action : 'list' )}"'>
				Cancel
			</button>
			<button
				class='delete big'
				type='button'
				onclick='document.location = "${createLink( action : 'deleteConfirmed', params : [ id : template.id ] )}"'>
				Delete
			</button>
		</div>
	</triv:panel>

</body>
</html>