import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] graph = new int[m][3];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken());

            graph[i] = new int[] {s, e, v};
        }

        long[] dists = new long[n];
        Arrays.fill(dists, Integer.MAX_VALUE);
        dists[0] = 0;

        for(int i = 0; i < n; i++) {
            for (int[] edge : graph) {
                int s = edge[0];
                int e = edge[1];
                int v = edge[2];

                if (dists[s] != Integer.MAX_VALUE && dists[e] > dists[s] + v) {
                    dists[e] = dists[s] + v;
                    if (i == n - 1) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        for(int i = 1; i < n; i++) {
            sb.append(dists[i] == Integer.MAX_VALUE ? -1 : dists[i]).append("\n");
        }
        System.out.print(sb);
    }
}