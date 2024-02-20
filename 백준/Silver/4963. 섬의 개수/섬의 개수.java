import java.io.*;
import java.util.*;

public class Main {

	static int[][] graph;
	static boolean[][] visited;
	static int n, m;

	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0, 1,-1, 1, -1, 0 ,1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			
			if(m == 0 && n == 0) return;
	
			graph = new int[n][m];
			visited = new boolean[n][m];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int count = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if(graph[i][j] == 1 && !visited[i][j]) {
						dfs(i, j);
						count++;
					}
				}
			}
			
			System.out.println(count);
		}
	}

	private static void dfs(int r, int c) {
		for (int d = 0; d < 8; d++) {
			int nr = r + dr[d]; 
			int nc = c + dc[d];
			if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
			if (visited[nr][nc] || graph[nr][nc] == 0) continue;

			visited[nr][nc] = true;
			dfs(nr, nc);
		}
	}

}