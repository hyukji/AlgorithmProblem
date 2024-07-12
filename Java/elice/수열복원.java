import java.io.*;
import java.util.*;

class Main {
   public static void main(String[] args) throws Exception{
      BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
      StringBuilder sb=new StringBuilder();
      
      int n=Integer.parseInt(br.readLine());
      int size=(int) Math.pow(2,  n);
      
      int[] arr=new int[size];
      StringTokenizer st=new StringTokenizer(br.readLine());
      for(int i=0; i<size; i++) arr[i]=Integer.parseInt(st.nextToken());
      Arrays.sort(arr);
      
      List<Integer> origin = new ArrayList<>();
      List<Integer> seq = new ArrayList<>();
      int[] possible = new int[n*100_001*2];
      origin.add(arr[1]);
      seq.add(arr[1]);
      
      for(int i=2; i<size; i++) {
         int v=arr[i];
         if(possible[v]>0) {
            possible[v]--;
            continue;
         }

         int seqSize=seq.size();
         for(int j=0; j<seqSize; j++) {
            possible[seq.get(j)+v]++;
            seq.add(seq.get(j)+v);
         }
         origin.add(v);
         seq.add(v);
         if(origin.size() == n) break;
      }
      for(int ori: origin) sb.append(ori).append(" ");
      System.out.println(sb);
      br.close();
   }
}
