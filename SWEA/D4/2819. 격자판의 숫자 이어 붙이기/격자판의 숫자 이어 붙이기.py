import java.io.*;
import java.util.*;

public class Solution  {

	static int[][] graph ;
	static Set<String>[][] visited;
	static Set<String> answer ;

	static final int n = 4;
	private static final int[] dr = {-1, 1, 0 ,0};
	private static final int[] dc = {0 ,0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			answer = new HashSet<>();
			visited = new HashSet[n][n];
			graph = new int[n][n];
			for(int r = 0; r < n; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < n; c++) {
					graph[r][c] = Integer.parseInt(st.nextToken());
					visited[r][c] = new HashSet<>();
				}
			}

			for(int r = 0; r < n; r++) {
				for(int c = 0; c < n; c++) {
					dfs(r, c, 1, "" + graph[r][c]);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer.size()).append("\n");
		}
		
		System.out.println(sb);

	}

	private static void dfs(int r, int c, int cnt, String result) {
		if(cnt == 7) {
			answer.add(result);
			return;
		}
		
		for(int d = 0;d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr < 0 || nr >= n || nc < 0|| nc >= n) continue;
			
			String nResult = result + graph[nr][nc];
			if(visited[nr][nc].contains(nResult)) continue;
			visited[nr][nc].add(nResult);
			dfs(nr, nc, cnt+1, result + graph[nr][nc]);
		}
		
	}

	
}