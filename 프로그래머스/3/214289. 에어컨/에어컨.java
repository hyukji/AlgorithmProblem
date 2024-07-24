import java.util.*;
import java.io.*;


class Solution {
    
    private static int MAX_VALUE = 100_001;
    
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int answer = MAX_VALUE;
        
        int[][] dp = new int[1000][51];
        temperature += 10;
        t1 += 10;
        t2 += 10;
        
        int work = 0, notwork = 0, low =0, high =0;
        if(temperature <= t1) {
            work = 1;
            notwork = -1;
            low = temperature;
            high = t2;
        }
        else if(temperature >= t2) {
            work = -1;
            notwork = 1;
            low = t1;
            high = temperature;
        }
        
        
        for(int temp = 0; temp < 51; temp++) dp[0][temp] = MAX_VALUE;
        dp[0][temperature] = 0;
        
        
        for(int time = 1; time < onboard.length; time++) {
            for(int temp = 0; temp < 51; temp++) dp[time][temp] = MAX_VALUE;
            
            if(onboard[time] == 0 && temperature < t1) {
                low = temperature;
                high = t2;
            } else if(onboard[time] == 0 && temperature > t2) {
                low = t1;
                high = temperature;
            } else {
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
                
                // 에어컨 꺼서 유지
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