import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static int[] graph;
	static int[] sumArr;
	static StringBuilder sb  =  new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		graph = new int[n];
		for(int i = 0; i < n; i++) {
			graph[i] = Integer.parseInt(st.nextToken());
		}

		sumArr = new int[n+1];
		for(int i = 1; i < n+1; i++) {
			sumArr[i] = sumArr[i-1] +graph[i-1];
		}

		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			System.out.println(sumArr[r] - sumArr[l-1]);
		}
	}
	
}
