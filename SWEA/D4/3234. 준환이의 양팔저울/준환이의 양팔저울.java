import java.io.*;
import java.util.*;

public class Solution  {

	static int[] graph ;
	static int n, tot;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			tot = 0;
			n = Integer.parseInt(br.readLine());
			
			graph= new int[n];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				graph[i] = Integer.parseInt(st.nextToken());
				tot+= graph[i]; // 총 합 더해주기
			}

			dp = new int[1<<n][1<<n];
			int answer = dfs(0, 0, 0, 0, 0);
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);

	}

	/*
	 * left, right 는 왼쪽 오른쪽 각각의 무게의 합
	 * lv는 왼쪽 무게추의 방문 bit 마스킹
	 * rv는 오른쪽 무게추의 방문 bit 마스킹
	 * cnt 확인한 무게추의 개수
	 * */
	private static int dfs(int left, int right, int lv, int rv, int cnt) {
		int result = 0;
		int v = lv | rv; // 확인 총 무게추의 비트 마스킹
		
		if(cnt == n) { // 모두 확인했다면 1
			dp[lv][rv] = 1;
			return 1;
		}
		
		if(left > tot/2) { // 왼쪽 무게 합이 총합의 절반 이상이면, 이후에는 무슨짓을 해도 상관없음. 경우의 수 계산해서 더함
			int fac = 1;
			for(int i = 1 ; i <= n-cnt; i++) fac *=i;
			result = (int) (fac * Math.pow(2, (n-cnt)));
			dp[lv][rv] = result; // 현재 상황 dp에 저장
			
			return result;
		}
		
		for(int i = 0; i < n; i++) {
			if((v & (1<< i)) > 0) continue; // i번째 무게추를 이전에 방문했다면 pass
			
			int nlv = lv | (1<<i); // 왼쪽에 무게추 넣은 경우
			if(dp[nlv][rv] > 0)  result+= dp[nlv][rv]; // 이전에 넣은 적이 있다면 
			else result += dfs(left + graph[i], right, nlv, rv, cnt+1); // 없다면 계싼
			
			if(left < right + graph[i]) continue; 
			int nrv = rv | (1<<i); // 오른쪽에 무게추 넣은 경우
			if(dp[lv][nrv] > 0)  result += dp[lv][nrv];
			else result+=dfs(left, right + graph[i], lv, nrv, cnt + 1);
		}
		
		dp[lv][rv] = result; // 현재 상황을 dp에 저장
		return result;
	}

}