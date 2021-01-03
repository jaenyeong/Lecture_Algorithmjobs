package com.jaenyeong.curriculum.lv01.ps05;

import java.util.Scanner;

public class GetEvenSum {
    /*
    짝수의 합 구하기

    [문제]
    1부터 N까지의 숫자 중 짝수의 합을 구하는 프로그램을 작성하여라.

    [입력]
    첫째 줄에 N이 주어진다. (1 <= N <= 2000)

    [출력]
    1부터 N까지의 숫자 중 짝수의 합을 출력한다.

    [예제 입력]
    2
    [예제 출력]
    2

    [예제 입력]
    10
    [예제 출력]
    30

     */

    private static final Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = SCAN.nextInt();
        SCAN.close();

        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) sum += i;
        }

        System.out.println(sum);
    }
}
