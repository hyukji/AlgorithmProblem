import java.io.*;
import java.util.*;

public class Solution {
	
	static int[][] graph;
	static int[] weights, costs;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc < T+1; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			graph= new int[n][k+1];
			weights = new int[n];
			costs = new int[n];
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				weights[i] = Integer.parseInt(st.nextToken());
				costs[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = weights[0]; i < k+1; i++) graph[0][i] = costs[0];
			for(int i = 1; i < n; i++) {
				int w = weights[i];
				int c = costs[i];
				for(int j = 0; j < w; j++) graph[i][j] = graph[i-1][j];
				for(int j = w; j < k+1; j++) {
					graph[i][j] = Math.max(graph[i-1][j], graph[i-1][j-w] + c); 
				}
			}
			
//			for(int[] row :graph) System.out.println(Arrays.toString(row));
			sb.append("#").append(tc).append(" ").append(graph[n-1][k]).append("\n");
			
		}
		System.out.print(sb);
	}
	
}