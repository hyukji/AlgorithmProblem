import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int answer = 0;

        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            int v = Integer.parseInt(br.readLine());
            arr[i] = v;
        }

        for(int i = n-1; i > 0; i--){
            int v = arr[i];
            int bv = arr[i-1];

            if(v > bv) continue;

            int nv = v - 1;
            arr[i-1] = nv;
            answer += (bv - nv);
        }

        System.out.println(answer);
    }
}