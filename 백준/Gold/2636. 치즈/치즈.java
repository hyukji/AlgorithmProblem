import java.io.*;
import java.util.*;

public class Main {

	static int[][] graph;
	static boolean[][] visited;
	static ArrayDeque<int[]> dq;
	static int n, m, time, count;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new int[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dq = new ArrayDeque<>();
		getCheeseWithZero(0, 0);

		bfs();

		System.out.println(time);
		System.out.println(count);
	}

	private static void bfs() {
		time = 0;
		ArrayDeque<int[]> zeroDq = new ArrayDeque<>();
		while (dq.size() > 0) {
			time++;
			count = dq.size();
			
			for (int i = 0; i < count; i++) {
				int[] loc = dq.poll();
				int r = loc[0];
				int c = loc[1];
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
					if (visited[nr][nc]) continue;

					visited[nr][nc] = true;
					if (graph[nr][nc] == 1) {
						dq.offer(new int[] { nr, nc });
						continue;
					}
					zeroDq.offer(new int[] { nr, nc });
				}
			}

			while (zeroDq.size() > 0) {
				int loc[] = zeroDq.poll();
				getCheeseWithZero(loc[0], loc[1]);
			}
			
		}
	}

	private static void getCheeseWithZero(int r, int c) {
		ArrayDeque<int[]> zeroDq = new ArrayDeque<>();
		zeroDq.offer(new int[] { r, c });
		visited[r][c] = true;

		while (zeroDq.size() > 0) {
			int[] loc = zeroDq.poll();
			r = loc[0];
			c = loc[1];
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (nr < 0 || nr >= n || nc < 0 || nc >= m)
					continue;
				if (visited[nr][nc])
					continue;

				visited[nr][nc] = true;
				if (graph[nr][nc] == 1) {
					dq.offer(new int[] { nr, nc });
					continue;
				}
				zeroDq.offer(new int[] { nr, nc });
			}

		}

	}

}