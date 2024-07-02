import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        int T = Integer.parseInt(br.readLine());
        for(int tc=0;tc<T;tc++){
            TreeMap<Integer, Integer> map = new TreeMap<>();

            int m = Integer.parseInt(br.readLine());
            for(int i=0;i<m;i++){
                st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                int v = Integer.parseInt(st.nextToken());

                if(cmd.equals("I")) {
                    map.put(v, map.getOrDefault(v, 0) + 1);
                } else {
                    if(map.isEmpty()) continue;

                    int key = (v == 1) ? map.lastKey() : map.firstKey();
                    int value = map.get(key) - 1;
                    if(value != 0) map.put(key, value);
                    else map.remove(key);
                }
            }

            if(map.isEmpty()) sb.append("EMPTY").append("\n");
            else sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
        }

        System.out.println(sb);
    }
}