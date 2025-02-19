import java.util.Scanner;

public class Application1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num;

        while (true) {
            System.out.printf("2보다 큰 정수를 하나 입력하세요 : ");
            num = sc.nextInt();
            if (num > 2) {
                break;
            }
            System.out.println("잘못 입력하셨습니다. 다시 입력하세요.");
        }

        boolean isPrime = true;

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                isPrime = false;
                break;
            }
        }

        if (isPrime) {
            System.out.println("소수다.");
        } else {
            System.out.println("소수가 아니다.");
        }
    }
}
