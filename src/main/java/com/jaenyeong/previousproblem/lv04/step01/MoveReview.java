package com.jaenyeong.previousproblem.lv04.step01;

import java.util.Scanner;

public class MoveReview {
    /*
    이동 복습

    [문제]
    N × M 크기의 2차원 배열이 있다.
    이 배열의 가장 왼쪽 위 칸은 (0, 0)으로, 가장 오른쪽 아래 칸은 (N-1, M-1)로 표현한다.
    총 q번의 질문에 대한 답을 하는 프로그램을 작성하여라.
    각 질문은 두 정수 d, r 로 주어지는데, 이전 출력 위치를 (y, x)라고 할 때,
    이 배열의 (y, x) 위치에서 d 방향으로 r 만큼 이동한 위치의 값을 출력해야 한다.
    d 가 0 이면 오른쪽, 1 이면 왼쪽, 2 면 아래, 3 이면 위를 의미한다.
    이동 과정에서 -1 을 만나거나 배열의 범위 밖으로 나가려고 할 경우, 그 이전 자리에서 멈춘다.
    프로그램 시작 시 초기 y, x 는 모두 0 이다.

    [입력]
    첫 번째 줄에 배열의 크기 N, M 과 질문의 수 q 가 주어진다.
    두 번째 줄부터 N 개의 줄에 걸쳐 배열의 정보가 주어진다.
    각 줄에는 M 개의 숫자가 주어진다.
    바로 다음 줄부터 q 개의 줄에 걸쳐 질문 정보가 주어진다.
    각 줄에는 d, r 이 주어진다.
    (3 <= N, M <= 100, 1 <= q <= 100, 0 <= d < 4, 1 <= r <= max(N, M))

    [출력]
    q 개의 줄에 걸쳐 문제에서 구하고자하는 값을 출력한다.

    [예제 입력]
    4 5 3
    2 -1 1 5 4
    1 5 5 4 2
    1 8 3 -1 1
    8 9 7 2 4
    2 2
    0 4
    3 4
    [예제 출력]
    1
    3
    1

    [예제 입력]
    4 5 3
    2 -1 1 5 4
    1 5 5 4 2
    -1 8 3 -1 1
    8 9 7 2 4
    2 2
    0 4
    3 4
    [예제 출력]
    1
    2
    4

    [예제 입력]
    4 5 2
    2 3 1 5 4
    1 3 5 4 2
    1 8 3 6 1
    8 9 7 2 4
    0 4
    1 2
    [예제 출력]
    4
    1

    [예제 입력]
    4 5 3
    2 3 1 5 4
    1 3 5 4 2
    1 8 3 -1 1
    8 9 7 2 4
    2 2
    1 1
    0 3
    [예제 출력]
    1
    1
    3

    [설명]
    입력의 예 1 번 상황의 그림이다.
    [이동_복습.png]

     */

    private static final Scanner SC = new Scanner(System.in);
    private static final int[][] NEXT_DIRECTION = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static final int BREAK = -1;
    private static final int ROW = 0;
    private static final int COL = 1;

    public static void main(String[] args) {
        final int n = SC.nextInt();
        final int m = SC.nextInt();
        final int q = SC.nextInt();

        final int[][] grid = new int[n][m];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                grid[row][col] = SC.nextInt();
            }
        }

        int currentRow = 0;
        int currentCol = 0;

        for (int i = 0; i < q; i++) {
            final int direction = SC.nextInt();
            final int numberOfMove = SC.nextInt();

            for (int move = 0; move < numberOfMove; move++) {
                int nextRow = currentRow + NEXT_DIRECTION[direction][ROW];
                int nextCol = currentCol + NEXT_DIRECTION[direction][COL];

                if ((0 > nextRow) || (nextRow >= n) || (0 > nextCol) || (nextCol >= m)) {
                    break;
                }

                if (grid[nextRow][nextCol] == BREAK) {
                    break;
                }

                currentRow = nextRow;
                currentCol = nextCol;
            }

            System.out.println(grid[currentRow][currentCol]);
        }

        SC.close();
    }
}
