package com.jaenyeong.curriculum.lv01.ps03;

import java.util.Scanner;

public class GetMaxValue {
    /*
    세 개의 숫자 중 최댓값 찾기

    [문제]
    3 개의 정수가 주어질 때, 이 중 최댓값을 찾는 프로그램을 작성하여라.

    [입력]
    첫째 줄에 3개의 정수 a, b, c 가 주어진다. (0 <= a, b, c <= 10,000)

    [출력]
    첫째 줄에 a,b,c 중 최댓값을 출력한다.

    [예제 입력]
    10 2 5
    [예제 출력]
    10

    [예제 입력]
    3 7 7
    [예제 출력]
    7

     */

    private static final Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) {
        final int p1 = SCAN.nextInt();
        final int p2 = SCAN.nextInt();
        final int p3 = SCAN.nextInt();

        SCAN.close();

        System.out.println(Math.max(p1, Math.max(p2, p3)));
    }
}
