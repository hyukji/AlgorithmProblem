import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        if(a + b -1 > N) {
            System.out.println(-1);
            return;
        }

        if(N == 1) {
            System.out.println(1);
            return;
        }
        if(N == 2) {
            if(a==2) System.out.println("1 2");
            else System.out.println("2 1");
            return;
        }

        int[] arr = new int[N];
        int prefix = N-a-b+1;
        for(int i = 0; i < prefix; i++) arr[i] = 1;
        for(int i = 0; i < a-1; i++) {
            arr[prefix+i] = i+1;
        }
        arr[prefix+a-1] = Integer.max(a, b);
        for(int i = 1; i < b; i++) {
            arr[prefix+a-1+i] = b-i;
        }

        if(a==1 && prefix > 0) {
            arr[0] = arr[prefix+a-1];
            arr[prefix+a-1] = 1;
        }

        StringBuilder sb = new StringBuilder();
        for(int v : arr) sb.append(v).append(" ");
        System.out.print(sb);
        br.close();

    }
}