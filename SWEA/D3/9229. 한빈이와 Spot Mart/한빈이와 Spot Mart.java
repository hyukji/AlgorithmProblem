import java.io.*;
import java.util.*;

public class Solution  {

	static int n, m, answer = 0;
	static int[] graph;

	static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			answer = -1;
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			graph = new int[n]; // 0 : 안전한 공간, 1 :지뢰가 있는 공간, 2 : 0클릭을 통해 자동으로 열린 공간
			
			st = new StringTokenizer(br.readLine());
			for(int i =0; i < n; i++) {
				graph[i] = Integer.parseInt(st.nextToken());
			}

			comb(0, 0, 0);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.print (sb);
	}

	private static void comb(int bef, int w, int cnt) {
		if(cnt == 2) {
			answer = Math.max(answer, w);
			return;
		}
		
		for(int i = bef; i < n; i++) {
			int nw = w + graph[i];
			if(nw <= m) comb(i+1, nw, cnt+1);
		}
		
	}
}