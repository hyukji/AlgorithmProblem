import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long T = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());
        long[] A = new long[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            A[i+1] = Integer.parseInt(st.nextToken());
            A[i+1] += A[i];
        }

        int m = Integer.parseInt(br.readLine());
        long[] B = new long[m+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            B[i+1] = Integer.parseInt(st.nextToken());
            B[i+1] += B[i];
        }

        Map<Long, Integer> map = new HashMap<>();

        for(int s = 0; s < m; s++) {
            for(int e = s + 1; e < m + 1; e++) {
                long v = B[e] - B[s];
                map.put(v, map.getOrDefault(v, 0) + 1);
            }
        }

        long answer = 0;
        for(int s = 0; s < n; s++) {
            for(int e = s+1; e < n+1; e++) {
                long v = A[e] - A[s];
                if(map.containsKey(T-v)) answer += map.get(T-v);
            }
        }

        System.out.println(answer);

    }
}