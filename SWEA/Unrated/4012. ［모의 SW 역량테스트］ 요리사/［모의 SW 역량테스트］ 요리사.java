import java.io.*;
import java.util.*;

public class Solution  {
	
	static int n;
	static long answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			answer = Integer.MAX_VALUE;
			n = Integer.parseInt(br.readLine());
			
			int[] visited = new int[n];
			for(int i = n/2; i < n; i++) visited[i] = 1;

			long tot = 0;
			int[][] graph = new int[n][n];
			for(int  r =0; r <n; r++) {
				st = new StringTokenizer(br.readLine());
				for(int  c =0; c <n; c++) {
					graph[r][c] = Integer.parseInt(st.nextToken());
					tot += graph[r][c];
				}
			}
				
			do {
				int[][] Graph0 = copy(graph, n);
				int[][] Graph1 = copy(graph, n);
				for(int i = 0; i < n; i++) {
					if(visited[i] == 1) {
						for(int j = 0; j < n; j++) {
							Graph0[i][j] = 0; Graph0[j][i] = 0;
						}
					} else {
						for(int j = 0; j < n; j++) {
							Graph1[i][j] = 0; Graph1[j][i] = 0;
						}
					}
				}
				
				long diff = Math.abs(getSynergy(Graph0) - getSynergy(Graph1));
				if(diff < answer) answer = diff;
			} while(nPn(visited, n));
			
			sb.append("#").append(tc).append(" ").append(answer).append(" ").append("\n");
		}
		
		System.out.print (sb);
	}
	
	private static long getSynergy(int[][] nGraph) {
		long sum = 0;
		for(int  r =0; r <n; r++) {
			for(int  c =0; c <n; c++) {
				sum += nGraph[r][c];
			}
		}
		return sum;
	}

	static int[][] copy(int[][] graph, int n) {
		int[][] nGraph = new int[n][n];
		for(int  r =0; r <n; r++) {
			for(int  c =0; c <n; c++) {
				 nGraph[r][c] = graph[r][c];
			}
		}
		return nGraph;
	}
	
	static void swap(int[] visited, int i, int j) {
		int T = visited[i];  visited[i] = visited[j];  visited[j] = T;
	}

	private static boolean nPn(int[] visited, int N) {
		int  i = N-1; // i 교환 위치 찾기
		while(i > 0 && visited[i-1] >= visited[i]) i--;
		if(i==0) return false;
		
		int  j = N-1; // j 교환할 값 찾기
		while(visited[i-1]>=visited[j]) j--;
		swap(visited, i-1, j);
		
		int  k = N-1; // k 오름차순 정렬
		while(i <k) swap(visited, i++, k--);
		return true;
	}

}