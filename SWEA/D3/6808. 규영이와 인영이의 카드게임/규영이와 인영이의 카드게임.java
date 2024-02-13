import java.io.*;
import java.util.*;

public class Solution  {
	
	static int[] graph = new int[9], nGraph  = new int[9];
	static boolean[] visited = new boolean[9];
	static int win, lose;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			st = new StringTokenizer(br.readLine());
			
			boolean[]  v = new boolean[19];
			for(int  i =0; i <9; i++) {
				graph[i] = Integer.parseInt(st.nextToken()); // 규영
				v[graph[i]] = true;
			}

			int cnt = 0;
			for(int  i =1; i <19; i++) {
				if(v[i]) continue;
				nGraph[cnt++] = i; // 인영
			}
			
			win = 0; lose =0;
			perm(0, 0, 0);
			
			sb.append("#").append(tc).append(" ").append(win).append(" ").append(lose).append(" ").append("\n");
		}
		
		System.out.print(sb);
	}

	private static void perm(int cnt, int kSum, int iSum) {
		if(cnt == 9) {
			if(kSum < iSum) lose++;
			else if(kSum > iSum) win++;
			return;
		}
		
		int kyu = graph[cnt];
		for(int i = 0; i < 9; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			if(kyu > nGraph[i]) perm(cnt+1, kSum + kyu + nGraph[i], iSum);
			else perm(cnt+1, kSum, iSum + kyu + nGraph[i]);
			visited[i] = false;
		}
	}
	
}