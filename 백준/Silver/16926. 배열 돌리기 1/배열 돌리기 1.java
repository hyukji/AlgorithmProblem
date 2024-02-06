import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m;
	static int[][] graph;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		graph = new int[n][m];
		for(int r =0; r < n; r++) {  
			st = new StringTokenizer(br.readLine());
			for(int c =0; c < m; c++) {
				graph[r][c] =  Integer.parseInt(st.nextToken());
			}
		}
		
		int min = (m < n) ? m : n;
		for(int i = 0; i < R; i++) rotate(min/2);

		for(int[] row : graph)  {
			for(int el : row) sb.append(el).append(" ");
			sb.append("\n");
		}
		
		System.out.print(sb);
	}

	private static void rotate(int s) {
		for(int i = 0; i < s; i++) {
			int d = 0;
			int temp = graph[i][i];
			int r = i, c = i;
			while(d < 4) {
				int nr = r + dr[d];
				int nc = c +dc[d];
				if(nr < i || nr >= n-i || nc < i || nc >= m-i) {
					d++;
					continue;
				}

				graph[r][c] = graph[nr][nc];
				r= nr; c = nc;
			}
			graph[i+1][i] = temp;       
		}

	}

}