import java.io.*;
import java.util.*;

public class Main {
	
	static int[] graph, visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int n =  Integer.parseInt(st.nextToken());
		int d =  Integer.parseInt(st.nextToken());
		int k =  Integer.parseInt(st.nextToken());
		int c =  Integer.parseInt(st.nextToken());
		
		graph = new int[n];
		visited = new int[d+1];
		
		for (int i = 0; i < n; i++)  graph[i] = Integer.parseInt(br.readLine());

		int answer = 1;
		visited[c]++;
		for(int i = 0; i < k; i++) {
			if(visited[graph[i]]++ == 0) answer++; 
		}

		int count = answer;
		for(int i = 0; i < n; i++) {
			if(visited[graph[i]]-- == 1) count--;
			if(visited[graph[(i+k)%n]]++ == 0) count++;
			answer = Math.max(answer, count);
			if(answer == k+1) break;
		}
		
		System.out.println(answer);
	}

}