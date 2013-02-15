Views.Generate.Options.Round = klass( function() {

	this.label        = "Trivia Round";

	this.numQuestions = 10;

	/**
	 * @type {Views.Generate.Options.Subject[]}
	 */
	this.subjects     = [];

}).methods({

	toListDom: function() {
		return $( "<span class='label'>" + this.label + " <span class='numQuestions'>" + this.numQuestions + "</span></span>" );
	},

	toFormDom: function() {
		var output = "";
		output += "	<div class='form'>\n";
		output += "		<form>\n";
		output += "			<h1>Trivia round</h1>\n";
		output += "			<label>Title: <input type='text' name='label' value='" + this.label + "' /></label>\n";
		output += "			<label>Num Questions: <input type='text' name='numQuestions' value='" + this.numQuestions + "' /></label>\n";
		output += "			Subjects: \n";

		output += "				<div class='subjects'>\n";
		output += "					<button type='button' class='add-subject'>Add subject</button>";
		output += "					<ul class='subjects'>";

		if ( this.subjects.length == 0 ) {
			output += "						<li>Any and/or all subjects</li>\n";
		} else {
			for ( var i = 0; i < this.subjects.length; i ++ ) {
				var subject = this.subjects[ i ];
				output += "						<li><span class='subject-item'>" + subject.toHtml() + "</span></li>\n";
			}
		}

		output += "					</ul>\n";
		output += "				</div>\n";
		output += "		</form>\n";
		output += "	</div>\n";

		var dom = $( output );
		dom.find( 'button.add-subject' ).click( function() {



		});

		return dom;

	},

	getSubjectsListHtml: function() {
		var output = "";

		return dom;
	}

});