import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        
        int[] arr = new int[n];
        for(int i = 0; i < n ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // System.out.println(Arrays.toString(arr));

        int sum = 0;
        for(int i = 0; i < k; i++) sum += arr[i];

        int maxSum = sum;
        for(int i = 0; i < n-k; i++) {
            sum -= arr[i];
            sum += arr[i+k];
            maxSum = Integer.max(maxSum, sum);
        }

        System.out.println(maxSum);
    }
}