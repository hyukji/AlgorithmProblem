import java.io.*;
import java.util.*;

public class Solution {

	static int n, m, home, answer;
	static int[][] graph;
	
	static int[] dr = {0,0,-1,1};
	static int[] dc = {-1,1,0,0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			answer = 1;
			home = 0;
			
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			graph = new int[n][n];
			
			for(int r = 0; r<n; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c =0; c < n; c++) {
					graph[r][c] = Integer.parseInt(st.nextToken());
					if(graph[r][c] == 1) home++;
				}
			}
			
			for(int r = 0; r<n; r++) {
				for(int c =0; c < n; c++) {
					bfs(r, c);
					if(answer == home) {
						r= n; c = n;
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
		
	}

	private static void bfs(int r, int c) {
		boolean[][] visited = new boolean[n][n];
		int k = 1, cnt = 0;
		Queue<Integer[]> q = new ArrayDeque<>();
		
		visited[r][c] = true;
		if(graph[r][c] == 1) cnt++;
		q.add(new Integer[] {r, c});
		
		while(!q.isEmpty()) {
			k++;
			int price = k * k + (k-1)  * (k-1);
			if(price > home * m) break;
			
			int size = q.size();
			for(int i = 0; i < size; i++) {
				Integer[] loc = q.poll();
				r = loc[0]; c = loc[1];
				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d]; int nc = c + dc[d];
					if(nr < 0 || nr >= n || nc <0 || nc >= n || visited[nr][nc]) continue;

					visited[nr][nc] = true;
					if(graph[nr][nc] == 1) cnt++;
					
					q.offer(new Integer[] {nr, nc});
				}
			}
			
			if(price <= cnt * m && answer < cnt) {
				answer = cnt;
			}
		}
	}
	
}