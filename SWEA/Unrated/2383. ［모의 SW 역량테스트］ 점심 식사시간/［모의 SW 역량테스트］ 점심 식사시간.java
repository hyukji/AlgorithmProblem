import java.io.*;
import java.util.*;

public class Solution {

	static int n, answer;
	static int[][] graph, people = new int[10][2], stairs = new int[2][3], startTimes = new int[2][10];
	static boolean[] visited;
	static int pCnt, sCnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			n = Integer.parseInt(br.readLine());
			pCnt = 0;
			sCnt = 0;
			answer = Integer.MAX_VALUE;
			for(int r = 0; r < n; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < n; c++) {
					int v = Integer.parseInt(st.nextToken());
					if(v == 0) continue;
					else if(v== 1) {
						people[pCnt][0] = r;
						people[pCnt++][1] = c;
					} else {
						stairs[sCnt][0] = r;
						stairs[sCnt][1] = c;
						stairs[sCnt++][2] = v;
					}
				}
			}
			
			for(int p = 0; p < pCnt; p++) {
				int pr = people[p][0];
				int pc = people[p][1];
				for(int s = 0; s < sCnt; s++) {
					int sr = stairs[s][0];
					int sc = stairs[s][1];
					int start = Math.abs(pr - sr) + Math.abs(pc - sc) + 1;
				
					startTimes[s][p] = start; 
				}
			}
			
			visited = new boolean[pCnt];
			dfs(0);

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);
	}

	private static void dfs(int idx) {
		if(idx == pCnt) { 
			answer = Math.min(answer, Math.max(cal0(), cal1()));
			return;
		}
		
		for(int i = idx; i < pCnt; i++) {
			visited[i] = true;
			dfs(i+1);
			visited[i] =false;
			dfs(i+1);
		}
	}

	private static int cal0() {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 0; i < pCnt; i++) {
			if(visited[i]) pq.add(startTimes[0][i]);
		}
		
		if(pq.isEmpty()) return 0;
		
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		while(!pq.isEmpty()) {
			int cur = pq.poll();
			if(dq.size() > 2) {
				int complete = dq.poll() + stairs[0][2];
				if(cur <= complete) cur = complete;
			}
			dq.add(cur);
		}
		
		return dq.pollLast() + stairs[0][2];
	}

	private static int cal1() {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 0; i < pCnt; i++) {
			if(!visited[i]) pq.add(startTimes[1][i]);
		}
		
		if(pq.isEmpty()) return 0;
		
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		while(!pq.isEmpty()) {
			int cur = pq.poll();
			if(dq.size() > 2) {
				int complete = dq.poll() + stairs[1][2];
				if(cur <= complete) cur = complete;
			}
			dq.add(cur);
		}
		
		return dq.pollLast() + stairs[1][2];
	}

}