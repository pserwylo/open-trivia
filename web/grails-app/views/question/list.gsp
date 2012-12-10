<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title></title>

	<g:javascript>
		$(document).ready( function() {



		});
	</g:javascript>

	<meta name="layout" content="main"></head>
<body>

	<div class="filter-bar">
		<select name="filter-subject" id="filterBySubject">
			<option value="0">All Subjects</option>
			<g:each in="${subjects}" var="subject">
				<option value="${subject.name}">${subject.name}</option>
			</g:each>
		</select>
		<select name="filter-difficulty" id="filterByDifficulty">
			<option value="0">All Difficulties</option>
			<g:each in="${difficulties}" var="difficulty">
				<option value="${difficulty.value}">${difficulty.label}</option>
			</g:each>
		</select>
	</div>

	<table>
		<tr>
			<th>Question</th>
			<th>Answer</th>
			<th>Score</th>
			<th>Used</th>
			<th>Tasks</th>
		</tr>
		<tbody>
			<g:each in="${questions}" var="question">
				<tr>
					<td>${question.question}</td>
					<td>${question.answer}</td>
					<td>
						<button>Edit</button>
						<button>Delete</button>
					</td>
				</tr>
			</g:each>
		</tbody>
	</table>

</body>
</html>