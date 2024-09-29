import java.util.*;

class Solution {
    
    int answer;
    int[] answerArr;

    int n, half;
    int[][] dices;
    int countSet = 0;

    public int[] solution(int[][] dice) {
        dices = dice;

        half = dice.length / 2;
        n = dice.length;

        countSet = 1;
        for(int i = 0; i < half; i++) {
            countSet *= (n - i);
        }
        for(int i = 1; i < half+1; i++) {
            countSet /= i;
        }
        countSet /= 2;

        answerArr = new int[half];
        comb(0, 0, new boolean[n]);

        // System.out.println(Arrays.toString(answerArr));

        for(int i = 0; i < half; i++) answerArr[i]++;
        return answerArr;
    }


    public void comb(int idx, int cnt, boolean[] checked) {
        if(countSet == 0) return;
        if(cnt == half) {
            countSet--;
            // 조합으로 주사위 세트 만들기 => 절반만 만들기
            // System.out.println(Arrays.toString(checked));

            int[] rSet = new int[half];
            int[] set = new int[half];

            int ii = 0, jj = 0;
            for(int i = 0; i < n; i++) {
                if(checked[i]) rSet[ii++] = i;
                else set[jj++] = i;
            }

            // System.out.println(Arrays.toString(set));
            // System.out.println(Arrays.toString(rSet));

            List<Integer> result = getResult(set);
            List<Integer> rResult = getResult(rSet);

            Collections.sort(rResult);

            int win = 0;
            int lose = 0;
            for(int v : result) {
                int M = findMaxIndex(rResult, v);
                int m = findMinIndex(rResult, v);

                win += m;
                lose += result.size() - 1 - M;
            }

            if(answer < win) {
                answer = win;
                answerArr = set;
            }
            if(answer < lose) {
                answer = lose;
                answerArr = rSet;
            }

            return;
        }

        for(int i = idx; i < dices.length; i++) {
            checked[i] = true;
            comb(i+1, cnt+1, checked);
            checked[i] = false;
        }


    }

    private List<Integer> getResult(int[] arr) {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.offer(0);

        for(int idx : arr) {
            int[] dice = dices[idx];

            int size = dq.size();
            for(int i = 0; i < size; i++) {
                int v = dq.pollFirst();
                for(int dv : dice) dq.offer(v + dv);
            }
        }

        return new ArrayList<>(dq);
    }

    private int findMinIndex(List<Integer> result, int v) {
        int s = -1;
        int e = result.size();

        while(s + 1 < e) {
            int mid = (s + e) / 2;
            if(result.get(mid) < v) s = mid;
            else e = mid;
        }

        return e;
    }

    private int findMaxIndex(List<Integer> result, int v) {
        int s = 0;
        int e = result.size();

        while(s + 1 < e) {
            int mid = (s + e) / 2;
            if(result.get(mid) > v) e = mid;
            else s = mid;
        }

        return s;
    }
}