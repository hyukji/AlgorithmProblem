import java.util.*;

class GetMine {

    public static void main(String[] args) {
        int[] picks = {1, 3, 2};
        String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};

        int answer = solution(picks, minerals);
        System.out.println(answer);
    }

    public static int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int[][] scoreBoard = new int[][]{{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};

        ArrayList<int[]> list = new ArrayList<>();
        int totalPicks = Arrays.stream(picks).sum();

        for(int i = 0; i < minerals.length; i+=5) {
            if(totalPicks == 0) break;
            int[] byPick = {0, 0, 0};
            for(int j = i; j < i+5; j++) {
                if(j == minerals.length) break;
                String mineral = minerals[j];
                int m = switch (mineral) {
                    case "diamond" -> 0;
                    case "iron" -> 1;
                    default -> 2;
                };

                for(int p = 0; p < 3; p++) {
                    byPick[p] += scoreBoard[p][m];
                }
            }
            list.add(byPick);
            totalPicks--;
        }

        list.sort(((o1, o2) -> o2[2] - o1[2]));
        for(int[] value : list) {
            for(int i = 0; i < 3; i++) {
                if(picks[i] > 0) {
                    picks[i]--;
                    answer += value[i];
                    break;
                }
            }
        }

        return answer;
    }
}
