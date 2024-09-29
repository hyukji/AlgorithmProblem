import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[][] jewels = new long[n][2];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            jewels[i][0] = Long.parseLong(st.nextToken());
            jewels[i][1] = Long.parseLong(st.nextToken());
        }

        long[] bags = new long[k];
        for(int i = 0; i < k; i++) {
            bags[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(bags);
        Arrays.sort(jewels, (o1, o2) -> (int) (o1[0] - o2[0]));

        PriorityQueue<Long> pq = new PriorityQueue<>((o1, o2) -> (int) (o2 - o1));

        long answer = 0;
        int i = 0;
        for(long bag : bags) {
            for(; i < n; i++) {
                if(jewels[i][0] <= bag) pq.offer(jewels[i][1]);
                else break;
            }

            Long v = pq.poll();
            answer += v == null ? 0 : v;
        }

        System.out.println(answer);
    }

}