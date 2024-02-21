import java.io.*;
import java.util.*;

public class Solution  {

	static int[] p ;
	static int n, m;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			st = new StringTokenizer(br.readLine());
			n =Integer.parseInt(st.nextToken());
			m =Integer.parseInt(st.nextToken());
			
			p = new int[n];
			for(int i = 0; i < n; i++)  p[i] = i;
			
			sb.append("#").append(tc).append(" ");
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int v = Integer.parseInt(st.nextToken());
				int x =Integer.parseInt(st.nextToken()) - 1;
				int y =Integer.parseInt(st.nextToken()) - 1;
				
				if(v == 0) union(x,  y);
				else {
					int answer = 0;
					if(find(x) == find(y)) answer++;
					sb.append(answer);
				}
			}

			sb.append("\n");
		}
		
		System.out.println(sb);

	}

	private static int find(int x) {
		if(p[x] == x) return x;
		return p[x] = find(p[x]);
	}

	private static void union(int x, int y) {
		if(find(x) == find(y)) return;
		p[find(x)] = find(y);
	}

}