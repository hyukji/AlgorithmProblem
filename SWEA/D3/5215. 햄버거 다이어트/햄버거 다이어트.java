import java.io.*;
import java.util.*;

public class Solution  {

	static int[][] graph;
	static boolean[] visited ;
	static int n, l, maxW, answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			maxW = 0;  answer = 0;
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			graph = new int[n][2];
			visited = new boolean[n];
			
			for(int  i =0; i <n; i++) {
				st = new StringTokenizer(br.readLine());
				graph[i][0] = Integer.parseInt(st.nextToken());
				graph[i][1] =Integer.parseInt(st.nextToken());
			}
			
			dfs(0, 0, 0);
			
			sb.append("#").append(tc).append(" ").append(answer).append(" ").append("\n");
		}
		
		System.out.print (sb);
	}

	private static void dfs(int bef, int score, int w) {
		if(w > l) return;
		if(answer < score)  answer = score;
		
		for(int  i = bef; i < n; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			dfs(i+1, score+ graph[i][0], w + graph[i][1]);
			visited[i] = false;
		}
		
	}

}