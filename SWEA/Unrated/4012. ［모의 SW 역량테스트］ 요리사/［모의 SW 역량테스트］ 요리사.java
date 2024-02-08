import java.io.*;
import java.util.*;


// 참고 : https://www.acmicpc.net/source/37713019
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
			
			int[] crossSum = new int[n];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					crossSum[i] += graph[i][j] + graph[j][i];
				}
			}
				
			do {
				long sumVisit = 0;
				for(int i = 0; i < n; i++) {
					if(visited[i] == 1) sumVisit += crossSum[i];
				}
				
				 // sumVisited안에 방문된 곳이 두번 들어가 있고, 방문 되지 않았을 때의 시너지의 합은 들어 있지 않음
				// 따라서 전체를 더한 값에 sumVisit을 빼주면 시너지의 차가 나온다.
				long syn = Math.abs(tot - sumVisit);
				if(answer > syn) answer = syn;
				
			} while(nPn(visited, n));
			
			sb.append("#").append(tc).append(" ").append(answer).append(" ").append("\n");
		}
		
		System.out.print (sb);
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