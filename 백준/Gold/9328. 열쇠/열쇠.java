import java.io.*;
import java.util.*;

public class Main {

    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {

        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for(int i = 0; i < tc; i++) {
            int result = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            ArrayDeque<int[]> starts = new ArrayDeque<>();

            boolean[][] visited = new boolean[h][w];
            char[][] building = new char[h][w];
            for(int r = 0; r < h; r++) {
                char[] row = br.readLine().toCharArray();
                building[r] = row;
            }

            Set<Integer> keys = new HashSet<>();
            for(char k : br.readLine().toCharArray()) keys.add(k-'a');

            List<int[]>[] doors = new ArrayList[26];
            for(int ii = 0; ii < 26; ii++) {
                doors[ii] = new ArrayList<>();
            }

            if(keys.contains(-49)) keys.clear();

            for(int r = 0; r < h; r++) {
                char[] row = building[r];
                for (int c = 0; c < w; c++) {
                    if (r == 0 || r == h - 1 || c == 0 || c == w - 1) {
                        if (row[c] == '*') continue;

                        visited[r][c] = true; // 방문 처리

                        char v = building[r][c];
                        if(v - 'A' >= 0 && v - 'Z' <= 0) { // 대문자(문)
//                        System.out.println("대 " + v);
                            int d = v - 'A';
                            if(!keys.contains(d)) { // 현재 방문 불가능한 문
                                doors[d].add(new int[] {r, c}); // 문을 기록해둠
                                continue;
                            }
                        } else if(v - 'a' >= 0 && v - 'z' <= 0) { // 소문자(문)
//                        System.out.println("소 " + v);
                            int key = v - 'a';
                            if(!keys.contains(key)) { // 새로운 키
                                keys.add(key); // 키 추가
                                for(int[] keyLoc : doors[key]) starts.offer(keyLoc); // 이전에 방문가능했던 문들 추가
                            }
                        }

                        if(v == '$') result++;
                        starts.offer(new int[] {r, c});
                    }
                }
            }


//            System.out.println(starts);

            while(!starts.isEmpty()) {
                int[] loc = starts.pollFirst();
                int r = loc[0];
                int c = loc[1];

                for(int[] direction : directions) {
                    int nr = r + direction[0];
                    int nc = c + direction[1];
                    if(nr < 0 || nr >= h || nc < 0 || nc >= w) continue;
                    if(visited[nr][nc] || building[nr][nc] == '*') continue; // 방문한 곳 or 벽

//                    System.out.println(nr + " " + nc);
                    visited[nr][nc] = true; // 방문 처리

                    char v = building[nr][nc];
                    if(v - 'A' >= 0 && v - 'Z' <= 0) { // 대문자(문)
//                        System.out.println("대 " + v);
                        int d = v - 'A';
                        if(!keys.contains(d)) { // 현재 방문 불가능한 문
                            doors[d].add(new int[] {nr, nc}); // 문을 기록해둠
                            continue;
                        }
                    } else if(v - 'a' >= 0 && v - 'z' <= 0) { // 소문자(문)
//                        System.out.println("소 " + v);
                        int key = v - 'a';
                        if(!keys.contains(key)) { // 새로운 키
                            keys.add(key); // 키 추가
                            for(int[] keyLoc : doors[key]) starts.offer(keyLoc); // 이전에 방문가능했던 문들 추가
                        }
                    }

                    if(v == '$') result++;

                    starts.offer(new int[] {nr, nc});
                }
            }

//            for(char[] row : building) System.out.println(Arrays.toString(row));
//            for(boolean[] row : visited) System.out.println(Arrays.toString(row));
//            System.out.println(keys);
//            System.out.println(result);

            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }
}