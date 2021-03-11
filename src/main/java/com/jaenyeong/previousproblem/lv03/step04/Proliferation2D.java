package com.jaenyeong.previousproblem.lv03.step04;

import java.util.Scanner;
import java.util.StringJoiner;

public class Proliferation2D {
    /*
    2차원 확산

    [문제]
    세로 길이 N, 가로 길이 M 인 2차원 배열이 주어진다.
    이 배열의 각 칸에 들어있는 수는 1 초에 한 번 씩 자기 자신을 복사해서 상하좌우로 인접한 칸들에 더해준다.
    t 초가 지난 후 배열의 상태를 출력하여라.
    [proliferation_2d.png]

    [입력]
    첫 줄에 N, M 이 주어진다.
    두 번째 줄부터 N 줄에 걸쳐 배열의 값이 주어진다.
    마지막 줄에 t 가 주어진다.
    (1 <= N, M <= 100, 1 <= 배열의 값, t <= 10)

    [출력]
    t 초 후 배열을 출력한다.

    [예제 입력]
    3 4
    5 1 3 4
    2 5 1 2
    7 8 3 1
    2
    [예제 출력]
    41 48 46 26
    61 87 61 37
    59 70 56 27

    [예제 입력]
    2 5
    1 0 1 0 1
    0 1 0 1 0
    5
    [예제 출력]
    226 399 436 399 226
    235 381 454 381 235

    [예제 입력]
    3 3
    2 4 2
    4 2 4
    2 4 2
    3
    [예제 출력]
    126 166 126
    166 250 166
    126 166 126

    [예제 입력]
    3 4
    7 8 1 2
    6 9 7 5
    8 8 3 8
    10
    [예제 출력]
    4381720 6997542 6881404 4197186
    6212937 9918239 9758514 5949716
    4402841 7031994 6916199 4218862

     */

    private static final Scanner SC = new Scanner(System.in);
    private static final int[] NEXT_ROW = {-1, 1, 0, 0};
    private static final int[] NEXT_COL = {0, 0, -1, 1};

    public static void main(String[] args) {
        final int n = SC.nextInt();
        final int m = SC.nextInt();

        final int[][] origin = new int[n][m];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                origin[row][col] = SC.nextInt();
            }
        }

        final int t = SC.nextInt();

        SC.close();

        for (int testCase = 0; testCase < t; testCase++) {
            final int[][] copy = new int[n][m];
            for (int row = 0; row < n; row++) {
                System.arraycopy(origin[row], 0, copy[row], 0, m);
            }

            // 배열의 모든 요소 순회
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < m; col++) {

                    // 상하좌우
                    for (int d = 0; d < 4; d++) {
                        final int copyRow = row + NEXT_ROW[d];
                        final int copyCol = col + NEXT_COL[d];

                        if ((0 > copyRow) || (copyRow >= n) || (0 > copyCol) || (copyCol >= m)) {
                            continue;
                        }

                        // 상하좌우 위치에 해당 위치의 값을 합산
                        copy[copyRow][copyCol] += origin[row][col];
                    }
                }
            }

            for (int row = 0; row < n; row++) {
                System.arraycopy(copy[row], 0, origin[row], 0, m);
            }
        }

        for (int row = 0; row < n; row++) {
            final StringJoiner sj = new StringJoiner(" ");
            for (int col = 0; col < m; col++) {
                sj.add(Integer.toString(origin[row][col]));
            }
            System.out.println(sj);
        }
    }
}
