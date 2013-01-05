var Utils = {};

/**
 * @param button jQuery object which references the button used to toggle the toggleItems.
 * @param toggleItems jQuery object which references all of the items to be toggled on or off.
 * @param invertedToggleItems Does the reverse, shows when it should hide and hide when it should show.
 */
Utils.Toggle = klass( function( button, toggleItems, options ) {

	this.button = button;
	this.toggleItems = toggleItems;
	this.invertedToggleItems = null;
	this.onShow = null;
	this.onHide = null;

	if ( typeof options !== "undefined" && options != null ) {

		if ( options.hasOwnProperty( 'invertedToggleItems' ) ) {
			this.invertedToggleItems = options.invertedToggleItems;
		}

		if ( options.hasOwnProperty( 'onShow' ) ) {
			this.onShow = options.onShow;
		}

		if ( options.hasOwnProperty( 'onHide' ) ) {
			this.onHide = options.onHide;
		}

	}

	var self = this;

	this.button.click( function() { self.onClick() } );

}).statics({

}).methods({

	onClick: function() {

		if ( this.button.html().substring( 0, 4 ) == 'Hide' )
		{
			this.hide();
		}
		else
		{
			this.show();
		}

	},

	show: function() {

		this.button.html( this.button.html().replace( 'Show', 'Hide' ) );
		this.toggleItems.show();

		if ( this.invertedToggleItems != null ) {
			this.invertedToggleItems.hide();
		}

		if ( this.onShow != null ) {
			this.onShow();
		}

	},

	hide: function() {

		this.button.html( this.button.html().replace( 'Hide', 'Show' ) );
		this.toggleItems.hide();

		if ( this.invertedToggleItems != null ) {
			this.invertedToggleItems.show();
		}

		if ( this.onHide != null ) {
			this.onHide();
		}

	}

});
