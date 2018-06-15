/**
 * 
 */
package spelling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class NearbyWords implements SpellingSuggest {
	
	// THRESHOLD to determine how many words to look through when looking
	// for spelling suggestions (stops prohibitively long searching)
	private static final int THRESHOLD = 50000; 

	Dictionary dict;

	public NearbyWords (Dictionary dict) 
	{
		this.dict = dict;
	}

	/** Return the list of Strings that are one modification away
	 * from the input string.  
	 * @param s The original String
	 * @param wordsOnly controls whether to return only words or any String
	 * @return list of Strings which are nearby the original string
	 */
	public List<String> distanceOne(String s, boolean wordsOnly )  {
		  		
		List<String> retList = new ArrayList<String>();
		
		 subsitution(s, retList, wordsOnly);
		 insertions(s, retList, wordsOnly);		 
		 deletions(s, retList, wordsOnly);
		   
		return retList;
	}

	
	/** Add to the currentList Strings that are one character mutation away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void subsitution(String s, List<String> currentList, boolean wordsOnly) {
		// for each letter in the s and for all possible replacement characters
		for(int index = 0; index < s.length(); index++){
			for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
				// use StringBuffer for an easy interface to permuting the 
				// letters in the String
				StringBuffer sb = new StringBuffer(s);
				sb.setCharAt(index, (char)charCode);

				// if the item isn't in the list, isn't the original string, and
				// (if wordsOnly is true) is a real word, add to the list
				if(!currentList.contains(sb.toString()) && 
						(!wordsOnly||dict.isWord(sb.toString())) &&
						!s.equals(sb.toString())) {
					
					currentList.add(sb.toString());
				}
			}
		}
	}
	
	/** Add to the currentList Strings that are one character insertion away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void insertions(String s, List<String> currentList, boolean wordsOnly ) {
		// for each letter in the s 
		for(int index = 0; index <= s.length(); index++){
			//  for all possible replacement characters
			for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++){
				
				StringBuffer sb = new StringBuffer(s);
				sb.insert(index , (char)charCode);
				
				// if the item isn't in the list, isn't the original string, and
				// (if wordsOnly is true) is a real word, add to the list
				
				if(!currentList.contains(sb.toString()) &&
						(!wordsOnly || dict.isWord(sb.toString())) &&
						!s.equals(sb.toString())){
					
					currentList.add(sb.toString());
				}
			}			
		}		
	}

	/** Add to the currentList Strings that are one character deletion away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void deletions(String s, List<String> currentList, boolean wordsOnly ) {
		// for each letter in the s 
				for(int index = 0; index < s.length(); index++){
					//  for all possible replacement characters
					for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++){
						
						StringBuffer sb = new StringBuffer(s);
						sb.deleteCharAt(index);
						
						// if the item isn't in the list, isn't the original string, and
						// (if wordsOnly is true) is a real word, add to the list
						
						if(!currentList.contains(sb.toString()) &&
								(!wordsOnly || dict.isWord(sb.toString())) &&
								!s.equals(sb.toString())){
							
							currentList.add(sb.toString());
						}
					}			
				}	
	}

	/** Add to the suggestions list Strings that are one character deletion away
	 * from the input string and are real words.  
	 * @param word The misspelled word
	 * @param numSuggestions is the maximum number of suggestions to return 
	 * @return the list of spelling suggestions
	 */
	@Override
	public List<String> suggestions(String w, int numSuggestions) {
		
		String word = w.toLowerCase();
		List<String> suggestions = new LinkedList<String>();   // words to return
		Queue<String> queue = new LinkedList<String>();     // Strings to explore
		HashSet<String> setOfvisited = new HashSet<String>();   // to avoid exploring the same  string multiple times
		
		setOfvisited.add(word);								//
		queue.add(word);		
		
		//while not enough words generated and there are still words in the queue
		while((suggestions.size() < numSuggestions) && (!queue.isEmpty()) && 
				setOfvisited.size() < THRESHOLD ){
			
			// remove first string from the queue and generate 1-away words for this string
			String curr =  queue.remove();
			List<String> oneWordAwayList = distanceOne(curr, false);
			
			// add the 1-away words to the queue. First check a word hasn't been there already
			for (String oneAwayWord : oneWordAwayList) {
				
				if(!setOfvisited.contains(oneAwayWord)){
					setOfvisited.add(oneAwayWord);
					queue.add(oneAwayWord);
					
					//add strings from queue to the suggestions list if they're real words
					if(dict.isWord(oneAwayWord)){
						suggestions.add(oneAwayWord);
					}
				}					
			}
			
		}
		
		return numSuggestions > suggestions.size() ? suggestions.subList(0, suggestions.size()) : suggestions.subList(0, numSuggestions);

	}	
	// for testing
   public static void main(String[] args) {
	   // basic testing code to get started
	   String word = "kura";
	   // Pass NearbyWords any Dictionary implementation you prefer
	   Dictionary d = new DictionaryHashSet();
	   //DictionaryLoader.loadDictionary(d, "data/dict.txt");
	   DictionaryLoader.loadDictionary(d, "data/Polish_dictionary.dic");
	   
	   NearbyWords w = new NearbyWords(d);
	   // generate words 1 step away from the given word (if "true" only words in the dictionary
	   List<String> listOf_1_Aways = w.distanceOne(word, true);
	   
	   System.out.println("One away word Strings for  \""+word+"\" are:");
	   System.out.println(listOf_1_Aways+"\n");

	   word = "smoj";
	   List<String> suggest = w.suggestions(word, 10); // asking for a list of 10 suggestions for word
	   System.out.println("Spelling Suggestions for \""+word+"\" are:");
	   System.out.println(suggest);
   }

}
