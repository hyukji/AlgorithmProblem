import java.io.*;
import java.util.*;

public class Solution  {

	static List<Integer>[] graph ;
	static boolean[] visited;
	static int m, s;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		for(int tc = 1; tc < 11; tc++) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			
			visited = new boolean[101];
			graph = new List[101];
			for(int i = 1; i < 101; i++)  graph[i] = new ArrayList<Integer>();
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < m/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph[from].add(to);
			}

			int answer = bfs();
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}

	private static int bfs() {
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		dq.offer(s);
		
		int M = 0;
		while(!dq.isEmpty()) {
			M = 0;
			int size = dq.size();
			for(int i =0; i < size; i++) {
				int v = dq.poll();
				M = Math.max(M, v);
				
				for(int nv : graph[v]) {
					if(visited[nv]) continue;
					visited[nv] = true;
					dq.offer(nv);
				}
			}
		}
		
		return M;
	}

}