import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

	static int n, m;
	static int[][] graph;
	static int[][] sumGraph;
	static StringBuilder sb  =  new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graph = new int[n][n ];
		for(int r = 0; r < n ;r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c = 0; c < n ; c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		sumGraph = new int[n+1][n+1];
		for(int i = 1; i < n+1; i++) {
			int[] row = new int[n+1];
			for(int c = 1;  c <n+1; c++) {
				row[c] = row[c-1] + graph[i-1][c-1];
				sumGraph[i][c] = sumGraph[i-1][c] +row[c];
			}
		}

		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y1 = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			System.out.println(sumGraph[y2][x2] - sumGraph[y2][x1-1] - sumGraph[y1-1][x2] + sumGraph[y1-1][x1-1]);
		}
	}
	
}