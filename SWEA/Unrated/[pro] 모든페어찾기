import java.util.*;

public class UserSolution {
	static int n;
	static List<Integer> largeList;
	
    public void playGame(int N) {
    	n = N;
		int[][] pairs = new int[n][4];
		int[] counts = new int[n];
		
    	for(int i = 1; i < 2*n; i++) {
    		int dff = findDiff(i);
    		pairs[dff][counts[dff]++] = i;
    	}
    	
    	for(int i = 1; i < n; i++) {
    		if(counts[i] == 2) Solution.checkCards(pairs[i][0], pairs[i][1], 0);
    		if(counts[i] > 2) {
    			Solution.checkCards(pairs[i][0], pairs[i][1], 0);
	    		Solution.checkCards(pairs[i][0], pairs[i][2], 0);
	    		Solution.checkCards(pairs[i][0], pairs[i][3], 0);
	    		Solution.checkCards(pairs[i][1], pairs[i][2], 0);
	    		Solution.checkCards(pairs[i][1], pairs[i][3], 0);
	    		Solution.checkCards(pairs[i][2], pairs[i][3], 0);
    		}
    	}
    }

	private int findDiff(int idx) {
		int s = -1, e = n-1;
		while(s + 1 < e) {
			int mid = (s+e)/2;
			if(Solution.checkCards(0, idx, mid)) e = mid;
			else s = mid;
		}

		return e;
	}

}
