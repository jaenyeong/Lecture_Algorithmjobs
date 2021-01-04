package com.jaenyeong.previousproblem.lv01.step03;

import java.util.Scanner;

public class Rotation {
    /*
    회전 알고리즘

    [문제]
    길이가 n인 수열이 있다.
    총 q번의 질문에 대한 답을 하는 프로그램을 작성하여라.
    각 질문은 두 정수 d, y로 주어지는데 이전에 출력한 수의 위치를 x라고 할 때,
    d가 1인 경우 x번 인덱스의 y 칸 앞의 수를, d가 2인 경우 x번 인덱스의 y칸 뒤의 수를 출력해야 한다.
    프로그램 시작 시 초기 x는 0이다.
    단, 이 수열의 끝은 처음과 연결되어있고, 인덱스는 0번부터 시작한다.
    0 번 인덱스 1 칸 앞은 n-1 번 인덱스이고, n-1 번 인덱스 1 칸 다음은 0번 인덱스이다.
    [회전_알고리즘.png]

    [입력]
    첫 번째 줄에 수열의 길이 n 과 질문의 수 q 가 주어진다.
    두 번째 줄에 수열을 구성하는 수 ai 가 n 개가 주어진다.
    같은 수의 ai 는 존재하지 않는다.
    세 번째 줄부터 q + 2 줄까지 각 줄마다 d, y 가 주어진다.
    (3 <= n, q <= 100, 1 <= ai <= n, d: 1 or 2, 1 <= y <= 100)

    [출력]
    q 개의 질문 각각에 대한 위치 값을 한 줄에 하나씩 출력한다.

    [예제 입력]
    5 3
    2 3 1 5 4
    1 4
    2 2
    1 14
    [예제 출력]
    4
    1
    3

    [예제 입력]
    7 3
    5 3 2 4 7 6 1
    1 5
    1 13
    2 19
    [예제 출력]
    6
    7
    1

    [예제 입력]
    10 4
    9 5 1 3 4 2 6 7 10 8
    2 6
    2 17
    1 26
    2 13
    [예제 출력]
    4
    7
    3
    9

     */

    private static final Scanner SCAN = new Scanner(System.in);
    private static final int CLOCK_WISE = 1;

    public static void main(String[] args) {
        final int arrLength = SCAN.nextInt();
        final int testCase = SCAN.nextInt();

        final int[] arr = new int[arrLength];
        for (int i = 0; i < arrLength; i++) {
            arr[i] = SCAN.nextInt();
        }

        // 최초 인덱스 위치는 0
        int currentIdx = 0;

        // 테스트 케이스 수만큼 반복하며 확인
        for (int i = 0; i < testCase; i++) {

            // 이동 방향과 이동할 거리 입력
            final int direction = SCAN.nextInt();
            final int distToMove = SCAN.nextInt();

            // 정방향 이동
            if (direction == CLOCK_WISE) {
                currentIdx = moveClockwise(arrLength, currentIdx, distToMove);
                System.out.println(arr[currentIdx]);
                continue;
            }

            // 역방향 이동
            currentIdx = moveCounterClockwise(arrLength, currentIdx, distToMove);
            System.out.println(arr[currentIdx]);
        }

        SCAN.close();
    }

    private static int moveClockwise(final int arrLength, final int startIdx, final int distToMove) {
        int currentIdx = startIdx;
        for (int i = 0; i < distToMove; i++) {
            currentIdx++;
            if (currentIdx == arrLength) {
                currentIdx = 0;
            }
        }

        return currentIdx;
    }

    private static int moveCounterClockwise(final int arrLength, final int startIdx, final int distToMove) {
        int currentIdx = startIdx;
        for (int i = 0; i < distToMove; i++) {
            currentIdx--;
            if (currentIdx == -1) {
                currentIdx = (arrLength - 1);
            }
        }

        return currentIdx;
    }
}
