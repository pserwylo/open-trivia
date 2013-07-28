<%@ page contentType="text/html;charset=UTF-8" %>
<html>
	<head>
	  <title><g:message code="app.title" /></title>
		<r:require module="home" />
	</head>
	<body>

		<h1><g:message code="page.home.call-to-action" /></h1>

		<ul id="main-options">
			<li class="option">
				<span class="label">
					<g:message code="page.home.label.demo" />
				</span>
				<button type="button" class="demo">
					<g:message code="page.home.btn.demo" />
				</button>
			</li>
			<li class="option">
				<span class="label">
					<g:message code="page.home.label.sign-up" args="${["1000's of"]}" />
				</span>
				<button type="button" class="sign-up">
					<g:message code="page.home.btn.sign-up" />
				</button>
				<button type="button" class="log-in">
					<g:message code="page.home.btn.login" />
				</button>
			</li>
		</ul>

	</body>
</html>