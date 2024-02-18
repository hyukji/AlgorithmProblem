import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m, answer = 0;
	static int[][] graph;
	static boolean[] visited;
	
	static final int[] dr = {0, 0, -1, 1};
	static final int[] dc = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graph = new int[n][m];
		visited = new boolean[26];
		
		for(int r =0; r < n; r++) {
			String row = br.readLine();
			for(int c =0; c < m; c++) {
				int v = row.charAt(c) - 'A';
				graph[r][c] = v;
			}
		}
		
		dfs(0, 0, 1);
		
		System.out.println(answer);
	}

	private static void dfs(int r, int c, int count) {
		visited[graph[r][c]] = true;
		answer= Math.max(answer, count);
		
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
			if(visited[graph[nr][nc]]) continue;
			
			dfs(nr, nc, count+1);
			visited[graph[nr][nc]] = false;
		}
		
		
	}

}