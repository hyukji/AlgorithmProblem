import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int half = n/2;

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] cards = new int[n];
        for(int i = 0; i < n; i++) cards[i] = Integer.parseInt(st.nextToken());

        // 홀수번째 카드 누적합, 짝수번째 카드 누적합
        long[] myCardSums = new long[half+1];
        long[] opponentCardSums = new long[half+1];
        for(int i = 0; i < half; i++) {
            myCardSums[i+1] = myCardSums[i] + cards[i*2];
            opponentCardSums[i+1] = opponentCardSums[i] + cards[i*2+1];
        }

        // 밑장빼기 안할수도!
        long answer = myCardSums[half];

        // 내 차례에 밑장빼기
        for(int i = 0; i < half; i++) {
            long nValue = myCardSums[i] + opponentCardSums[half] - opponentCardSums[i];
            answer = Long.max(answer, nValue);
        }

        // 상대방차례에 밑장빼기
        for(int i = 0; i < half-1; i++) {
            long nValue = myCardSums[i+1] + opponentCardSums[half-1] - opponentCardSums[i];
            answer = Long.max(answer, nValue);
        }

        System.out.println(answer);
    }

}