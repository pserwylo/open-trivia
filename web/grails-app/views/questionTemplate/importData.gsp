<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Import questions</title>
	<r:require module="adminImport" />
	<meta name="layout" content="admin"></head>
<body>

<div class="main-details">

	<h2>Import "${template.name}" questions</h2>

	<g:form action="performImport">

		<g:hiddenField name="id" value="${template.id}" />

		<div class="input">
			<label for="input-file">Upload a spreadsheet</label>
			<input id="input-file" type="file" name="file" />
		</div>

		<div class="or">
			or
		</div>

		<div class="input">
			<label for="input-text">Copy and paste</label>
			<g:textArea
				id="input-text"
				class="large"
				name="text"/>
		</div>

		<div class="buttons">
			<button class="big" type="submit">
				Import
			</button>
			<button class="big" type="button" onclick="document.location = '${createLink( action : 'list' )}">
				Cancel
			</button>
		</div>

	</g:form>
</div>

<div class="other-details">
	<h2>Requirements</h2>

	<h3>File type</h3>
	<p>
		Must be a <em>tab</em> delimited file.
	</p>

	<h3>Columns</h3>
	<p>
		Must have these columns:
	</p>
	<ul class='required-placeholders'>
		<li>id</li>
		<g:each in="${template.requiredPlaceholders}" var="placeholder">
			<li>${placeholder}</li>
		</g:each>
	</ul>
	<p class="info">
		("id" is a special column that uniquely identifies rows, and doesn't change once a row has been added.
		If you change a row but leave the "id" unchanged, this import will update the existing question,
		rather than create a new one.)
	</p>

</div>

</body>
</html>