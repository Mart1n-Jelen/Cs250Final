import java.util.Scanner;

public class HangMan {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		startMenu();
		String phrase = getPhrase(stdIn);
		int phraseLength = arrLength(phrase);
		char[] arrPhrase = new char[phraseLength];
		char[] arrLetters = new char[50];
		arrPhrase(phrase, arrPhrase, phraseLength);
		int usedLives = 0;
		int lettersLength = 0;
		int endGame = 0;
		do {

			manImage(usedLives);
			phraseArrPrint(arrPhrase, phraseLength);
			lettersArrPrint(arrLetters, lettersLength);
			int p2MenuChoice = p2MenuChoice(stdIn);
			if (p2MenuChoice == 1) {
				char letter = p2LetterGuess(stdIn, arrLetters, lettersLength);
				letterAddLetterArr(letter, arrLetters, lettersLength);
				lettersLength += 1;
				letterAddPhraseArr(letter, arrPhrase, phraseLength, phrase);
				int tempLives = livesUsed(letter, arrPhrase, phraseLength, phrase);
				if (tempLives < 1)
					usedLives += 1;
				if (usedLives == 6)
					endGame = 2;
				if (checkWinLetters(arrPhrase, phrase, phraseLength))
					endGame = 1;

			} else if (p2MenuChoice == 2) {
				if (p2PraseGuess(stdIn, phrase, phraseLength))
					endGame = 1;
				else
					usedLives = 6;
				endGame = 2;
			}
		} while ((endGame != 1) && (endGame != 2));
		manImage(usedLives);
		phraseArrPrint(arrPhrase, phraseLength);
		lettersArrPrint(arrLetters, lettersLength);
		if (endGame == 1)
			System.out.print("Player 2 Wins!!!");
		else if (endGame == 2)
			System.out.print("Player 1 Wins!!!");
	}

	public static void startMenu() {
		System.out.println("\n  H A N G M A N\n" + "      _____\n" + "     |     |\n" + "     |     0\n"
				+ "     |    /|\\\n" + "     |    / \\\n" + "  ___|___\n\n" + "Directions: \n"
				+ "1. P1 chooses a phrase for P2 to guess...\n2. P2 can guess a letter or can guess the entire phrase...\n3. P2 has 5 lives to pick the right letters or one \n"
				+ "   chance to guess the whole phrase...\n4. Play in lower case...\n5. Have fun!!!");
	}

	public static String getPhrase(Scanner stdIn) {
		String phrase;
		System.out.println("\nP1 enter word or phrase : ");
		phrase = stdIn.nextLine();
		for (int i = 0; i < 24; ++i)
			System.out.print("\n");
		return phrase;
	}

	public static int arrLength(String phrase) {
		return phrase.length();
	}

	public static char[] arrPhrase(String phrase, char[] arrPhrase, int length) {
		for (int i = 0; i < length; ++i) {
			if (phrase.charAt(i) == ' ') {
				arrPhrase[i] = ' ';
			} else {
				arrPhrase[i] = '_';
			}
		}
		return arrPhrase;
	}

	public static void phraseArrPrint(char[] arrPhrase, int length) {
		System.out.print("W/P: ");
		for (int i = 0; i < length; ++i) {
			System.out.print(arrPhrase[i]);
			System.out.print(" ");
		}
		System.out.println("");
	}

	public static void manImage(int usedLives) {

		if (usedLives == 0)
			System.out
					.print("      _____\n" + "     |     |\n" + "     |\n" + "     |\n" + "     |\n" + "  ___|___\n\n");
		else if (usedLives == 1)
			System.out.print(
					"      _____\n" + "     |     |\n" + "     |     0\n" + "     |\n" + "     |\n" + "  ___|___\n\n");
		else if (usedLives == 2)
			System.out.print("      _____\n" + "     |     |\n" + "     |     0\n" + "     |     |\n" + "     |\n"
					+ "  ___|___\n\n");
		else if (usedLives == 3)
			System.out.print("      _____\n" + "     |     |\n" + "     |     0\n" + "     |    /|\n" + "     |\n"
					+ "  ___|___\n\n");
		else if (usedLives == 4)
			System.out.print("      _____\n" + "     |     |\n" + "     |     0\n" + "     |    /|\\\n" + "     |\n"
					+ "  ___|___\n\n");
		else if (usedLives == 5)
			System.out.print("      _____\n" + "     |     |\n" + "     |     0\n" + "     |    /|\\\n"
					+ "     |    / \n" + "  ___|___\n\n");
		else if (usedLives == 6)
			System.out.print("      _____\n" + "     |     |\n" + "     |     0\n" + "     |    /|\\\n"
					+ "     |    / \\\n" + "  ___|___\n\n");

	}

	public static void lettersArrPrint(char[] arrletters, int length) {
		System.out.print("Letters guessed: ");
		for (int i = 0; i < length; ++i)
			System.out.print(arrletters[i] + ",");
		System.out.println("\n");
	}

	public static int p2MenuChoice(Scanner stdIn) {
		int choice = 0;
		do {
			System.out.println("P2, would you like too:\nGuess a Letter (1)\nGuess the Phrase (2)");
			choice = stdIn.nextInt();
		} while ((choice != 1) && (choice != 2));
		return choice;
	}

	public static char p2LetterGuess(Scanner stdIn, char[] arrLetters, int length) {
		char letter = ' ';
		int match = 0;
		do {
			System.out.println("Guess a letter you have not guessed: ");
			letter = stdIn.next().charAt(0);
			for (int i = 0; i < length; ++i) {
				if (letter == arrLetters[i])
					match = 1;

			}
		} while (match == 1);
		return letter;
	}

	public static char[] letterAddLetterArr(char letter, char[] arrLetters, int length) {
		arrLetters[length] = letter;
		return arrLetters;
	}

	public static char[] letterAddPhraseArr(char letter, char[] arrphrase, int length, String phrase) {
		for (int i = 0; i < length; ++i) {
			if (phrase.charAt(i) == (letter)) {
				arrphrase[i] = letter;
			}
		}
		return arrphrase;
	}

	public static int livesUsed(char letter, char[] arrphrase, int length, String phrase) {

		int temp = 0;
		for (int i = 0; i < length; ++i) {
			if (phrase.charAt(i) == (letter)) {
				temp += 1;
			}
		}
		return temp;
	}

	public static boolean p2PraseGuess(Scanner stdIn, String phrase, int length) {
		System.out.print("Guess the phrase : ");
		int temp = 0;
		String guess = stdIn.next();
		guess += stdIn.nextLine();
		if (phrase.length() == guess.length()) {
			for (int i = 0; i < length; ++i)
				if (phrase.charAt(i) == guess.charAt(i))
					temp += 1;
			if (temp == length)
				return true;
			else
				return false;

		}
		return false;

	}

	public static boolean checkWinLetters(char[] arrPhrase, String phrase, int length) {
		int temp = 0;
		for (int i = 0; i < length; ++i)
			if (phrase.charAt(i) == arrPhrase[i])
				temp += 1;
		if (temp == length)
			return true;
		return false;
	}
}
