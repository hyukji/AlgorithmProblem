import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] graph;
	static int n, m;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		graph = new int[n][m];
		
		for(int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < m; c++) {
				graph[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());

		
		while(--R >= 0) {
			int opt = Integer.parseInt(st.nextToken());
			switch (opt) {
			case 1:
				opt1();
				break;
			case 2:
				opt2();
				break;
			case 3:
				opt3();
				break;
			case 4:
				opt4();
				break;
			case 5:
				opt5();
				break;
			case 6:
				opt6();
				break;
			default:
				break;
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int[] row :graph) {
			for(int v : row) {
				sb.append(v).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

	private static void opt2() {
		int[][] nGraph = new int[n][m];
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < m; c++) {
				nGraph[r][c] = graph[r][m-1-c];
			}
		}
		
		graph = nGraph;
	}

	private static void opt1() {
		int[][] nGraph = new int[n][m];
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < m; c++) {
				nGraph[r][c] = graph[n-1-r][c];
			}
		}
		
		graph = nGraph;
	}
	private static void opt3() {
		int[][] nGraph = new int[m][n];
		for(int r = 0; r < m; r++) {
			for(int c = 0; c < n; c++) {
				nGraph[r][c] = graph[n-1-c][r];
			}
		}
		int temp = n;
		n = m; m = temp;
		graph = nGraph;
	}	
	private static void opt4() {
		int[][] nGraph = new int[m][n];
		for(int r = 0; r < m; r++) {
			for(int c = 0; c < n; c++) {
				nGraph[r][c] = graph[c][m-1-r];
			}
		}

		int temp = n;
		n = m; m = temp;
		graph = nGraph;
	}	
	private static void opt5() {
		int[][] nGraph = new int[n][m];
		for(int r = 0; r < n/2; r++) {
			for(int c = 0; c < m/2; c++) {
				nGraph[r][c] = graph[r+n/2][c];
			}
		}
		for(int r = 0; r < n/2; r++) {
			for(int c = m/2; c < m; c++) {
				nGraph[r][c] = graph[r][c-m/2];
			}
		}
		for(int r = n/2; r < n; r++) {
			for(int c = m/2; c < m; c++) {
				nGraph[r][c] = graph[r-n/2][c];
			}
		}		
		for(int r = n/2; r < n; r++) {
			for(int c = 0; c < m/2; c++) {
				nGraph[r][c] = graph[r][c+m/2];
			}
		}
		graph = nGraph;
	}	
	private static void opt6() {
		int[][] nGraph = new int[n][m];
		for(int r = 0; r < n/2; r++) {
			for(int c = 0; c < m/2; c++) {
				nGraph[r][c] = graph[r][c+m/2];
			}
		}
		for(int r = 0; r < n/2; r++) {
			for(int c = m/2; c < m; c++) {
				nGraph[r][c] = graph[r+n/2][c];
			}
		}
		for(int r = n/2; r < n; r++) {
			for(int c = m/2; c < m; c++) {
				nGraph[r][c] = graph[r][c-m/2];
			}
		}		
		for(int r = n/2; r < n; r++) {
			for(int c = 0; c < m/2; c++) {
				nGraph[r][c] = graph[r-n/2][c];
			}
		}
		graph = nGraph;
	}
}