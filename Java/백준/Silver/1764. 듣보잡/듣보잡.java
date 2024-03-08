import java.io.*;
import java.util.*;

public class Main {

    // hash 및 mergesort연습
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		String[] notLL = new String[500_000];
		int notLLCnt = 0;
		
		HashSet<String> notListen = new HashSet<>();
		for(int i = 0; i < n; i++) notListen.add(br.readLine());
		for(int i = 0; i <m; i++) {
			String notLook = br.readLine();
			if(!notListen.contains(notLook)) continue;
			notLL[notLLCnt++] = notLook;
		}
		
		mergeSort(notLL, 0, notLLCnt-1);
		
		StringBuilder sb = new StringBuilder();
		sb.append(notLLCnt).append("\n");
		for(int i = 0; i < notLLCnt; i++) sb.append(notLL[i]).append("\n");
		System.out.print(sb);
	}

	private static void mergeSort(String[] notLL, int s, int e) {
		if(s >= e) return;
		int mid = (s + e) / 2;
		
		mergeSort(notLL, s, mid);
		mergeSort(notLL, mid+1, e);
		
		merge(notLL, s, e);
	}

	private static void merge(String[] notLL, int s, int e) {
		int mid = (s+e)/2;
		String[] temp = new String[e-s+1];
		int ns = s, ne = mid+1;
		for(int idx = 0; idx < e-s+1; idx++) {
			if(ns > mid) temp[idx] = notLL[ne++];
			else if(ne > e) temp[idx] = notLL[ns++];
			else temp[idx] = (notLL[ne].compareTo(notLL[ns]) < 0) ? notLL[ne++] : notLL[ns++];
		}
		
		for(int i = 0; i < e-s+1; i++) {
			notLL[i+s] = temp[i];
		}
	}


}