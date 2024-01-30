import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static int m;
	static int[] visited;
	static int[] answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		visited = new int[n+1];
		answer = new int[m];
		
		perm(0);

	}

	private static void perm(int depth) {
		if(depth == m) {
			for(int i = 0; i < m; i++) {
				System.out.print(answer[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = 1; i < n+1; i++) {
			if(visited[i] == 1) continue;
			visited[i] = 1;
			answer[depth] = i;
			perm(depth+1);
			visited[i] = 0;
		}

	}

}