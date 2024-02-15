import java.io.*;
import java.util.*;

public class Solution  {
	
	static final int[] dr = {0, 1, 0, -1};
	static final int[] dc = {1, 0, -1, 0};
	static final String direction = "RDLUS", tank = ">v<^";

	static char[][] graph ;
	static int n, h, w, d, r, c;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			st = new StringTokenizer(br.readLine());
			h =Integer.parseInt(st.nextToken());
			w =Integer.parseInt(st.nextToken());
			
			graph = new char[h][w];
			
			for(int i = 0; i < h; i++) {
				String row = br.readLine();
				for(int j = 0; j < w; j++) {
					graph[i][j] = row.charAt(j);
					int t = tank.indexOf(graph[i][j]);
					if(t >= 0) { d = t; r = i; c = j;  }
				}
			}

			n = Integer.parseInt(br.readLine());
			String opts = br.readLine();
			
			for(int i = 0; i < n; i++) {
				char opt = opts.charAt(i);
				int nd = direction.indexOf(opt);
				
				if(nd == 4)  shoot();
				else move(nd); 
			}

			sb.append("#").append(tc).append(" ");
			for(char[] row : graph) sb.append(row).append("\n");
		}
		
		System.out.print(sb);
	}

	private static void shoot() {
		int nr = r, nc = c;
		while(true) {
			nr += dr[d]; nc += dc[d];
			if(nr < 0 || nr >= h || nc < 0 || nc >= w) return;
			if(graph[nr][nc] == '#') return;
			else if(graph[nr][nc] == '*') {
				graph[nr][nc] = '.';
				return;
			}
		}
	}

	private static void move(int nd) {
		graph[r][c] = '.';
		
		d = nd;
		int nr = r + dr[d];
		int nc = c + dc[d];
		if(nr >= 0 && nr < h && nc >= 0 && nc < w && graph[nr][nc] == '.') {
			r= nr; c = nc;
		}
		graph[r][c] = tank.charAt(d);
	}
	
}