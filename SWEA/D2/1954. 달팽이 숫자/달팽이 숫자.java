import java.io.*;
import java.util.*;

public class Solution {
	
	static int[][] graph; 
	static int n;
	
	// 오아왼위
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			n = Integer.parseInt(br.readLine());
			graph = new int[n][n];
			
			fillClockWise();
			
			sb.append("#").append(tc).append("\n");
			for(int[] row : graph) {
				for(int el : row) {
					sb.append(el).append(" ");
				}
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}

	private static void fillClockWise() {
		int r =0, c =0, d =0;
		int cnt = 1;
		while(true) {
			graph[r][c] = cnt++;
			if(cnt > n*n) return;
			
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr < 0 || nr >= n || nc < 0 || nc >= n || graph[nr][nc] != 0) {
				d = (++d) % 4;	
			}
			
			r += dr[d]; c += dc[d];
		}
	}

}