import java.io.*;
import java.util.*;

public class Solution {
	
	static final int[] dr = {-1, 0, 1, 0};
	static final int[] dc = {0, 1, 0, -1};
	static final int[][] directions = {{}, {0, 1, 2, 3}, {0, 2}, {1, 3}, {0, 1}, {1, 2}, {2, 3}, {3, 0}};
	
	static int n, m;
	static int[][] graph;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			n =  Integer.parseInt(st.nextToken());
			m=  Integer.parseInt(st.nextToken());
			int sr =  Integer.parseInt(st.nextToken());
			int sc =  Integer.parseInt(st.nextToken());
			int l =  Integer.parseInt(st.nextToken());
			
			graph = new int[n][m];
			visited = new boolean[n][m];

			for(int r = 0; r < n; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < m; c++) {
					graph[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			sb.append("#").append(tc).append(" ").append(bfs(sr, sc, l)).append("\n");
		}
		
		System.out.print(sb);
	}

	private static int bfs(int sr, int sc, int l) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		int cnt = 1;
		for(int t = 0; t < l-1; t++) {	
			int size = dq.size();
			if(size == 0) break;
			for(int i = 0; i < size; i++) {
				int[] cur = dq.poll();
				int r = cur[0]; 
				int c = cur[1];
				int type = graph[r][c];
				
				for(int d : directions[type]) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue; // 갈수 없는 범위인 경우 
					if(visited[nr][nc]) continue;
					
					int nType = graph[nr][nc];
					if(!hasDirection(nType, d)) continue; 

					visited[nr][nc] = true;
					cnt++;
					dq.offer(new int[] {nr, nc});
				}
			}
		}
		
		return cnt;
	}

	private static boolean hasDirection(int nType, int d) {
		d = (d+2) % 4;
		for(int nd : directions[nType]) {
			if(nd == d) return true;
		}
		return false;
	}	

}