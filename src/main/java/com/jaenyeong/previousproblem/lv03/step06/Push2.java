package com.jaenyeong.previousproblem.lv03.step06;

import java.util.Scanner;
import java.util.StringJoiner;

public class Push2 {
    /*
    밀기 알고리즘2

    [문제]
    세로 길이 N, 가로 길이 M 인 2차원 배열과 두 정수 u, d 가 주어진다.
    이 배열의 0 번 부터 u 번 까지의 행은 테두리가 시계방향으로 한 칸 씩 밀리고,
    d 번 부터 n - 1 번 까지의 행은 테두리가 반시계방향으로 한 칸 씩 밀린 배열을 출력하여라.
    여기서 테두리란 해당 범위의 상하좌우 끝 줄을 의미한다.
    [push2.png]

    [입력]
    첫 줄에 N, M 이 주어진다.
    두 번째 줄부터 N 줄에 걸쳐 각 줄에 M 개씩 배열의 수가 주어진다.
    마지막 줄에 u, d 가 주어진다.
    (4 <= N, M <= 100, 1 <= 수열을 구성하는 수 <= (N * M), 0 < u < d < (N - 1))

    [출력]
    밀기가 끝난 배열을 출력한다.

    [예제 입력]
    5 5
    2 3 1 1 4
    1 3 2 3 4
    1 2 7 3 7
    3 2 1 4 2
    1 4 5 5 1
    1 2
    [예제 출력]
    1 2 3 1 1
    3 2 3 4 4
    2 7 3 7 2
    1 2 1 4 1
    3 1 4 5 5

    [예제 입력]
    5 5
    1 3 4 2 4
    1 2 5 8 7
    6 4 5 2 7
    3 4 2 8 9
    9 8 7 4 5
    1 3
    [예제 출력]
    1 1 3 4 2
    2 5 8 7 4
    6 4 5 2 7
    4 2 8 9 5
    3 9 8 7 4

    [예제 입력]
    4 4
    1 2 4 2
    3 4 1 7
    8 3 1 6
    9 8 9 7
    1 2
    [예제 출력]
    3 1 2 4
    4 1 7 2
    3 1 6 7
    8 9 8 9

    [예제 입력]
    6 5
    7 1 3 5 4
    2 3 4 8 3
    2 1 4 7 9
    9 8 5 3 1
    2 3 6 7 4
    1 4 3 2 5
    2 4
    [예제 출력]
    2 7 1 3 5
    2 3 4 8 4
    1 4 7 9 3
    9 8 5 3 1
    3 6 7 4 5
    2 1 4 3 2

     */

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = SC.nextInt();
        final int m = SC.nextInt();

        final int[][] numbers = new int[n][m];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                numbers[row][col] = SC.nextInt();
            }
        }

        final int u = SC.nextInt();
        final int d = SC.nextInt();

        SC.close();

        // 시계
        final int topStart = numbers[0][0];

        // left
        for (int row = 1; row <= u; row++) {
            numbers[row - 1][0] = numbers[row][0];
        }

        // bottom
        System.arraycopy(numbers[u], 1, numbers[u], 0, m - 1);

        // right
        for (int row = u - 1; row >= 0; row--) {
            numbers[row + 1][m - 1] = numbers[row][m - 1];
        }

        // top
        System.arraycopy(numbers[0], 0, numbers[0], 1, m - 1);

        numbers[0][1] = topStart;

        // 반시계
        final int bottomStart = numbers[d][0];

        // top
        System.arraycopy(numbers[d], 1, numbers[d], 0, m - 1);

        // right
        for (int row = (d + 1); row < n; row++) {
            numbers[row - 1][m - 1] = numbers[row][m - 1];
        }

        // bottom
        System.arraycopy(numbers[n - 1], 0, numbers[n - 1], 1, m - 1);

        // left
        for (int row = (n - 2); row >= d; row--) {
            numbers[row + 1][0] = numbers[row][0];
        }

        numbers[d + 1][0] = bottomStart;

        printArr(n, m, numbers);
    }

    private static void printArr(int n, int m, int[][] numbers) {
        for (int row = 0; row < n; row++) {
            final StringJoiner sj = new StringJoiner(" ");
            for (int col = 0; col < m; col++) {
                sj.add(Integer.toString(numbers[row][col]));
            }

            System.out.println(sj);
        }
    }
}
