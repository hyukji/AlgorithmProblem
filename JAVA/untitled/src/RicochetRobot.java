import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/169199
class RicochetRobot {

    static class Location {
        final private int y;
        final private int x;

        public Location() {
            this.y = -1;
            this.x = -1;
        }

        public Location(int y, int x) {
            this.y = y;
            this.x = x;
        }

    }

    public int solution(String[] board) {
        int answer = 0;
        int Mx = board[0].length(), My = board.length;
        int[][] visited = new int[board.length][board[0].length()];
        int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        Location now = new Location();

        for(int y = 0; y < board.length; y++) {
            String row = board[y];
            int x = row.indexOf('R');
            if(x == -1) continue;

            now = new Location(y, x);
        }

        Queue<Location> queue = new LinkedList<>();
        queue.add(now);

        while(!queue.isEmpty()) {
            answer += 1;
            int size = queue.size();
            for(int q = 0; q < size; q++) {
                Location loc = queue.poll();
                for(int i = 0; i < 4; i++) {
                    int[] direction = directions[i];
                    int y = loc.y, x = loc.x;
                    int ny = y, nx = x;
                    while(true) {
                        ny += direction[0]; nx += direction[1];
                        if(ny < 0 | ny >= My | nx < 0 | nx >= Mx) break;
                        if(board[ny].charAt(nx) == 'D') break;
                    }
                    y = ny - direction[0]; x = nx - direction[1];

                    if(board[y].charAt(x) == 'G') return answer;
                    if(visited[y][x] == 1) continue;
                    queue.add(new Location(y, x));
                    visited[y][x] = 1;
                }
            }
        }
        answer = -1;
        return answer;
    }
}