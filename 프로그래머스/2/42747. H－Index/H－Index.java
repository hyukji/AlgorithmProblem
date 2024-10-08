import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        int answer = 0;
        // System.out.println(Arrays.toString(citations));
        
        int i = 0;
        for(int h = 1; h <= 10000; h++) {
            while(citations[i] < h) {
                if(++i == n) return answer;
            }
            
            // System.out.println(h + " " + i);
            int c = citations[i];
            if(n-i >= h) answer = h;
        }
        
        return answer;
    }
}