import java.io.*;
import java.util.*;

public class Main {

	static int[][] graph = new int[6][3];
	static int[][] nGraph ;
	
	static int[] da = {0, 1, 2};
	static int[] db = {2, 1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		for(int tc = 1; tc < 5; tc++) {
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 3; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			nGraph = new int[6][3];
			int result = 0;
			if(dfs(0, 1)) result++;
			
			sb.append(result).append(" ");
		}
		
		System.out.println(sb);
	}
	
	private static boolean dfs(int a, int b) {
		if(b == 6) { a++; b = a+1; }
		if(a == 5)  return check();
	
		for(int i = 0; i < 3; i++) {
			nGraph[a][i]++;
			nGraph[b][2-i]++;
			if(dfs(a, b+1)) return true;
			nGraph[a][i]--;
			nGraph[b][2-i]--;
		}
		return false;
	}
	
	private static boolean check() {
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 3; j++) {
				if(graph[i][j] != nGraph[i][j]) return false;
			}
		}
		return true;
	}


}