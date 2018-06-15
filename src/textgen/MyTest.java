package textgen;

import java.util.LinkedList;
import java.util.List;

public class MyTest {

	public static void main(String[] args) {

		List<ListNode> wordList = new LinkedList<ListNode>();
		ListNode node1 = new ListNode("red");
		ListNode node2 = new ListNode("blue");
		ListNode node3 = new ListNode("green");
		wordList.add(node1);
		wordList.add(node2);
		wordList.add(node3);
		
		System.out.println(wordList.contains(node1));
		System.out.println(wordList.contains(new ListNode("red")));
		

	}

}
