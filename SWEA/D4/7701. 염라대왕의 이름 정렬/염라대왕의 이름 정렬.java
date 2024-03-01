import java.io.*;
import java.util.*;

public class Solution  {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			int n = Integer.parseInt(br.readLine());

			TreeSet<String> set = new TreeSet<>((o1, o2) -> {
				int r = Integer.compare(o1.length(), o2.length());
				if(r == 0) r = o1.compareTo(o2);
				return r;
			});
			for(int i = 0; i < n; i++) set.add(br.readLine());
			
			sb.append("#").append(tc).append("\n");
			while(!set.isEmpty()) sb.append(set.pollFirst()).append("\n");
		}
		
		System.out.print(sb);
	}


}