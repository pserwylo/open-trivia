package com.serwylo.trivia

class HostOutputTagLib {

	/**
	 * @attr round REQUIRED
	 * @attr roundNumber REQUIRED
	 */
	def round = { attrs ->

		TriviaRound round = attrs.round
		int roundNumber = attrs.roundNumber

		HostSheet.create( round ).render( roundNumber )

	}

}

abstract class HostSheet<T> {

	T round


	abstract void render( int roundNumber );

	static HostSheet create( T round ) {

		HostSheet output = null

		if ( round instanceof SubjectRound ) {



		} else if ( round instanceof QuestionAndChallengeRound ) {

			output = new QuestionHostSheet();

		} else if ( round instanceof QuestionRound ) {

			output = new QuestionHostSheet();

		} else {

			throw new Exception( "Round '" + round.getClass().getCanonicalName() + "' cannot render its host sheet." )

		}

		output.round = round
		return output

	}

}

class QuestionHostSheet extends HostSheet<QuestionRound>
{

	@Override
	void render( int roundNumber ) {

		out << """
			<div class='round'>
				<h2>Round ${roundNumber + 1}</h2>
				<ul class='questions-list'>
		"""

		round.questions.eachWithIndex { it, i ->

			out << """
				<li class='question-item'>
					<div class='question-number'>${i+1}</div>
					<div class='question'><span class='label'>Q: </span>${it.question.replaceAll( "\n", "<br />" )}</div>
					<div class='comment'>${it.commentToHost.replaceAll( "\n", "<br />" )}</div>
					<div class='answer'><span class='label'>A: </span>${it.answer.replaceAll( "\n", "<br />" )}</div>
				</li>
			"""

		}

		out << """
				</ul>
			</div>
			<div style="page-break-after: always;"><span style="display: none;">&nbsp;</span></div>
		"""

	}

}
