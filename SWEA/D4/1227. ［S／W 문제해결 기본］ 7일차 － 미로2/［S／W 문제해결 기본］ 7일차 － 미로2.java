import java.io.*;
import java.util.*;

public class Solution  {

	static String[] graph = new String[100];
	static boolean[][] visited = new boolean[100][100];
	static int answer = 0;

	static int[] dr = {1, 0, 0, -1};
	static int[] dc = {0, 1, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		
		for(int tc = 1; tc < 11; tc++) {
			br.readLine();
			for(int r =0; r < 100; r++) { 
				graph[r] = br.readLine();
			}
			
			int sr =1, sc = 1;
			if(graph[1].charAt(1) != '2') {
				int[] start = findStart();
				sr = start[0]; sc = start[1];
			}
			
			answer = 0;
			visited = new boolean[100][100];
			dfs(sr, sc);
			
			sb.append("#").append(tc).append(" ").append(answer).append(" ").append("\n");
		}
		
		System.out.print (sb);
	}

	private static void dfs(int r, int c) {
        if(answer == 1) return;
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(visited[nr][nc]) continue;
//			if(nr < 0 || nr >= 100 || nc < 0 || nc >= 100) continue;
			
			if(graph[nr].charAt(nc) == '0')  {
				visited[nr][nc] = true;
				dfs(nr, nc);
			}
			else if(graph[nr].charAt(nc) == '3') {
				answer = 1;
				return;
			}
		}
	}
	
	private static int[] findStart() {
		for(int r =0; r < 100; r++) { 
			for(int c = 0; c <100; c++) {
				if(graph[r].charAt(c) == '2') return new int[] {r, c};
			}
		}
		return new int[] {0, 0};
	}

}