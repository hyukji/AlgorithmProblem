import java.io.*;
import java.util.*;

public class Main {

	static List<Integer>[]  to;
	static boolean[] visited;
	static int n, m, from[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		visited = new boolean[n];
		from = new int[n];
		to = new ArrayList[n];
		for (int i = 0; i < n; i++) to[i] = new ArrayList<>();
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int low = Integer.parseInt(st.nextToken()) -1;
			int high = Integer.parseInt(st.nextToken()) -1;
			from[high]++;
			to[low].add(high);
		}

		ArrayDeque<Integer> dq = new ArrayDeque<>();
		for (int i = 0; i < n; i++)  if(from[i] == 0) dq.offer(i);
		
		while(dq.size() > 0) {
			int idx = dq.poll();
			sb.append(idx+1).append(" ");
			visited[idx] = true;
			
			for(int nxt : to[idx]) {
				if(visited[nxt]) continue;
				if(--from[nxt] == 0) dq.offer(nxt);
			}
		}
		
		System.out.println(sb);
		
	}

}