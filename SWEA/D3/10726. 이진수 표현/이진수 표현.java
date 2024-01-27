import java.util.*;
import java.io.*;

public class Solution {

	static StringTokenizer st;
	static StringBuilder sb;
	static int n;
	static int m;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			int target = (1 << n) - 1;
			int result = target & m;
			String answer = (result == target) ? "ON" : "OFF";
			
			sb = new StringBuilder("#");
			sb.append(tc).append(" ").append(answer);
			
			System.out.println(sb);
		}
	}
	
}


