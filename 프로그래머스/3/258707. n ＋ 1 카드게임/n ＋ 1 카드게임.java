import java.util.*;

class Solution {

    public static int solution(int coin, int[] cards) {

        int n = cards.length;
        int matching = 0;
        int canPlay = 1;
        int round = 0;

        boolean[] hasCards = new boolean[n+1];
        boolean[] checked = new boolean[n+1];

        // 초기 카드들 hasCards에 저장
        // 저장시에 pair 카드가 이미 저장되어 있다면, canPlay++한다.
        int initSize = n / 3;
        for (int i = 0; i < initSize; i++) {
            int card = cards[i];
            hasCards[card] = true;

            int pair = n+1-card;
            if(hasCards[pair]) canPlay++;
        }

        // 매번 canPlay-- 해주면서 0이 되면 멈춘다.
        while(canPlay-- > 0) {
            int idx = initSize + (2 * round++);
            if(idx == n) return round;

            // 새로 나왔다는 걸 checked에 
            int fCard = cards[idx];
            int sCard = cards[idx+1];
            checked[fCard] = true;
            checked[sCard] = true;

            int fPair = n + 1 - fCard;
            int sPair = n + 1 - sCard;
            int[] pairs = new int[] {fPair, sPair};
            
            for(int pair : pairs) {
                if(coin > 0 && hasCards[pair]) {
                    // 페어 카드를 처음에 가지고 있었다면
                    coin--;
                    canPlay++;
                }
                if(checked[pair]) {
                    // 페어 카드가 처음 이후에 등장했다면 살 수 있다는 걸 체크해둔다.
                    matching++;
                }
            }
            
            // 처음에 뽑은 두 카드의 합이 n+1 이라면 matching 에서 하나를 뺀ㄴ다.
            if(fCard + sCard == n+1) matching--;

            // 매칭된 것에서 하나 빼서 라운드를 추가한다!
            if(canPlay == 0 && coin > 1 && matching > 0) {
                matching--;
                coin-=2;
                canPlay++;
            }

        }

        return round;
    }

}