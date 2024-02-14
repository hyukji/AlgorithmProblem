import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static char[][] graph;
	
	static final int[] dr = {0, 0, 1, 1};
	static final int[] dc = {0, 1, 0, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
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

	/*
	 * r, c : quadTree 압축의 시작 좌표
	 * m : quadTree 압축을 위한 한 변의 길이
	 */
	private static String quadTree(int r, int c, int m) {
		if(canArchive(r, c, m)) {
			return Character.toString(graph[r][c]); // 압축이 가능하다면 압축!
		}
		
		StringBuilder result = new StringBuilder();
		result.append("(");
		int half = m/2;
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i] * half;
			int nc = c + dc[i] * half;
			result.append(quadTree(nr, nc, half)); // 4개로 쪼개서 해결
		}
		result.append(")");
		
		return result.toString(); 
	}

	private static boolean canArchive(int r, int c, int m) {
		char v = graph[r][c];
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < m; j++) {
				if(v != graph[r+i][c+j]) return false;
			}
		}
		return true;
	}

}