$( function() {

	$( ".toggle-answers" ).each( function( i, item ) {

		var btn = $( item );
		var answers = btn.closest( '.round' ).find( '.answer' );
		new Utils().setupToggle( btn, answers.find( '.value' ).hide(), answers.find( '.show' ) );

		answers.each( function( j, answer) {

			var answerBtn = $( answer ).find( '.show' );
			var answerToggle = $( answer ).find( '.value' );
			new Utils().setupToggle( answerBtn, answerToggle );

		});

	});

});