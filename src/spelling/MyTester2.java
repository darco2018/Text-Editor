package spelling;

public class MyTester2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AutoCompleteDictionaryTrie myDictionaryTrie = new AutoCompleteDictionaryTrie();
		
		//System.out.println("ooooooooooooo M is in dictionary: " + myDictionaryTrie.isWord("M"));
		
		myDictionaryTrie.addWord("Mister");	
		myDictionaryTrie.printTree();
		
		myDictionaryTrie.addWord("M");		
		//myDictionaryTrie.addWord("Mr");
		//myDictionaryTrie.addWord("MrS");
		//System.out.println("ooooooooooooo M is in dictionary: " + myDictionaryTrie.isWord("M"));
		//System.out.println("ooooooooooooo Mr is in dictionary: " + myDictionaryTrie.isWord("Mr"));
		//System.out.println("ooooooooooooo Mrs is in dictionary: " + myDictionaryTrie.isWord("Mrs"));
		
	/*	// adding "M"
				System.out.println();
				System.out.println("Add M expect true): " + myDictionaryTrie.addWord("M"));
				System.out.println("Expected true from isWord(\"M\"): " + myDictionaryTrie.isWord("M"));
				System.out.println("myDictionaryTrie.printTree(). Expect: M");
				myDictionaryTrie.printTree();
		
		// adding "Mr"
		System.out.println();
		System.out.println("Add Mr expect true): " + myDictionaryTrie.addWord("Mr"));
		System.out.println("Expected true from isWord(\"Mr\"): " + myDictionaryTrie.isWord("Mr"));
		System.out.println("myDictionaryTrie.printTree(). Expect: Mr");
		myDictionaryTrie.printTree();*/
		
		/*// adding "Mrs"
		System.out.println();
		System.out.println("Add Mrs expect true): " + myDictionaryTrie.addWord("Mrs"));
		System.out.println("Expected true from isWord(\"Mrs\"): " + myDictionaryTrie.isWord("Mrs"));
		System.out.println("myDictionaryTrie.printTree(). Expect: Mr Mrs");
		myDictionaryTrie.printTree();*/
				
		
		
				
	}

}
