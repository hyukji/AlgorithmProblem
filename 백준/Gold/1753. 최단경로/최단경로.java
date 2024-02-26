import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static List<int[]>[] graph;
	static int[] dists;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int k = Integer.parseInt(br.readLine()) - 1;
		
		graph = new ArrayList[n];
		for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			
			graph[u].add(new int[] {v, w});
		}

		dists = new int[n];
		for(int i = 0; i < n; i++) dists[i] = Integer.MAX_VALUE;
		
		dijk(k);
		
		StringBuilder sb = new StringBuilder();
		for(int v : dists) {
			if(v == Integer.MAX_VALUE) sb.append("INF\n");
			else sb.append(v).append("\n");
		}
		
		System.out.print(sb);
	}

	private static void dijk(int k) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		pq.offer(new int[] {k, 0});
		
		int cnt = 0;
		while(!pq.isEmpty()) {
			if(cnt == n) return;
			
			int[] cur = pq.poll();
			int node = cur[0];
			int dist = cur[1];
			
			if(dists[node] <= dist) continue;
			dists[node] = dist;
			cnt++;
			
			for(int[] next : graph[node]) {
				int nDist = dist + next[1];
				if(dists[next[0]] <= nDist) continue;
				pq.offer(new int[] {next[0], nDist});
			}
		}
	
	}

	
}