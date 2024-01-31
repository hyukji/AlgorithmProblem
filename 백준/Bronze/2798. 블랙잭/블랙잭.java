import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int[] graph;
	static int answer;
	static int ansDiff = Integer.MAX_VALUE;
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new int[n];

		st = new StringTokenizer(br.readLine(), " ");
		for(int i =0; i < n; i++) {
			graph[i] = Integer.parseInt(st.nextToken());
		}
		
		comb(0, 0);
		
		System.out.print(answer);
	}

	private static void comb(int cnt, int start) {
		if(cnt == 3) {
			int diff =m - result;
			if(diff >= 0 && diff < ansDiff) {
				ansDiff =diff;
				answer = result;
			}
			return;
		}
		
		for(int i = start; i < n; i++) {
			result += graph[i];
			comb(cnt+1, i+1);
			result -=  graph[i];
		}
	}
	
}