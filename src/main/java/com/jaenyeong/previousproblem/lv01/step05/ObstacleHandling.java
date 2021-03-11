package com.jaenyeong.previousproblem.lv01.step05;

import java.util.Scanner;

public class ObstacleHandling {
    /*
    장애물 처리 알고리즘

    [문제]
    N x M 크기의 2 차원 배열이 있다.
    이 배열의 가장 왼쪽 위 칸은 (0, 0)으로, 가장 오른쪽 아래 칸은 (N-1, M-1)로 표현한다.
    배열의 크기 n, m 과 배열의 값들, y, x, d, r 이 주어질 때,
    이 배열의 (y, x) 위치에서 d 방향으로 r 만큼 이동한 위치의 값을 출력하는 프로그램을 작성하여라.
    d 가 0 이면 오른쪽, 1 이면 왼쪽, 2 면 아래, 3 이면 위를 의미한다.
    단, 이동 과정에서 -1 을 만나면 그 이전 위치에서 멈춘다.
    처음 위치와 도착 위치 모두 배열 안에 있고, 처음 위치는 -1 이 아님이 보장된다.

    [입력]
    첫 번째 줄에 배열의 크기 N, M 이 주어진다.
    두 번째 줄부터 N 개의 줄에 걸쳐 배열의 값들이 주어진다.
    각 줄마다 M 개씩 숫자가 존재한다.
    마지막 줄에 y, x, d, r 이 주어진다.
    (3 <= N, M <= 100, 1 <= y <= (N - 1), 1 <= x <= (M - 1), 0 <= d < 4)
    ([if] d: 2, 3 → 0 <= (x ± r) < N, [if] d: 0, 1 → 0 <= (y ± r) < M)

    [출력]
    첫 줄에 문제에서 구해야하는 위치 값을 출력한다.

    [예제 입력]
    4 5
    2 -1 1 5 4
    1 5 5 4 2
    1 8 3 6 1
    8 9 7 2 4
    3 1 3 3
    [예제 출력]
    5

    [예제 입력]
    4 5
    2 3 1 5 4
    1 -1 5 4 2
    1 8 3 6 1
    8 9 7 2 4
    3 1 3 3
    [예제 출력]
    8

    [예제 입력]
    4 5
    2 3 1 5 4
    1 3 5 4 2
    1 8 3 6 1
    8 9 7 2 -1
    0 4 2 3
    [예제 출력]
    1

    [예제 입력]
    4 5
    2 3 1 5 4
    1 3 5 4 2
    -1 8 3 6 1
    8 9 7 2 4
    2 2 1 2
    [예제 출력]
    8

    [설명]
    입력의 예 1 번의 경우 (3, 1) 위치에 있는 9 가 출발지점이다.
    d 가 3, r 이 3 이므로 위로 3 칸 이동해야 한다.
    하지만 2 칸은 정상적으로 이동해서 (1, 1)로 이동한다.
    그 다음 칸인 (0, 1)로 이동하는 시점에 (0, 1)이 -1 이므로, 이동하지 못하고 (1, 1)위치에 정지하게 된다.
    따라서 5가 정답이다.
    [obstacle_handling.png]

     */

    private static final Scanner SCAN = new Scanner(System.in);

    // Right, Left, Down, Up
    private static final int[] ROW = {0, 0, 1, -1};
    private static final int[] COL = {1, -1, 0, 0};

    private static final int WALL = -1;

    public static void main(String[] args) {
        final int n = SCAN.nextInt();
        final int m = SCAN.nextInt();

        final int[][] grid = new int[n][m];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                grid[row][col] = SCAN.nextInt();
            }
        }

        int currentRow = SCAN.nextInt();
        int currentCol = SCAN.nextInt();

        final int dir = SCAN.nextInt();
        final int distToMove = SCAN.nextInt();

        SCAN.close();

        for (int i = 0; i < distToMove; i++) {
            final int nextRow = currentRow + ROW[dir];
            final int nextCol = currentCol + COL[dir];

            final int nextValue = grid[nextRow][nextCol];

            if (nextValue == WALL) break;

            currentRow = nextRow;
            currentCol = nextCol;
        }

        System.out.println(grid[currentRow][currentCol]);
    }
}
