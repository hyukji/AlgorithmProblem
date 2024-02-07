import java.io.*;
import java.util.*;

public class Main {
	
	static class Element implements Comparable<Element> {
		int absValue;
		int value;
		
		public Element(int value) {
			super();
			this.absValue = Math.abs(value);
			this.value = value;
		}

		@Override
		public int compareTo(Element o) {
			int r = Integer.compare(absValue, o.absValue);
			if(r==0) r = Integer.compare(value, o.value);
			return r;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		PriorityQueue<Element> pq = new PriorityQueue<>();
		
		int n = Integer.parseInt(br.readLine());
		while(n-- > 0) {
			int v = Integer.parseInt(br.readLine());
			if(v != 0) pq.offer(new Element(v));
			else {
				int out = 0;
				if(!pq.isEmpty()) out = pq.poll().value;
				sb.append(out).append("\n");
			}
		}
		
		System.out.println(sb);
	}

}