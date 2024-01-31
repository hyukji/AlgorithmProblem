import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] nums;
	static int diff = Integer.MAX_VALUE;
	static int ans = -1;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		comb(0, 0, 0, 3);
		
		System.out.println(ans);
	}
	
	static void comb(int s, int cnt, int sum, int R) {
		if(diff == 0 || sum > M) {
			return;
		}
		if(cnt == R) {
			if(diff > Math.abs(M - sum)) {
				diff = Math.min(diff, Math.abs(M - sum));
				ans = sum;
			}
			return;
		}
		
		for(int i = s; i < N; i++) {
			comb(i + 1, cnt + 1, sum + nums[i], R);
		}
	}
}