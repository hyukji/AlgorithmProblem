import java.io.*;
import java.util.*;

public class Solution  {
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int[][] graph, maxLocs, visited;
	static int n, maxCnt, answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			answer = 0;
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			graph = new int[n][n];
			maxCnt = 0;
			maxLocs = new int[5][2];
			int maxHeight = 0;
			for(int r = 0; r < n; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < n; c++) {
					int h = Integer.parseInt(st.nextToken());
					graph[r][c] = h;
					if(maxHeight < h) {
						maxHeight = h;
						maxCnt = 0;
						maxLocs[maxCnt++] = new int[] {r, c};
					} else if(maxHeight == h) maxLocs[maxCnt++] = new int[] {r, c};
				}
			}
			
			for(int i = 0; i < maxCnt; i++) {
				int[] maxLoc = maxLocs[i]; // maxHeight 좌표 
				for(int r = 0; r < n; r++) {
					for(int c = 0; c < n; c++) {
						if(r == maxLoc[0] && c == maxLoc[1]) continue;
						
						int ori = graph[r][c];
						for(int depth = 1; depth <= k; depth++) {
							graph[r][c]--;
							if(graph[r][c] < 0) break;
							
							dfs(maxLoc[0], maxLoc[1], 1);
							
						}
						graph[r][c] = ori;
						
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append(" ").append("\n");
		}
		
		System.out.print(sb);
	}

	private static void dfs(int r, int c, int depth) { 
		boolean isLeaf = true;
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
			
			if(graph[r][c] <= graph[nr][nc]) continue;
			dfs(nr, nc, depth+1);
			isLeaf = false;
		}
		
		if(isLeaf) answer = Math.max(answer, depth);
	}
	

}