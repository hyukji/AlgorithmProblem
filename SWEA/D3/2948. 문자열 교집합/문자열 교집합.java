import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			Set<String> set = new HashSet<>();

			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) set.add(st.nextToken());

			int answer = 0;
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < m; i++) if(set.contains(st.nextToken())) answer++;
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
			
		}
		
		System.out.print(sb);
	}

}