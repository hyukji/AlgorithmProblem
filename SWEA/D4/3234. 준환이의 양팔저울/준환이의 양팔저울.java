import java.io.*;
import java.util.*;

public class Solution  {

	static int[] graph ;
	static boolean[] visited;
	static int n, tot, answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			answer = 0;
			tot = 0;
			n = Integer.parseInt(br.readLine());
			
			graph= new int[n];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				graph[i] = Integer.parseInt(st.nextToken());
				tot+= graph[i];
			}
			Arrays.sort(graph);

			for(int i = 0; i < n; i++) {
				visited = new boolean[n];
				visited[i] = true;
				dfs(graph[i], 0, 1);
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);

	}

	private static void dfs(int left, int right, int cnt) {
		if(cnt == n) {
			answer++;
			return;
		}
		
		if(left > tot/2) {
			int fac = 1;
			for(int i = 1 ; i <= n-cnt; i++) fac *=i;
			answer+= fac * Math.pow(2, (n-cnt));
			
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			int w = graph[i];
			dfs(left + w, right, cnt+1);
			if(left >= right + w) dfs(left, right + w, cnt+1);
			visited[i] = false;
		}
	}

}