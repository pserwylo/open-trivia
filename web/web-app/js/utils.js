var Utils = klass( function() {

}).statics({

}).methods({

	/**
	 * @param button jQuery object which references the button used to toggle the toggleItems.
	 * @param toggleItems jQuery object which references all of the items to be toggled on or off.
	 * @param invertedToggleItems Does the reverse, shows when it should hide and hide when it should show.
	 */
	setupToggle: function( button, toggleItems, invertedToggleItems ) {

		button.click( function() {

			if ( this.innerHTML.substring( 0, 4 ) == 'Hide' )
			{
				this.innerHTML = this.innerHTML.replace( 'Hide', 'Show' );
				toggleItems.hide( 'fast' );

				if ( typeof invertedToggleItems !== "undefined" ) {
					invertedToggleItems.show( 'fast' );
				}
			}
			else
			{
				this.innerHTML = this.innerHTML.replace( 'Show', 'Hide' );
				toggleItems.show( 'fast' );

				if ( typeof invertedToggleItems !== "undefined" ) {
					invertedToggleItems.hide( 'fast' );
				}
			}

		});

	}

});
