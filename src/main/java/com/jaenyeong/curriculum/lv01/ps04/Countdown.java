package com.jaenyeong.curriculum.lv01.ps04;

import java.util.Scanner;

public class Countdown {
    /*
    Countdown

    [문제]
    N이 주어질 때, N부터 1까지 카운트다운을 하는 프로그램을 작성하시오.
    즉, N부터 1까지 숫자를 모두 출력하는 프로그램을 작성하시오.

    [입력]
    첫 번째 줄에 숫자 N이 주어진다. (1 <= N <= 100)

    [출력]
    숫자 N부터 1까지를 한 줄에 하나씩 출력한다.

    [예제 입력]
    10
    [예제 출력]
    10
    9
    8
    7
    6
    5
    4
    3
    2
    1

     */

    private static final Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = SCAN.nextInt();
        SCAN.close();

        for (int i = n; i > 0; i--) {
            System.out.println(i);
        }
    }
}
