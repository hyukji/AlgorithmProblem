import java.io.*;
import java.util.*;

public class Solution  {
	
	static final int[] dr = {1, 1, -1, -1};
	static final int[] dc = {-1, 1, 1, -1};
	
	static int n;
	static int answer;
	static int[][] graph;
	static boolean[] eaten;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			answer = -1;
			n = Integer.parseInt(br.readLine());
			
			graph = new int[n][n];
			eaten = new boolean[101];
			
			for(int  r =0; r <n; r++) {
				st = new StringTokenizer(br.readLine());
				for(int  c =0; c <n; c++) {
					graph[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			for(int r =0; r < n-1; r++) {
				for(int c =1; c < n-1; c++) {
					dfs(r, c, 0, new int[4], new boolean[101]);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append(" ").append("\n");
		}
		
		System.out.print(sb);
	}
	
	private static void dfs(int r, int c, int d, int[] moves, boolean[] eaten) {
		if(d == 4) {
			int depth = (moves[2] + moves[3]) * 2;
			if(answer < depth) answer = depth;
			return;
		}
		
		if(d > 1) {
			for(int i = 0; i < moves[d]; i++) {
				r += dr[2]; c += dc[d];
				if(r < 0 || r >= n || c < 0 || c >= n) return;

				int dessert = graph[r][c];
				if(eaten[dessert]) return;
				eaten[dessert] = true;
			}
            
			dfs(r, c, d+1, moves, Arrays.copyOf(eaten, 101));
			
		} else {
			int nr = r += dr[d];
			int nc = c += dc[d];
			
			while(nr >= 0 && nr < n && nc >= 0 && nc < n) {
				int dessert = graph[nr][nc];
				if(eaten[dessert]) return;
				eaten[dessert] = true;
				
				moves[d+2]++; 
				dfs(nr, nc, d+1, Arrays.copyOf(moves, 4), Arrays.copyOf(eaten, 101));
				
				nr += dr[d]; nc += dc[d];
			}
		}
	}
	

}