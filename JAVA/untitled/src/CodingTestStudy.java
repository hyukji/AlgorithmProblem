import java.util.Arrays;

public class CodingTestStudy {
    public int solution(int alp, int cop, int[][] problems) {
        int goal_alp = alp, goal_cop = cop;
        for (int[] p : problems) {
            int alp_req = p[0], cop_req = p[1];
            goal_alp = Math.max(alp_req, goal_alp);
            goal_cop = Math.max(cop_req, goal_cop);
        }

        int[][] dp = new int[goal_alp+2][goal_cop+2];

        if (goal_alp <= alp && goal_cop <= cop) return 0;

        for (int[] ints : dp) {
            Arrays.fill(ints, 1_000_000);
        }

        dp[alp][cop] = 0;
        for (int a = alp; a <= goal_alp; a++) {
            for (int c = cop; c <= goal_cop; c++) {
                dp[a+1][c] = Math.min(dp[a][c] + 1, dp[a+1][c]);
                dp[a][c+1] = Math.min(dp[a][c] + 1, dp[a][c+1]);

                for (int[] p : problems) {
                    int alp_req = p[0], cop_req = p[1], alp_rwd = p[2], cop_rwd = p[3], cost = p[4];
                    if(alp_req <= a && cop_req <= c) {
                        int new_a = Math.min(a + alp_rwd, goal_alp);
                        int new_c = Math.min(c + cop_rwd, goal_cop);
                        dp[new_a][new_c] = Math.min(dp[a][c] + cost, dp[new_a][new_c]);
                    }
                }
            }
        }

        return dp[goal_alp][goal_cop];
    }
}
