import java.io.*;
import java.util.Arrays;

public class Main {

    // LIS 최장 증가 수열 문제. =>  최장 증가 수열을 찾은 후에 n에서 해당 개수를 빼면 됨. 그 숫자 만큼만 이동하는 것이 최저이기 때문.
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = Integer.parseInt(br.readLine());

        int[] lengths = new int[n];
        for(int i = 0; i < n; i++) {
            lengths[i] = 1;
            for(int j = 0; j < i; j++) {
                if(arr[i] > arr[j])
                    lengths[i] = Integer.max(lengths[i], lengths[j] + 1);
            }
        }

        int answer = 0;
        for(int i = 0; i < n; i++)
            answer = Integer.max(lengths[i], answer);

        System.out.println(n - answer);
    }

}