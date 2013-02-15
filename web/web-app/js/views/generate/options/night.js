Views.Generate.Options.Night = klass( function() {

	this.dom = $( "<div id='builder'><ul id='rounds'></ul></div>" );
	$( 'body' ).append( this.dom );

	/**
	 * @type {Views.Generate.Options.Round[]}
	 */
	this.rounds = [ new Views.Generate.Options.Round() ]
	this.build();

}).methods({

	build: function() {
		$( '#rounds' ).empty();
		for ( var i = 0; i < this.rounds.length; i ++ ) {
			this.addRound( this.rounds[ i ] );
		}
	},

	addRound: function( round ) {
		var list = $( '#rounds' );
		var item = $( "<li><span class='clickable'></span></li>" );
		item.find( '.clickable' ).append( round.toListDom() ).click( function() {
			item.empty();
			item.append( round.toFormDom() );
		});
		list.append( item );
	}

});