import java.io.*;
import java.util.*;

public class Solution  {
	
	static long[] graph ;
	static int n;
	static long m, answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			st = new StringTokenizer(br.readLine());
			n =Integer.parseInt(st.nextToken());
			m =Long.parseLong(st.nextToken());
			answer = 0;
			
			graph = new long[n];
			long maxV = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int  i =0; i <n; i++) {
				graph[i] = Long.parseLong(st.nextToken()); 
				if(maxV < graph[i]) maxV = graph[i];
			}
			
			binarySearch(0, maxV+1);
			
			sb.append("#").append(tc).append(" ").append(answer).append(" ").append("\n");
		}
		
		System.out.print(sb);
	}
	
	private static void binarySearch(long s, long e) {
		s = 0;  e = 1000000000000000001L;
		while(s + 1 < e) {
			long mid = (s+e) / 2; 
			long candies = getCandies(mid);

			if(candies >= m) {
				s = mid;
			}else {
				e  = mid;
			}
//			if(candies == m && getCandies(mid+1) == m-1) {
//				answer = mid;
//				return;
//			} 
		}

		answer = s;
	}

	private static long getCandies(long mid) { // mid 라는 가방의 크기에 들어갈 수 있는 캔디의 최대 수
		if(mid == 0) mid++; // / by zero 방지
		
		long cnt = 0;
		for(int  i =0; i <n; i++)  cnt += graph[i] / mid;
		return cnt;
	}
	
	
	

}