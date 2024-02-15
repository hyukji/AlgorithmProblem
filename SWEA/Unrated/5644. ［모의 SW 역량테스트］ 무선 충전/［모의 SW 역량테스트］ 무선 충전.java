import java.io.*;
import java.util.*;

public class Solution  {
	
	private static final int[] dr = {0, -1, 0, 1 ,0};
	private static final int[] dc = {0, 0 ,1, 0, -1};
	static int[][] graph, BCs;
	static int[] a, b;
	static int answer, M, A;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			answer = 0;
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			a = new int[M];
			b = new int[M];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) a[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) b[i] = Integer.parseInt(st.nextToken());
			
			graph = new int[10][10];
			BCs = new int[A][4];
			
			for(int i = 0; i < A; i++) {			
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken()) - 1; // 함정... 양아치네 이거 
				int r = Integer.parseInt(st.nextToken()) - 1;
				int depth = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());
				
				BCs[i] = new int[] {r, c, depth, power};
			}
			
			// power가 높은순으로 정렬 
			Arrays.sort(BCs, (o1, o2) -> -Integer.compare(o1[3], o2[3]));
			for(int i = 0; i < A; i++) { // power 영역채우기 
				bfs(BCs[i], i);
			}

			
			int r1 = 0, c1 = 0, r2 = 9, c2 = 9;
			getPower(graph[r1][c1], graph[r2][c2]); // 충전 계산 
			for(int i = 0; i < M; i++) {
				r1 += dr[a[i]]; c1 += dc[a[i]];
				r2 += dr[b[i]]; c2 += dc[b[i]];
				getPower(graph[r1][c1], graph[r2][c2]);
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}

	private static void getPower(int v1, int v2) {
		int cnt = 0;
		for(int i = 0; i < A; i++) { // 정렬했으므로 i가 작을수록 power 쎈 것 
			boolean powerA = (v1 & (1 << i)) > 0; // i 포함 여부 
			boolean powerB = (v2 & (1 << i)) > 0;
			if(!powerA && !powerB) continue; // 둘다 포함하지 않았다면 

			answer += BCs[i][3];
			if(++cnt == 2) break; // BC는 2개 이상 찾을 수 없음 

			if(powerA && !powerB) v1 = 0; // i번째 BC를 A가 사용한다면, A는 다른 BC연결 못함 
			else if(!powerA && powerB) v2 = 0;
			else {
				int v = v1 | v2; // i번째 BC를 A, B 모두 사용가능하다면, AB 상관없이 그 다음으로 연결가능한 BC를 찾아야 함 
				for(int j = i+1; j < A; j++) { 
					if((v & (1 << j)) > 0) {
						answer += BCs[j][3];
						return;
					}
				}
			} 
			
		}
	}
		
	private static void bfs(int[] BC, int index) {
		boolean visited[][] = new boolean[10][10];
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {BC[0], BC[1]});
		
		int cnt = 0;
		while(cnt++ <= BC[2]) {
			int size = dq.size();
			for(int i = 0; i < size; i++) {
				int[] loc = dq.poll();
				int r = loc[0]; int c = loc[1];
				graph[r][c] |= (1 << index); // 비트마스킹을 이용. 
				
				for(int d = 1; d < 5; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if(nr < 0 || nr >= 10 || nc < 0 || nc >= 10) continue;
					if(visited[nr][nc]) continue;
					
					visited[nr][nc] = true;
					dq.offer(new int[] {nr, nc});
				}
				
			}
		}
		
	}


}