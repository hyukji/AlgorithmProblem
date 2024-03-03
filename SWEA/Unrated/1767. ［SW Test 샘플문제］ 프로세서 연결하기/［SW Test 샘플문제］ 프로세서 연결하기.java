import java.io.*;
import java.util.*;

public class Solution  {
	
	static final int[] dr = {1, 0, -1, 0};
	static final int[] dc = {0, 1, 0, -1};
	
	static int n, coreIdx, maxCore, minLine;
	static int[][] graph, cores;
	static int[][] lines;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			cores = new int[13][2];
			
			coreIdx = 1;
			maxCore = 0;
			minLine = Integer.MAX_VALUE;
			
			n = Integer.parseInt(br.readLine());
			graph = new int[n][n];
			lines = new int[n][n];
			
			int coreCnt = 0;
			for(int  r =0; r <n; r++) {
				st = new StringTokenizer(br.readLine());
				for(int  c =0; c <n; c++) {
					graph[r][c] = Integer.parseInt(st.nextToken());
					if(graph[r][c] == 1) {
						if(r == 0 || r == n-1 || c == 0 || c == n-1) coreCnt++;
						else cores[coreIdx++] = new int[] {r, c}; // 프로세서의 코어 따로 저장 
					}
				}
			}

			dfs(1, coreCnt, 0);
			
			sb.append("#").append(tc).append(" ").append(minLine).append(" ").append("\n");
		}
	
		System.out.print(sb);
	}

	private static void dfs(int i, int coreCnt, int lineCnt) {
		if(i == coreIdx) {
			if(maxCore < coreCnt || (maxCore == coreCnt && minLine > lineCnt)) {
				maxCore = coreCnt;
				minLine = lineCnt;
//				for(int[] row : lines) System.out.println(Arrays.toString(row));
			}
			return;
		}

		for(int d = 0; d < 4; d++) {
			int filled = fill(i, d); 
			if(filled > 0) dfs(i+1, coreCnt+1, lineCnt + filled);
			rollBack(i, d); 
		}
		dfs(i+1, coreCnt, lineCnt);
	}

	private static void rollBack(int i, int d) {
		int nr = cores[i][0];
		int nc = cores[i][1];
		
		while(true) {
			nr += dr[d];
			nc += dc[d];
			if(nr < 0 || nr >= n || nc < 0 || nc >= n) break; // 라인 다 채웠으
			if(lines[nr][nc] == i) lines[nr][nc] = 0;
			else return;
		}
		
	}

	private static int fill(int i, int d) {
		int nr = cores[i][0];
		int nc = cores[i][1];
		
		int filled = 0;
		while(true) {
			nr += dr[d];
			nc += dc[d];
			if(nr < 0 || nr >= n || nc < 0 || nc >= n) break; // 라인 다 채웠으
			if(graph[nr][nc] == 1 || lines[nr][nc] != 0) return 0; // 가는 길에 다른 코어나 라인이있다
			lines[nr][nc] = i;
			filled++;
		}
		
		return filled;
	}

}