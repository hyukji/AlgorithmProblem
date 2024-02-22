import java.io.*;
import java.util.*;

public class Main {
	
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {1, 0, -1, 0};
	
	static int n;
	static int[][] graph, nGraph[];
	static ArrayDeque<int[]> dists;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		n = Integer.parseInt(br.readLine());
		graph = new int[n][n];
		nGraph = new int[n][n][2];
		dists = new ArrayDeque<>();
		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < n; c++) {
				graph[r][c] =  Integer.parseInt(st.nextToken());
			}
		}

		int num = 0;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if(graph[r][c] == 1 && nGraph[r][c][0] == 0) if(checkIsland(r, c, ++num)) {
					System.out.println(1);
					return;
				}
			}
		}

		System.out.println(getDist());
	}

	private static void print() {
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				System.out.print(nGraph[r][c][1] + " ");
			}
			System.out.println();
		}
	}
	
	private static int getDist() {
		boolean find = false;
		int dist = 2, result = Integer.MAX_VALUE;
		while(!dists.isEmpty()) {
			if(find) return result;
			int size = dists.size();
			for(int i = 0; i < size; i++) {
				int[] cur = dists.poll();
				int num = cur[2];

				for(int d = 0; d < 4;d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];
					if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
					if(nGraph[nr][nc][0] == 0) { // 새로운 곳
						dists.offer(new int[] {nr, nc, num, dist});
						nGraph[nr][nc] =new int[] {num, dist};
					}
					else if(nGraph[nr][nc][1] == -1 ) continue; // 해당 위치가 섬
					else if(nGraph[nr][nc][0] != num) {
						result =Math.min(nGraph[nr][nc][1] + dist -1, result);
					}
				}
			}

			dist++;
		}
		return result;
	}

	private static boolean checkIsland(int r, int c, int num) {
		nGraph[r][c] = new int[] {num, -1};
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {r, c});
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			r = cur[0]; c = cur[1];
			for(int d = 0;d < 4;d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
				if(nGraph[nr][nc][0] == num) continue; // 같은 섬으로 전에 방문했다면
				else if(graph[nr][nc] == 1) { //섬이라면
					nGraph[nr][nc] = new int[] {num, -1};
					dq.offer(new int[] {nr, nc});
				}
				else if(graph[nr][nc] == 0) { // 바다라면
					if(nGraph[nr][nc][0] > 0) {
						return true; // 다른 섬이 방문했다면 거리 1
					}
					nGraph[nr][nc] = new int[] {num, 1};
					dists.offer(new int[] {nr, nc, num});
				}
			}
		}
		return false;
	}


}