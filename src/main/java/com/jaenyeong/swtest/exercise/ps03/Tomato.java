package com.jaenyeong.swtest.exercise.ps03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Tomato {
    /*
    TOMATO

    [문제]
    철수의 토마토 농장에서는 토마토를 보관하는 큰 창고를 가지고 있다.
    토마토는 아래의 그림과 같이 격자모양 상자의 칸에 하나씩 넣은 다음, 상자들을 수직으로 쌓아 올려서 창고에 보관한다.
    [tomato.png]
    창고에 보관되는 토마토들 중에는 잘 익은 것도 있지만,아직 익지 않은 토마토들도 있을 수 있다.
    보관 후 하루가 지나면, 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 된다.
    하나의 토마토에 인접한 곳은 위, 아래, 왼쪽, 오른쪽, 앞, 뒤 여섯 방향에 있는 토마토를 의미한다.
    대각선 방향에 있는 토마토들에게는 영향을 주지 못하며, 토마토가 혼자 저절로 익는 경우는 없다고 가정한다.
    철수는 창고에 보관된 토마토들이 며칠이 지나면 다 익게 되는지 그 최소 일수를 알고 싶어 한다.
    토마토를 창고에 보관하는 격자모양의 상자들의 크기와 익은 토마토들과 익지 않은 토마토들의 정보가 주어졌을 때,
    며칠이 지나면 토마토들이 모두 익는지,
    그 최소 일수를 구하는 프로그램을 작성하라.
    단, 상자의 일부 칸에는 토마토가 들어있지 않을 수도 있다.

    [입력]
    첫 줄에는 상자의 크기를 나타내는 두 정수 M,N과 쌓아올려지는 상자의 수를 나타내는 H 가 주어진다.
    M은 상자의 가로 칸의 수, N은 상자의 세로 칸의 수를 나타낸다.
    단, 2 <= M <= 100, 2 <= N <= 100, 1 <= H <= 100 이다.
    둘째 줄부터는 가장 밑의 상자부터 가장 위의 상자까지에 저장된 토마토들의 정보가 주어진다.
    즉, 둘째 줄부터 N개의 줄에는 하나의 상자에 담긴 토마토의 정보가 주어진다.
    각 줄에는 상자 가로줄에 들어있는 토마토들의 상태가 M개의 정수로 주어진다.
    정수 1은 익은 토마토, 정수 0 은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸을 나타낸다.
    이러한 N개의 줄이 H 번 반복하여 주어진다.

    [출력]
    여러분은 토마토가 모두 익을 때까지 최소 며칠이 걸리는지를 계산해서 출력해야 한다.
    만약, 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력해야 하고, 토마토가 모두 익지는 못하는 상황이면 -1 을 출력해야 한다.

    [예제 입력]
    5 3 1
    0 -1 0 0 0
    -1 -1 0 1 1
    0 0 0 1 1
    [예제 출력]
    -1

    [예제 입력]
    5 3 2
    0 0 0 0 0
    0 0 0 0 0
    0 0 0 0 0
    0 0 0 0 0
    0 0 1 0 0
    0 0 0 0 0
    [예제 출력]
    4

    [예제 입력]
    4 3 2
    1 1 1 1
    1 1 1 1
    1 1 1 1
    1 1 1 1
    -1 -1 -1 -1
    1 1 1 -1
    [예제 출력]
    0

     */

    private static final Scanner SC = new Scanner(System.in);
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int UP = 1;
    private static final int DOWN = -1;
    private static final int EMPTY = -1;
    private static final int UNRIPE_TOMATO = 0;
    private static final int RIPE_TOMATO = 1;

    public static void main(String[] args) {
        final int colSize = SC.nextInt();
        final int rowSize = SC.nextInt();
        final int heightSize = SC.nextInt();

        final int[][][] tomatoes = new int[heightSize][rowSize][colSize];
        final boolean[][][] isRipesTomatoes = new boolean[heightSize][rowSize][colSize];

        for (int height = 0; height < heightSize; height++) {
            for (int row = 0; row < rowSize; row++) {
                for (int col = 0; col < colSize; col++) {
                    tomatoes[height][row][col] = SC.nextInt();
                }
            }
        }

        SC.close();

        final Queue<TomatoDto> ripeTomatoes = new LinkedList<>();

        for (int height = 0; height < heightSize; height++) {
            for (int row = 0; row < rowSize; row++) {
                for (int col = 0; col < colSize; col++) {
                    if (tomatoes[height][row][col] == RIPE_TOMATO) {
                        ripeTomatoes.add(new TomatoDto(height, row, col, 0));
                        isRipesTomatoes[height][row][col] = true;
                    }
                }
            }
        }

        int ripeDay = 0;

        while (!ripeTomatoes.isEmpty()) {
            final TomatoDto curTomato = ripeTomatoes.poll();
            final int curHeight = curTomato.getHeight();
            final int curRow = curTomato.getRow();
            final int curCol = curTomato.getCol();
            final int curTomatoDay = curTomato.getDay();
            ripeDay = Math.max(curTomatoDay, ripeDay);

            // 같은 칸 내에서 4방향으로 이동
            for (int[] dir : DIRECTIONS) {
                final int nextRow = curTomato.getRow() + dir[0];
                final int nextCol = curTomato.getCol() + dir[1];

                if (invalidRange(rowSize, colSize, nextRow, nextCol)) {
                    continue;
                }

                if (isRipesTomatoes[curHeight][nextRow][nextCol]
                    || tomatoes[curHeight][nextRow][nextCol] == EMPTY
                    || tomatoes[curHeight][nextRow][nextCol] == RIPE_TOMATO) {
                    continue;
                }

                ripeTomatoes.offer(new TomatoDto(curHeight, nextRow, nextCol, curTomatoDay + 1));
                isRipesTomatoes[curHeight][nextRow][nextCol] = true;
                tomatoes[curHeight][nextRow][nextCol] = RIPE_TOMATO;
            }

            // 위 아래
            final int bottomHeight = curHeight + DOWN;

            if (bottomHeight >= 0) {
                if (!isRipesTomatoes[bottomHeight][curRow][curCol]
                    && tomatoes[bottomHeight][curRow][curCol] == UNRIPE_TOMATO) {
                    ripeTomatoes.offer(new TomatoDto(bottomHeight, curRow, curCol, curTomatoDay + 1));
                    isRipesTomatoes[bottomHeight][curRow][curCol] = true;
                    tomatoes[bottomHeight][curRow][curCol] = RIPE_TOMATO;
                }
            }

            final int upHeight = curHeight + UP;

            if (upHeight < heightSize) {
                if (!isRipesTomatoes[upHeight][curRow][curCol]
                    && tomatoes[upHeight][curRow][curCol] == UNRIPE_TOMATO) {
                    ripeTomatoes.offer(new TomatoDto(upHeight, curRow, curCol, curTomatoDay + 1));
                    isRipesTomatoes[upHeight][curRow][curCol] = true;
                    tomatoes[upHeight][curRow][curCol] = RIPE_TOMATO;
                }
            }
        }

        // 모두 익지 않았다면 -1 출력
        for (int height = 0; height < heightSize; height++) {
            for (int row = 0; row < rowSize; row++) {
                for (int col = 0; col < colSize; col++) {
                    if (tomatoes[height][row][col] == UNRIPE_TOMATO) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        // 모두 익었다면 ripeDay 출력
        System.out.println(ripeDay);
    }

    private static boolean invalidRange(final int rowSize, final int colSize, final int nextRow, final int nextCol) {
        return (0 > nextRow) || (nextRow >= rowSize) || (0 > nextCol) || (nextCol >= colSize);
    }
}

class TomatoDto {
    private final int height;
    private final int row;
    private final int col;
    private final int day;

    TomatoDto(final int height, final int row, final int col, final int day) {
        this.height = height;
        this.row = row;
        this.col = col;
        this.day = day;
    }

    int getHeight() {
        return height;
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }

    int getDay() {
        return day;
    }
}
