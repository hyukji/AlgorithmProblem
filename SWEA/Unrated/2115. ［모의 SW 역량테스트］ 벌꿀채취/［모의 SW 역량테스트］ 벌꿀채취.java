import java.io.*;
import java.util.*;

public class Solution  {
	
	static int n,m,capa;
	static int answer;
	static int BucketAmout;
	
	static int[][] graph;
	
	static class BeeBucket implements Comparable<BeeBucket> {
		int s, e, amount;

		public BeeBucket(int s, int e, int amount) {
			super();
			this.s = s;
			this.e = e;
			this.amount = amount;
		}

		@Override
		public String toString() {
			return "[s=" + s + ", e=" + e + ", amount=" + amount + "]";
		}

		@Override
		public int compareTo(BeeBucket o) {
			return -Integer.compare(amount, o.amount);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			capa = Integer.parseInt(st.nextToken());
			answer = 0;
			
			graph = new int[n][n];
			for(int r = 0; r < n; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < n; c++) {
					graph[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			int[] rowMax = new int[n];
			for(int r = 0; r < n; r++) {
				BeeBucket[] row = new BeeBucket[n-m+1];
				for(int c = 0; c < n-m+1; c++) {
					BucketAmout = 0;
					sub(r, c, 0, 0, 0);
					row[c] = new BeeBucket(c, c+m-1, BucketAmout);
				}
				
				Arrays.sort(row);
				rowMax[r] = row[0].amount;
				
				comb(row, 0, 0, 0);
			}
			
			Arrays.sort(rowMax);
			answer = Math.max(answer, rowMax[n-1] +rowMax[n-2]);
			
			sb.append("#").append(tc).append(" ").append(answer).append(" ").append("\n");
		}
		
		System.out.print(sb);
	}

	private static void comb(BeeBucket[] row, int idx, int cnt, int amount) {
		if(cnt == 2) {
			answer = Math.max(answer, amount);
			return;
		}

		for(int i = idx; i < row.length; i++) {
			comb(row, i+m, cnt+1, amount + row[i].amount);
			comb(row, i+1, cnt, amount);
		}
	}

	private static void sub(int r, int c, int i, int cnt, int amount) {
		if(cnt > capa) return;
		
		if(i == m) {
			BucketAmout = Math.max(BucketAmout, amount);
			return;
		}
		
		int v = graph[r][c + i];
		sub(r, c, i+1, cnt+v, amount + v*v);
		sub(r, c, i+1, cnt, amount);
	}

}