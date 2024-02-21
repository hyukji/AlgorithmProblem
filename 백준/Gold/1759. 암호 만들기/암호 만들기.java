import java.io.*;
import java.util.*;

public class Main {

	static char[] graph;
	static int l, c, vowelLimit;

	static final String vowels = "aeiou";
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		vowelLimit = l - 2;

		graph = new char[c];
		String row= br.readLine();
		for (int i = 0; i < c; i++) {
			graph[i] = row.charAt(i*2);
		}
		Arrays.sort(graph);
		
		dfs(0, 0, 0, "");
		
		System.out.println(sb);
	}

	private static void dfs(int idx, int cnt, int vCnt, String result) {
		if(cnt == l) {
			if(vCnt > 0) sb.append(result).append("\n");
			return;
		}
		
		for(int i = idx; i <c; i++) {
			boolean isVowel = vowels.indexOf(graph[i]) > -1;
			if(!isVowel) dfs(i+1, cnt+1, vCnt, result+graph[i]);
			else if(vCnt < vowelLimit) dfs(i+1, cnt+1, vCnt+1, result+graph[i]);
		}
		
	}

}