<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Manage questions</title>

	<g:javascript>
		$(document).ready( function() {

			var filterRole = $( '#filter-role' );
			var tableBody  = $( '#list-user' ).find( 'tbody' );

			filterRole.change( function() {
				refreshList();
			});

			var refreshList = function() {
				var params = {};

				var roleId = filterRole.val();
				if ( roleId != '0' ) {
					params.roleId = roleId;
				}

				tableBody.load( '${createLink( action : 'ajaxList' )}', params );
			};

		});
	</g:javascript>

	<r:require module="adminList" />

	<meta name="layout" content="main"></head>
<body>

	<div class="filter-bar">
		<button
			id="btn-add"
			onclick="document.location='${createLink(action: 'edit')}'">
			New user
		</button>
		<g:select
			name="filter-role"
			from="${roles}"
			optionKey="id"
			noSelection="${[ 0 : 'All Roles' ]}"/>
	</div>

	<triv:userList/>

</body>
</html>