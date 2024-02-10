import java.io.*;
import java.util.*;

public class Solution  {
	
	static final int[] dr = {1, 0, -1, 0};
	static final int[] dc = {0, 1, 0, -1};
	
	static int n, totCnt, maxConnected, maxLines;
	static int[][] cores;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			cores = new int[12][2];
			maxConnected = 0;
			maxLines = 0;
			totCnt = 0;
			
			n = Integer.parseInt(br.readLine());
			
			int[][] graph = new int[n][n];
			for(int  r =0; r <n; r++) {
				st = new StringTokenizer(br.readLine());
				for(int  c =0; c <n; c++) {
					graph[r][c] = Integer.parseInt(st.nextToken());
					if(graph[r][c] == 1) cores[totCnt++] = new int[] {r, c};
				}
			}

			dfs(0, 0, 0, graph);
			
			sb.append("#").append(tc).append(" ").append(maxLines).append(" ").append("\n");
		}
		
		System.out.print(sb);
	}

	private static void dfs(int cnt, int connected, int lines, int[][] graph) {
		if(totCnt - cnt + 1 < maxConnected - connected) return;
		
		if(cnt == totCnt) {
			if(connected > maxConnected || (connected == maxConnected && lines < maxLines)) {
				maxConnected = connected;
				maxLines = lines;
			}
			return;
		}

		int r = cores[cnt][0];
		int c = cores[cnt][1];
		if(r == 0 || c == 0 || r == n-1 || c == n-1) {
			dfs(cnt+1, connected+1, lines, graph);
			return;
		}
		
		for(int d = 0; d < 4; d++) {
			int[][] nGraph = copy(graph);
			int plusLines = check(r, c, d, nGraph);
			if(plusLines != 0)  dfs(cnt+1, connected+1, lines + plusLines, nGraph);
		}

		dfs(cnt+1, connected, lines, graph);
	}

	private static int[][] copy(int[][] graph) {
		int[][] nGraph = new int[n][n];
		for(int r = 0; r <n; r++) {
			for(int c = 0; c <n; c++) {
				nGraph[r][c] = graph[r][c];
			}
		}
		return nGraph;
	}

	private static int check(int r, int c, int d, int[][] graph) {
		int plusLines = 0;
		while(true) {
			r += dr[d]; c += dc[d];
			if(r < 0 || r >= n || c < 0 || c >= n) return plusLines;
			if(graph[r][c] != 0) return 0;
			
			graph[r][c] = 2;
			plusLines++;
		}
	}
	

}