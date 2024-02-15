import java.io.*;
import java.util.*;

public class Solution  {

	static int[][] graph;
	static int n;
	
	static boolean[] second, visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		for(int tc = 1; tc < 11; tc++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			n =Integer.parseInt(st.nextToken());
			
			graph = new int[2][100];
			second = new boolean[100];
			visited = new boolean[100];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				if(second[from]) {
					graph[1][from] = to;
					continue;
				}
				
				second[from] = true;
				graph[0][from] = to;
			}
			
			sb.append("#").append(tc).append(" ").append(dfs(0) ? 1 : 0).append("\n");
		}
		System.out.println(sb);
	}

	private static boolean dfs(int i) {
		if(i == 99) return true;
		
		if(visited[i]) return false;
		visited[i] = true;
		
		if(dfs(graph[0][i])) return true;
		if(second[i] && dfs(graph[1][i])) return true;
		return false;
	}
}