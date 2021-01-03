package com.jaenyeong.curriculum.lv19.ps06;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class EscapeMazeOfCarpenter {
    /*
    목수의 미로 탈출

    [문제]
    아래와 같이 이동할 수 있는 길, 그리고 이동할 수 없는 벽으로 이루어진 크기 N x M 의 지도가 주어진다.
    이 때, (N-1, 0) 에서 출발하여 (0, M-1) 까지 도착하는 최단거리를 찾으려 한다.
    그런데 목수는 도끼 하나를 갖고 있으며, 이 도끼를 이용하여 벽을 깨부술 수 있다.
    하지만 이 도끼는 내구성이 그렇게 좋지 않기 때문에, 벽을 최대 1개밖에 깰 수 없다.
    목수가 출발점에서 도착점까지 이동하기 위한 최단거리를 출력하는 프로그램을 작성하시오.
    물론, 벽은 최대 1개까지 깰 수 있다.
    아래 예제의 경우 ‘X’ 로 표시된 벽을 깰 경우 거리 18만에 출발점에서 도착점으로 이동할 수 있다.
    [escape_maze_of_carpenter.png]

    [입력]
    첫째 줄에 지도의 세로 길이 N과 지도의 가로 길이 M이 주어진다. (1 <= N, M <= 1,000)
    둘째 줄부터 지도의 정보가 주어진다.
    0은 이동할 수 있는 부분, 1은 이동할 수 없는 부분을 나타낸다.

    [출력]
    목수가 (N - 1, 0) 에서 출발하여 (0, M - 1) 까지 이동하는 데 필요한 최단거리를 출력한다.
    목수는 미로를 항상 탈출할 수 있다고 가정한다.

    [예제 입력]
    10 10
    0 0 0 0 0 0 1 1 0 0
    0 1 1 1 0 0 1 0 1 0
    0 1 1 1 0 0 1 0 1 0
    0 0 0 0 0 0 0 0 1 0
    0 0 1 1 1 1 0 0 1 0
    0 0 0 0 0 0 1 1 0 0
    0 0 1 1 1 0 1 1 0 0
    0 0 1 1 1 0 0 0 0 0
    0 0 0 0 0 1 1 1 0 0
    0 0 0 0 0 0 0 1 0 0
    [예제 출력]
    18

     */

    private static final Scanner SC = new Scanner(System.in);
    private static final int WALL = 1;
    private static final int EMPTY = 0;
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int ROW = 0;
    private static final int COL = 1;

    public static void main(String[] args) {
        final int n = SC.nextInt();
        final int m = SC.nextInt();

        final int[][] maze = new int[n][m];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                maze[row][col] = SC.nextInt();
            }
        }

        SC.close();

        final int[] firstStart = {n - 1, 0};
        final int[][] firstHistory = new int[n][m];
        escape(maze, firstStart, firstHistory, n, m);

        final int[] secondStart = {0, m - 1};
        final int[][] secondHistory = new int[n][m];
        escape(maze, secondStart, secondHistory, n, m);

        int minValue = (int) 1e9;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (maze[row][col] == WALL && firstHistory[row][col] > 0 && secondHistory[row][col] > 0) {
                    final int historySum = firstHistory[row][col] + secondHistory[row][col];
                    minValue = Math.min(minValue, historySum);
                }
            }
        }

        System.out.println(minValue);
    }

    private static void escape(final int[][] maze, final int[] start, final int[][] history, final int n, final int m) {
        final int startRow = start[0];
        final int startCol = start[1];

        final Queue<Zone> move = new LinkedList<>();
        move.add(new Zone(startRow, startCol, 0));

        final boolean[][] visited = new boolean[n][m];
        visited[startRow][startCol] = true;

        while (!move.isEmpty()) {
            final Zone current = move.poll();

            final int curRow = current.getRow();
            final int curCol = current.getCol();
            final int curStep = current.getStep();

            for (int[] dir : DIRECTIONS) {
                final int nextRow = curRow + dir[ROW];
                final int nextCol = curCol + dir[COL];

                if ((0 > nextRow) || (nextRow >= n) || (0 > nextCol) || (nextCol >= m)) {
                    continue;
                }

                if (visited[nextRow][nextCol]) {
                    continue;
                }

                visited[nextRow][nextCol] = true;
                history[nextRow][nextCol] = curStep + 1;

                if (maze[nextRow][nextCol] == EMPTY) {
                    move.add(new Zone(nextRow, nextCol, curStep + 1));
                }
            }
        }
    }
}

class Zone {
    private final int row;
    private final int col;
    private final int step;

    public Zone(int row, int col, int step) {
        this.row = row;
        this.col = col;
        this.step = step;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getStep() {
        return step;
    }
}
