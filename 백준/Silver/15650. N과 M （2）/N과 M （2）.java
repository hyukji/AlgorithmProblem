import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int[] result;
	static StringBuilder sb  =  new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		result  = new int[m];
		
		comb(0, 1);
		
		System.out.print(sb);
	}

	private static void comb(int cnt, int start) {
		if(cnt == m) {
			for(int v : result) sb.append(v).append(" ");
			sb.append("\n");
			return;
		}
		
		for(int i = start; i <= n; i++) {
			result[cnt] = i;
			comb(cnt+1, i+1);
		}
		
	}
}
