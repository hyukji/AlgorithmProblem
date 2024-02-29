import java.io.*;
import java.util.*;

public class Main {

	static int n, answer = Integer.MAX_VALUE;
	static int[][] graph;
	static boolean[] visited;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		n = Integer.parseInt(br.readLine());

		graph = new int[n][n];
		visited = new boolean[n];
		for(int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < n; c++)  {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		visited[0] = true;
		dfs(0, 0, 0);
		System.out.println(answer);
	}

	private static void dfs(int idx, int cnt, int v) {
		if(cnt == n-1) {
			if(graph[idx][0] != 0)
				answer = Math.min(answer, v + graph[idx][0]);
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(visited[i] || graph[idx][i] == 0) continue;
			int nv = v +graph[idx][i];
			
			visited[i] = true;
			dfs(i, cnt + 1, nv);
			visited[i] = false;
		}
	}

}