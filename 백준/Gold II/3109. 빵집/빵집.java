import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m, answer;
	static int[][] graph;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new int[n][m];
		
		for(int r  = 0; r< n; r++) {
			String row = br.readLine();
			for(int c = 0; c < m; c++) {
				if(row.charAt(c) == 'x') graph[r][c]++;
			}
		}

		for(int r = 0; r < n; r++) dfs(r, 0);
		System.out.println(answer);
	}

	private static boolean dfs(int r, int c) {
		if(c == m-1) {
			answer++;
			return true;
		}

		for(int i = -1; i < 2; i++) {
			int nr = r+i; int nc = c+1;
			if(nr <0 || nr >= n || nc < 0 || nc >= m) continue;
			if(graph[nr][nc] != 0) continue;
			
			graph[nr][nc] = 2;
			if(dfs(nr, nc)) return true;
		}
		
		return false;
	}

}