import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int value = Integer.parseInt(br.readLine());
		int L = value / 5;
		for(int l = L; l >= 0; l--) {
			int nv = value - l*5;
			if(nv % 3 == 0) {
				System.out.println(l + nv/3);
				return;
			}
		}
		
		System.out.println(-1);
	}

}