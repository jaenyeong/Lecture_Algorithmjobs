package com.jaenyeong.curriculum.lv02.ps04;

import java.util.Scanner;

public class FindSecondMinValue {
    /*
    두 번째 최솟값 찾기

    [문제]
    9개의 서로 다른 자연수가 주어질 때, 이들 중 두 번째 최솟값을 찾고, 그 두 번째 최솟값이 몇 번째 수인지를 구하는 프로그램을 작성하시오.
    예를 들어, 서로 다른 9개의 자연수 3, 29, 38, 12, 57, 74, 40, 85, 61 이 주어지면, 이들 중 두 번째 최솟값은 12이고, 이 값은 4번째 수이다.

    [입력]
    첫째 줄부터 아홉 번째 줄까지 한 줄에 하나의 자연수가 주어진다.
    주어지는 자연수는 10,000,000 보다 작다.

    [출력]
    첫째 줄에 두 번째 최솟값을 출력하고, 둘째 줄에 두 번째 최솟값이 몇 번째 수인지를 출력한다.

    [예제 입력]
    3
    29
    38
    12
    57
    74
    40
    85
    61
    [예제 출력]
    12
    4

     */

    private static final Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) {
        final int[] arr = new int[9];

        for (int i = 0; i < 9; i++) {
            arr[i] = SCAN.nextInt();
        }

        SCAN.close();

        // 가장 작은 수 찾기
        int firstMin = (int) 1e9;
        int firstMinIdx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (firstMin > arr[i]) {
                firstMin = arr[i];
                firstMinIdx = i;
            }
        }

        // 두 번째 작은 수 찾기
        int secondMin = (int) 1e9;
        int secondIdx = 8;
        for (int i = 0; i < arr.length; i++) {
            if ((firstMinIdx != i) && (secondMin > arr[i])) {
                secondMin = arr[i];
                secondIdx = i;
            }
        }

        System.out.printf("%d\n%d", secondMin, secondIdx + 1);
    }
}
