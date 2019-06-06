package cs311Anagrams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class CS311Anagrams {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		//check args
		if(args.length != 1) {
			System.err.println("I dont understand your args");
			System.exit(0);
		}
		//load dictionary
		ArrayList<DictEntry> dictionary = readDictionary(args[0]);
		System.out.println("Loaded " + dictionary.size() + " dictionary entries");
		
		//sort each dictEntry orgWord to sortedWord
		for(DictEntry de : dictionary) {
			de.sortedWord = sortString(de.orgWord);
		}
		System.out.println("sorted dictEntry's");
		
		//sort array list
		sortDict(dictionary);
		
		//combine common anagram classes and write to file
		File file = new File("anagram-output");
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file);
			
			DictEntry prev = null; //this will track the last one
			for(DictEntry de : dictionary){
				if(prev != null && de.sortedWord.equals(prev.sortedWord)) { //if the last one had the same anagram class
					fileWriter.write(" " + de.orgWord); //add this one on the same line
				} else if(prev == null) { //first entry
					fileWriter.write(de.orgWord); //print out that word
				} else { //not first entry
					fileWriter.write("\n"); //if not move to next line
					fileWriter.write(de.orgWord); //print out that word
				}
				prev = de;
			}
			
			fileWriter.flush();
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to write to file");
		}
		
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime + " millisecond");
	}
	
	public static ArrayList<DictEntry> sortDict(ArrayList<DictEntry> dict) {
		quicksortDict(dict,0,dict.size());
		return dict;
	}
	
	//basically the same as our other method
	public static void quicksortDict(ArrayList<DictEntry> dict, int p, int r) {
		if(p<r){
			int q=partition2(dict,p,r);
			quicksortDict(dict,p,q);
			quicksortDict(dict,q+1,r);
		}
	}
	
	//basically the same as our other method
	public static int partition2(ArrayList<DictEntry> dict, int p, int r) {
		int i = p - 1;
		for(int j=p; j<= r-1; j++) {
			//lexicographically compare words
			if(dict.get(j).sortedWord.compareTo(dict.get(r-1).sortedWord) < 0) {
				i++;
				//swap Dict[i] with Dict[j]
				DictEntry temp = dict.get(i);
				dict.set(i, dict.get(j));
				dict.set(j, temp);
			} 
		}
		//swap Dict[i+1] with Dict[r-1]
		DictEntry temp = dict.get(i+1);
		dict.set(i+1, dict.get(r-1));
		dict.set(r-1, temp);
		return i+1;
	}
	
	public static String sortString(String word){
		char[] wordAsChar = word.toCharArray();
		quicksortString(wordAsChar,0,word.length());
		return new String(wordAsChar); //is passed by reference
	}
	
	//from the book
	public static void quicksortString(char[] word, int p, int r){
		if(p<r) {
			int q= partition(word,p,r);
			quicksortString(word,p,q);
			quicksortString(word,q+1,r);
		}
	}
	
	//the same method from the book
	public static int partition(char[] word, int p, int r) {
		int i = p - 1;
		for(int j=p; j<= r-1; j++) {
			if(word[j] < word[r-1]) {
				i++;
				//swap word[i] with word[j]
				char temp = word[i];
				word[i] = word[j];
				word[j] = temp;
			} 
		}
		//swap word[i+1] with word[r-1]
		char temp = word[i+1];
		word[i+1] = word[r-1];
		word[r-1] = temp;
		return i+1;
	}
	
	public static ArrayList<DictEntry> readDictionary(String filename) {
		ArrayList<DictEntry> dictionary = new ArrayList<DictEntry>();  //this will hold our dicEntries
		System.out.println("reading " + filename);
		
		try { 
			//load words from dictionary to dictEntry array list
			File file = new File(filename);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if(!line.isEmpty())
					dictionary.add(new DictEntry(line));  // create new entry and append
			}
			fileReader.close();
		} catch (Exception e) {
			System.out.println("failed to read file");
			System.exit(0);
		}
		
		System.out.println("Finished reading " + filename);
		
		return dictionary;
	}
}
