import java.io.*;
import java.util.*;

public class Main {
	
	static class Loc {
		int r, c;
		public Loc(int r, int c) {
			this.r = r;
			this.c = c;
		}
		public int getDist(Loc loc) {
			return Math.abs(r - loc.r) + Math.abs(c - loc.c);
		}
	}
	
	static int countChick = 0, countHome = 0, answer = Integer.MAX_VALUE;
	static Loc[] chickens = new Loc[13], homes;
	static int[] combArr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		homes = new Loc[n*2];
		
		for(int r =0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c =0; c < n; c++) {
				int v = Integer.parseInt(st.nextToken());
				if(v == 1) homes[countHome++] = new Loc(r, c);
				else if(v == 2)chickens[countChick++] = new Loc(r, c);
			}
		}

		combArr = new int[countChick];
		for(int i = 0; i < m; i++) combArr[countChick - 1 - i] = 1;
		
		do {
			answer = Math.min(answer, getTotDist());
		} while(npn());
		
		System.out.println(answer);
	}

	private static int getTotDist() {
		int totDist = 0;
		for(int i = 0; i < countHome; i++) {
			Loc home = homes[i];
			int dist = Integer.MAX_VALUE;
			for(int j = 0; j < countChick; j++) {
				if(combArr[j] == 0) continue;
				Loc chic = chickens[j];
				dist = Math.min(home.getDist(chic), dist);
			}
			totDist += dist;
		}
		return totDist;
		
	}

	private static boolean npn() {
		int i = countChick -1;
		while(i > 0 && combArr[i-1] >= combArr[i]) i--;
		if(i == 0) return false;

		int j = countChick -1;
		while(combArr[i-1] >= combArr[j]) j--;
		swap(i-1, j);

		int k = countChick -1;
		while(i <= k) swap(i++, k--);
		return true;
	}

	private static void swap(int i, int j) {
		int temp = combArr[i];
		combArr[i] = combArr[j];
		combArr[j] = temp;
	}

}