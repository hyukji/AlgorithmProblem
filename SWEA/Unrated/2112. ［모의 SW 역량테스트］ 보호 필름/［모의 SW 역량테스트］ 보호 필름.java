import java.io.*;
import java.util.*;

public class Solution  {
	
	static int[][] graph, nGraph;
	static int d, w, k, answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			answer = k;
			
			graph = new int[d][w];
			nGraph = new int[d][w];
			for(int r = 0; r < d; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < w; c++) {
					graph[r][c] = Integer.parseInt(st.nextToken());
					nGraph[r][c] = graph[r][c];
				}
			}
			
			dfs(0, 0);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}

	private static void dfs(int idx, int cnt) {
		if(cnt >= answer) return;
		if(check()) {
			answer = Math.min(answer, cnt);
			return;
		}
		
		for(int i = idx; i < d; i++) {
			for(int t = 0; t < 2; t++) {
				fill(i, t);
				dfs(i+1, cnt+1);
			}
			backTo(i);
		}
	}

	private static boolean check() {
		for(int c = 0; c < w; c++) {
			int cnt = 1;
			for(int r = 0; r < d-1; r++) {
				if(nGraph[r][c] == nGraph[r+1][c]) {
					if(++cnt >= k) break;
				}
				else cnt = 1;
			}
			if(cnt < k) return false;
		}
		return true;
	}

	private static void backTo(int r) {
		for(int c = 0; c < w; c++) nGraph[r][c] = graph[r][c];
	}

	private static void fill(int r, int type) {
		for(int c = 0; c < w; c++) nGraph[r][c] = type;
	}

}