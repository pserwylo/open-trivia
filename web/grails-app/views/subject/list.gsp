<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Manage subjects</title>

	<r:require module="adminSubjectList" />

	<meta name="layout" content="admin"></head>
<body>

	<triv:notify />

	<triv:filterBar>
		<button onclick="document.location = '${createLink( action : 'edit' )}'">
			New subject
		</button>
	</triv:filterBar>

	<triv:subjectList/>

</body>
</html>