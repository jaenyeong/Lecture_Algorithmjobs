package com.jaenyeong.previousproblem.lv03.step02;

import java.util.Scanner;
import java.util.StringJoiner;

public class Push2D {
    /*
    2차원 밀기

    [문제]
    세로 길이 N, 가로 길이 M 인 2차원 배열이 주어진다.
    이 배열의 테두리가 시계방향으로 한 칸 씩 밀린 배열을 출력하여라.
    여기서 테두리란 배열의 상하좌우 끝 줄을 의미한다.
    [2차원_밀기.png]

    [입력]
    첫 줄에 N, M 이 주어진다.
    두 번째 줄부터 N 줄에 걸쳐 각 줄에 M 개씩 배열의 수가 주어진다.
    (3 <= N, M <= 100, 1 <= 수열을 구성하는 수 <= (N * M))

    [출력]
    밀기가 끝난 배열을 출력한다.

    [예제 입력]
    3 5
    2 3 1 1 4
    1 3 2 3 4
    1 2 7 3 7
    [예제 출력]
    1 2 3 1 1
    1 3 2 3 4
    2 7 3 7 4

    [예제 입력]
    4 4
    1 2 4 2
    3 4 1 7
    8 3 1 6
    9 8 9 7
    [예제 출력]
    3 1 2 4
    8 4 1 2
    9 3 1 7
    8 9 7 6

    [예제 입력]
    3 5
    1 3 4 2 4
    1 2 5 8 7
    6 4 5 2 7
    [예제 출력]
    1 1 3 4 2
    6 2 5 8 4
    4 5 2 7 7

    [예제 입력]
    6 5
    7 1 3 5 4
    2 3 4 8 3
    2 1 4 7 9
    9 8 5 3 1
    2 3 6 7 4
    1 4 3 2 5
    [예제 출력]
    2 7 1 3 5
    2 3 4 8 4
    9 1 4 7 3
    2 8 5 3 9
    1 3 6 7 1
    4 3 2 5 4

     */

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = SC.nextInt();
        final int m = SC.nextInt();

        final int[][] arr = new int[n][m];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                arr[row][col] = SC.nextInt();
            }
        }

        SC.close();

        // 시계 방향으로 밀기
        final int first = arr[0][0];

        // 왼쪽
        for (int row = 1; row < n; row++) {
            arr[row - 1][0] = arr[row][0];
        }

        // 아래쪽
        if (m - 1 >= 0) {
            System.arraycopy(arr[n - 1], 1, arr[n - 1], 0, m - 1);
        }

        // 오른쪽
        for (int row = (n - 2); row >= 0; row--) {
            arr[row + 1][m - 1] = arr[row][m - 1];
        }

        // 위쪽
        System.arraycopy(arr[0], 0, arr[0], 1, m - 1);

        arr[0][1] = first;

        for (int row = 0; row < n; row++) {
            final StringJoiner sj = new StringJoiner(" ");
            for (int col = 0; col < m; col++) {
                sj.add(Integer.toString(arr[row][col]));
            }

            System.out.println(sj.toString());
        }
    }
}
