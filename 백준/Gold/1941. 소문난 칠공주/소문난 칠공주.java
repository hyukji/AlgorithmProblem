import java.io.*;
import java.util.*;

public class Main {

    static int[][] seats;
    static int answer;

    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        seats = new int[5][5];
        for(int r = 0; r < 5; r++) {
            char[] row = br.readLine().toCharArray();
            for(int c = 0; c < 5; c++) {
                seats[r][c] = (row[c] == 'S') ? 1 : 0;
            }
        }

        dfs(0, 0, new int[7]);

        System.out.println(answer);
    }

    private static void dfs(int s, int cnt, int[] selected) {
        if(cnt == 7) {
            if(check(selected)) answer++;
            return;
        }

        for(int i = s; i < 25; i++) {
            selected[cnt] = i;
            dfs(i+1, cnt+1, selected);
        }
    }


    private static boolean check(int[] selected) {
        int cnt = 0;

        boolean[][] graph = new boolean[5][5];
        boolean[][] visited = new boolean[5][5];

        for(int i = 0; i < 7; i++) {
            int v = selected[i];
            int r = v / 5;
            int c = v % 5;
            cnt += seats[r][c];
            graph[r][c] = true;
        }

        int near = 0;
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[] {selected[0] / 5, selected[0] % 5});

        while(!dq.isEmpty()) {
            int[] loc = dq.pop();
            int r = loc[0];
            int c = loc[1];

            for(int[] direction : directions) {
                int nr = r + direction[0];
                int nc = c + direction[1];

                if(nr < 0 || nr >=5 || nc < 0 || nc >= 5) continue;
                if(visited[nr][nc]) continue;
                visited[nr][nc] = true;

                if(!graph[nr][nc]) continue;
                near++;
                dq.offerLast(new int[] {nr, nc});
            }

        }

        return cnt >= 4 && near == 7;
    }

}