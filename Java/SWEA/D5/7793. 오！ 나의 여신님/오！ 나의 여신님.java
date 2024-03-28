import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Solution {
	
    static final int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static int n, m;
    static char[][] graph;

    static String solve() {
    	ArrayDeque<int[]> suyeons = new ArrayDeque<>();
    	ArrayDeque<int[]> devils = new ArrayDeque<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (graph[r][c] == '*') {
                    devils.add(new int[]{r, c});
                } else if (graph[r][c] == 'S') {
                    suyeons.add(new int[]{r, c});
                }
            }
        }

        int time = 0;
        while (!suyeons.isEmpty()) {
            time++;
            int devilSize = devils.size();
            for (int i = 0; i < devilSize; i++) {
                int[] pos = devils.poll();
                int r = pos[0];
                int c = pos[1];
                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if (nr < 0 || nc < 0 || nr >= n || nc >= m) {
                        continue;
                    }

                    char v = graph[nr][nc];
                    if (v == '.' || v == 'S') {
                        graph[nr][nc] = '*';
                        devils.add(new int[]{nr, nc});
                    }
                }
            }

            int suyeonsSize = suyeons.size();
            for (int i = 0; i < suyeonsSize; i++) {
                int[] pos = suyeons.poll();
                int r = pos[0];
                int c = pos[1];
                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if (nr < 0 || nc < 0 || nr >= n || nc >= m) {
                        continue;
                    }

                    char v = graph[nr][nc];
                    if (v == '.') {
                        graph[nr][nc] = '*';
                        suyeons.add(new int[]{nr, nc});
                    } else if (v == 'D') {
                        return " " + time;
                    }
                }
            }
        }

        return " GAME OVER";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();;
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
           graph = new char[n][m];
            
            for (int r = 0;r < n; r++) {
            	graph[r] = br.readLine().toCharArray();
            }
            
            sb.append("#").append(tc).append(solve()).append('\n');
        }
        System.out.println(sb);
    }
}