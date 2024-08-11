import java.io.*;
import java.util.*;

public class Main {

    static char[][] cogs = new char[4][8];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        for(int i = 0; i < 4; i++) {
            cogs[i] = br.readLine().toCharArray();
        }

        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken()) - 1;
            boolean isClockWise = Integer.parseInt(st.nextToken()) == 1;

            useRightCog(index, isClockWise);
            useLeftCog(index, isClockWise);
            turn(index, isClockWise);
        }

        int answer = 0;
        for(int i = 0; i < 4; i++) {
            if(cogs[i][0] == '1')
                answer += (1 << i);
        }

        System.out.println(answer);
    }

    private static void useRightCog(int i, boolean direction) {
        if(i > 2) return;
        if(cogs[i][2] == cogs[i+1][6]) return;

        useRightCog(i+1, !direction);
        turn(i+1, !direction);
    }

    private static void useLeftCog(int i, boolean direction) {
        if(i < 1) return;
        if(cogs[i][6] == cogs[i-1][2]) return;

        useLeftCog(i-1, !direction);
        turn(i-1, !direction);
    }


    private static void turn(int index, boolean isClockWise) {
        char[] newCog = new char[8];
        if(isClockWise) {
            for(int i = 0; i < 7; i++) newCog[i+1] = cogs[index][i];
            newCog[0] = cogs[index][7];
        }
        else {
            for(int i = 0; i < 7; i++) newCog[i] = cogs[index][i+1];
            newCog[7] = cogs[index][0];
        }

        cogs[index] = newCog;
    }

}