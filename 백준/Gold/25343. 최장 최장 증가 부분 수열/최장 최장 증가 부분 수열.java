import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());
		int[][] graph = new int[n][n];
		for(int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < n; c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] dp = new int[n][n]; // 각각 위치에서의 최대 lis값!
		// 결국 오른쪽, 아래로만 움직임 -> r,c 의 lis라면 00부터 rc 까지의 사각형에서 
		// rc의 값보다 작은 값에서의 lis 들 중에 최대+1 이다. 
		
		dp[0][0] = 1;
		for(int c = 1; c < n; c++) {
			dp[0][c] = 1;
			for(int nc = 0; nc < c; nc++) {
				if(graph[0][nc] < graph[0][c]) 
					dp[0][c] = Integer.max(dp[0][c] ,dp[0][nc] + 1);
			}
		}
		
		
		for(int r = 1; r < n; r++) {
			dp[r][0] = 1;
			for(int nr = 0; nr < r; nr++) {
				if(graph[nr][0] < graph[r][0]) 
					dp[r][0] = Integer.max(dp[r][0], dp[nr][0] + 1);
			}
			
			for(int c = 1; c < n; c++) {
				dp[r][c] = 1;
				for(int nr = 0; nr < r+1; nr++) {
					for(int nc = 0; nc < c+1; nc++) {
						if(graph[nr][nc] < graph[r][c]) 
							dp[r][c] = Integer.max(dp[r][c], dp[nr][nc] + 1);
					}
				}
			}
		}

		int answer = 0;
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < n; c++) {
				answer = Integer.max(answer, dp[r][c]);
			}
		}
		System.out.println(answer);
	}
}