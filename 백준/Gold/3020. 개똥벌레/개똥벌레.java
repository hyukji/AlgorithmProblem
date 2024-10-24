import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[] base = new int[h+1];
        int[] ceiling = new int[h+1];
        for(int i = 0; i < n; i++) {
            int v = Integer.parseInt(br.readLine());
            if(i % 2 == 0) base[v] += 1;
            else ceiling[v] += 1;
        }


        for(int i = 0; i < h; i++) {
            base[h-1-i] += base[h-i];
            ceiling[h-1-i] += ceiling[h-i];
        }

        int answer = n+1;
        int cnt = 0;
        for(int i = 1; i <= h; i++) {
            int v = base[i] + ceiling[h-i+1];
            if(v < answer) {
                answer = v;
                cnt = 1;
            }
            else if(v == answer) cnt++;
        }

        System.out.println(answer + " " + cnt);
    }
}