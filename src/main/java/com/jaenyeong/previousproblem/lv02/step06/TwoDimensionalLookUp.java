package com.jaenyeong.previousproblem.lv02.step06;

import java.util.Scanner;
import java.util.StringJoiner;

public class TwoDimensionalLookUp {
    /*
    2차원 조회

    [문제]
    세로 길이 n, 가로 길이 m인 2차원 배열이 있다.
    두 점 A, B가 인접하다는 것은 두 점이 배열에서 상하좌우로 붙어있는 것을 의미한다.
    이 배열의 각 인덱스에 대해 인접한 인덱스에 자신과 같은 수가 있으면 1을, 없으면 0을 출력하여라.
    [two_dimensional_look_up.png]

    [입력]
    첫 줄에 n, m이 주어진다.
    두 번 째 줄부터 n개의 줄에 각 m개의 수가 주어진다.
    (3 <= n, m <= 100, 1 <= 수열의 구성하는 수 <= (n * m))

    [출력]
    n줄에 각 인덱스마다 인접한 인덱스에 같은 수가 있으면 1을, 없으면 0을 출력한다.

    [예제 입력]
    3 5
    2 3 1 1 4
    1 3 2 3 4
    1 2 7 3 7
    [예제 출력]
    0 1 1 1 1
    1 1 0 1 1
    1 0 0 1 0

    [예제 입력]
    2 5
    1 2 1 3 2
    1 2 1 3 2
    [예제 출력]
    1 1 1 1 1
    1 1 1 1 1

    [예제 입력]
    3 7
    2 2 2 3 3 3 4
    1 3 1 2 1 3 1
    2 4 2 4 2 3 3
    [예제 출력]
    1 1 1 1 1 1 0
    0 0 0 0 0 1 0
    0 0 0 0 0 1 1

    [예제 입력]
    3 3
    1 2 3
    4 5 6
    7 8 9
    [예제 출력]
    0 0 0
    0 0 0
    0 0 0

     */

    private static final Scanner SC = new Scanner(System.in);
    // 상하좌우 순서
    private static final int[] NEXT_ROW = {-1, 1, 0, 0};
    private static final int[] NEXT_COL = {0, 0, -1, 1};

    public static void main(String[] args) {
        final int n = SC.nextInt();
        final int m = SC.nextInt();

        final int[][] numbers = new int[n][m];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                numbers[row][col] = SC.nextInt();
            }
        }

        SC.close();

        final int[][] check = new int[n][m];

        search(n, m, numbers, check);

        print(check, n, m);
    }

    private static void search(final int n, final int m, final int[][] numbers, final int[][] check) {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                aroundSearch(n, m, numbers, check, row, col);
            }
        }
    }

    private static void aroundSearch(final int n, final int m, final int[][] numbers, final int[][] check,
                                     final int row, final int col) {
        for (int i = 0; i < 4; i++) {
            final int nextRow = row + NEXT_ROW[i];
            final int nextCol = col + NEXT_COL[i];

            if ((0 > nextRow) || (nextRow >= n) || (0 > nextCol) || (nextCol >= m)) {
                continue;
            }

            if (numbers[row][col] == numbers[nextRow][nextCol]) {
                check[row][col] = 1;
                break;
            }
        }
    }

    private static void print(final int[][] check, final int n, final int m) {
        for (int row = 0; row < n; row++) {
            final StringJoiner sj = new StringJoiner(" ");
            for (int col = 0; col < m; col++) {
                sj.add(Integer.toString(check[row][col]));
            }

            System.out.println(sj);
        }
    }
}
