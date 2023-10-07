import java.util.Scanner;

public class Q9655 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        String answer = (n%2==1) ? "SK" : "CY";
        System.out.println(answer);
    }

}