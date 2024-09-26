import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] graph;
    static List<int[]> viruses;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][n];
        viruses = new ArrayList<>();
        for(int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < n; c++) {
                graph[r][c] = Integer.parseInt(st.nextToken());
                if(graph[r][c] == 2) {
                    viruses.add(new int[] {r, c});
                    graph[r][c] = 0;
                }
            }
        }

        comb(0, 0, new int[m]);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

    }

    private static void comb(int idx, int cnt, int[] combArr) {
        if(cnt == m) {
            int r = getDist(combArr);
//            System.out.println(Arrays.toString(combArr) + " " + r);
            answer = Integer.min(r, answer);
            return;
        }

        for(int i = idx; i < viruses.size(); i++) {
            combArr[cnt] = i;
            comb(i+1, cnt+1, combArr);
        }
    }

    private static int getDist(int[] combArr) {
        int[][] nGraph = new int[n][n];
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                nGraph[r][c] = graph[r][c];
            }
        }

        ArrayDeque<int[]> dq = new ArrayDeque<>();
        for(int i : combArr) {
            int[] virus = viruses.get(i);
            int r = virus[0];
            int c = virus[1];

            nGraph[r][c] = 2;
            dq.offer(new int[] {r, c});
        }

        int time = 0;
        while(!dq.isEmpty()) {
            time++;
            int size = dq.size();
            for(int i = 0; i < size; i++) {
                int[] cur = dq.pollFirst();
                int r = cur[0];
                int c = cur[1];

                for(int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                    if(nGraph[nr][nc] == 0) {
                        dq.offer(new int[] {nr, nc});
                        nGraph[nr][nc] = 2;
                    }

                }
            }
        }


        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                if(nGraph[r][c] == 0) return Integer.MAX_VALUE;
            }
        }

        return time - 1;
    }

}