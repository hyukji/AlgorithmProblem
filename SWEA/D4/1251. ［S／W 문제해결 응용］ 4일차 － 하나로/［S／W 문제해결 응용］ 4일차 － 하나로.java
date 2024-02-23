import java.io.*;
import java.util.*;

public class Solution  {

	static int n;
	static double e;
	static int[][] graph;
	static boolean[] visited;
	
	static class Bridge implements Comparable<Bridge>{
		int to;
		double  cost;
		public Bridge(int from, int to) {
			super();
			this.to = to;
			this.cost = e* (Math.pow(graph[from][0] - graph[to][0], 2) + Math.pow(graph[from][1] - graph[to][1], 2));
		}
		@Override
		public int compareTo(Bridge o) {
			return Double.compare(cost, o.cost);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T + 1; tc++) {
			n = Integer.parseInt(br.readLine());
			graph = new int[n][2];
			StringTokenizer stR = new StringTokenizer(br.readLine());
			StringTokenizer stC = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				graph[i][0] = Integer.parseInt(stR.nextToken());
				graph[i][1] = Integer.parseInt(stC.nextToken());
			}
			e = Double.parseDouble(br.readLine());
			
			visited = new boolean[n];
			PriorityQueue<Bridge> pq = new PriorityQueue<>();	
			pq.offer(new Bridge(0, 0));

			int cnt = 0;
			double answer = 0;
			while(!pq.isEmpty()) {
				Bridge bridge = pq.poll();
				
				int from = bridge.to;
				if(visited[from]) continue;
				visited[from] = true;
				answer += bridge.cost;
				if(++cnt == n) break;
				
				for(int to = 0; to < n; to++) {
					if(visited[to]) continue;
					pq.add(new Bridge(from, to));
				}
				
			}
			sb.append("#").append(tc).append(" ").append(String.format("%.0f", answer)).append("\n");
			
		}
		
		System.out.print(sb);
	}

}