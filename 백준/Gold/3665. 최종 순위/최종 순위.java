import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static int[] ranks;
	static boolean[][] graph;
	static boolean[] visited;
	static int[] upperCnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc< T; tc++) {
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			upperCnt = new int[n];
			ranks = new int[n];
			visited = new boolean[n];
			graph = new boolean[n][n];
			for(int i = 0; i < n; i++) {
				ranks[i] = Integer.parseInt(st.nextToken()) - 1;
			}
			
			for(int i = n-1; i > 0; i--) {
				int rank = ranks[i];
				for(int j = i-1; j >= 0; j--) {
					graph[rank][ranks[j]] = true;
					upperCnt[rank]++;
				}
			}
			
			int m = Integer.parseInt(br.readLine());
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				if(graph[a][b]) {
					upperCnt[a]--;
					upperCnt[b]++;
				} else {
					upperCnt[a]++;
					upperCnt[b]--;
				}
				graph[a][b] = !graph[a][b];
				graph[b][a] = !graph[b][a];
			}
			
			System.out.println(check()); 
//			for(boolean[] row : graph) System.out.println(Arrays.toString(row));

		}
	}

	private static String check() {
		StringBuilder sb = new StringBuilder();
		
		int rank = -1;
		for(int i = 0; i < n; i++) {
			if(upperCnt[i] == 0) {
				if(rank != -1) return "IMPOSSIBLE";
				rank = i;
			}
		}

		for(int cnt = 0; cnt < n; cnt++) {
			if(rank == -1) return "IMPOSSIBLE";
			visited[rank] = true;
			int nRank = -1;
			for(int i = 0; i < n; i++) {
				if(visited[i]) continue; // 출력했다며
				if(graph[i][rank]) {
					graph[i][rank] = false;
						if(--upperCnt[i] == 0) {
							if(nRank != -1) return "IMPOSSIBLE";
							nRank = i;
					}
				}
			}

			if(rank == -1) return "IMPOSSIBLE";
			sb.append(rank+1).append(" ");
			rank = nRank;
		}
		
		return sb.toString();
	}
	
}