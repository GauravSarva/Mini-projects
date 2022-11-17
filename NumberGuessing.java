import java.util.Scanner;

public class NumberGuessing {
	public static void GUESS(){
		try (Scanner sc = new Scanner(System.in)) {
			int number = 1 + (int)(100 * Math.random());

			int chances = 5;
			int UserGuess, guess;

			System.out.println("A number have to be chosen between 1 to 100");
			System.out.println("You're getting 5 trials for guessing the number");

			for (UserGuess = 0; UserGuess < chances; UserGuess++) {
				System.out.println("Guess the number:");
				guess = sc.nextInt();
			
			    if (number == guess) {
					System.out.println("\nCONGRATULATIONS!" + " You guessed the number.");
					break;
				}
			
			    else if (number > guess && UserGuess != chances - 1) {
					System.out.println("\nThe number is greater than " + guess);
				}
			
			    else if (number < guess && UserGuess != chances - 1) {
					System.out.println("\nThe number is less than " + guess);
				}
			}
			
			if (UserGuess == chances) {
				System.out.println("\n You ran out of chances");
				System.out.println("You lost the game!");
				System.out.println("The number was " + number);
			}
		}
	}

	public static void main(String arg[]) {
		GUESS();
	}
}