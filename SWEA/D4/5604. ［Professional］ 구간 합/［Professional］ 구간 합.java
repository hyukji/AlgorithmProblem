import java.io.*;
import java.util.StringTokenizer;

public class Solution {
	
	private static final int[] oneDigitSum = {0, 0, 1, 3, 6, 10, 15, 21, 28, 36, 45};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
        	st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();

    		int a_value = 0;
    		int a_size = a.length();
    		for(int i = 0 ; i < a_size; i++) {
    			a_value += a.charAt(i) - '0';
    		}
            
            sb.append("#").append(tc).append(" ").append(calculate(b) - calculate(a) + a_value).append("\n");
        }
        
        System.out.println(sb);
    }

	private static long calculate(String value) {
		int n = value.length();
		int[] arr = new int[n];
		int s = 0;
		
		for(int i = 0 ; i < n; i++) {
			arr[i] = value.charAt(i) - '0';
			s += arr[i];
		}
		
		long answer = s;
		for(int i = 0; i < n; i++) {
			long digit = (long) Math.pow(10, i);
			
			s -= arr[n-1-i];
			answer += digit * s * arr[n-1-i];
			answer += digit * oneDigitSum[arr[n-1-i]];
			answer += arr[n-1-i] * 45 * i * (long) digit / 10;
		}
		
		return answer;
	}

}