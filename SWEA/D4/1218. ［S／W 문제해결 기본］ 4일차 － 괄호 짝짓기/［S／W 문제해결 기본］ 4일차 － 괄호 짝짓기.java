import java.io.*;
import java.util.*;

public class Solution {

	static int n;
	static final char[] left = {'(', '<', '[', '{'};
	static final char[] right = {')', '>', ']', '}'};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for(int tc = 1; tc<=10; tc++) {
			n = Integer.parseInt(br.readLine());
			String str = br.readLine();
			sb.append("#").append(tc).append(" ").append(solve(str)).append("\n");
		}
		System.out.print(sb);
	}

	private static int solve(String str) {
		Deque<Integer> stack = new ArrayDeque<>();
		for(int i = 0; i < n; i++) {
			char a = str.charAt(i);
			int l = indexLeft(a);
			if(l != -1) {
				stack.push(l) ;
				continue;
			}
			
			int r = indexRight(a);
			if(r == -1) break; // 에러인데
			if(stack.isEmpty() || stack.pop() != r) return 0;
		}
		
		if(!stack.isEmpty()) return 0;
		return 1;
	}

	private static int indexRight(char a) { 
		for(int i = 0; i < 4; i++) {
			if(a == right[i]) return i;
		}
		return -1;
	}

	private static int indexLeft(char a) {
		for(int i = 0; i < 4; i++) {
			if(a == left[i]) return i;
		}
		return -1;
	}
	
	

}