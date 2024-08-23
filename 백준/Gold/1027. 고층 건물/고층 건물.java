import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] buildings = new long[n];
        for(int i = 0; i < n; i++) buildings[i] = Long.parseLong(st.nextToken());

        int[] possibleCount = new int[n];

        // 우측으로 가능한 것 카운트
        for(int i = 0; i < n; i++) {
            long h = buildings[i];
            double maxAngle = -Long.MAX_VALUE;
            for(int j = i+1; j < n; j++){
                long nh = buildings[j];
                double angle = ((double) (nh - h))/ (j - i);

                if(angle > maxAngle) {
                    maxAngle = angle;
                    possibleCount[i]++;
                }
            }
        }

        // 좌측으로 가능한 것 카운트
        for(int i = n-1; i >= 0; i--) {
            long h = buildings[i];
            double minAngle = Long.MAX_VALUE;
            for(int j = i-1; j >= 0; j--){
                long nh = buildings[j];
                double angle = ((double) (h - nh))/ (i - j);

                if(angle < minAngle) {
                    minAngle = angle;
                    possibleCount[i]++;
                }
            }
        }

        int answer = 0;
        for(int result : possibleCount) answer = Integer.max(answer, result);
        System.out.println(answer);

    }

}