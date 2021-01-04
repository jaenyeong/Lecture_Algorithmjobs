package com.jaenyeong.swtest.exercise.ps01;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Cheese {
    /*
    CHEESE

    [문제]
    아래 <cheese1.png>과 같이 정사각형 칸들로 이루어진 사각형 모양의 판이 있고, 그 위에 얇은 치즈(회색 으로 표시된 부분)가 놓여 있다.
    판의 가장자리(<cheese1.png>에서 네모칸에 엑스친 부분)에는 치즈가 놓여 있지 않으며 치즈에는 하나 이상의 구멍이 있을 수 있다.
    이 치즈를 공기 중에 놓으면 녹게 되는데 공기와 접촉된 칸은 한 시간이 지나면 녹아 없어진다.
    치즈의 구멍 속에는 공기가 없지만 구멍을 둘러싼 치즈가 녹아서 구멍이 열리면 구멍 속으로 공기가 들어 가게 된다.
    <cheese1.png>의 경우, 치즈의 구멍을 둘러싼 치즈는 녹지 않고 ‘c’로 표시된 부분만 한 시간 후 에 녹아 없어져서 <cheese2.png>와 같이 된다.
    [cheese1.png]
    다시 한 시간 후에는 <cheese2.png-그림2>에서 ‘c’로 표시된 부분이 녹아 없어져서 <cheese2.png-그림3>과 같이 된다.
    [cheese2.png]
    <cheese2.png-그림3>은 원래 치즈의 두 시간 후 모양을 나타내고 있으며, 남은 조각들은 한 시간이 더 지나면 모두 녹아 없어진다.
    그러므로 처음 치즈가 모두 녹아 없어지는 데는 세 시간이 걸린다.
    <cheese2.png-그림3>과 같이 치즈가 녹는 과정에서 여러 조각으로 나누어 질 수도 있다.
    입력으로 사각형 모양의 판의 크기와 한 조각의 치즈가 판 위에 주어졌을 때,
    공기 중에서 치즈가 모두 녹아 없어지는 데 걸리는 시간과 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수를 구하는 프로그램을 작성하시오.

    [입력]
    첫째 줄에는 사각형 모양 판의 세로와 가로의 길이가 양의 정수로 주어진다.
    세로와 가로의 길이는 최대 100이다.
    판의 각 가로줄의 모양이 윗 줄부터 차례로 둘째 줄부터 마지막 줄까지 주어진다.
    치즈가 없는 칸은 0, 치즈가 있는 칸은 1로 주어 지며 각 숫자 사이에는 빈칸이 하나씩 있다.

    [출력]
    첫째 줄에는 치즈가 모두 녹아서 없어지는 데 걸리는 시간을 출력하고,
    둘째 줄에는 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수를 출력한다.

    [예제 입력]
    13 12
    0 0 0 0 0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 1 1 0 0 0
    0 1 1 1 0 0 0 1 1 0 0 0
    0 1 1 1 1 1 1 0 0 0 0 0
    0 1 1 1 1 1 0 1 1 0 0 0
    0 1 1 1 1 0 0 1 1 0 0 0
    0 0 1 1 0 0 0 1 1 0 0 0
    0 0 1 1 1 1 1 1 1 0 0 0
    0 0 1 1 1 1 1 1 1 0 0 0
    0 0 1 1 1 1 1 1 1 0 0 0
    0 0 1 1 1 1 1 1 1 0 0 0
    0 0 0 0 0 0 0 0 0 0 0 0
    [예제 출력]
    3
    5

     */

    private static final Scanner SC = new Scanner(System.in);
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int EMPTY = 0;
    private static final int CHEESE = 1;

    public static void main(String[] args) {
        final int rowSize = SC.nextInt();
        final int colSize = SC.nextInt();

        final int[][] cheeseGrid = new int[rowSize][colSize];

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                cheeseGrid[row][col] = SC.nextInt();
            }
        }

        SC.close();

        int cheeseCount = 0;
        for (int row = 1; row < (rowSize - 1); row++) {
            for (int col = 1; col < (colSize - 1); col++) {
                if (cheeseGrid[row][col] == CHEESE) {
                    cheeseCount++;
                }
            }
        }

        int meltTime = 0;

        while (cheeseCount > 0) {
            final int melt = meltCheese(cheeseGrid, rowSize, colSize);
            meltTime++;

            if (cheeseCount - melt != 0) {
                cheeseCount -= melt;
                continue;
            }

            System.out.println(meltTime);
            System.out.println(cheeseCount);
            return;
        }
    }

    private static int meltCheese(final int[][] grid, final int rowSize, final int colSize) {
        final boolean[][] visited = new boolean[rowSize][colSize];

        final Queue<Zone> airZoneList = new LinkedList<>();
        airZoneList.offer(new Zone(0, 0));
        int meltCount = 0;

        while (!airZoneList.isEmpty()) {
            final Zone current = airZoneList.poll();
            final int curRow = current.getRow();
            final int curCol = current.getCol();

            for (int[] dir : DIRECTIONS) {
                final int nextRow = curRow + dir[0];
                final int nextCol = curCol + dir[1];

                if ((0 > nextRow) || (nextRow >= rowSize) || (0 > nextCol) || (nextCol >= colSize)) {
                    continue;
                }

                if (grid[nextRow][nextCol] == CHEESE && !visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    grid[nextRow][nextCol] = EMPTY;
                    meltCount++;
                    continue;
                }

                if (grid[nextRow][nextCol] == EMPTY && !visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    airZoneList.offer(new Zone(nextRow, nextCol));
                }
            }
        }

        return meltCount;
    }
}

class Zone {
    private final int row;
    private final int col;

    Zone(final int row, final int col) {
        this.row = row;
        this.col = col;
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }
}
