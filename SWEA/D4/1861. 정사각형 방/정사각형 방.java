import java.io.*;
import java.util.*;

public class Solution  {

	static class Element implements Comparable<Element> {
		int r, c, v;

		public Element(int r, int c, int v) {
			this.r = r;
			this.c = c;
			this.v = v;
		}
		
		public int getDist(Element el) {
			return Math.abs(r - el.r) + Math.abs(c - el.c);
		}
		
		@Override
		public String toString() {
			return "Element [r=" + r + ", c=" + c + ", v=" + v + "]";
		}
		
		@Override
		public int compareTo(Element o) {
			return Integer.compare(v, o.v);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			int n = Integer.parseInt(br.readLine());
			PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.reverseOrder());
			for(int r =0; r < n; r++) { 
				st = new StringTokenizer(br.readLine());
				for(int c =0; c < n; c++) {
					pq.offer(new Element(r, c, Integer.parseInt(st.nextToken())));
				}
			}
			
			int cnt = 1;
			int maxCnt = 0;
			int ansV= 0;
			Element bef = pq.poll();
			while(!pq.isEmpty()) {
				Element el = pq.poll();
				if(el.getDist(bef) == 1) {
						if(++cnt >= maxCnt) {
							maxCnt = cnt;
							ansV = el.v;
						}
				}
				else cnt = 1;

				bef = el;
			}
			
			sb.append("#").append(tc).append(" ").append(ansV).append(" ").append(maxCnt).append("\n");
		}
		
		System.out.print (sb);
	}

}