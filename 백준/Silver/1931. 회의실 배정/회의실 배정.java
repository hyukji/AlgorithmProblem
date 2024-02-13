import java.io.*;
import java.util.*;

public class Main {
	
	static class Meeting implements Comparable<Meeting>{
		int start;
		int end;
		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Meeting o) {
				int r =  Integer.compare(end, o.end);
				if(r == 0) r =  Integer.compare(start, o.start);
				return r;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());
		Meeting[] graph = new Meeting[n];
		for(int i =0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			graph[i] = new Meeting(s, e);
		}
		
		Arrays.sort(graph);
        
		int size = 0, e = 0;
		for(int i = 0; i < n; i++) {
			Meeting m = graph[i];
			if(m.start < e) continue;
			size++;
			e = m.end;
		}
		
		System.out.println(size);
	}

}