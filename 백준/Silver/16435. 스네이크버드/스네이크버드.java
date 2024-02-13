import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		
		int[] graph = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			graph[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(graph);

		for(int i = 0; i < n; i++) {
			if(graph[i] > l) break;
			l++;
		}
		
		System.out.println(l);
	}

}