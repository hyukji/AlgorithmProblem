import java.io.*;
import java.util.*;

public class Main {

	static List<Integer>[] graph;
	static boolean[] visited;
	
	static int n, m;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new List[n];
		for(int i = 0; i < n; i++) graph[i] = new ArrayList<Integer>();
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		int answer = 0;
		for(int i = 0; i < n; i++) {
			visited = new boolean[n];
			visited[i] = true;
			if(dfs(i, 1))  {
				answer++;
				break;
			}
		}
		System.out.println(answer);	
	}

	private static boolean dfs(int i, int cnt) {
		if(cnt == 5) return true;
		
		for(int nxt : graph[i]) {
			if(visited[nxt]) continue;
			visited[nxt] = true;
			if(dfs(nxt, cnt+1)) return true;
			visited[nxt] = false;
		}
		
		return false;
	}

}