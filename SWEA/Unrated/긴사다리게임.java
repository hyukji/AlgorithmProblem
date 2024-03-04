package 긴사다리게임;

import java.util.*;

class UserSolution {
	
	static class Node {
		int x, y, idx;
		Node[] next = new Node[2];
		public Node(int x, int y, int idx) {
			super();
			this.x = x;
			this.y = y;
			this.idx = idx;
		}
	}
	
	static Node[] nodes ;
	static boolean[] isExist;
	static TreeMap<Integer, Node>[] map;
	static int nodeIdx = 0;
	
	public void init() {
		nodes = new Node[200000]; 
		isExist = new boolean[200000];
		map = new TreeMap[101];
		
		for(int i = 1; i < 101; i++) {
			map[i] = new TreeMap<>();
		}
	}

	public void add(int mX, int mY) {
		Node node = new Node(mX, mY, nodeIdx);
		nodes[nodeIdx] = node;
		isExist[nodeIdx] = true;
		nodeIdx++;

		map[mX].put(mY, node);
		Node bef1 = findBefNode(mX, mY);
		if(bef1 != null) {
			node.next[0] = bef1.next[1];
			bef1.next[1] = node;
		}

		map[mX+1].put(mY, node);
		Node bef2 = findBefNode(mX+1, mY);
		if(bef2 != null) {
			node.next[1] = bef2.next[0];
			bef2.next[0] = node;
		}
	}

	private Node findBefNode(int mX, int mY) {
		int bef = 0;
		for(int cur : map[mX].keySet()) {
			if(cur >= mY) return map[mX].get(bef);
			bef = cur;
		}
		return null;
	}

	public void remove(int mX, int mY) {
		
	}

	public int numberOfCross(int mID) {
		return 0;
	}

	public int participant(int mX, int mY) {
		return 0;
	}
	
}
