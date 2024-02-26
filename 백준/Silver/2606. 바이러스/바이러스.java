import java.io.*;
import java.util.*;

public class Main {

	static StringTokenizer st;
	static int[][] graph;
	static int[] visited;
	static int n;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		graph = new int[n+1][n+1];
		visited = new int[n+1];
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from][to] = 1;
			graph[to][from] = 1;
		}
		
		
		dfs(1);
		
		System.out.println(answer);
	}

	public static void dfs(int from) {
		visited[from] = 1;
		for(int to = 1; to < n+1; to++) {
			int e = graph[from][to];
			if(e == 0 || visited[to] == 1) continue;
			
			visited[to] = 1;
			answer++;
			dfs(to);
		}
	}
	
}
