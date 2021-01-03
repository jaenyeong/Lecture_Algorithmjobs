package com.jaenyeong.curriculum.lv01.ps08;

import java.util.Scanner;

public class CountMultiple {
    /*
    배수의 개수 세기

    [문제]
    세 개의 자연수 A, B, C가 주어진다.
    이 때, A부터 B까지 숫자 중에서 C의 배수의 개수를 출력하는 프로그램을 작성하시오.
    예를 들어, A = 3, B = 9, C = 2 라고 하자.
    그러면 3부터 9까지 숫자 중 2의 배수의 개수이므로 4, 6, 8 총 3개가 존재한다.
    따라서 3을 출력한다.
    또한, A = 10, B = 3, C = 4라고 하자.
    그러면 10에서 3까지 숫자 중 4의 배수의 개수이므로 4, 8 총 2개가 존재한다.
    따라서 2를 출력한다.

    [입력]
    첫 번째 줄에 A, B, C가 주어진다. (1 <= A, B, C <= 1000)

    [출력]
    A부터 B까지 숫자 중에서 C의 배수의 개수를 출력한다.

    [예제 입력]
    3 9 2
    [예제 출력]
    3

    [예제 입력]
    10 3 4
    [예제 출력]
    2

     */

    private static final Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) {
        final int a = SCAN.nextInt();
        final int b = SCAN.nextInt();
        final int target = SCAN.nextInt();
        SCAN.close();

        final int min = Math.min(a, b);
        final int max = Math.max(a, b);

        int count = 0;
        for (int rangeIdx = min; rangeIdx <= max; rangeIdx++) {
            if (rangeIdx % target == 0) count++;
        }

        System.out.println(count);
    }
}
