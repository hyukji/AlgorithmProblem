import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        List<Integer>[] edges = new ArrayList[N+1];
        for(int i = 0; i < N+1; i++) edges[i] = new ArrayList<>();
        int[] edgeCount = new int[N+1];

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            edges[u].add(v);
            edges[v].add(u);
            edgeCount[u]++;
            edgeCount[v]++;
        }
        edgeCount[1]++; // 루트의 경우 1 추가

        ArrayDeque<Integer> dq = new ArrayDeque<>();
        int[] result = new int[N+1]; // 현재 위치에서 시작했을 때 (선공 - 후공)
        boolean[] leaves = new boolean[N+1]; // 방문한 리프인지
        for(int i = 1; i < N+1; i++) {
            result[i] = i;
            if (edgeCount[i] == 1) { // 1이라면 부모밖에 남지 않은 경우이기에 리프
                leaves[i] = true;
                dq.offer(i); // 리프인 것들 모아두기
            }
        }

        while(!dq.isEmpty()) {
            int node = dq.poll(); 

            int nextMinValue = Integer.MAX_VALUE;
            for(int next : edges[node]) {
                if(leaves[next]) { // 다음 노드가 부모가 아니라면 그곳에서 시작값이 최소값이 되도록(최적의 이동을 위해서)
                    nextMinValue = Integer.min(nextMinValue, result[next]);
                    continue;
                }
                // 다음 노드가 부모 노드라면 edgeCount에서 1 제거 
                if(--edgeCount[next] == 1) {
                    dq.offer(next);
                    leaves[next] = true;
                }
            }

            if(nextMinValue != Integer.MAX_VALUE) result[node] -= nextMinValue; // 자식이 있는 경우만 최적값 적용
        }

        for(int i = 1; i < N+1; i++) sb.append(result[i] >= 0 ? 1 : 0).append("\n");
        System.out.print(sb);
        br.close();
    }
}
