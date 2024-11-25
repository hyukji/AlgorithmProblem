import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};

    static int[] xdr = {1, 1, -1, -1};
    static int[] xdc = {-1, 1, 1, -1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] land = new int[n][n];
        for(int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < n; c++) {
                land[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[][] visited = new boolean[n][n];
        List<int[]> clouds = new ArrayList<>();
        clouds.add(new int[] {n-1, 0});
        clouds.add(new int[] {n-1, 1});
        clouds.add(new int[] {n-2, 0});
        clouds.add(new int[] {n-2, 1});

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken()) % n;

            for(int[] cloud : clouds) {
                int nr = cloud[0] + dr[d] * s;
                int nc = cloud[1] + dc[d] * s;

                cloud[0] = (nr + n) % n;
                cloud[1] = (nc + n) % n;
                land[cloud[0]][cloud[1]]++;
            }

            visited = new boolean[n][n];
            for(int[] cloud : clouds) {
                int r = cloud[0];
                int c = cloud[1];
                for(int dd = 0; dd < 4; dd++) {
                    int nr = r + xdr[dd];
                    int nc = c + xdc[dd];
                    if(nr < 0 || nc < 0 || nr >= n || nc >= n) continue;

                    if(land[nr][nc] > 0) land[r][c]++;
                }

                visited[r][c] = true;
            }

            clouds = new ArrayList<>();
            for(int r = 0; r < n; r++) {
                for(int c = 0; c < n; c++) {
                    if(visited[r][c]) continue;
                    if(land[r][c] >= 2) {
                        clouds.add(new int[]{r, c});
                        land[r][c] -= 2;
                    }
                }
            }

        }


        int answer = 0;
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                answer += land[r][c];
            }
        }

        System.out.println(answer);
    }
}