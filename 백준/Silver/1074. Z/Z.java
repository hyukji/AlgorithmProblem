import java.io.*;
import java.util.*;

public class Main {
	
	static int er, ec;
	static char[][] graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		er = Integer.parseInt(st.nextToken());
		ec = Integer.parseInt(st.nextToken());

		System.out.println(findZ(0, 0, 1 << (n-1)));
	}

	private static int findZ(int r, int c, int n) {
		int loc = 0;
		int nr = r, nc = c;
		if(c + n <= ec) { loc++; nc = c + n; }
		if(r + n <= er) { loc+=2; nr = r + n; }
		
		if(n == 1) return loc;
		return findZ(nr, nc, n >> 1) + loc * n * n;
	}

}