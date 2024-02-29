import java.io.*;
import java.util.*;

public class Main {

	static boolean[][] graph;
	static boolean[][][] visited;
	static final int[] dr = {0, 0, 1, -1};
	static final int[] dc = {1, -1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		graph = new boolean[n][m];
		visited = new boolean[n][m][2];
		for(int r = 0; r < n; r++) {
			String row = br.readLine();
			for(int c = 0; c < m; c++)  {
				graph[r][c] = row.charAt(c) == '1';
			}
		}
			
		if(n == 1 && m == 1) System.out.println(1);
		else System.out.println(bfs(n, m));
	}

	private static int bfs(int n, int m) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {0, 0, 0}); // r, c, dist, brokeWall
		visited[0][0][0] = true;
		
		int dist = 1;
		while(!dq.isEmpty()) {
			int size = dq.size();
			dist++;
			for(int i = 0; i <size; i++) {
				int[] cur = dq.poll();
				
				int broken = cur[2];
				for(int d = 0; d < 4; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];
					if(nr < 0 || nr >= n || nc <0 || nc >= m) continue;
					
					if(nr == n-1 && nc == m-1) return dist;
					
					boolean isWall = graph[nr][nc];
					if(isWall && broken == 0) { // 벽이라면 사전에 부수지 않은 경우만! -> 부수고 간다
						if(visited[nr][nc][1]) continue;
						visited[nr][nc][1] = true;
						dq.offer(new int[] {nr, nc, 1});
					} else if(!isWall) { // 벽이아니라면 
						if(visited[nr][nc][broken]) continue;
						visited[nr][nc][broken] = true;
						dq.offer(new int[] {nr, nc, broken});
					}
					
				}
				
			}
		}
		
		return -1;
	}

}