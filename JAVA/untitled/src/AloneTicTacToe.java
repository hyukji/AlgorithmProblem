import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/160585
// refactoring 필요.
class AloneTicTacToe {

    private static boolean winO = false;
    private static boolean winX = false;

    private static int cntO = 0;
    private static int cntX = 0;

    public static void main(String[] args) {
        String[] board = {"OOO", "XXX", "..."};
        solution(board);
    }

    public static int solution(String[] board) {
        // cntO, cntX 구하기.
        countPiece(board);

        // cnt 개수를 이용해 진위 여부
        int diff = cntO - cntX;
        if(diff < 0 | diff >= 2) return 0;

        // O, X의 승리 여부.
        char[][] map = Arrays.stream(board).map(String::toCharArray).toArray(char[][]::new);
        winO = isWin(map, 'O');
        winX = isWin(map, 'X');

        // 승리 조건 횟수와 말의 개수를 이용한 진위 여부
        if(winX && winO) return 0;
        if(winX && diff != 0) return 0;
        if(winO & diff != 1) return 0;

        return 1;
    }

    private static boolean isWin(char[][] map, char piece) {
        for(int i = 0; i < 3; i++) {
            if(map[i][0] == piece && map[i][1] == piece && map[i][2] == piece) return true;
            if(map[0][i] == piece && map[1][i] == piece && map[2][i] == piece) return true;
        }
        if(map[0][0] == piece && map[1][1] == piece && map[2][2] == piece) return true;
        if(map[0][2] == piece && map[1][1] == piece && map[2][0] == piece) return true;

        return false;
    }

    private static void countPiece(String[] board) {
        for(String row : board) {
            for (char c : row.toCharArray()) {
                if(c == 'O') cntO += 1;
                else if(c == 'X') cntX += 1;
            }
        }
    }

}