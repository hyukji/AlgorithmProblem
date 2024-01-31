import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int num[][];
	static int sum[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		num = new int[N+1][N+1];
		sum = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				num[i][j] = Integer.parseInt(st.nextToken());
				sum[i][j] = sum[i - 1][j] + num[i][j];
			}
		}
		
		int sy, sx, ey, ex, ans;
		for(int i = 1; i <= M; i++) {
			ans = 0;
			st = new StringTokenizer(br.readLine());
			sy = Integer.parseInt(st.nextToken()) - 1;
			sx = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());
			ex = Integer.parseInt(st.nextToken());
			for(int j = sx; j <= ex; j++) {
				ans += (sum[ey][j] - sum[sy][j]);
			}
			sb.append(ans + "\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}