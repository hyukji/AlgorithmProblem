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
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		int cnt  = 0;

		Queue<int[]> queue = new ArrayDeque<>();
		graph = new int[n][m];
		for(int r =0; r < n; r++) {  
			st = new StringTokenizer(br.readLine());
			for(int c =0; c < m; c++) {
				int v = Integer.parseInt(st.nextToken());
				graph[r][c] =  v;
				if(v == 1) queue.add(new int[] {r, c});
				else if(v==0) cnt++;
			}
		}
		
		int time = -1;
		while(!queue.isEmpty()) {
			time++;
			int size = queue.size();
			for(int i =0; i < size; i++) {
				int[] loc = queue.poll();
				for(int d = 0; d < 4; d++) {
					int nr = loc[0] + dr[d];
					int nc = loc[1] + dc[d];
					if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
						
					if(graph[nr][nc] == 0) {
						cnt--;
						queue.add(new int[] {nr, nc});
						graph[nr][nc]++;
					}
				}
			}
		}
		
		if(cnt != 0) time = -1;
		System.out.print(time);
	}

}