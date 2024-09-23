import java.util.*;
import java.io.*;

class Main {

    static int[] dr = {-1,0,0,1};
    static int[] dc = {0,-1,1,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        boolean[][] check = new boolean[n][n];
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        int[][] place = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                place[i][j] = Integer.parseInt(st.nextToken());
                if(place[i][j] == 9) {
                    place[i][j] = 0;
                    queue.add(new int[] {i, j});
                    check[i][j] = true;
                }
            }
        }

        int result = 0;
        int time = 0;
        int shark = 2;
        int eaten = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            int r =  Integer.compare(o1[0], o2[0]);
            if(r == 0) r = Integer.compare(o1[1], o2[1]);
            return r;
        });

        while(!queue.isEmpty()) {
            time++;
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int r = cur[0], c = cur[1];

                for(int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                    if(check[nr][nc]) continue;

                    if(place[nr][nc] > 0) {
                        if(place[nr][nc] > shark) continue;
                        else if(place[nr][nc] < shark) {
                            pq.add(new int[] {nr, nc});
                        }
                    }

                    queue.offer(new int[] {nr, nc});
                    check[nr][nc] = true;
                }
            }

            if(!pq.isEmpty()) {
                int[] loc = pq.poll();
                int r = loc[0], c = loc[1];

                place[r][c] = 0;
                if(++eaten == shark) {
                    shark++;
                    eaten = 0;
                }

                queue.clear();
                check = new boolean[n][n];
                pq.clear();

                place[r][c] = 0;
                queue.add(new int[] {r, c});
                check[r][c] = true;

                result += time;
                time = 0;
            }
        }

        System.out.println(result);
    }

}