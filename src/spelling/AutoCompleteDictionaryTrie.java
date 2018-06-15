package spelling;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete

 */
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete {

	private TrieNode root;
	private int size;

	public AutoCompleteDictionaryTrie() {
		root = new TrieNode();
	}

	/**
	 * Insert a word into the trie. 
	 */
	public boolean addWord(String wordToAdd) {
		// don't add a null or empty string
		if (wordToAdd == null || "".equals(wordToAdd))
			return false;

		String word = wordToAdd.toLowerCase();
		
		// add the word
		if(addWordRecursive(root, word, 0)){
			size++;
			return true;
		} else{
			return false;
		}
	}

	/**
	 * Return the number of words in the dictionary. This is NOT necessarily the
	 * same as the number of TrieNodes in the trie.
	 */
	public int size() {		
		return size;
	}

	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) {
		// don't check a null or empty string as they're not allowed to be added
		// to dictionary
		if ((s == null) || ("".equals(s)))
			return false;
		else {			
			return isWordInDictionary(root, s.toLowerCase(), 0);
		}
	}

	/**
	 * * Returns up to the n "best" predictions, including the word itself, in
	 * terms of length If this string is not in the trie, it returns null.
	 * 
	 * @param text
	 *            The text to use at the word stem
	 * @param n
	 *            The maximum number of predictions desired.
	 * @return A list containing the up to n best predictions
	 */
	@Override
	public List<String> predictCompletions(String prefix, int numCompletions) {
		
		List<String> listOfCompletions = new LinkedList<>();
		
		// don't check a null or empty string as they're not allowed to be added to dictionary
		if ((prefix == null) || ("".equals(prefix))){
			for (int i = 0; i < numCompletions; i++) {
				listOfCompletions.add("");
			}
			return listOfCompletions;
		}
			
				
		String stem = prefix.toLowerCase();
		// Find the stem in the trie. If the stem does not appear in the
		// trie, return an empty list		
		
		TrieNode nodeForLevelOrderStart = getStartingNode(root, stem, 0);
				
		if( nodeForLevelOrderStart == null){
			return listOfCompletions;
		}
		else{
		//Once the stem is found, perform a breadth first search to generate completions
			Queue <TrieNode> nodesToVisit = new LinkedList<TrieNode>();
			nodesToVisit.add(nodeForLevelOrderStart);
			
			while(! nodesToVisit.isEmpty() && listOfCompletions.size() < numCompletions){
				TrieNode curr = nodesToVisit.remove();
				
				if(curr != null){
					
					// return only valid words
					if(curr.endsWord())
						listOfCompletions.add(curr.getText()); // dodaj do List of Completions
					
					for(Character letter : curr.getValidNextCharacters()){
						nodesToVisit.add(curr.getChild(letter));
					}
				}			
			}
			return listOfCompletions;
		}		
		
	}

	// For debugging
	public void printTree() {
		printNode(root);
	}

	/** Do a pre-order traversal from this node down */
	public void printNode(TrieNode curr) {
		if (curr == null)
			return;

		System.out.println(curr.getText());

		TrieNode next = null;
		for (Character c : curr.getValidNextCharacters()) {
			next = curr.getChild(c);
			printNode(next);
		}
	}

	private boolean isWordInDictionary(TrieNode node, String word, int letterIndex) {
		
		char letterToNextChild = word.charAt(letterIndex);
		TrieNode childNode = node.getChild(letterToNextChild);
		
		//base case 1 >> there's no childNode (and we didn't get to the last letter yet)
		if (childNode == null) {
			return false;
		}			
		
		//base case 2 >> there's a childNode and we got to the last letter of the word
		if (childNode != null && letterIndex == word.length()-1 ) {
			
			// check if this childNode is a wordNode or just a "node on the way" to another node
			if (childNode.endsWord()) {
				return true; 				
			} else {
				return false; 	
			}
			
		}				
		
		letterIndex++;
		// move to next node and letter in this word
		return isWordInDictionary(childNode, word, letterIndex);
	} 

	

	private boolean addWordRecursive(TrieNode node, String word, int letterIndex) {
		
		char letterToNextChild = word.charAt(letterIndex);
		TrieNode childNode = node.getChild(letterToNextChild);
		
		//base case  >> we got to the last letter 
		if (letterIndex == word.length()-1) {
			
			// check if duplicate
			if(childNode != null && childNode.endsWord()){
				return false; // (duplicate > unsuccessful attempt to add)
			}				
			else { 
				
				if (node.getChild(letterToNextChild) != null)// there is a node
				{
					node.getChild(letterToNextChild).setEndsWord(true);//just set it to EndsWord true
					return true; // successfully added
				}	
				else // if there isnt a node, so create the FINAL NODE
				{
					TrieNode finalNode = node.insert(letterToNextChild); 
					finalNode.setEndsWord(true);  
					return true; // successfully added
				}	 
			}
						
		}		
		
		letterIndex++;
		
		if (childNode == null){
			// create a through node to move on
			 TrieNode throughNode = node.insert(letterToNextChild);
			 return addWordRecursive(throughNode, word, letterIndex);
		}
		else { 
			// use the existing node to move on	
			return addWordRecursive(childNode, word, letterIndex);
		}
		
	}
	
	protected TrieNode getRoot(){
		return root;
	}
	//////////////////////////my methods/////////////////////////////
	public void printTreeWords() {
		printWordNode(root);
	}

	/** Do a pre-order traversal from this node down */
	public void printWordNode(TrieNode curr) {
		if (curr == null)
			return;
		
		if(curr.endsWord())
			System.out.println(curr.getText());

		TrieNode next = null;
		for (Character c : curr.getValidNextCharacters()) {
			next = curr.getChild(c);
			printWordNode(next);
		}
	}
	
	
	private TrieNode getStartingNode(TrieNode node, String stem, int letterIndex){
		
		// go to the node with the last character of the stem
		char letterToNextChild = stem.charAt(letterIndex);
		TrieNode childNode = node.getChild(letterToNextChild);
		
		//base case 1 >> there's no childNode (and we didn't get to the last letter yet)
		if (childNode == null) {
			return null;
		}			
		
		//base case 2 >> there's a childNode and we got to the last letter of the word
		if (childNode != null && letterIndex == stem.length()-1 ) {						
			return childNode; 
		}				
		
		letterIndex++;
		// move to next node and letter in this word
		return getStartingNode(childNode, stem, letterIndex);
	}
}