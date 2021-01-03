package com.jaenyeong.curriculum.lv14.ps01;

import java.util.Scanner;

public class FindPower {
    /*
    거듭 제곱 구하기

    [문제]
    n과 m이 주어질 때, n의 m승을 구하는 프로그램을 작성하시오.
    단, n의 m승의 값이 커질 수 있기 때문에, 정답을 10,007 으로 나눈 나머지를 출력한다.

    [입력]
    첫 번째 줄에 숫자 n과 m이 주어진다. (1 <= n <= 100, 1 <= m <= 1,000,000,000,000,000,000)

    [출력]
    첫째 줄에 n의 m승을 10,007 으로 나눈 나머지를 출력한다.

    [예제 입력]
    3 4
    [예제 출력]
    81

     */

    private static final Scanner SC = new Scanner(System.in);
    private static final int REMAINDER = 10_007;

    public static void main(String[] args) {
        final int n = SC.nextInt();
        final long m = SC.nextLong();

        SC.close();

        System.out.println(power(n, m));
    }

    private static long power(final int n, final long m) {
        if (m == 0) {
            return 1;
        }

        if (m == 1) {
            return n;
        }

        // 지수가 짝수인 경우
        if (m % 2 == 0) {
            final long pow = power(n, (m / 2));
            return (pow * pow) % REMAINDER;
        }

        // 지수가 홀수인 경우
        final long pow = power(n, m - 1);
        return (pow * n) % REMAINDER;
    }
}
