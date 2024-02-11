import java.io.*;
import java.util.*;

public class Main {
	
	static final int[] dc = {0, 1, 0, -1};
	static final int[] dr = {-1, 0, 1, 0};
	
	static int[][] graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		
		graph = new int[n][m];
		
		int airCleaner = 0;
		for(int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < m; c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
				if(graph[r][c] == -1)  airCleaner = r;
			}
		}

		for(int i = 0; i < t; i++) {
			diffusion(n, m);
			cleaning(n, m, airCleaner);
		}
		
		int answer = 0;
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < m; c++) {
				if(graph[r][c] > 0) answer += graph[r][c];
			}
		}
		
		System.out.println(answer);

	}

	private static void cleaning(int n, int m, int airCleaner) {
		// 위쪽 반시계 
		int[] dr = new int[] {-1, 0, 1, 0};
		int[] dc = new int[] {0, 1, 0, -1};
		
		int r = airCleaner-2, c = 0, d = 0;
		while(d < 4) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr < 0 || nr >= airCleaner || nc < 0 || nc >= m) {
				d++;
				continue;
			}
			
			graph[r][c] = graph[nr][nc];
			r = nr; c = nc;
		}

		graph[airCleaner-1][0] = -1;
		graph[airCleaner-1][1] = 0;
		
		
		// 아래쪽 시계 
		dr = new int[] {1, 0, -1, 0};
		dc = new int[] {0, 1, 0, -1};
		
		r = airCleaner+1; c = 0; d = 0;
		while(d < 4) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr < airCleaner || nr >= n || nc < 0 || nc >= m) {
				d++;
				continue;
			}
			
			graph[r][c] = graph[nr][nc];
			r = nr; c = nc;
		}
		

		graph[airCleaner][0] = -1;
		graph[airCleaner][1] = 0;

	}

	private static void diffusion(int n, int m) {
		int[][] nGraph = new int[n][m];

		for(int r = 0; r < n; r++) {
			for(int c = 0; c < m; c++) {
				if(graph[r][c] == 0) continue;
				if(graph[r][c] == -1) {
					nGraph[r][c] = -1;
					continue;
				}
				
				int diffAmount = 0;
				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
					if(graph[nr][nc] == -1) continue;
					
					nGraph[nr][nc] += graph[r][c] / 5;
					diffAmount += graph[r][c] / 5;
				}
				
				nGraph[r][c] += graph[r][c] - diffAmount;
				
			}
		}
		
		graph = nGraph;
	}

}