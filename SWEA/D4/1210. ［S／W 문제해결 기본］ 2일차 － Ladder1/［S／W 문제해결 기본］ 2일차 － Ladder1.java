import java.io.*;
import java.util.*;

public class Solution {

	static int[][] graph;
	static final int[] dc = {-1, 1};
	
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader br = new BufferedReader(new FileReader("res/input_1210.txt"));
		
		for(int tc = 1; tc <= 10; tc++) {
			br.readLine();
			graph = new int[100][100];
			for(int r = 0; r <99; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int c = 0; c < 100; c++) {
					graph[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			int target = 0;
			st = new StringTokenizer(br.readLine(), " ");
			for(int c = 0; c < 100; c++) {
				int v = Integer.parseInt(st.nextToken());
				graph[99][c] = v;
				if(v == 2) target = c;
			}
			
			for(int r = 98; r >= 0; r--) {
				for(int d = 0; d < 2; d++) {
					int nc = target + dc[d];
					if(nc < 0 || nc >= 100) continue;
					
					int v = graph[r][nc];
					if(v == 0) continue;
					while(v == 1) {
						target = nc;
						nc = target + dc[d];
						if(nc < 0 || nc >= 100) break;
						v = graph[r][nc];
					}
					break;
				}
			}
			
			System.out.printf("#%d %d\n", tc, target);
		}
		
	}

}
