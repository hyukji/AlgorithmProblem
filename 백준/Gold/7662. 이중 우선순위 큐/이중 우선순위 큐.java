import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        int T = Integer.parseInt(br.readLine());
        for(int tc=0;tc<T;tc++){
            PriorityQueue<Integer> lpq = new PriorityQueue<>();
            PriorityQueue<Integer> hpq = new PriorityQueue<>((o1, o2) -> -Integer.compare(o1, o2));
            Map<Integer, Integer> map = new HashMap<>();
            int cnt = 0;

            int m = Integer.parseInt(br.readLine());
            for(int i=0;i<m;i++){
                st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                int v = Integer.parseInt(st.nextToken());

                if(cmd.equals("I")) {
                    hpq.offer(v);
                    lpq.offer(v);
                    cnt++;
                    map.put(v, map.getOrDefault(v, 0) + 1);
                } else {
                    if(cnt == 0) continue;

                    if(v == 1) {
                        int polled = hpq.poll();
                        while(map.get(polled) == 0) polled = hpq.poll();
                        map.put(polled, map.get(polled) - 1);
                    }
                    else {
                        int polled = lpq.poll();
                        while(map.get(polled) == 0) polled = lpq.poll();
                        map.put(polled, map.get(polled) - 1);
                    }

                    if(--cnt == 0) {
                        hpq.clear();
                        lpq.clear();
                    }
                }
            }
            if(cnt ==0) sb.append("EMPTY").append("\n");
            else {
                int hPolled = hpq.poll();
                while(map.get(hPolled) == 0) hPolled = hpq.poll();

                int lPolled = lpq.poll();
                while(map.get(lPolled) == 0) lPolled = lpq.poll();

                sb.append(hPolled).append(" ").append(lPolled).append("\n");
            }
        }

        System.out.println(sb);
    }
}