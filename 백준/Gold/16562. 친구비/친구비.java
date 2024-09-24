import java.io.*;
import java.util.*;

public class Main {

    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        parents = new int[n];

        int[] nodes = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            nodes[i] = Integer.parseInt(st.nextToken());
            parents[i] = i;
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken()) - 1;
            union(v, w);
        }

        // 전체 부모 업데이트
        for(int i = 0; i < n; i++) find(i);

        // 그룹별로 root이름으로 최소 비용 계산.
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            int pi = find(i);
            // 비용 계산할 때, pi가 아닌 i로 계산!
            map.put(pi, Integer.min(nodes[i], map.getOrDefault(pi, Integer.MAX_VALUE)));
        }

        // 비용들의 합이므로 long!!
        long price = 0;
        for(int v : map.values()) price += v;

        if(price > k) System.out.println("Oh no");
        else System.out.println(price);
    }
    public static int find(int i) {
        if(parents[i] == i) return i;
        return parents[i] = find(parents[i]);

    }
    public static void union(int v, int w) {
        int pv = find(v);
        int pw = find(w);

        if(pv < pw) parents[pw] = parents[pv];
        else parents[pv] = parents[pw];
    }

}