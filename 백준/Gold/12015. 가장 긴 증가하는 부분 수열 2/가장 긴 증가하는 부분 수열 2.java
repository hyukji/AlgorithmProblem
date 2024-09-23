import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int n = Integer.parseInt(br.readLine());

        List<Integer> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        list.add(Integer.parseInt(st.nextToken()));
        for(int i = 1; i < n; i++) {
            int v = Integer.parseInt(st.nextToken());
            int idx = Collections.binarySearch(list, v);
            if(idx >= 0) continue;

            idx = -(idx+1);
            if(idx < list.size()) list.set(idx, v);
            else list.add(v);

        }

        System.out.println(list.size());
    }

}