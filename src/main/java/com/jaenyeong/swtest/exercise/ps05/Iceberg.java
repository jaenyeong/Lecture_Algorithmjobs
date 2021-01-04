package com.jaenyeong.swtest.exercise.ps05;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Iceberg {
    /*
    ICEBERG

    [문제]
    지구 온난화로 인하여 북극의 빙산이 녹고 있다.
    빙산을 그림 1과 같이 2차원 배열에 표시한다고 하자.
    빙산의 각 부분별 높이 정보는 배열의 각 칸에 양의 정수로 저장된다.
    빙산 이외의 바다에 해당되는 칸에는 0이 저장된다.
    그림 1에서 빈칸은 모두 0으로 채워져 있다고 생각한다.
    [iceberg1.png]
    <iceberg1.png-그림1>. 행의 개수가 5이고 열의 개수가 7인 2차원 배열에 저장된 빙산의 높이 정보
    빙산의 높이는 바닷물에 많이 접해있는 부분에서 더 빨리 줄어들기 때문에,
    배열에서 빙산의 각 부분에 해당되는 칸에 있는 높이는 일년마다 그 칸에 동서남북 네 방향으로 붙어있는 0이 저장된 칸의 개수만큼 줄어든다.
    단, 각 칸에 저장된 높이는 0보다 더 줄어들지 않는다.
    바닷물은 호수처럼 빙산에 둘러싸여 있을 수도 있다.
    따라서 그림 1의 빙산은 일년후에 그림 2와 같이 변형된다.
    <iceberg1.png-그림3>은 그림 1의 빙산이 2년 후에 변한 모습을 보여준다.
    2차원 배열에서 동서남북 방향으로 붙어있는 칸들은 서로 연결되어 있다고 말한다.
    따라서 그림 2의 빙산은 한 덩어리이지만, 그림 3의 빙산은 세 덩어리로 분리되어 있다.
    [iceberg2.png]
    빙산의 정보가 주어질 때, 이 빙산이 두 덩어리 이상으로 분리되는 최초의 시간(년)을 구하는 프로그램을 작성하시오.
    그림 1의 빙산에 대해서는 2가 답이다.
    만일 전부 다 녹을 때까지 두 덩어리 이상으로 분리되지 않으면 프로그램은 0을 출력한다.

    [입력]
    첫 줄에는 이차원 배열의 행의 개수와 열의 개수를 나타내는 두 정수 N과 M이 한 개의 빈칸을 사이에 두고 주어진다.
    N과 M은 3 이상 300 이하이다.
    그 다음 N개의 줄에는 각 줄마다 배열의 각 행을 나타내는 M개의 정수가 한 개의 빈 칸을 사이에 두고 주어진다.
    각 칸에 들어가는 값은 0 이상 10 이하이다.
    배열에서 빙산이 차지하는 칸의 개수, 즉, 1 이상의 정수가 들어가는 칸의 개수는 10,000 개 이하이다.
    배열의 첫 번째 행과 열, 마지막 행과 열에는 항상 0으로 채워진다.

    [출력]
    첫 줄에 빙산이 분리되는 최초의 시간(년)을 출력한다.
    만일 빙산이 다 녹을 때까지 분리되지 않으면 0을 출력한다.

    [예제 입력]
    5 7
    0 0 0 0 0 0 0
    0 2 4 5 3 0 0
    0 3 0 2 5 2 0
    0 7 6 2 4 0 0
    0 0 0 0 0 0 0
    [예제 출력]
    2

     */

    private static final Scanner SC = new Scanner(System.in);
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int EMPTY = 0;

    public static void main(String[] args) {
        final int rowSize = SC.nextInt();
        final int colSize = SC.nextInt();

        final int[][] northPole = new int[rowSize][colSize];
        final Queue<IcebergDto> icebergs = new LinkedList<>();

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                final int input = SC.nextInt();
                northPole[row][col] = input;

                if (input != EMPTY) {
                    icebergs.offer(new IcebergDto(row, col, input));
                }
            }
        }

        SC.close();

        int time = 0;

        while (!icebergs.isEmpty()) {

            // 그룹 확인 DFS
            final int icebergGroup = checkIcebergGroup(rowSize, colSize, northPole);

            if (icebergGroup > 1) {
                System.out.println(time);
                return;
            }

            final int[][] copyNorthPole = new int[rowSize][colSize];
            for (int row = 0; row < rowSize; row++) {
                System.arraycopy(northPole[row], 0, copyNorthPole[row], 0, colSize);
            }

            final int icebergSize = icebergs.size();
            for (int i = 0; i < icebergSize; i++) {
                final IcebergDto curIceberg = icebergs.poll();

                assert curIceberg != null;
                final int curRow = curIceberg.getRow();
                final int curCol = curIceberg.getCol();

                int meltCount = EMPTY;

                for (int[] dir : DIRECTIONS) {
                    final int nextRow = curRow + dir[0];
                    final int nextCol = curCol + dir[1];

                    if ((0 > nextRow) || (nextRow >= rowSize) || (0 > nextCol) || (nextCol >= colSize)) {
                        continue;
                    }

                    final int nextPosition = northPole[nextRow][nextCol];
                    if (nextPosition != EMPTY) {
                        continue;
                    }

                    meltCount++;
                }

                final int newIcebergValue = Math.max(EMPTY, curIceberg.getValue() - meltCount);
                copyNorthPole[curRow][curCol] = newIcebergValue;

                if (newIcebergValue > EMPTY) {
                    icebergs.offer(new IcebergDto(curRow, curCol, newIcebergValue));
                }
            }

            time++;

            for (int row = 0; row < rowSize; row++) {
                System.arraycopy(copyNorthPole[row], 0, northPole[row], 0, colSize);
            }
        }

        System.out.println(EMPTY);
    }

    private static int checkIcebergGroup(final int rowSize, final int colSize, final int[][] northPole) {
        int icebergGroup = 0;
        final boolean[][] visited = new boolean[rowSize][colSize];

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (!visited[row][col] && northPole[row][col] > EMPTY) {
                    visited[row][col] = true;
                    icebergGroup++;
                    dfsIceberg(northPole, visited, rowSize, row, colSize, col);
                }
            }
        }

        return icebergGroup;
    }

    private static void dfsIceberg(final int[][] northPole, final boolean[][] visited,
                                   final int rowSize, final int row, final int colSize, final int col) {
        visited[row][col] = true;

        for (int[] dir : DIRECTIONS) {
            final int nextRow = row + dir[0];
            final int nextCol = col + dir[1];

            if ((0 > nextRow) || (nextRow >= rowSize) || (0 > nextCol) || (nextCol >= colSize)) {
                continue;
            }

            if (northPole[nextRow][nextCol] == EMPTY || visited[nextRow][nextCol]) {
                continue;
            }

            dfsIceberg(northPole, visited, rowSize, nextRow, colSize, nextCol);
        }
    }
}

class IcebergDto {
    private final int row;
    private final int col;
    private final int value;

    IcebergDto(final int row, final int col, final int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }

    int getValue() {
        return value;
    }
}
