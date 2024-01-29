import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine());
			int answer =0;
			int[][] graph = new int[n][n];
			for(int i = 0; i < n; i++) {
				String row = br.readLine();
				for(int j = 0; j < n; j++) {
					graph[i][j]= row.charAt(j) - '0';
				}
			}
			
			for(int i = 0; i < n/2; i++) {
					int start = n/2-i;
					for(int j =0; j <2*i+1;j++) {
						answer += graph[i][start + j];
					}
			}
			
			for(int i = 0; i < n; i++) answer += graph[n/2][i];
			
			for(int i = 0; i < n/2; i++) {
				int start = n/2-i;
				for(int j =0; j <2*i+1;j++) {
					answer += graph[n-1-i][start + j];
				}
			}
			
			
			System.out.printf("#%d %d\n", tc, answer);
		}
	}
}