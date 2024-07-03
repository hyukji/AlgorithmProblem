class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int delivered = 0, picked = 0;
        for(int i = n-1; i >= 0; i--) {
            delivered -= deliveries[i];
            picked -= pickups[i];
            while(delivered < 0 || picked < 0) {
                delivered += cap;
                picked += cap;
                answer += ((i+1) * 2);
            }
        }
        return answer;
    }
}