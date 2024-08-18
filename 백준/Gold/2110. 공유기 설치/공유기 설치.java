import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        long[] houses = new long[n];
        for(int i = 0; i < n; i++) houses[i] = Long.parseLong(br.readLine());
        Arrays.sort(houses); // 집 위치 sort! -> 이분탐색 쓰기 위해서


        // 정답 거리를 이분탐색을 통해 찾는다.
        // 특정 거리로 c개 설치가 가능하다면 true 아니면 false
        // 가능한 거리중 최대 거리를 찾는다.
        long s = 0, e = houses[n-1]+1;
        while(s + 1 < e) {
            long mid = (s + e) / 2;
            boolean result = check(houses, n, c, mid);
            if(result) s = mid;
            else e = mid;
        }

        System.out.println(s);
    }

    private static boolean check(long[] houses, int n, int c, long dx) {
        int now = 0;
        int cnt = 1;

        while(true) {
            if(cnt > c) return false;
            if(houses[now] + dx > houses[n-1]) return false;
            int next = findNext(houses, houses[now], dx);

            if(++cnt == c) return true;
            now = next;
        }
    }


    // Arrays.binarySearch 이 찾는 key값이 없으면 (-(insertion point) - 1) 반환함.
    private static int findNext(long[] houses, long now, long dx) {
        int result = Arrays.binarySearch(houses, now + dx);
        if(result > 0) return result;
        return (result + 1) * (-1);
    }

}