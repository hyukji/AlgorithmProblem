import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();

        int n = A.length;
        int m = B.length;

        int answer = 0;
        int[][] dp = new int[n][m];

        for(int c = 0; c < m; c++) {
            char a = A[0];
            char b = B[c];
            if(a != b) continue;

            dp[0][c] = 1;
        }

        for(int r = 1; r < n; r++){
            char a = A[r];
            if(a == B[0]) dp[r][0] = 1;

            for(int c = 1; c < m; c++) {
                char b = B[c];
                if(a != b) continue;

                dp[r][c] = dp[r-1][c-1] + 1;
                if(dp[r][c] > answer) answer = dp[r][c];
            }
        }

//        for(int[] row : dp) System.out.println(Arrays.toString(row));
        System.out.println(answer);
    }

}