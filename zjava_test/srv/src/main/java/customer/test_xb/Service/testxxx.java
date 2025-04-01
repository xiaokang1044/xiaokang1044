package customer.test_xb.Service;

import java.util.Scanner;

public class testxxx {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入数组的大小：");
        int size = scanner.nextInt();

        int[] array = new int[size];

        System.out.println("请输入数组的元素：");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

    }

    public static void test(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入质数之和：");
        int number = scanner.nextInt();
        int numberResult = 0;
        for (int i = 2; i <= number / 2; i++) {
            if (isPrime(i) && isPrime(number - i)) {
                if (numberResult <= i * (number - i)) {
                    numberResult = i * (number - i);

                }
            }
        }
        System.out.println(numberResult);

        int aa[] = new int[101];
        aa[2] = 0;
        int k = 2, tt = 0;
        while (tt < 101) {
            for (int i = 1; i < aa.length; i++) // 将不是素数的数筛出
            {
                if (i % k == 0 && i != k)
                    aa[i] = 1;
            }
            for (int i = 1; i < aa.length; i++) // 将筛选后的第一个数当做新的筛子
            {
                if (i > k && aa[i] == 0) {
                    k = i;
                    break;
                }
            }
            tt++;
        }

        for (int i = 1; i < aa.length; i++)
            if (aa[i] == 0)
                System.out.printf("%d ", i);

    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}