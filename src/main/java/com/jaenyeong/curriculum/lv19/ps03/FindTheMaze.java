package com.jaenyeong.curriculum.lv19.ps03;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;

public class FindTheMaze {
    /*
    미로 찾기

    [문제]
    아래와 같이 이동할 수 있는 길, 그리고 이동할 수 없는 벽으로 이루어진 크기 N x M 의 지도가 주어진다.
    이 때, (N-1, 0) 에서 출발하여 (0, M-1) 까지 도착하는 최단거리를 출력하는 프로그램을 작성하시오.
    아래 그림에 대하여 S에서 E까지 가는 최단거리는 22이다.
    [find_the_maze.png]

    [입력]
    첫째 줄에 지도의 세로 길이 N과 지도의 가로 길이 M이 주어진다. ( 1 ≤ N, M ≤ 1,000 )
    둘째 줄부터 지도의 정보가 주어진다.
    0은 이동할 수 있는 부분, 1은 이동할 수 없는 부분을 나타낸다.

    [출력]
    (N - 1, 0) 에서 출발하여 (0, M-1) 까지 이동하는 데 필요한 최단거리를 출력한다.

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
    22

     */

    private static final Scanner SC = new Scanner(System.in);
    private static final int[][] DIRECTION = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int ROW = 0;
    private static final int COL = 1;
    private static final int WALL = 1;

    public static void main(String[] args) {
        final int n = SC.nextInt();
        final int m = SC.nextInt();

        final int[][] maze = new int[n][m];
        final boolean[][] visited = new boolean[n][m];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                maze[row][col] = SC.nextInt();
            }
        }

        SC.close();

        final Step start = new Step(n - 1, 0, 0);
        final Step dest = new Step(0, m - 1, 0);

        final Queue<Step> steps = new LinkedList<>();
        steps.add(start);
        visited[start.getRow()][start.getCol()] = true;

        while (!steps.isEmpty()) {
            final Step current = steps.poll();

            // 도착한 경우
            if (dest.equals(current)) {
                System.out.println(current.getStep());
                return;
            }

            for (int[] dir : DIRECTION) {
                final int nextRow = current.getRow() + dir[ROW];
                final int nextCol = current.getCol() + dir[COL];

                if ((0 > nextRow) || (nextRow >= n) || (0 > nextCol) || (nextCol >= m)) {
                    continue;
                }

                if (maze[nextRow][nextCol] == WALL || visited[nextRow][nextCol]) {
                    continue;
                }

                visited[nextRow][nextCol] = true;
                steps.add(new Step(nextRow, nextCol, current.getStep() + 1));
            }
        }
    }
}

class Step {
    private final int row;
    private final int col;
    private final int step;

    public Step(int row, int col, int step) {
        this.row = row;
        this.col = col;
        this.step = step;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return getRow() == step.getRow() && getCol() == step.getCol();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRow(), getCol());
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
