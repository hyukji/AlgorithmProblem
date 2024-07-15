import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n-1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =0; i< n-1; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[] start = new int[n];
        Arrays.fill(start, 1);

        int result = bfs(start, arr, n);
        System.out.println(result);
    }

    public static int bfs(int[] start, int[] turns, int n) {
        ArrayDeque<Result> dq = new ArrayDeque<>();
        Map<String, Integer> minProductMap = new HashMap<>();

        dq.offer(new Result(start, 0));
        minProductMap.put(Arrays.toString(start), 0);

        for(int depth = 0; depth < n-1; depth++) {
            int size = dq.size();
            boolean useNewSum = turns[depth] == 0;

            for (int s = 0; s < size; s++) {
                Result current = dq.poll();
                int[] currentArray = current.array;
                int currentSum = minProductMap.get(Arrays.toString(currentArray));

                HashSet<String> visited = new HashSet<>();

                for (int i = 0; i < currentArray.length; i++) {
                    for (int j = i + 1; j < currentArray.length; j++) {
                        int sum = currentArray[i] + currentArray[j];
                        int product = currentArray[i] * currentArray[j];

                        String newStateString = Arrays.toString(new int[] {sum, product});
                        if (visited.contains(newStateString)) continue;
                        visited.add(newStateString);

                        int[] newArr = new int[currentArray.length - 1];
                        int index = 0;
                        for (int k = 0; k < currentArray.length; k++) {
                            if (k != i && k != j) {
                                newArr[index++] = currentArray[k];
                            }
                        }
                        newArr[index] = sum;
                        Arrays.sort(newArr);
                        
                        String stateString = Arrays.toString(newArr);
                        int newSum = (useNewSum) ? currentSum+product : currentSum;

                        if(!minProductMap.containsKey(stateString)) {
                            dq.offer(new Result(newArr, newSum));
                            minProductMap.put(stateString, newSum);
                        }
                        else if (newSum < minProductMap.get(stateString)) {
                            minProductMap.put(stateString, newSum);
                        }

                        // System.out.println("depth : " + depth + " arr : " + current + ", " + currentSum + " => "  + Arrays.toString(newArr));

                    }

                }

            }
        }

        Result result = dq.poll();
        return minProductMap.get(Arrays.toString(result.array));
    }

    static class Result {
        int[] array;
        int product;

        Result(int[] array, int product) {
            this.array = array;
            this.product = product;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "array=" + Arrays.toString(array) +
                    ", product=" + product +
                    '}';
        }
    }
}
