import java.io.*;
import java.util.*;

public class Main {

	static int[][] graph, willDisappear, nGraph;
	static int[] arrows;
	static int n, m, D, enermy = 0, answer = 0;

	static int[] dr = { 0,-1, 0 };
	static int[] dc = { -1,0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		graph = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if (graph[i][j] == 1)
					enermy++;
			}
		}

		arrows = new int[3];
		comb(0, 0);
		System.out.println(answer);
	}

	private static void comb(int v, int cnt) { // 궁수들 위치 조합으로 구하기
		if (answer == enermy) return; // 적들을 이미 다 찾았다면 끝내기.

		if (cnt == 3) {
			int count = solve();
			answer = Math.max(count, answer);
			return;
		}

		for (int i = v; i < m; i++) {
			arrows[cnt] = i;
			comb(i + 1, cnt + 1);
		}

	}

	private static int solve() {
		int count = 0, time = 0;
		nGraph = copyGraph();
		
		while (time++ != n) {
			willDisappear = new int[][] {{-1, -1}, {-1, -1}, {-1, -1}};
			int r = n - time;
			for (int i = 0; i < 3; i++) {
				int c = arrows[i];
				bfs(r, c, i); // 궁수들마다 적 찾기
			}

			
			for (int i = 0; i < 3; i++) {
				r = willDisappear[i][0];
				if(r == -1) continue;
				int c = willDisappear[i][1];
				if(nGraph[r][c] == 1) {
					nGraph[r][c] = 0;
					count++;
				}
			}
			
		}
		return count;
	}
	private static void bfs(int r, int c, int idx) {
		int cnt = 1;
		if (nGraph[r][c] == 1) {
			willDisappear[idx] = new int[] {r, c};
			return;
		}

		boolean[][] visited = new boolean[n][m];
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {r, c});
		
		while (dq.size() > 0) {
			if(cnt++ == D) return;
			
			int size = dq.size();
			for (int i = 0; i < size; i++) {
				int[] loc = dq.poll();
				r = loc[0]; c = loc[1];
				for (int d = 0; d < 3; d++) {
					int nr = r + dr[d]; int nc = c + dc[d];
					if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
					if (visited[nr][nc]) continue;

					if (nGraph[nr][nc] == 1) {
						willDisappear[idx] = new int[] {nr, nc};
						return;
					}

					visited[nr][nc] = true;
					dq.offer(new int[] { nr, nc });
				}
			}
			
		}
	}


	private static int[][] copyGraph() {
		int[][] a = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				a[i][j] = graph[i][j];
			}
		}
		return a;
	}

}