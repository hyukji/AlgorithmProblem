import java.io.*;
import java.util.*;
 
public class Solution {
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
         
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc < T+1; tc++) {
            st = new StringTokenizer(br.readLine());
        	int n = Integer.parseInt(st.nextToken());
        	int m = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
        	
            TreeSet<Integer> set = new TreeSet<>();
            set.add(0);
            
            for(int i =0; i < n; i++) {
            	int nv = Integer.parseInt(st.nextToken());
            	Set<Integer> tmp = new HashSet<>();
            	for(int v : set) tmp.add(v + nv);
            	set.addAll(tmp);
            }
            
            sb.append("#").append(tc).append(" ").append(set.ceiling(m)-m).append(" ").append("\n");
        }
         
        System.out.print(sb);
    }
 
 
}