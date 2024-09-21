import java.util.*;
import java.io.*;

class Main {

    static int[] parents;
    static int[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        parents = new int[n+1];
        arr = new int[n+1][4];
        arr[0] = new int[]{0,0,0,0};

        boolean hasZeroLocation = false;
        for(int i = 1; i < n+1; i++) {
            parents[i] = i;

            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            arr[i] = new int[]{x1, y1, x2, y2};

//            if((x1 == 0 || x2 == 0) && (y1 == 0 || y2 == 0)) hasZeroLocation = true;
        }

//        int answer = (hasZeroLocation) ? -1 : 0;

        for(int i = 0; i < n+1; i++) {
            for (int j = 0; j < i; j++) {
                if (checkInner(i, j)) union(i, j);
            }
        }

        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < n+1; i++) set.add(find(i));

//        System.out.println(Arrays.toString(parents));
//        System.out.println(set);

        System.out.println(set.size()-1);
    }

    private static boolean checkInner(int i, int j) {
        int nx1 = arr[i][0], ny1 = arr[i][1], nx2 = arr[i][2], ny2 = arr[i][3];
        int x1 = arr[j][0], y1 = arr[j][1], x2 = arr[j][2], y2 = arr[j][3];

        // 직사각형이 포함되거나 감쌀때, 혹은 아예 왼쪽, 아예 오른쪽, 아예 위, 아예 아래
        if((x1 < nx1 && nx2 < x2 && y1 < ny1 && ny2 < y2)
                || (x1 > nx1 && nx2 > x2 && y1 > ny1 && ny2 > y2)
                || x2 < nx1 || nx2 < x1 || y2 < ny1 || ny2 < y1) return false;
        return true;
    }

    private static int find(int i) {
        if(parents[i] == i) return i;
        return parents[i] = find(parents[i]);
    }

    private static void union(int i, int j) {
        int pi = find(i);
        int pj = find(j);

        int mP = Integer.min(pi, pj), MP = Integer.max(pi, pj);
        parents[MP] = mP;
    }

}