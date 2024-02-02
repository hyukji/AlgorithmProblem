import java.io.*;
import java.util.*;

public class Main {
	
	static class Element implements Comparable<Element> {
		int value;
		int count;

		public Element(int value, int count) {
			this.value = value;
			this.count = count;
		}

		@Override
		public int compareTo(Element o) {
			if( Integer.compare(count, o.count) != 0)  return Integer.compare(count, o.count);
			return Integer.compare(value, o.value);
		}
	}
	
	
	static int tr, tc, k, time = 0, answer = -1;
	static int[][] graph = new int[101][101];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		tr = Integer.parseInt(st.nextToken());
		tc = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for(int r = 1; r < 4; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c = 1; c < 4; c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int rSize = 4, cSize = 4;
		while(time  < 101) {
			
			if(graph[tr][tc] == k) {
				answer = time;
				break;
			}
			
			if(cSize - rSize > 0) { // c 정렬
				int maxSize = 0;
				for(int c = 1; c < cSize;c++) {
					int[] visited = new int[101];
					for(int r = 1; r <rSize; r++) visited[graph[r][c]]++;
					
					List<Element> elements = new ArrayList<>();
					for(int i = 1; i < 101; i++) {
						if(visited[i] > 0) elements.add(new Element(i, visited[i]));
					}
					
					Collections.sort(elements);
					
					for(int i = 0; i < elements.size(); i++) {
						graph[i*2+1][c] = elements.get(i).value;
						graph[i*2+2][c] = elements.get(i).count;
					}
					
					if(rSize > 2*elements.size()+1) {
						for(int r = 2*elements.size()+1; r < rSize; r++)  graph[r][c] = 0;
					}
					maxSize = Math.max(maxSize, 2*elements.size()+1);
				}
				
				rSize = maxSize;
			} else { // r 정렬
				int maxSize = 0;
				for(int r = 1; r < rSize; r++) {
					int[] visited = new int[101];
					for(int c = 1; c <cSize; c++) visited[graph[r][c]]++;
					
					List<Element> elements = new ArrayList<>();
					for(int i = 1; i < 101; i++) {
						if(visited[i] > 0) elements.add(new Element(i, visited[i]));
					}
					
					Collections.sort(elements);
					
					for(int i = 0; i < elements.size(); i++) {
						graph[r][i*2+1] = elements.get(i).value;
						graph[r][i*2+2] = elements.get(i).count;
					}
					
					if(cSize > 2*elements.size()+1) {
						for(int c = 2*elements.size()+1; c <= cSize; c++)  graph[r][c] = 0;
					}
					maxSize = Math.max(maxSize, 2*elements.size()+1);
				}
				
				cSize = maxSize;
			}
			time++;
			
		}
		
		System.out.println(answer);
	}

}