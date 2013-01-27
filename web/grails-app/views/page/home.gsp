<%@ page contentType="text/html;charset=UTF-8" %>
<html>
	<head>
	  <title><g:message code="app.title" /></title>
	</head>
	<body>

		<h1><g:message code="page.home.call-to-action" /></h1>

		<ul>
			<li>
				<g:message code="page.home.label.demo" />
				<button type="button" class="demo">
					<g:message code="page.home.btn.demo" />
				</button>
			</li>
			<li>
				<g:message code="page.home.label.sign-up" args="${["1000's of"]}" />
				<button type="button" class="sign-up">
					<g:message code="page.home.btn.sign-up" />
				</button>
			</li>
			<li>
				<g:message code="page.home.label.log-in" />
				<button type="button" class="log-in">
					<g:message code="page.home.btn.login" />
				</button>
			</li>
		</ul>

	</body>
</html>