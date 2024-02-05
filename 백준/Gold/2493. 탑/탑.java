import java.io.*;
import java.util.*;

public class Main {
	
	static int tr, tc, k, time = 0, answer = -1;
	static int[][] graph = new int[101][101];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		
		Deque< int[]> stack = new ArrayDeque<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int bef=0, h = Integer.parseInt(st.nextToken());
			while(stack.size() > 0) {
				int[] last = stack.peek();
				if(last[1] > h) {
					bef = last[0];
					break;
				}
				stack.pop();
			}
			
			sb.append(bef).append(" ");
			stack.push(new int[] {i+1, h});
		}
		
		System.out.print(sb);
	}

}