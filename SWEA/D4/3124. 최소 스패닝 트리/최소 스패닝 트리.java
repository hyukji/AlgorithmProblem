import java.io.*;
import java.util.*;

public class Solution  {

	static int n, m;
	static int[] p;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			p = new int[n];
			for(int i = 0; i < n; i++) p[i] = i;
			PriorityQueue<int[]> pq= new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				int w = Integer.parseInt(st.nextToken());
				pq.offer(new int[] {a, b, w});
			}
	
			long answer = 0, cnt = 0;
			while(cnt < n-1) {
				int[] cur = pq.poll();
				int a = cur[0];
				int b= cur[1];
				int w = cur[2];
				
				if(!union(a, b)) continue;
				cnt++;
				answer += w;
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}

	private static boolean union(int a, int b) {
		int ra = find(a);
		int rb = find(b);
		if(ra == rb) return false;
		p[ra] = rb;
		return true;
	}

	private static int find(int a) {
		if(p[a] == a) return a;
		return p[a] = find(p[a]);
	}

	

}