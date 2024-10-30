import java.io.*;
import java.util.*;

public class Main {

    private static final int MAX = 1_000_001;

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[] dp = new long[MAX];

        for(int i = 1; i < MAX; i++)  {
            for(int v = i; v < MAX; v+=i) {
                dp[v] += i;
            }

            dp[i] += dp[i-1];
        }

        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine()); // n = 1,000,000
        for(int i = 0; i < t; i++) {
            int v = Integer.parseInt(br.readLine()); // n = 1,000,000
            sb.append(dp[v]).append("\n");
        }
        System.out.println(sb);

    }
}