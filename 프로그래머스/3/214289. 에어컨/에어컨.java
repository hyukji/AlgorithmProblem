import java.util.*;
import java.io.*;


class Solution {
    
    private static int MAX_VALUE = 100_001; // a,b의 최대값 100 * onboard의 길이 1000
    
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int answer = MAX_VALUE;
        
        int[][] dp = new int[1000][51];
        temperature += 10; // 온도들이 -10부터 이어서 모두 +10을 해줌
        t1 += 10;
        t2 += 10;
        
        int work = 0, notwork = 0, low =0, high =0;
        if(temperature <= t1) { 
            // 실외 온도가 t1보다 작다면 에어컨 가동이 온도가 올라가고, 끄면 내려가야 한다.
            work = 1;
            notwork = -1;
        }
        else if(temperature >= t2) {
            work = -1;
            notwork = 1;
        }
        
        // dp 초기화
        for(int temp = 0; temp < 51; temp++) dp[0][temp] = MAX_VALUE;
        dp[0][temperature] = 0;
        
        // 시간마다 택시 탑승 여부를 이용해 최소, 최대를 구하고 그 시간 까지의 최소 비용을 구한다.
        for(int time = 1; time < onboard.length; time++) {
            for(int temp = 0; temp < 51; temp++) dp[time][temp] = MAX_VALUE;
            
            // 탑승여부 + 실외온도 이용해 최소 온도와 최대 온도를 구함.
            if(onboard[time] == 0 && temperature < t1) {
                low = temperature;
                high = t2;
            } else if(onboard[time] == 0 && temperature > t2) {
                low = t1;
                high = temperature;
            } else { // 탑승중이라면 무조건 t1~t2사이
                low = t1;
                high = t2;
            }
             
            for(int temp = low; temp <= high; temp++) {
                
                // 에어컨 켜서 변화
                if(temp-work >= 0 && temp-work < 51)
                    dp[time][temp] = Integer.min(dp[time][temp], dp[time-1][temp-work] + a);
                
                // 에어컨 켜서 유지
                dp[time][temp] = Integer.min(dp[time][temp], dp[time-1][temp] + b);
                
                // 에어컼 꺼서 변화
                if(temp-notwork >= 0 && temp-notwork < 51)
                    dp[time][temp] = Integer.min(dp[time][temp], dp[time-1][temp-notwork]);
                
                // 에어컨 꺼서 유지 == 실외랑 온도가 같을 때만 가능
                if(temp == temperature)
                    dp[time][temp] = Integer.min(dp[time][temp], dp[time-1][temp]);
                
            }
            
        }
        
        for(int temp = low; temp <= high; temp++) {
            answer = Integer.min(answer, dp[onboard.length-1][temp]);
        }
        
        return answer;
    }
}