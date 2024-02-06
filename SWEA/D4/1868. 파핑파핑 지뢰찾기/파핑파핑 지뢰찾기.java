import java.io.*;
import java.util.*;

public class Solution {

	static int n, answer = 0;
	static int[][] graph;

	static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			answer = 0;
			n = Integer.parseInt(br.readLine());
			graph = new int[n][n]; // 0 : 안전한 공간, 1 :지뢰가 있는 공간, 2 : 0클릭을 통해 자동으로 열린 공간
			
			
			for(int r =0; r < n; r++) { 
				String row = br.readLine();
				for(int c =0; c < n; c++) {
					if(row.charAt(c) == '*') graph[r][c]++; // 지뢰인 칸은 1로
					else answer++;  // 지뢰가 아닌 칸 개수 계산
				}
			}

			for(int r =0; r < n; r++) { 
				for(int c =0; c < n; c++) {
					if(graph[r][c] == 0 && isZero(r, c)) {
						graph[r][c] = 2;
						dfs(r, c);
					};
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.print (sb);
	}

	private static void print() {
		for(int[] row : graph) System.out.println(Arrays.toString(row));
		System.out.println(answer);
		System.out.println();
	}

	private static boolean isZero(int r, int c) {
		for(int d = 0; d < 8; d++) {
			int nr = r + dr[d]; 
			int nc = c + dc[d]; 
			if(nr <0 || nr >= n || nc < 0 || nc >= n ) continue;
			
			if(graph[nr][nc] == 1) return false; // 근처에 지뢰가 있다면 false
		}
		return true;
	}

	private static void dfs(int r, int c) {
		for(int d = 0; d < 8; d++) {
			int nr = r + dr[d]; 
			int nc = c + dc[d]; 
			if(nr <0 || nr >= n || nc < 0 || nc >= n ) continue;
			
			if(graph[nr][nc] == 0 ) {
				answer--;
				graph[nr][nc] = 2;
				if(isZero(nr, nc)) dfs(nr, nc);
			}
			
		}
	}

}