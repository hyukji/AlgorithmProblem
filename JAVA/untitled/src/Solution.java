import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/160585
// refactoring 필요.
class Solution {

    private static boolean winO = false;

    private static boolean winX = false;

    public static void main(String[] args) {
        String[] board = {"XX.", "OOO", "..X"};
        solution(board);
    }

    public static int solution(String[] board) {
        int answer = 1;
        int cntO = 0, cntX = 0;

        List<Character> flatBoard = new ArrayList<>();
        for(String row : board) {
            for (char c : row.toCharArray()) {
                flatBoard.add(c);
                if(c == 'O') cntO += 1;
                else if(c == 'X') cntX += 1;
            }
        }

        int diff = cntO - cntX;
        if(diff < 0 | diff >= 2) return 0;

        checkRow(flatBoard);
        checkColumn(flatBoard);
        checkDiagonal(flatBoard);

        if(winX & diff == 0) return 1;
        if(winO & diff == 1) return 1;
        if(!winO & !winX) return 1;

        return 0;
    }

    private static void checkDiagonal(List<Character> flatBoard) {
        int[][] diagonals = {{0, 4, 8}, {2, 4, 6}};
        for(int[] seq : diagonals) {
            Character bef = '.';
            int continuity = 1;
            for(int i : seq) {
                Character now = flatBoard.get(i);
                if(now.equals(bef)) {
                    continuity += 1;
                    continue;
                }
                bef = now;
                continuity = 1;
            }

            chooseWinner(continuity, bef);
        }
    }

    private static void checkColumn(List<Character> flatBoard) {
        for(int i = 0; i < 3; i++) {
            Character bef = '.';
            int continuity = 1;
            for(int j = 0; j < 9; j+=3) {
                Character now = flatBoard.get(i + j);
                if(now.equals(bef)) {
                    continuity += 1;
                    continue;
                }
                bef = now;
                continuity = 1;
            }

            chooseWinner(continuity, bef);
        }

    }

    private static void checkRow(List<Character> flatBoard) {
        for(int i = 0; i < 9; i += 3) {
            Character bef = '.';
            int continuity = 1;
            for(int j = 0; j < 3; j++) {
                Character now = flatBoard.get(i + j);
                if(now.equals(bef)) {
                    continuity += 1;
                    continue;
                }
                bef = now;
                continuity = 1;
            }

            chooseWinner(continuity, bef);
        }
    }


    private static void chooseWinner(int continuity, Character bef) {
        if(continuity != 3)
            return;
        if(bef.equals('X')) winX = true;
        if(bef.equals('O')) winO = true;
    }

}