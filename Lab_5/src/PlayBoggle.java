/*
 * 		Malcolm Groves
 * 		28 October 2016
 * 
 * 		Creates a boggle board object from a
 * 		file and initiates play. Code is adapted
 * 		from starter file provided by Prof Majercik
 * 
 */

public class PlayBoggle {

	// Purpose: Main method of the class, plays boggle
	public static void main(String[] args) {

		// Header with instructions
		System.out.println("Welcome to Boggle!");
		Boggle boggleBoard = new Boggle();
		boggleBoard.play();

	}

}