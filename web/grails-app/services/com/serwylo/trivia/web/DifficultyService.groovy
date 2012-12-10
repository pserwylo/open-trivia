package com.serwylo.trivia.web

import com.serwylo.trivia.Difficulty

class DifficultyService {

	List<Difficulty> getDifficulties() {
		return Difficulty.list();
	}

	Difficulty getEasy() {
		return Difficulty.findByValue( Difficulty.DIFF_EASY )
	}

	Difficulty getMedium() {
		return Difficulty.findByValue( Difficulty.DIFF_MEDIUM )
	}

	Difficulty getHard() {
		return Difficulty.findByValue( Difficulty.DIFF_HARD )
	}

}
