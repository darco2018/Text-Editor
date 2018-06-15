package spelling;

public class MyTester3 {

	public static void main(String[] args) {
		
		StringBuffer sb1 = new StringBuffer("obile");
		System.out.println(sb1);
		/*sb1.insert(0, 'm');
		System.out.println(sb1);*/
		sb1.insert(2, 'z');
		System.out.println(sb1);
		
		/*AutoCompleteDictionaryTrie myDictionaryTrie = new AutoCompleteDictionaryTrie();
		
		myDictionaryTrie = new AutoCompleteDictionaryTrie();
		
		//loading word one by one to smallDict
		myDictionaryTrie.addWord("Hello");
		myDictionaryTrie.addWord("HElLo");
		myDictionaryTrie.addWord("help");
		myDictionaryTrie.addWord("he");
		myDictionaryTrie.addWord("hem");
		myDictionaryTrie.addWord("hot");
		myDictionaryTrie.addWord("hey");
		myDictionaryTrie.addWord("a");
		myDictionaryTrie.addWord("subsequent");
		
		myDictionaryTrie.printTree();
		System.out.println();
		
		List<String> completions =  myDictionaryTrie.predictCompletions("a", 3);
		System.out.println(completions.contains("a"));
		System.out.println(completions.toString());
		*/
		/*myDictionaryTrie.addWord("amber");
		myDictionaryTrie.printNode(myDictionaryTrie.getRoot());
		myDictionaryTrie.addWord("a");
		myDictionaryTrie.printNode(myDictionaryTrie.getRoot());
		//myDictionaryTrie.addWord("ar");
		myDictionaryTrie.printNode(myDictionaryTrie.getRoot());
		myDictionaryTrie.addWord("at");
		myDictionaryTrie.printNode(myDictionaryTrie.getRoot());
		myDictionaryTrie.addWord("are");
		myDictionaryTrie.printNode(myDictionaryTrie.getRoot());
		myDictionaryTrie.addWord("as");
		myDictionaryTrie.printNode(myDictionaryTrie.getRoot());
		
		System.out.println();
		System.out.println("Calling printTreeWords ONLY:");
		myDictionaryTrie.printTreeWords();
		
		System.out.println();
		System.out.println("Trying method levelOrder:");
		myDictionaryTrie.levelOrder();
		
		System.out.println("Completions: ");
		List<String> completions =  myDictionaryTrie.predictCompletions("a", 3);
		System.out.println(completions.toString());*/
		
		

	}

}
