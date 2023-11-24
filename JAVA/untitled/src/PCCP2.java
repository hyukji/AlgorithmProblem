import java.util.*;

class PCCP2 {

    int[] answers;
    int land_size;
    int depth_size;

    public int solution(int[][] land) {
        depth_size = land.length;
        land_size = land[0].length;
        answers = new int[land_size];
        Arrays.fill(answers, 0);

        for (int r = 0; r < land_size; r++) {
            for (int d = 0; d < depth_size; d++) {
                if (land[d][r] == 1) {
                    int[] loc = {d, r};
                    bfs(loc, land);
                }
            }
        }

        return Arrays.stream(answers).max().orElse(0);
    }

    public void bfs(int[] loc, int[][] land) {
        int[][] directions = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

        LinkedList<int[]> queue = new LinkedList<>();
        Set<Integer> target = new HashSet<>();
        int count = 1;

        queue.add(loc);
        target.add(loc[1]);
        land[loc[0]][loc[1]] = 0;

        while (!queue.isEmpty()) {
            loc = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nd = loc[0] + directions[d][0];
                int nr = loc[1] + directions[d][1];

                if (nd < 0 || nd >= depth_size || nr < 0 || nr >= land_size) {
                    continue;
                }

                if (land[nd][nr] == 1) {
                    int[] nLoc = {nd, nr};
                    queue.add(nLoc);
                    target.add(nr);

                    count++;
                    land[nd][nr] = 0;
                }

            }
        }

        for (int el : target) {
            answers[el] += count;
        }

    }
}