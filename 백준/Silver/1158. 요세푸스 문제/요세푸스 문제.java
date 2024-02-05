import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		List<Integer> ll = new LinkedList<>();  // 현재 위치보다 이전이면서 높이가 작은 타워는 불필요함

		for(int i = 0; i < n; i++) ll.add(i);
		
		int idx = k-1;
		sb.append("<").append(ll.remove(idx)+1);
		while(!ll.isEmpty()) {
			idx += k-1;
			if(idx >= ll.size()) idx %= ll.size();
			sb.append(", ").append(ll.remove(idx)+1);
		}
		
		for(int v : ll) sb.append(", ").append(v+1);
		sb.append(">");
		
		System.out.print (sb);
	}

}