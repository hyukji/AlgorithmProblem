import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        
        int[] arr = new int[n+2];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
        arr[n] = Integer.MAX_VALUE;
        arr[n+1] = Integer.MIN_VALUE;
        Arrays.sort(arr);
        
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            int v = Integer.parseInt(st.nextToken());
            sb.append(binarySearch(arr, v)).append("\n");
        }
        System.out.println(sb);
    }

    private static int binarySearch(int[] arr, int v) {
        int s = 0;
        int e = arr.length;
        while(s + 1 < e) {
            int mid = (s+e) / 2;
            int mv = arr[mid];
            if(v < mv) e = mid;
            else s = mid;
        }
        return (arr[s] == v) ? 1 : 0;
    }
}