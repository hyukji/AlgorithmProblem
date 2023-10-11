import java.util.*;

class convertingNumber {
    public int solution(int x, int y, int n) {
        int answer = 0;
        if(x == y) return answer;

        int[] visited = new int[y+1];
        Queue<Integer> pq = new LinkedList<>();
        pq.add(x);

        while(!pq.isEmpty()) {
            answer += 1;

            int size = pq.size();
            for(int i = 0; i < size; i++) {
                int v = pq.poll();

                int[] nvs = {v + n, v * 2, v * 3};
                for(int nv : nvs) {
                    if(nv > y) continue;
                    if(nv == y) return answer;

                    if(visited[nv] == 1) continue;
                    visited[nv] = 1;

                    pq.add(nv);
                }
            }
        }

        answer = -1;
        return answer;
    }
}