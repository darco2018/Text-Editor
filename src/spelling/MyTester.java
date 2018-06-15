package spelling;

public class MyTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AutoCompleteDictionaryTrie myDictionaryTrie = new AutoCompleteDictionaryTrie();
		
		// testing after implementing isWord METHOD but before implementing add METHOD
		//check isWord
		System.out.println("Expected false when checkin isWord(null): " + myDictionaryTrie.isWord(null));
		System.out.println("Expected false when checkin isWord(\"\"): " + myDictionaryTrie.isWord(""));
		System.out.println("Expected false when checkin unadded 2-letter word isWord(\"ab\"): " + myDictionaryTrie.isWord("ab"));
		System.out.println("Expected false  when checkin unadded 1-letter word \"a\" from isWord(\"a\"): " + myDictionaryTrie.isWord("a"));
		System.out.println();
		
		//check addWord with invalid values
		System.out.println("Expected false when adding addWord(null): " + myDictionaryTrie.addWord(null));
		System.out.println("Expected false when adding addWord(\"\"): " + myDictionaryTrie.addWord(""));
		System.out.println();
				
		//check addWord for a 1-letter word
		System.out.println("Expected false from isWord(\"a\") before adding \"a\" : " + myDictionaryTrie.isWord("a"));
		// checking printTree();
		System.out.println("myDictionaryTrie.printTree(). Expect empty line: ");
		myDictionaryTrie.printTree();
		System.out.println("Add \"a\" with addWord(\"a\") and expect true(successfully added): " + myDictionaryTrie.addWord("a"));
		System.out.println("Expected true from isWord(\"a\"): " + myDictionaryTrie.isWord("a"));
		System.out.println("myDictionaryTrie.printTree(). Expect: \"a\"");
		myDictionaryTrie.printTree();
		System.out.println("Expected true from isWord(\"a\"): "+ myDictionaryTrie.isWord("a"));
		System.out.println("Add \"a\" AGAIN with addWord(\"a\") and expect false(duplicate): " + myDictionaryTrie.addWord("a"));
		
		
		// adding "b"
		System.out.println();
		System.out.println("Add \"b\" with addWord(\"b\" and expect true): " + myDictionaryTrie.addWord("b"));
		System.out.println("Expected true from isWord(\"b\"): " + myDictionaryTrie.isWord("b"));
		System.out.println("Checking how myDictionaryTrie.printTree() works. Expect: \"a  b\" ");
		myDictionaryTrie.printTree();
		// adding "c"
		System.out.println();
		System.out.println("Add \"c\" with addWord(\"c\" and expect true): " + myDictionaryTrie.addWord("c"));
		System.out.println("Expected true from isWord(\"c\"): " + myDictionaryTrie.isWord("c"));
		System.out.println("Checking how myDictionaryTrie.printTree() works. Expect: \"a  b  c\" ");
		myDictionaryTrie.printTree();
		
		// adding "at"
		System.out.println();
		System.out.println("Add \"at\" with addWord(\"at\" and expect true): " + myDictionaryTrie.addWord("at"));
		System.out.println("Expected true from isWord(\"at\"): " + myDictionaryTrie.isWord("at"));
		System.out.println("Checking how myDictionaryTrie.printTree() works. Expect: \"a  b  c  at\" ");
		myDictionaryTrie.printTree();
				
		// adding "cut"		
		System.out.println();
		System.out.println("Add \"cut\" with addWord(\"cut\" and expect true): " + myDictionaryTrie.addWord("cut"));
		System.out.println("Expected true from isWord(\"cut\"): " + myDictionaryTrie.isWord("cut"));
		System.out.println("Checking how myDictionaryTrie.printTree() works. Expect: \"a  b  c  at  cut\" ");
		myDictionaryTrie.printTree();
		
		// adding "hellow"	
		System.out.println();
		System.out.println("Add \"hellow\" with addWord(\"hellow\" and expect true): " + myDictionaryTrie.addWord("hellow"));
		System.out.println("Expected true from isWord(\"hellow\"): " + myDictionaryTrie.isWord("hellow"));
		System.out.println("Checking how myDictionaryTrie.printTree() works. Expect: \"a  b  c  at  cut hellow\" ");
		myDictionaryTrie.printTree();
		
		// adding "Mr", "Mrs" 
		System.out.println();
		System.out.println("Add \"hellow\" with addWord(\"hellow\" and expect true): " + myDictionaryTrie.addWord("hellow"));
		System.out.println("Expected true from isWord(\"hellow\"): " + myDictionaryTrie.isWord("hellow"));
		System.out.println("Checking how myDictionaryTrie.printTree() works. Expect: \"a  b  c  at  cut hellow\" ");
		myDictionaryTrie.printTree();
		
		
		/*
		//check adding duplicate
		System.out.println("Expected false when adding duplicate \"a\" with addWord(\"a\"): " + myDictionaryTrie.addWord("a"));
		System.out.println();
		
		
		
		///
		
		//
		// checking printTree();
		
		System.out.println();
		///
		System.out.println("Add \"ar\" with addWord(\"ar\" and expect successful attempt with true): " + myDictionaryTrie.addWord("ar"));
		System.out.println("Expected true from isWord(\"ar\"): " + myDictionaryTrie.isWord("ar"));		
		// checking printTree();
				System.out.println("Checking how myDictionaryTrie.printTree() works. Expect: \"a  b  ar\" ");
				myDictionaryTrie.printTree();
				System.out.println();*/
				
		
	}

}
