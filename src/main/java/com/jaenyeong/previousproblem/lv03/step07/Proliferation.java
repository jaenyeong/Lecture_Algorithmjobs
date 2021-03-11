package com.jaenyeong.previousproblem.lv03.step07;

import java.util.Scanner;
import java.util.StringJoiner;

public class Proliferation {
    /*
    확산 알고리즘

    [문제]
    세로 길이 N, 가로 길이 M 인 2차원 배열이 주어진다.
    이 배열의 각 칸에 들어있는 수는 1초에 한번씩 자기 자신을 나눠서 x, y좌표와의 거리가 1이상 k이하인 칸들에 더해준다.
    두 점 (x1, y1), (x2, y2)의 거리는 |x1 - x2| + |y1 - y2|로 구한다.
    이때 주변에 나눠주는 값은 현재 그 칸에 있는 값을 2 k2 + 2 k + 1로 나눈 몫이고, 주변에 나눠준 만큼 원래 있던 값은 줄어들게 된다.
    예를 들어, 아래 그림에서 k가 1이라면 t = 0의 (2,1)위치에 있는 8은 8/5의 몫인 1을 주변에 있는 세 칸에 나눠주고, 자기자신은 3만큼 줄어든다.
    t초가 지난 후 배열의 상태를 출력하여라.
    [proliferation.png]

    [입력]
    첫 번째 줄에 N, M, k 가 주어진다.
    두 번째 줄부터 N 줄에 걸쳐 배열의 값이 주어진다.
    마지막 줄에 t 가 주어진다.
    (1 <= N, M <= 5, 1 <= k <= 4, 1 <= 배열의 값 <= 10, 1 <= t <= 3)

    [출력]
    t 초 후 배열을 출력한다.

    [예제 입력]
    3 4 1
    5 1 3 4
    2 5 1 2
    7 8 3 1
    2
    [예제 출력]
    4 3 3 4
    3 4 2 2
    6 5 5 1

    [예제 입력]
    3 3 1
    2 4 2
    4 2 4
    2 4 2
    3
    [예제 출력]
    2 4 2
    4 2 4
    2 4 2

    [예제 입력]
    2 5 2
    10 10 10 10 10
    10 10 10 10 10
    5
    [예제 출력]
    10 10 10 10 10
    10 10 10 10 10

    [예제 입력]
    3 4 2
    17 18 11 12
    16 19 17 15
    18 18 13 18
    10
    [예제 출력]
    16 16 15 15
    16 16 13 18
    17 17 18 15

     */

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = SC.nextInt();
        final int m = SC.nextInt();

        final int k = SC.nextInt();

        final int[][] numbers = new int[n][m];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                numbers[row][col] = SC.nextInt();
            }
        }

        final int t = SC.nextInt();

        SC.close();

        final int figure = getFigure(k);

        for (int i = 0; i < t; i++) {

            // 복사 배열 초기화
            final int[][] copyArr = new int[n][m];
            for (int copyRow = 0; copyRow < n; copyRow++) {
                System.arraycopy(numbers[copyRow], 0, copyArr[copyRow], 0, m);
            }

            for (int row = 0; row < n; row++) {
                for (int col = 0; col < m; col++) {

                    final int curFigure = numbers[row][col] / figure;

                    if (curFigure < 1) {
                        continue;
                    }

                    final int startRow = Math.max((row - k), 0);
                    final int endRow = Math.min((row + k), (n - 1));

                    final int startCol = Math.max((col - k), 0);
                    final int endCol = Math.min((col + k), (m - 1));

                    final int proliferation =
                        getProliferation(k, copyArr, row, col, curFigure, startRow, endRow, startCol, endCol);

                    copyArr[row][col] -= (proliferation * curFigure);
                }
            }

            // copy
            for (int row = 0; row < n; row++) {
                System.arraycopy(copyArr[row], 0, numbers[row], 0, m);
            }
        }

        print(n, m, numbers);
    }

    private static int getProliferation(final int k, final int[][] copyArr, final int row, final int col, final int curFigure,
                                        final int startRow, final int endRow, final int startCol, final int endCol) {
        int proliferation = 0;

        for (int nextRow = startRow; nextRow <= endRow; nextRow++) {
            for (int nextCol = startCol; nextCol <= endCol; nextCol++) {

                final int dist = Math.abs(row - nextRow) + Math.abs(col - nextCol);

                if (dist > k) {
                    continue;
                }

                if ((nextRow == row) && (nextCol == col)) {
                    continue;
                }

                copyArr[nextRow][nextCol] += curFigure;
                proliferation++;
            }
        }
        return proliferation;
    }

    private static void print(final int n, final int m, final int[][] numbers) {
        for (int row = 0; row < n; row++) {
            final StringJoiner sj = new StringJoiner(" ");
            for (int col = 0; col < m; col++) {
                sj.add(Integer.toString(numbers[row][col]));
            }
            System.out.println(sj);
        }
    }

    private static int getFigure(final int k) {
        //  2 k2 + 2 k + 1
        return (2 * (k * k)) + (2 * k) + 1;
    }
}
