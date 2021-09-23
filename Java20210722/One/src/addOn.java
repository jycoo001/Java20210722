import java.util.Scanner;

public class addOn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        int n = Integer.parseInt(str1);
        String str2 = scanner.nextLine();
        int[] a = new int[n];
        int ma = 0;
        int count = 0;
        for (int i = 0; i < a.length; i+=2) {
            a[i] = str2.charAt(i) - 48;
        }
        int max = 1;
        for (int i = 0; i < a.length; i++) {
            max = a[i] * max;
        }
        int[] b = new int[n];
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (i != j) {
                    b[i] = b[i] * b[j];
                }
            }
        }
        for (int i = 0; i < a.length; i++) {
            int num = b[i];
            for (int j = i; j < a.length; j++) {
                num = num + b[j];
                if (num == max) {
                    count++;
                    num = num - b[j];
                } else if (num > max) {
                    num = num - b[j];
                }
            }
        }
        if (count == 0) {
            System.out.println("No solution!");
        }else System.out.println(count);
    }
}
