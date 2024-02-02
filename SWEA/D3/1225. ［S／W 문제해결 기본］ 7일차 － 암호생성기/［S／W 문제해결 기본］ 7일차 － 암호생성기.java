import java.io.*;
import java.util.*;

public class Solution {

	static int n;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		for(int tc = 1; tc<=10; tc++) {
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			
			Deque<Integer> queue = new ArrayDeque<>();
			for(int i = 0; i < 8; i++) {
				queue.offer(Integer.parseInt(st.nextToken()));
			}

			for(int i = 1;; i = (i%5)+1) {
				int updated = queue.poll() - i;
				if(updated < 0) updated = 0;
				queue.offer(updated);
				if(updated == 0) break;
			}
			
			sb.append("#").append(tc).append(" ");
			for(int v : queue) sb.append(v).append(" ");
			sb.append("\n");
		}
		System.out.print(sb);
	}

}