import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		boolean[][] graph = new boolean[101][101];
		int answer = n * 100;
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			for(int nr = r; nr < r+10; nr++) {
				for(int nc = c; nc < c +10; nc++) {
					if(graph[nr][nc])  answer--;
					else graph[nr][nc] = true;
				}
			}
		}
		
		System.out.println(answer);

	}

}