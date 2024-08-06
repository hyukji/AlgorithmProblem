import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++) {
            char[] ws = br.readLine().toCharArray();
            int k =  Integer.parseInt(br.readLine());

            int maxResult = 0;
            int minResult = 10001;

            Deque<Integer>[] graph = new ArrayDeque[26];
            for(int i = 0; i < 26; i++) graph[i] = new ArrayDeque<Integer>();
            int[] count = new int[26];

            for(int i = 0; i < ws.length; i++) {
                int wtoi = ws[i] - 'a';

                if(count[wtoi] >= k) graph[wtoi].pollFirst();

                count[wtoi]++;
                graph[wtoi].offerLast(i);

                if(count[wtoi] >= k) {
                    int dist = graph[wtoi].getLast() - graph[wtoi].getFirst() + 1;
                    if(dist < minResult) minResult = dist;
                    if(dist > maxResult) maxResult = dist;
                }
            }
            
            if(maxResult == 0)  sb.append(-1).append("\n");
            else sb.append(minResult).append(" ").append(maxResult).append("\n");
        }

        System.out.println(sb);
    }
}