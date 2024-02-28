import java.io.*;
import java.util.*;

public class Main {

	static final int[] dr = {0, 1, 1};
	static final int[] dc = {1, 0, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] graph = new int[n][m];
		
		st = new StringTokenizer(br.readLine());
		graph[0][0] = Integer.parseInt(st.nextToken());
		for(int c = 1; c < m; c++)  {
			graph[0][c] = graph[0][c-1] + Integer.parseInt(st.nextToken());
		}
		
		for(int r = 1; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			graph[r][0] = graph[r-1][0] + Integer.parseInt(st.nextToken());
			for(int c = 1; c < m; c++) {
				int  v = Integer.parseInt(st.nextToken());
				graph[r][c] = Math.max(graph[r-1][c], graph[r][c-1]) + v;
			}
		}
		
		System.out.println(graph[n-1][m-1]);

	}

}