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
		
		for(int v : dists) {
			if(v == Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(v);
		}
		
	}

	private static void dijk(int k) {
		dists[k] = 0;
		boolean[] visited = new boolean[n];
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		pq.offer(new int[] {k, 0});
		
		int cnt = 0;
		while(!pq.isEmpty()) {
			if(cnt == n) return; // n개 다 모았으면 dijk 종료
			
			int[] cur = pq.poll();
			int node = cur[0];
			int dist = cur[1];
			if(visited[node]) continue;
			
            // 방문처리
			dists[node] = dist;
			visited[node] = true;
			cnt++;
			
			for(int[] next : graph[node]) { // 다음 노드까지의 거리 pq에 저장
				if(visited[next[0]]) continue;
				pq.offer(new int[] {next[0], dist + next[1]});
			}
			
		}
	
	}

	
}