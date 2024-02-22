import java.io.*;
import java.util.*;

// 프림
public class Solution  {

	static List<int[]>[] graph ;
	static int n, m;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			boolean[] visited = new boolean[n];
			int[] minEdges = new  int[n];
			graph = new List[n];
			for(int i = 0; i < n; i++) {
				graph[i] = new ArrayList<>();
				minEdges[i] = Integer.MAX_VALUE;
			}
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				int w = Integer.parseInt(st.nextToken());
				graph[a].add(new int[] {b, w});
				graph[b].add(new int[] {a, w});
			}

			long answer = 0, cnt = 0;		
			PriorityQueue<int[]> pq= new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
			minEdges[0] = 0;
			pq.offer(new int[] {0, 0});
			while(!pq.isEmpty()){
				int[] cur = pq.poll();
				int minV = cur[0];
				int minE =cur[1];
				
				if(visited[minV]) continue;
				visited[minV] = true;
				answer += minE;
				if(++cnt == n) break;
				
				for(int[] edge : graph[minV]) {
					int nv = edge[0], ne = edge[1];
					if(visited[nv]) continue;
					if(minEdges[nv] > ne) {
						pq.offer(new int[] {nv, ne});
						minEdges[nv] = ne;
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}


}