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
			<span class='label'>Template</span>
			${template.template}
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
				onclick='document.location = "${createLink( action : 'deleteConfirmed', params : [ id : template.id ] )}"'>
				Delete
			</button>
		</div>
	</triv:panel>

</body>
</html>