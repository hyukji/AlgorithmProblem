import java.io.*;
import java.util.*;

public class Main {
	
	static int[] graph = new int[9];
	static int[] result = new int[7];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 9; i++) {
			graph[i] = Integer.parseInt(br.readLine());
		}
		
		comb(0, 0, 0);
	}

	private static void comb(int bef, int cnt, int s) {
		if(cnt == 7) {
			if(s == 100) {
				for(int v : result) System.out.println(v);
			}
			return;
		}
		
		for(int i = bef; i < 9; i++) {
			result[cnt] = graph[i];
			comb(i+1, cnt+1, s + graph[i]);
		}
	}
	
}