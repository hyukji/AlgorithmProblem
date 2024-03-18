package 끝말잇기2;

import java.util.*;
import java.util.Map.Entry;

class UserSolution {
	
	static class Node implements Comparable<Node> {
		char value;
		boolean isFinal;
		TreeMap<Character, Node> nodes;
		
		public Node() {
			super();
			this.isFinal = false;
			this.nodes = new TreeMap<>();
		}

		@Override
		public int compareTo(Node o) {
			return Character.compare(value, o.value);
		}
	}
	
	static class Trie {
		Node root;
		
		public Trie() {
			super();
			this.root = new Node();
		}

		public void add(String word) {
			Node cur = root;
			for(int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				if(c == '\0') return;
				if(!cur.nodes.containsKey(c)) cur.nodes.put(c, new Node());
				
				cur = cur.nodes.get(c);
			}
			cur.isFinal = true;
		}

		public String find(char mCh) {
			if(root.nodes.get(mCh) == null) return null;
			
			String result = "";
			ArrayDeque<Node> history = new ArrayDeque<>();
			
			Node cur = root;
			while(!cur.isFinal) {
				history.push(cur);
				Entry<Character, Node> entry = cur.nodes.firstEntry();
				result += entry.getKey();
				cur = entry.getValue();
			}
			
			cur.isFinal = false;
			if(cur.nodes.isEmpty() && !history.isEmpty()) {
				Node parent = history.pop();
				parent.nodes.pollFirstEntry();
				cur = parent;	
			}
			
			return result;
		}
		
	}
	
	static class Person {
		int id;
		Person next;
		Person prev;
		
		public Person(int id) {
			super();
			this.id = id;
		}
	}
	
	
	static int n;
	static Person[] people;
	static Trie trie;
	
    public void init(int N, int M, char[][] mWords) {
    	n = N;
    	people = new Person[N+1];
    	trie = new Trie();
    	
    	people[1] = new Person(1);
    	for(int i = 2; i < N+1; i++) {
    		people[i] = new Person(i);
    		people[i-1].next = people[i];
    		people[i].prev = people[i-1];
    	}
    	people[N].next = people[1];
    	people[1].prev = people[N];
    	
    	for(int i = 0; i < M; i++) {
    		String word = makeString(mWords[i]);
    		trie.add(word);
    		System.out.println("add" + word);
    	}
    }
    
    String makeString(char[] word) {
    	String str = "";
    	int i = 0;
    	while(true) {
    		char c = word[i++];
    		if(c == '\0') return str;
    		str += c;
    	}
    }

    public int playRound(int mID, char mCh) {
    	Person p = people[mID];
    	while(true) {
    		String str = trie.find(mCh);
    		System.out.println("play " + str);
    		
    		if(str == null) break;
    		
    		String reverse = reverseString(str);
    		trie.add(reverse);
    		
    		System.out.println("play add " + reverse);
    		
    		mCh = reverse.charAt(0);
    	}
    	
    	Person front = p.prev;
    	Person back = p.next;
    	front.next = back;
    	back.prev = front;
    	
        return p.id;
    }

	private String reverseString(String str) {
		String rev = "";
		int l = str.length();
		for(int i = l-1; i >= 0; i--) {
			rev += str.charAt(i);
		}
		return rev;
	}
}
