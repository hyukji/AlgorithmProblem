import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static char[][] graph;
	
	static int[] dr = {0, 0, 1, 1};
	static int[] dc = {0, 1, 0, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		graph = new char[n][n];
		for(int r = 0; r < n; r++) {
			String row = br.readLine();
			for(int c = 0; c < n; c++) {
				graph[r][c] = row.charAt(c);
			}
		}
		
		System.out.println(quadTree(0, 0, n));
	}

	private static String quadTree(int r, int c, int m) {
		if(m == 1)  return "" + graph[r][c];

		String result = "";
		for(int i = 0; i < 4; i++) {
			result += quadTree(r + dr[i] * m/2, c + dc[i] * m/2, m/2);
		}

		if(result.length() == 4) {
			for(int i = 0; i < 3; i++) {
				if(result.charAt(i) != result.charAt(i+1)) return "(" + result + ")";
			}
			 return "" + result.charAt(0);
		}
		
		return "(" + result + ")";
		
	}

}