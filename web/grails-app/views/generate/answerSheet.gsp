<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title></title>
	<r:require module="answerSheet" />
	<r:layoutResources media="print" />
	<r:layoutResources />
</head>
<body>

	<g:each in="${night.rounds}" var="round" status="i">
		<g:round round="${round}" roundNumber="${i}" />
	</g:each>

</body>
</html>