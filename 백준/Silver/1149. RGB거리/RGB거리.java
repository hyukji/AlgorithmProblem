import java.io.*;
import java.util.*;

public class Main {

	static int n;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		int[] dp = {0, 0, 0};
		for(int i = 0; i< n; i++) {
			st = new StringTokenizer(br.readLine());
			int[] rgb = {
					Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken())};
			
			int[] minCost = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
			for(int now = 0; now < 3; now++) {
				for(int bef = 0; bef < 3; bef++) {
					if(now == bef) continue;
					
					int cost = dp[bef] + rgb[now];
					if(cost < minCost[now]) minCost[now] = cost; 
				}
			}
			
			dp = minCost;
		}
		
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < 3; i++) min = Math.min(min, dp[i]);
		System.out.println(min);
	}
}