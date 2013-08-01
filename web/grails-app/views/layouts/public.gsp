<!doctype html>
<!--[if lt IE 7 ]><html lang="en" class="ie6"><![endif]-->
<!--[if    IE 7 ]><html lang="en" class="ie7"><![endif]-->
<!--[if    IE 8 ]><html lang="en" class="ie8"><![endif]-->
<!--[if    IE 9 ]><html lang="en" class="ie9"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en"><!--<![endif]-->
	<head>
		<title>
			<g:layoutTitle default="Open Trivia"/>
		</title>

		<g:layoutHead/>
        <r:require module="publicPage" />
		<r:layoutResources />
	</head>
	<body>
		<div id="header">
			<h1>Trivia</h1>
			%{--<nav:primary scope="publicScope" />--}%
		</div>
		<div id="content">
			<g:layoutBody/>
		</div>
		<div class="footer" role="contentinfo"></div>
        <r:layoutResources />
	</body>
</html>