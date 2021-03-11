package com.jaenyeong.previousproblem.lv01.step01;

import java.util.Scanner;

public class ClockwiseRotation {
    /*
    정방향 회전 알고리즘

    [문제]
    길이가 n인 수열이 있다.
    이 수열에서 x 번 인덱스의 y 칸 뒤의 수를 출력하는 프로그램을 작성하시오.
    단, 이 수열의 끝은 처음과 연결되어있다.
    인덱스가 0번부터 시작한다면, 0 번 인덱스 1 칸 뒤는 n-1 번 인덱스이다.
    본 문제에서는 시계 방향 회전을 정방향 회전이라고 정의했습니다.
    [clockwise_rotation.png]

    [입력]
    첫 번째 줄에 수열의 길이 n 이 주어진다.
    두 번째 줄에 수열을 구성하는 수 ai 가 n 개가 주어진다.
    같은 수의 ai 는 존재하지 않는다.
    세 번째 줄에 x 와 y 가 주어진다.
    (3 <= n <= 100, 1 <= ai <= n, 0 <= x <= n-1, 1 <= y <= n)

    [출력]
    첫 줄에 수열의 x 번 인덱스의 y 칸 뒤에 있는 수를 출력한다.

    [예제 입력]
    5
    2 3 1 5 4
    1 4
    [예제 출력]
    2

    [예제 입력]
    8
    1 3 4 2 8 7 5 6
    5 6
    [예제 출력]
    2

    [예제 입력]
    9
    1 6 9 7 4 2 3 5 8
    4 8
    [예제 출력]
    7

    [예제 입력]
    15
    5 3 1 2 4 7 11 14 9 8 6 12 13 15 10
    11 12
    [예제 출력]
    9

     */

    private static final Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = SCAN.nextInt();

        final int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = SCAN.nextInt();
        }

        final int startIdx = SCAN.nextInt();
        final int distToMove = SCAN.nextInt();

        SCAN.close();

        final int targetIdx = increaseIdx(n, startIdx, distToMove);
        System.out.println(arr[targetIdx]);
    }

    private static int increaseIdx(final int n, final int startIdx, final int distToMove) {
        int currentIdx = startIdx;
        for (int i = 0; i < distToMove; i++) {
            currentIdx++;
            if (currentIdx == n) currentIdx = 0;
        }

        return currentIdx;
    }
}
