import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static int[][] graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n =  Integer.parseInt(br.readLine());
		graph = new int[n][9];
		for(int i = 0; i < n; i++) { 
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) { 
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
	
		int[] perm = {1,2,3,4,5,6,7,8};
		
		int answer = 0;
		do {
			int[] nums = new int[9];
			for(int i = 0, j = 0; i < 9; i++, j++) {
				if(i == 3) j--;
				else nums[i] = perm[j];
			}
			answer = Math.max(run(nums), answer);
		} while(npn(perm, perm.length));
		
		System.out.println(answer);
	}

	private static int run(int[] nums) {
		int idx = 0, score = 0;
		for(int inn = 0; inn < n; inn++) {
			int[] bases = new int[3];
			int out = 0;
			while(out < 3) {
				int hit = graph[inn][nums[idx]];
				if(hit == 0) out++;
				else score += move(hit, bases);
				
				idx = (idx+1) % 9;
			}
		}
		
		return score;
	}

	private static int move(int hit, int[] bases) {
		int score = 0;
		for(int h = 0; h <hit; h++) {
			score += bases[2];
			bases[2] = bases[1];
			bases[1] = bases[0];
			bases[0] = 0;
		}
		if(hit == 4) score++;
		else bases[hit-1] = 1;
		
		return score;
	}

	private static boolean npn(int[] nums, int n) {
		int i = n-1;
		while(i > 0 && nums[i-1] > nums[i]) i--;
		if(i == 0) return false;

		int j = n-1;
		while(nums[j] < nums[i-1]) j--;
		swap(j, i-1, nums);

		int k = n-1;
		while(i < k) swap(i++, k--, nums);
		return true;
	}

	private static void swap(int j, int i, int[] nums) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
}