import java.io.*;
import java.util.*;

public class Solution  {
	
	static final int[] dr = {0, 1, 0, -1};
	static final int[] dc = {1, 0, -1, 0};

	static Cell[][] graph ; 
	static int n, m, k;
	
	static class Cell {
		int r;
		int c;
		boolean status; // 0, 1
		int life;
		int age;
		int createdAt;
		
		public Cell(int r, int c, int life, int createdAt) {
			super();
			this.age = 0;
			this.status = false;
			this.r = r;
			this.c = c;
			this.life = life;
			this.createdAt = createdAt;
		}

		public void plusAge() {
			age++;
			if(age == life && !status) {
				status = true;
				age = 0;
			}
		}
		
		public boolean extend() {
			return status == true && age == 0;
		}
		
		public boolean alive() {
			return !(status == true && age >= life);
		}

		@Override
		public String toString() {
			return "Cell [r=" + r + ", c=" + c + ", status=" + status + ", life=" + life + ", age=" + age
					+ ", createdAt=" + createdAt + "]";
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc < T+1; tc++) {
			st = new StringTokenizer(br.readLine());
			n =Integer.parseInt(st.nextToken());
			m =Integer.parseInt(st.nextToken());
			k =Integer.parseInt(st.nextToken());
			
			graph = new Cell[800][800];
			ArrayDeque<Cell> dq = new ArrayDeque<>();
			
			for(int r = 0; r < n; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c< m; c++) {
					int life = Integer.parseInt(st.nextToken());
					if(life > 0) {
						Cell cell = new Cell(400+r, 400+c, life, 0);
						graph[400+r][400+c] = cell;
						dq.offer(cell);
					}
				}
			}
			
			for(int time = 1; time < k+1; time++) {
				int size = dq.size();
				for(int i = 0; i < size; i++) {
					Cell cell = dq.poll();
					int r = cell.r; int c = cell.c;
					
					if(cell != graph[r][c]) continue; // 확장 시 생기는 중복 방지
					
					if(cell.extend()) { // 확장
						for(int d = 0; d <4; d++) {
							int nr = r + dr[d];
							int nc = c +dc[d];
							Cell nCell = new Cell(nr, nc, cell.life, time);
							if(graph[nr][nc] == null) {
								graph[nr][nc] = nCell;
								dq.offer(nCell);
							}
							else if(graph[nr][nc].createdAt == time && graph[nr][nc].life < cell.life) {
								graph[nr][nc] = nCell;
								dq.offer(nCell);
							}
						}
					}
					
					cell.plusAge();
					if(!cell.alive()) continue; // 죽음
					dq.offer(cell);
					
				}
			}	

			int answer = 0;
			while(dq.size() > 0) {
				Cell cell = dq.poll();
				if(graph[cell.r][cell.c]== cell) answer++;
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}