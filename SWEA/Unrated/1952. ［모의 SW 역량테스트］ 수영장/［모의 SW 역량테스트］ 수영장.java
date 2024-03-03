import java.io.*;
import java.util.*;

public class Solution  {
	
	static int n;
	static int answer;
	
	static int[] graph, costs, category;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			category = new int[4];
			costs = new int[12];
			graph = new int[12];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 4; i++) 
				category[i] = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 12; i++) {
				int v = Integer.parseInt(st.nextToken());
				graph[i] = v;
				costs[i] = Math.min(category[0] * v, category[1]);
			}
			
			int[] dp = new int[12];
			int nv = 0;
			dp[2] = Math.min(costs[0] + costs[1] + costs[2], category[2]);
			
			nv = Math.min(costs[1] + costs[2] + costs[3], category[2]);
			dp[3] = Math.min(nv + costs[0], dp[2] + costs[3]);
			
			nv = Math.min(costs[2] + costs[3] + costs[4], category[2]);
			dp[4] = Math.min(nv + costs[0] + costs[1], dp[3] + costs[4]);
			
			for(int i = 5; i < 12; i++) {
				nv = Math.min(costs[i-2] + costs[i-1] + costs[i], category[2]);
				dp[i] = Math.min(dp[i-3] + nv, dp[i-1] + costs[i]);
			}

			answer = Math.min(category[3], dp[11]);
			
			sb.append("#").append(tc).append(" ").append(answer).append(" ").append("\n");
		}
		
		System.out.print(sb);
	}

}