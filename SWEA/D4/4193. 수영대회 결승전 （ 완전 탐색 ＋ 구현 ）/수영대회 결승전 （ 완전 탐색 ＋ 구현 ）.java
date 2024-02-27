import java.io.*;
import java.util.*;

public class Solution {
	
	static final int[] dr = {0, 1, 0, -1};
	static final int[] dc = {1, 0, -1, 0};
	
	static int n;
	static int[][] graph;
	static boolean[][] visited;
	static int[] start;
	static int[] end;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			n = Integer.parseInt(br.readLine());
			graph = new int[n][n];
			visited = new boolean[n][n];

			for(int r = 0; r < n; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < n; c++) {
					graph[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			start = new int[2];
			st = new StringTokenizer(br.readLine());
			start[0] = Integer.parseInt(st.nextToken());
			start[1] = Integer.parseInt(st.nextToken());

			end = new int[2];
			st = new StringTokenizer(br.readLine());
			end[0] = Integer.parseInt(st.nextToken());
			end[1] = Integer.parseInt(st.nextToken());
			
			sb.append("#").append(tc).append(" ").append(solve() - 1).append("\n");
		}
		
		System.out.print(sb);
	}

	private static int solve() {			
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.offer(start);
		visited[start[0]][start[1]] = true;
	
		int time = 0;
		while(!dq.isEmpty()) {
			int size = dq.size();
			time++;
			boolean  isStorm = ((time % 3) != 0);
			for(int i = 0; i < size; i++) {
				int[] cur = dq.poll();
				int r = cur[0]; 
				int c = cur[1];
				
				if(r == end[0] && c ==  end[1]) {
					return time;
				}
				
				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue; // 갈수 없는 범위인 경우 
					if(graph[nr][nc] == 1  || visited[nr][nc]) continue;
					if(graph[nr][nc] == 2 && isStorm)  dq.offer(new int[] {r, c});
					else {
						visited[nr][nc] = true;
						dq.offer(new int[] {nr, nc});
					}
				}
				
			}
		}
		
		return 0;
		
	}

}