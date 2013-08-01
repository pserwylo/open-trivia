<html>
<head>
	<meta name='layout' content='public'/>
	<r:require module="login" />
	<title><g:message code="springSecurity.login.title"/></title>

	<g:javascript>
		(function() {
			document.forms['loginForm'].elements['j_username'].focus();
		})();
	</g:javascript>
</head>

<body>

	<triv:panel titleCode="springSecurity.login.header">
		<g:if test='${flash.message}'>
			<div class="errors">
				<ul>
					<li>${flash.message}</li>
				</ul>
			</div>
		</g:if>

		<form action='${postUrl}' method='POST'>
			<div class="input">
				<label for='username'>
					<g:message code="springSecurity.login.username.label"/>
				</label>
				<g:textField name='j_username' id='username'/>
			</div>

			<div class="input">
				<label for='password'>
					<g:message code="springSecurity.login.password.label"/>
				</label>
				<g:passwordField name='j_password' id='password'/>
			</div>

			<div class="input">
				<label>
					<g:checkBox name='${rememberMeParameter}' id='remember_me' checked='${hasCookie}' />
					<g:message code="springSecurity.login.remember.me.label"/>
				</label>
			</div>

			<g:submitButton name='${message(code: "springSecurity.login.button")}'/>
		</form>
	</triv:panel>
</body>
</html>
