import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		for(int tc = 1; tc < 11; tc++) {
			List<Integer> graph = new LinkedList<>();
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i =0; i<n; i++) {
				graph.add(Integer.parseInt(st.nextToken()));
			}
			
			int opts = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i =0; i<opts; i++) {
				String o = st.nextToken();
				int idx = Integer.parseInt(st.nextToken());
				int nums = Integer.parseInt(st.nextToken());
				for(int j = 0; j < nums; j++) {
					graph.add(idx+j, Integer.parseInt(st.nextToken()));
				}
			}
			
			
			sb.append("#").append(tc).append(" ");
			for(int i = 0; i < 10; i++) sb.append(graph.get(i)).append(" ");
			sb.append("\n");
		}
		System.out.print(sb);
		
	}
	
}