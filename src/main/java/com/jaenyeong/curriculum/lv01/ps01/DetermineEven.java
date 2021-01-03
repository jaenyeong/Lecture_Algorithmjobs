package com.jaenyeong.curriculum.lv01.ps01;

import java.util.Scanner;

public class DetermineEven {
    /*
    짝수 판별하기

    [문제]
    정수 N을 입력받고, N이 짝수인지 아닌지 판별하는 프로그램을 작성해보자. (단, if문과 else문 모두 사용할 것)

    [입력]
    첫째 줄에 자연수 N이 주어진다.

    [출력]
    첫째 줄에 N이 짝수라면 “even”, N이 짝수가 아니라면 “not even"를 출력한다.

    [예제 입력]
    10
    [예제 출력]
    even

    [예제 입력]
    5
    [예제 출력]
    not even

     */

    private static final Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = SCAN.nextInt();
        SCAN.close();

        // even
        if (n % 2 == 0) {
            System.out.println("even");
        } else {
            System.out.println("not even");
        }
    }
}
