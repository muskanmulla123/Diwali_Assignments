import java.util.Scanner;

public class Question2 {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
    int n = sc.nextInt();

    int reversed = 0;
    int original = n;

        while (n != 0) {
        int digit = n % 10;        // Extract last digit
        reversed = reversed * 10 + digit; // Append to reversed
        n = n / 10;                // Remove last digit
    }

        System.out.println("Reversed number: " + reversed);
}
}
