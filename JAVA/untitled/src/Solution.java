class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int maxHealth = health;

        int attackTime = 0;
        for (int[] attack : attacks) {
            int interval = attack[0] - attackTime - 1;
            int plus = (interval / bandage[0]) * bandage[2] + interval * bandage[1];
            health = Math.min(health + plus, maxHealth);

            health -= attack[1];
            if(health <= 0) {
                return -1;
            }
            attackTime = attack[0];
        }

        return health;
    }

}