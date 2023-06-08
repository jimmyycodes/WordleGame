import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/*
 * This class is the main core of the game in which it uses hashmaps and collections as a way to randomize a word and store it into a hashmap containing the value
 * which would be returned.
 */
public class WordGame {
	//initializes three arraylist that will store the categories of word and a hashmap that is used to find the specific category given the key.
	private static ArrayList<String> fruit = new ArrayList<String>();
	private static ArrayList<String> flower = new ArrayList<String>();
	private static ArrayList<String> animal = new ArrayList<String>();
	private static HashMap<Integer, ArrayList<String>> ca = new HashMap<>();
	public static HashMap<String, String> randomizer(int key) throws FileNotFoundException {//ranomizer method returns a hashmap containing the key(randomized word) and the value(answer of that randomized word)
		readFiles();
		Random rnd = new Random();
		int index = rnd.nextInt(category(key).size());//uses method category as a way find the specific list for the given key using hashmaps.
		String answer = category(key).get(index).toUpperCase();
		
		String question = "";
		List<String> temp = Arrays.asList(answer.split(""));//splits the word into a list of string which would be shuffled using built in method .shuffle
		Collections.shuffle(temp);
		for(int i = 0; i < temp.size(); i++) {
			question += temp.get(i).toUpperCase();
		}
		HashMap<String, String> hm = new HashMap<>();//creates hashmap, stores randomized word and value.
		hm.put(question, answer);
		return hm;
		
	}
	public static ArrayList<String> category(int key) {//category is used to return the specific list given key value.
		ca.put(1, fruit);
		ca.put(2, animal);
		ca.put(3, flower);
		return ca.get(key);
	}
	public static void readFiles() throws FileNotFoundException {//readFile reads a single data file from a text in which it will differentiate Fruit, Animal, Flower by codings within the file(1:Fruit,2:Animal,3:Flower).
		Scanner scan = new Scanner(new File(System.getProperty("user.dir")+"/RandomWords.txt"));
		while(scan.hasNextLine()) {
			String word = scan.nextLine();//reads the whole line
			int code = word.charAt(0)-'0';//converts the code into a integer value
			if(code==1) {//finds the specific code and adds it to its specific list.
				fruit.add(word.substring(2));
			}else if(code == 2) {
				animal.add(word.substring(2));
			}else if(code == 3) {
				flower.add(word.substring(2));
			}
		}
	}
	
}
