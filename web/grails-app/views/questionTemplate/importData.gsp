<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Import questions</title>
	<r:require module="admin" />
	<meta name="layout" content="admin"></head>
<body>

<ul>
<g:each in="${template.requiredPlaceholders}" var="placeholder">
	<li>${placeholder}</li>
</g:each>
</ul>

</body>
</html>