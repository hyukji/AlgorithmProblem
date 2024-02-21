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

			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int x =Integer.parseInt(st.nextToken()) - 1;
				int y =Integer.parseInt(st.nextToken()) - 1;
				
				union(x,  y);
			}
			
			// root의 개수 카운팅
//			int answer = 0;
//			boolean[] v = new boolean[n];
//			for(int i = 0; i < n; i++) {
//				int r = find(i);
//				if(v[r]) continue;
//				v[r] = true;
//				answer++;
//			}
//			
			// hashSet을 이용한 처리 
			Set<Integer> set = new HashSet<>();
			for(int i = 0; i < n; i++) set.add(find(i));
			int answer = set.size();
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);

	}

	private static void union(int x, int y) {
		if(find(x) == find(y)) return; // 같은 집합에 속해 있다면 pass
		p[find(x)] = find(y); // x 의 루트위에 y의 루트를 넣는다.
	}

	private static int find(int x) {
		if(p[x] == x) return x; // 본인이 루트라면 return 
		return p[x] = find(p[x]); // find(p[x])로 루트를 찾고 p에 업데이트
	}
	
}