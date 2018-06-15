package document;

import java.util.ArrayList;
import java.util.List;

/** 
 * A naive implementation of the Document abstract class. 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class BasicDocument extends Document 
{
	/** Create a new BasicDocument object
	 * 
	 * @param text The full text of the Document.
	 */
	public BasicDocument(String text)
	{
		super(text);
	}
	
	
	/**
	 * Get the number of words in the document.
	 * "Words" are defined as contiguous strings of alphabetic characters
	 * i.e. any upper or lower case characters a-z or A-Z
	 * 
	 * @return The number of words in the document.
	 */
	@Override
	public int getNumWords()
	{
		String regex = "[a-zA-Z]+";
		List<String> tokens = getTokens(regex);	// protected Document method	
		
		return tokens.size();
	}
	
	/**
	 * Get the number of sentences in the document.
	 * Sentences are defined as contiguous strings of characters ending in an 
	 * end of sentence punctuation (. ! or ?) or the last contiguous set of 
	 * characters in the document, even if they don't end with a punctuation mark.
	 * 
	 * @return The number of sentences in the document.
	 */
	@Override
	public int getNumSentences()
	{
		
		String regex = "([?!.]+)";
		List<String> tokens = getTokens(regex);	// protected Document method
		
		if(getText().length() != 0){
			
			int lastIndexOfText = getText().length() -1;			
			
			char lastChar = getText().charAt(lastIndexOfText);
			
			if (!( Character.toString(lastChar).matches(regex)))
				return tokens.size() + 1;		
			
			/* ALTERNATIVE
			 * if (!(punctuationMarks.contains(   Character.toString(lastChar)  )))
				return tokens.size() + 1;	*/		
		}
		
        return tokens.size();
	}
	
	/**
	 * Get the number of syllables in the document.
	 * Words are defined as above.  Syllables are defined as:
	 * a contiguous sequence of vowels, except for a lone "e" at the 
	 * end of a word if the word has another set of contiguous vowels, 
	 * makes up one syllable.   y is considered a vowel.
	 * @return The number of syllables in the document.
	 */
	@Override
	public int getNumSyllables()
	{
		//get words
		String regex = "[a-zA-Z]+";
		List<String> tokens = getTokens(regex);	// protected Document method	
		//System.out.println("\n" + tokens.toString());
		
		//for each word count the syllables
		int totalSyllables = 0;
		
		for(String word : tokens){
			
			
			int syllables = countSyllables(word);
			
			//System.out.println("Word: " + word + "-" + syllables + " syllables");
			
			totalSyllables += syllables;
		}
		
        return totalSyllables;
	}
	
	
	/* The main method for testing this class. 
	 * You are encouraged to add your own tests.  */
	public static void main(String[] args)
	{
		System.out.println( new BasicDocument("I am here right now whatever happens to anybody within reach.").getFleschScore());
		
		testCase(new BasicDocument("a"), 1, 1, 1);
		testCase(new BasicDocument("cOntiguous"), 3, 1, 1);
		testCase(new BasicDocument("sleepY"), 2, 1, 1);
		testCase(new BasicDocument("obvious"), 2, 1, 1);
		testCase(new BasicDocument("toga"), 2, 1, 1);
		 
		testCase(new BasicDocument("soMe"), 1, 1, 1);
		testCase(new BasicDocument("flo"), 1, 1, 1);
		testCase(new BasicDocument("the"), 1, 1, 1);
		testCase(new BasicDocument("thE"), 1, 1, 1);
		testCase(new BasicDocument("cave"), 1, 1, 1);
		testCase(new BasicDocument("Segue"), 2, 1, 1);
		testCase(new BasicDocument("Sentence"), 2, 1, 1);
		testCase(new BasicDocument("This is a test.  How many???  "
		        + "Senteeeeeeeeeences are here... there should be 5!  Right?"),
				16, 13, 5);
		testCase(new BasicDocument(""), 0, 0, 0);
		testCase(new BasicDocument("sentence, with, lots, of, commas.!  "
		        + "(And some poaren)).  The output is: 7.5."), 15, 11, 4);
		testCase(new BasicDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2);
		testCase(new BasicDocument("Here is a series of test sentences. Your program should "
				+ "find 3 sentences, 33 words, and 49 syllables. Not every word will have "
				+ "the correct amount of syllables (example, for example), "
				+ "but most of them will."), 49, 33, 3);
		
		testCase(new BasicDocument("Sentences?!"), 3, 1, 1);
		testCase(new BasicDocument("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."),
		         32, 15, 1);
		
		
	}
	
}
