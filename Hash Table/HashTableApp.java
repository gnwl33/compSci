/*
THIS CODE WAS MY OWN WORK , IT WAS WRITTEN WITHOUT CONSULTING
CODE WRITTEN BY OTHER STUDENTS . _Gene Lee_
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HashTableApp {
	public static void main(String[] args) {
		if (args.length < 1){
			System.out.println("Pass the filename to be read as an argument.");
			System.exit(-1);
		}

		File input = new File(args[0]);
		Scanner fileReader = null;

		try{
			fileReader = new Scanner(input);
		} catch (FileNotFoundException e) {
			System.out.println("File was not found.  Check the file name and try again.");
			System.exit(-2);
		}

		// TODO: Build a hash table of frequencies from the given input file
		// and query the user for words in order to lookup their frequencies.
		HashSeparateChaining diction = new HashSeparateChaining();	// Create a new hash table/dictionary

		String word;
		Integer freq;

		while (fileReader.hasNext()){	// Read each string in the text
			StringBuilder Word = new StringBuilder(fileReader.next().toLowerCase()); // Store the string as lowercase and StringBuilder (for char deletion)

			for(int i = 0; i < Word.length(); i++){	// If there is punctuation at the beginning of the word, remove it
				if (Character.isLetterOrDigit(Word.charAt(i)))
					break;
				else
					Word.deleteCharAt(i);
			}

			for(int i = Word.length() - 1; i > -1; i--){	// If there is punctuation at the end of the word, remove it
				if (Character.isLetterOrDigit(Word.charAt(i)))
					break;
				else
					Word.deleteCharAt(i);
			}

			if (Word.length() == 0)	// If the string is null, don't add it to the hash table
				continue;

			word = Word.toString();	// Convert the StringBuilder back into a string

			freq = diction.get(word);	// Check the current frequency of the word and update

			if (freq == null)
				freq = 0;

			freq += 1;

			diction.put(word, freq);	// Add/update the word in the hash table
		}

		Scanner in = new Scanner(System.in);

		while (true){	// Query the user for a word to search
			System.out.print("Enter a word to find its frequency: ");

			word = in.next().toLowerCase(); // Make word into lowercase

			if (word.equals("qqq"))	// To terminate the program
				break;

			freq = diction.get(word);

			if (freq == null)
				freq = 0;

			System.out.println("\"" + word + "\" appears " + freq + " time(s)");	// Tell the word's frequency
		}
	}
}