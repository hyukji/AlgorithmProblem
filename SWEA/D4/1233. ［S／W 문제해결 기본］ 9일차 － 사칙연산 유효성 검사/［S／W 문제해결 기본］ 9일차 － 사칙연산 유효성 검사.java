import java.io.*;
import java.util.*;

public class Solution  {

	static int[] graph;
	static BufferedReader br;
	static StringTokenizer st;
	
	
	static final String opts = "+-/*";
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		st = null;
		
		for(int tc = 1; tc < 11; tc++) {
			int answer = solve();
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.print (sb);
	}

	private static int solve() throws Exception {
		int n = Integer.parseInt(br.readLine());
		int answer = 1;
		for(int i = 0; i < n; i++) {
			if(answer == 0) {
				br.readLine();
				continue;
			}
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			if(st.countTokens() == 1 && opts.contains(st.nextToken())) answer = 0;
			if(st.countTokens() == 3 && !opts.contains(st.nextToken())) answer = 0;
		}
		return answer;
	}

		
}