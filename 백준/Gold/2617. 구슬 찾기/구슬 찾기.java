import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<Integer>[] largeTree = new HashSet[n];
        Set<Integer>[] smallTree = new HashSet[n];

        for(int i = 0; i < n; i++) {
            largeTree[i] = new HashSet<>();
            smallTree[i] = new HashSet<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken()) - 1;

            largeTree[s].add(l);
            smallTree[l].add(s);
        }

        int answer = 0;
        for(int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            visited[i] = true;
            if(dfs(i, largeTree, visited)-1 > n/2) {
                answer++;
                continue;
            }

            visited = new boolean[n];
            visited[i] = true;
            if(dfs(i, smallTree, visited)-1 > n/2) {
                answer++;
            }
        }

        System.out.println(answer);

    }

    private static int dfs(int v, Set<Integer>[] tree, boolean[] visited) {
        int result = 1;
        for(int nv : tree[v]) {
            if(visited[nv]) continue;

            visited[nv] = true;
            result += dfs(nv, tree, visited);
        }
        return result;
    }

}