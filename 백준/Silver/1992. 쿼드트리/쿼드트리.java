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
		if(m == 1)  return Character.toString(graph[r][c]); // 압축할 것이 없음 -> 그대로 반환

		StringBuilder result = new StringBuilder();
		result.append("(");
		int half = m/2;
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i] * half;
			int nc = c + dc[i] * half;
			result.append(quadTree(nr, nc, half)); // 4개로 쪼개서 해결 -> result에는 4개로 쪼갠 각각의 결과가 저장됨
		}
		result.append(")");
		
		 // 압축이 가능하려면 각각이 4가지의 결과가 0 or 1이어야 하며, 모두 같아야 함.
		if(result.length() != 6) return result.toString(); // 괄호를 고려하면 result의 크기가 6
		for(int i = 1; i < 4; i++) {
			if(result.charAt(i) != result.charAt(i+1)) return result.toString();  // 쪼갠 결과가 같지 않다면
		}
		
		 return Character.toString(result.charAt(1)); // 압축 성공!
	}

}