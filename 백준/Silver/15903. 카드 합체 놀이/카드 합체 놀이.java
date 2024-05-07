import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long m = Long.parseLong(st.nextToken());
        
        long answer = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
        	long v = Integer.parseInt(st.nextToken());
        	pq.add(v);
        	answer += v;
        }
        
        for(long i = 0; i < m; i++) {
        	long a = pq.poll();
        	long b = pq.poll();
        	answer += b;
        	answer += a;

        	long tmp = a + b;
        	pq.add(tmp);
        	pq.add(tmp);
        }
        
        
        System.out.println(answer);
        
    }

}