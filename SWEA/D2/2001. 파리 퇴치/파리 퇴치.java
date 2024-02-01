import java.io.*;
import java.util.*;

public class Solution {

	static int[][] graph, dp; 
	static int n, m;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			graph = new int[n][n];
			dp = new int[n+1][n+1];
			
			for(int r = 0; r<n; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c =0; c < n; c++) {
					graph[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int r = 1; r<n+1; r++) {
				for(int c =1; c < n+1; c++) {
					dp[r][c] = dp[r-1][c] + dp[r][c-1] - dp[r-1][c-1] + graph[r-1][c-1];
				}
			}
			
			int answer = 0;
			for(int r = m; r < n+1; r++) {
				for(int c = m; c < n+1; c++) {
					int value = dp[r][c] - dp[r-m][c] -  dp[r][c-m] + dp[r-m][c-m];
					if(value > answer) answer = value;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

}