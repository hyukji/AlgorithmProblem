import java.io.*;
import java.util.*;

public class Main {
	
	static int n,  m, k, answer = Integer.MAX_VALUE;
	static boolean[] visited;
	static int[] perm;
	static int[][] graph;
	static int[][] rGraph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		graph = new int[n][m];
		for(int r =0; r< n; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c =0; c< m; c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		rGraph = new int[k][3];
		for(int r =0; r< k; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c =0; c< 2; c++) {
				rGraph[r][c] = Integer.parseInt(st.nextToken())-1;
			}
			rGraph[r][2] = Integer.parseInt(st.nextToken());
		}

		perm = new int[k];
		visited = new boolean[k];
		permRotate(0);
		
		System.out.println(answer);
	}
	
	private static void permRotate(int cnt) {
		if(cnt == k) {
			int[][] nGraph = copyGraph();
			for(int i =0; i < k; i++)  rotate(nGraph, rGraph[perm[i]]);	
			int value = getMinRow(nGraph);
			if(answer > value) answer = value; 
			return;
		}
		
		for(int i =0; i < k; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			perm[cnt] = i;
			permRotate(cnt+1);
			visited[i] = false;
		}
	}

	private static int getMinRow(int[][] nGraph) {
		int minValue = Integer.MAX_VALUE;
		for(int r =0; r< n; r++) {
			int row = 0;
			for(int c =0; c< m; c++)  row += nGraph[r][c];
			if(minValue > row) minValue = row;
		}
		return minValue;
	}

	private static void rotate(int[][] nGraph, int[] rotation) {
		int r = rotation[0];
		int c = rotation[1];
		for(int s = 1; s <= rotation[2]; s++) {
			int temp = nGraph[r-s][c+s];
			
			// 위
			int nr = r-s;
			for(int nc = c+s; nc > c-s; nc--) {
				nGraph[nr][nc] = nGraph[nr][nc-1];
			}

			// 왼
			int nc = c-s;
			for(nr = r-s; nr < r+s; nr++) {
				nGraph[nr][nc] = nGraph[nr+1][nc];
			}

			// 아래
			nr = r+s;
			for(nc = c-s; nc < c+s; nc++) {
				nGraph[nr][nc] = nGraph[nr][nc+1];
			}

			// 오른
			nc = c+s;
			for(nr = r+s; nr > r-s; nr--) {
				nGraph[nr][nc] = nGraph[nr-1][nc];
			}
			nGraph[r-s+1][c+s] = temp;
		}
		
	}

	private static int[][] copyGraph() {
		int[][] nGraph = new int[n][m];
		for(int r =0; r< n; r++) {
			for(int c =0; c< m; c++) {
				nGraph[r][c] = graph[r][c];
			}
		}
		return nGraph;
	}

}