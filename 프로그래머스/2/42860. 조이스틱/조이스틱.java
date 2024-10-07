import java.util.*;

class Solution {
    
    public int solution(String name) {
        int answer = 0;
        
        int n = name.length();
        char[] bases = name.toCharArray();
        int[] numbers = new int[n];
        
        
        for(int i = 0; i < n; i++) {
            numbers[i] = bases[i] - 'A';
            answer += Integer.min(numbers[i], 26 - numbers[i]);
        }
        
        if(answer == 0) return 0;
        
        int s = 0, e = 0;
        boolean befA = true;
        
        int move = n-1;
        for(int i = 0; i < n; i++) {
            if(!befA && numbers[i] == 0) {
                befA = true;
                s = i;
            }
            if(befA && numbers[i] != 0) {
                befA = false;
                e = i;
                if(s == 0) continue;
                
                int m = Integer.min(s-1,  n -e);
                int M = Integer.max(s-1,  n -e);
                move = Integer.min(m*2 + M, move);
                
            }
        }
        
        if(befA) {
            e = n;
                
            int m = Integer.min(s-1,  n -e);
            int M = Integer.max(s-1,  n -e);
            move = Integer.min(m*2 + M, move);
            
        }
        
        answer += move;
        return answer;
    }
}