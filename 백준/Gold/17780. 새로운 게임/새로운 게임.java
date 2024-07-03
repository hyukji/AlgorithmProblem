import java.io.*;
import java.util.*;

public class Main {

    private static final int[][] directions = new int[][] {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    private static int[][] graph;
    private static Deque<Integer>[][] locations;
    private static int[][] pieces;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        graph = new int[n][n];
        locations = new ArrayDeque[n][n];
        pieces = new int[k][3];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                locations[i][j] = new ArrayDeque<>();
            }
        }

        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int d = Integer.parseInt(st.nextToken())-1;
            locations[r][c].offer(i);
            pieces[i] = new int[] {r, c, d};
        }

        int turn = 1;
        for(; turn < 1002; turn++) {
            for(int p = 0; p < k; p++) {
                int r = pieces[p][0], c = pieces[p][1], d = pieces[p][2];
                if(locations[r][c].peekFirst() != p) continue;

                int nr = r + directions[d][0];
                int nc = c + directions[d][1];

                if(nr < 0 || nr >= n || nc < 0 || nc >= n || graph[nr][nc] == 2) {
                    int nd = (d % 2 == 0) ? d+1 : d-1;
                    nr = r + directions[nd][0];
                    nc = c + directions[nd][1];

                    pieces[p][2] = nd;
                    if(nr < 0 || nr >= n || nc < 0 || nc >= n || graph[nr][nc] == 2) {
                        pieces[p][2] = (d % 2 == 0) ? d+1 : d-1;
                        continue;
                    }
                    else if(graph[nr][nc] == 0) moveWhite(r, c, nr, nc);
                    else if(graph[nr][nc] == 1) moveRed(r, c, nr, nc);
                }
                else if(graph[nr][nc] == 0) moveWhite(r, c, nr, nc);
                else if(graph[nr][nc] == 1) moveRed(r, c, nr, nc);

//                printTurn(turn, p, n);

                if(locations[nr][nc].size() >= 4) {
                    System.out.println(turn);
                    return;
                }
            }
        }
        System.out.println(-1);
    }

    private static void moveWhite(int r, int c, int nr, int nc) {
        int size = locations[r][c].size();
        for(int i = 0; i < size; i++) {
            int np = locations[r][c].pollFirst();
            locations[nr][nc].offer(np);
            pieces[np] = new int[] {nr, nc, pieces[np][2]};
        }
    }

    private static void moveRed(int r, int c, int nr, int nc) {
        int size = locations[r][c].size();
        for(int i = 0; i < size; i++) {
            int np = locations[r][c].pollLast();
            locations[nr][nc].offer(np);
            pieces[np] = new int[] {nr, nc, pieces[np][2]};
        }
    }

    private static void printTurn(int turn, int p, int n) {
        System.out.println("turn : " + turn + " , piece : " + p);
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(locations[i][j]);
            }
            System.out.println();
        }

        for(int i = 0; i < n; i++) System.out.println(Arrays.toString(pieces[i]));
        System.out.println();
    }

}