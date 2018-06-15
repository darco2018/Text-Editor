/**
 * 
 */
package spelling;

//import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * WPTree implements WordPath by dynamically creating a tree of words during a Breadth First
 * Search of Nearby words to create a path between two words. 
 * 
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class WPTree implements WordPath {
	
	private WPTreeNode root; // this is the root node of the WPTree	
	private NearbyWords nw; // used to search for nearby Words
	
	// This constructor is used by the Text Editor Application
	// You'll need to create your own NearbyWords object here.
	public WPTree () {
		this.root = null;
		Dictionary dict = new DictionaryHashSet();
		DictionaryLoader.loadDictionary(dict, "data/dict.txt");
		this.nw = new NearbyWords(dict);
	}
	
	//This constructor will be used by the grader code
	public WPTree (NearbyWords nw) {
		this.root = null;
		this.nw = nw;
	}
	
	// see method description in WordPath interface
	public List<String> findPath(String word1, String word2) 
	{
		String startWord = word1.toLowerCase();
		String targetWord = word2.toLowerCase();
		
		// If the user gives you a targetWord which isn't a word in the dictionary, no path will exist,
		// as you'll never find targetWord as a dictionary (real) word when calling the distanceOne method.
		
		if( !nw.dict.isWord(targetWord) )
			return null;
				
		Queue<WPTreeNode> queue = new LinkedList<WPTreeNode>();     // WPTreeNodes to explore
		HashSet<String> setOfvisited = new HashSet<String>();   // to avoid exploring the same string multiple times
		
		// Set the root to be a WPTreeNode containing startWord
		root = new WPTreeNode(startWord, null);
				
		setOfvisited.add(startWord);								
		queue.add(root);		
		
		// while the queue has elements and we have not yet found targetWord
		while( !queue.isEmpty()){
			
			WPTreeNode curr =  queue.remove();
			
			// get a list of real word neighbors (one mutation from curr's word)
			List<String> oneWordAwayList = nw.distanceOne(curr.getWord(), true);
			
			for (String neighbourWord : oneWordAwayList) {
				
				if(!setOfvisited.contains(neighbourWord)){
					
					WPTreeNode childNode = curr.addChild(neighbourWord); //add neighbourWord as a child of curr 
					setOfvisited.add(neighbourWord); //add neighbourWord to the visited set
					queue.add(childNode); //add the child node for neighbourWord to the back of the queue
					
					//if n is word2 no path will exist
					         
					if(neighbourWord.equals(targetWord)){						
						return childNode.buildPathToRoot();
					}
				}				
			} 
		}//end while
		
		return null;
	    
	}
	
	// Method to print a list of WPTreeNodes (useful for debugging)
	@SuppressWarnings("unused")
	private String printQueue(List<WPTreeNode> list) {
		String ret = "[ ";
		
		for (WPTreeNode w : list) {
			ret+= w.getWord()+", ";
		}
		ret+= "]";
		return ret;
	}
	
}

/* Tree Node in a WordPath Tree. This is a standard tree with each
 * node having any number of possible children.  Each node should only
 * contain a word in the dictionary and the relationship between nodes is
 * that a child is one character mutation (deletion, insertion, or
 * substitution) away from its parent
*/
class WPTreeNode {
    
    private String word;
    private List<WPTreeNode> children;
    private WPTreeNode parent;
    
    /** Construct a node with the word w and the parent p
     *  (pass a null parent to construct the root)  
	 * @param w The new node's word
	 * @param p The new node's parent
	 */
    public WPTreeNode(String w, WPTreeNode p) {
        this.word = w;
        this.parent = p;
        this.children = new LinkedList<WPTreeNode>();
    }
    
    /** Add a child of a node containing the String s
     *  precondition: The word is not already a child of this node
     * @param s The child node's word
	 * @return The new WPTreeNode
	 */
    public WPTreeNode addChild(String s){
        WPTreeNode child = new WPTreeNode(s, this);
        this.children.add(child);
        return child;
    }
    
    /** Get the list of children of the calling object
     *  (pass a null parent to construct the root)  
	 * @return List of WPTreeNode children
	 */
    public List<WPTreeNode> getChildren() {
        return this.children;
    }
   
    /** Allows you to build a path from the root node to 
     *  the calling object
     * @return The list of strings starting at the root and 
     *         ending at the calling object
	 */
    public List<String> buildPathToRoot() {
        WPTreeNode curr = this;
        List<String> path = new LinkedList<String>();
       
        while(curr != null) {
            path.add(0,curr.getWord());
            curr = curr.parent; 
        }
        return path;
    }
    
    /** Get the word for the calling object
     *
	 * @return Getter for calling object's word
	 */
    public String getWord() {
        return this.word;
    }
    
    /** toString method
    *
	 * @return The string representation of a WPTreeNode
	 */
    public String toString() {
        String ret = "Word: "+word+", parent = ";
        
        if(this.parent == null) {
           ret+="null.\n";
        }
        else {
           ret += this.parent.getWord()+"\n";
        }
        
        ret+="[ ";
        
        for(WPTreeNode curr: children) {
            ret+=curr.getWord() + ", ";
        }
         
        ret+=(" ]\n");
        
        return ret;
    }

}

