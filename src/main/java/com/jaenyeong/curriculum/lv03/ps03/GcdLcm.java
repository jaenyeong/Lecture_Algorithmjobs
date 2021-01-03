package com.jaenyeong.curriculum.lv03.ps03;

import java.util.Scanner;

public class GcdLcm {
    /*
    GCD LCM

    [문제]
    두 개의 자연수를 입력받아 최대공약수(GCD)와 최소공배수(LCM)를 출력하는 프로그램을 작성하시오.

    [입력]
    첫째 줄에는 두 개의 자연수가 주어진다.
    이 둘은 10,000 이하의 자연수이며 사이에 한 칸의 공백이 주어진다.

    [출력]
    첫째 줄에는 입력으로 주어진 두 수의 최대공약수를, 둘째 줄에는 입력으로 주어진 두 수의 최소공배수를 출력한다.

    [예제 입력]
    24 18
    [예제 출력]
    6
    72

     */

    private static final Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) {
        final int a = SCAN.nextInt();
        final int b = SCAN.nextInt();

        SCAN.close();

        // GCD
        final int gcd = getGCD(a, b);
        System.out.println(gcd);

        // LCM
        System.out.println(getLCM(a, b, gcd));
    }

    private static int getGCD(final int a, final int b) {
        int p1 = a;
        int p2 = b;

        while (true) {
            int r = p1 % p2;

            if (r == 0) return p2;

            p1 = p2;
            p2 = r;
        }
    }

    private static int getLCM(final int a, final int b, final int gcd) {
        return (a / gcd) * (b / gcd) * gcd;
    }
}
