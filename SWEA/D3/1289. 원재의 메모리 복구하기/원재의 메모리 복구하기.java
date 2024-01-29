import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			String value = br.readLine();
			int answer = 0;
			int bef = 0;
			for(int i = 0; i < value.length(); i++) {
				int bit = (int)value.charAt(i) - 48;
				if(bit == bef) continue;
				answer+=1;
				bef = answer % 2;
			}
			
			System.out.printf("#%d %d\n", tc, answer);
		}
		
	}
		
}