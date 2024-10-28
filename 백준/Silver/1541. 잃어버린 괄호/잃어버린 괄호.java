import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int answer = 0;

        StringTokenizer minus_st = new StringTokenizer(br.readLine(), "-");

        StringTokenizer plus_st = new StringTokenizer(minus_st.nextToken(), "+");
        while(plus_st.hasMoreTokens()) {
            answer += Integer.parseInt(plus_st.nextToken());
        }

        while(minus_st.hasMoreTokens()) {
            plus_st = new StringTokenizer(minus_st.nextToken(), "+");
            int result = 0;
            while(plus_st.hasMoreTokens()) {
                result += Integer.parseInt(plus_st.nextToken());
            }
            answer -= result;
        }

        System.out.println(answer);
    }
}