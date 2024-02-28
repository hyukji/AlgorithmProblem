import java.io.*;
import java.util.*;

public class Main {

	static int n, graph[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		graph = new int[n];
		for(int i = 0; i< n; i++) graph[i] = Integer.parseInt(br.readLine());
		
		int answer = 0;
		for(int i = n-1; i >= 0; i--) {
			if(graph[i] > k) continue;
			
			int cnt = k / graph[i];
			answer += cnt;
			k -= graph[i] * cnt;
		}
		
		System.out.println(answer);
	}
}