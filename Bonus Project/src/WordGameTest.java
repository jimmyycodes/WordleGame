import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/*
 * This is the test program for the wordGame which it asks the user for a category in which it will randomize a word within
 * that category. It will keep track of a players health, score, and time it takes to respond. The player has three tries
 * in total for one game. This is the console version of the game.
 */
public class WordGameTest {
	static Scanner user = new Scanner(System.in);
	static HashMap<String, String> key;
	static double startTime;
	static double endTime;
	static int score;
	static int health = 3;
	static boolean firstTry = true;
	public static boolean getKey(WordGame word) {//getKey is used to ask the user for a category and uses wordGame randomizer to return a hashmap with the key and value.
		boolean error = false;
		boolean endGame = false;
		do {
			//uses try and catch to catch any invalid statement
			try {
				System.out.print("Pick a category(1:fruit,2:animal,3:flower, q: quit): ");
				String category = user.next();
				if(category.equalsIgnoreCase("q")) {//ends game if user types q
					System.out.println("goodbye!");
					endGame = true;
					break;
				}
				key = word.randomizer(Integer.parseInt(category));
				error = false;
			}catch(Exception e){
				System.out.println("Sorry invalid category please try again.\n");
				error = true;
			}
		}while(error);
		return endGame;
	}
	public static boolean checkAnswer(String answer) {//checkAnswer is used to check the input that the user types and compares it with the actual answer.
		startTime = System.currentTimeMillis();//tracks time it takes
		String input = user.next();
		boolean stillAlive = true;
		while(!input.equalsIgnoreCase(answer)) {//while loop that will continue to ask the user for an answer if user entered wrong answer.
			health--;//decreases the health
			System.out.println("Incorrect! try again");
			System.out.println("Health: " + health);
			if(health <=0) {//check if health is < 0 if true, it will end the game.
				System.out.println("Game over! No more health.");
				stillAlive = false;
				break;
			}
			input = user.next();
			firstTry = false;//firstTry is used so that if user gets it first try, user gets double the points.
		}
		endTime = System.currentTimeMillis();
		return stillAlive;
		
	}
	public static void printStats() {//printStats is used to print the current statistics of a user after every game or word.
		System.out.println("Correct!!");
		System.out.println("Health: " + health);
		System.out.println("Response Time: " + (endTime -startTime)/1000.0 + " sec");
		System.out.println("Score: " + score + "\n");
	}
	public static void runGame() throws Exception {//runGame combines the three methods above to create a functioning word game.
		boolean run = true;
		WordGame word = new WordGame();
		while(run) {
			firstTry = true;
			if(getKey(word)) {
				break;
			}
			String question = "";
			String answer = "";
			for(String q: key.keySet()) {
				question = q;
				answer = key.get(q);
			}
			//WordGUI test = new WordGUI(question, answer);
			System.out.println(question);
			System.out.println("What word do these letters make?");
			if(!checkAnswer(answer)) {
				break;
			}
			if(firstTry) {
				score+=10;
			}else {
				score+=5;
			}
			printStats();
		}
	}
	public static void main(String[] args) throws Exception {//main method calls runGame which will run the game.
		runGame();
	}
}
