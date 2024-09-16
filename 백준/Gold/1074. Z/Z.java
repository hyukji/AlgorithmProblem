import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        long result = 0;

        for(int i = n-1; i >= 0; i--) {
            int v = 1 << i;
            int cnt = 0;
            if(r >= v) {
                cnt += 2;
                r -= v;
            }
            if(c >= v) {
                cnt += 1;
                c -= v;
            }

            result += cnt * v * v;
        }

        System.out.println(result);
    }

}