$( function() {

	$( ".toggle-answers" ).each( function( i, item ) {

		var individualAnswerToggles = [];
		var btn = $( item );
		var answers = btn.closest( '.round' ).find( '.answer' );

		answers.each( function( j, answer) {

			var answerBtn = $( answer ).find( '.show' );
			var answerToggle = $( answer ).find( '.value' );
			individualAnswerToggles.push( new Utils.Toggle( answerBtn, answerToggle ) );

		});

		var toggleAll = new Utils.Toggle(
			btn,
			answers.find( '.value' ).hide(),
			{
				invertedToggleItems: answers.find( '.show' ),
				onHide: function() {

					// Ensures that when we: toggle an individual to show, then toggling all to show, then hiding all,
					// the individual which we showed before is hidden (because we hid all of them).
					$( individualAnswerToggles ).each( function( i, item ) {
						item.hide();
					});

				}
			}
		);

	});

});