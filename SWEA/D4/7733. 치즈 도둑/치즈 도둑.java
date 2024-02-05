import java.io.*;
import java.util.*;

public class Solution {
	
	static int maxH = Integer.MIN_VALUE, minH = Integer.MAX_VALUE, n;
	static int[][] graph, visited;

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			n = Integer.parseInt(br.readLine());
			graph = new int[n][n];
			for(int r =0; r < n; r++) { 
				st = new StringTokenizer(br.readLine());
				for(int c =0; c < n; c++) {
					int v= Integer.parseInt(st.nextToken());
					graph[r][c] = v;
					if(maxH < v) maxH = v;
					if(minH > v) minH = v;
				}
			}
			
			int answer = 1;
			for(int rain = minH; rain < maxH; rain++) {
				visited = new int[n][n];
				int cnt = 0;
				for(int r =0; r < n; r++) { 
					for(int c =0; c < n; c++) {
						if(visited[r][c] == 1) continue;
						visited[r][c] = 1;
						if(graph[r][c] > rain) {
							cnt++;
							dfs(r, c, rain);
//							System.out.println("rain " + rain + ",cnt "+ cnt + " " + r + " " + c);
						}
					}
				}
				if(answer < cnt) answer = cnt;
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.print (sb);
	}

	private static void dfs(int r, int c, int rain) {
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d]; 
			int nc = c + dc[d]; 
			if(nr <0 || nr >= n || nc < 0 || nc >= n) continue;
			if(visited[nr][nc] == 1) continue;
			visited[nr][nc] = 1;
			if(graph[nr][nc] > rain) dfs(nr, nc, rain);
		}
	}

}