import java.io.*;
import java.util.*;

public class Main {
	
	static final int[] dc = {0, 1, 0, -1};
	static final int[] dr = {1, 0, -1, 0};
	
	static int n, L, R;
	static int[][] graph, opend = new int[2500][2];
	static boolean[][] visited;
	
	static int cnt, amount;
	static boolean isMoved;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		graph = new int[n][n];
		
		for(int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < n; c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		
		
		while(move()) time++;
		
		System.out.println(time);
		
	}
	
	
	private static boolean move() {
		visited = new boolean[n][n];
		isMoved = false;
		 
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < n; c++) {
				if(visited[r][c]) continue;
				
				visited[r][c] = true;
				cnt = 0; amount = graph[r][c];
				opend[cnt++] = new int[] {r, c};
				findGroup(r, c);

				if(cnt == 1) continue;
				
				isMoved = true;
				int abs = amount / cnt;
				for(int i = 0; i < cnt; i++) {
					int nr = opend[i][0], nc = opend[i][1];
					graph[nr][nc] = abs;
				}


			}
			
		}
		
		return isMoved;
		
		
	}


	private static void findGroup(int r, int c) {
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
			if(visited[nr][nc]) continue;
			
			int diff = Math.abs(graph[r][c] - graph[nr][nc]);
			if(diff < L || diff > R) continue;
			
		
			visited[nr][nc] = true;
			amount += graph[nr][nc];
			opend[cnt++] = new int[] {nr, nc};
			findGroup(nr, nc);
		}
		
	}


}