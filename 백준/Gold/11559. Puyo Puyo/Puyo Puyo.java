import java.io.*;
import java.util.*;

public class Main {

	static final int[] dr = {0, 0, -1, 1};
	static final int[] dc = {-1, 1, 0, 0};
	static final int n = 12, m = 6;
	
	static int answer = 0;
	static int[] heights;
	static char[][] graph = new char[n][m];
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		heights = new int[] {0,0,0,0,0,0};
		for(int r = n-1; r > -1; r--) {
			String row = br.readLine();
			for(int c = 0; c < m; c++)  {
				graph[r][c] = row.charAt(c);
				if(heights[c] == 0 && graph[r][c] != '.') heights[c] = r+1; 
			}
		}
		
		while(true) {
			visited = new boolean[n][m];
			boolean isErase = false;
			for(int c = 0; c < m; c++) {
				for(int r = 0; r < heights[c]; r++) {
					if(bfs(r, c)) isErase = true;
				}
			}

			if(isErase) answer++;
			else break;
			
			for(int c = 0; c < m; c++) {
				int nr = 0;
				for(int r = 0; r < heights[c]; r++) {
					if(graph[r][c] != '.')  graph[nr++][c] = graph[r][c];
				}
				heights[c] = nr;
			}
			
		}
		
		System.out.println(answer);
		
	}

	private static boolean bfs(int r, int c) {
		ArrayDeque<int[]> history = new ArrayDeque<>();
		ArrayDeque<int[]> dq = new ArrayDeque<>();

		history.add(new int[] {r, c});
		dq.add(new int[] {r, c});
		visited[r][c] = true;
		
		int cnt = 1;
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			for(int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if(nc < 0 || nc >= m || nr < 0 || nr >= heights[nc]) continue;
				if(visited[nr][nc] || graph[nr][nc] != graph[r][c]) continue;
				
				cnt++;
				visited[nr][nc] = true;
				dq.add(new int[] {nr, nc});
				history.add(new int[] {nr, nc});
			}
		}
		
		if(cnt >3) {
			while(!history.isEmpty()) {
				int[] cur = history.poll();
				graph[cur[0]][cur[1]] = '.';
			}
			return true;
		}
		
		return false;
	}
	
}