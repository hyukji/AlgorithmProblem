import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BfsDfs {
    static boolean[] visit;
    //연결 리스트, 행렬 그래프 중 선택
//    static LinkedList<Integer>[] graph;
    static int[][] graph;

    public void bfs() {
        int start = 0;
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visit[start] = true;

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            System.out.println(poll);

            for (int nxt : graph[poll]) {
                if (visit[nxt]) {
                    continue;
                }
                queue.add(nxt);
                visit[nxt] = true;
            }

        }

    }

    public void dfs() {
        int start = 0;
        Stack<Integer> stack = new Stack<>();

        stack.push(start);
        visit[start] = true;

        while (!stack.isEmpty()) {
            Integer pop = stack.pop();

            System.out.println(pop);

            for (int next : graph[pop]) {
                if (visit[next]) {
                    continue;
                }
                stack.push(next);
                visit[next] = true;
            }

        }

    }
}
