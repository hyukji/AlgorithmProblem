import java.io.*;
import java.util.*;

public class Solution  {
	
	static int n,m;
	static int answer;
	
	static boolean[][] graph;
	static int[] inCnt;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			n = Integer.parseInt(br.readLine());
			m = Integer.parseInt(br.readLine());

			answer = 0;
			inCnt = new int[n];
			graph = new boolean[n][n];
			
			for(int i =0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				
				graph[a][b] = true;
				inCnt[b]++;
			}
			
			ArrayDeque<Integer> dq = new ArrayDeque<>();
			for(int i = 0; i < n; i++) {
				if(inCnt[i] == 0) dq.offer(i);
			}
			
			for(int i = 0; i < n; i++) {
				boolean[] visited = new boolean[n];
				visited[i] = true;
				taller(i, visited);
				smaller(i, visited);
				
				if(check(visited)) answer++;
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append(" ").append("\n");
		}
		
		System.out.print(sb);
	}

	private static boolean check(boolean[] visited) {
		for(boolean v : visited) {
			if(!v) return false; 
		}
		return true;
	}

	private static void taller(int cur, boolean[] v) {
		for(int i = 0; i < n; i++) {
			if(v[i]) continue;
			if(graph[cur][i]) {
				v[i] = true;
				taller(i, v);
			}
		}
	}

	private static void smaller(int cur, boolean[] v) {
		for(int i = 0; i < n; i++) {
			if(v[i]) continue;
			if(graph[i][cur]) {
				v[i] = true;
				smaller(i, v);
			}
		}
	}	

}