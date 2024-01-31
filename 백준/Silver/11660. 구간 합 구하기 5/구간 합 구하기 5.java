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

		sumGraph = new int[n+1][n+1]; // [y][x] 좌표에 00~ yx까지 전부 더함
		for(int r = 1; r < n+1; r++) {
			for(int c = 1;  c <n+1; c++) {
				sumGraph[r][c] = graph[r-1][c-1] + sumGraph[r-1][c] + sumGraph[r][c-1] - sumGraph[r-1][c-1];
			}
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y1 = Integer.parseInt(st.nextToken());
			int x1 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			sb.append(sumGraph[y2][x2] - sumGraph[y2][x1-1] - sumGraph[y1-1][x2] + sumGraph[y1-1][x1-1]).append("\n");
		}
		
		System.out.print(sb);
	}
	
}