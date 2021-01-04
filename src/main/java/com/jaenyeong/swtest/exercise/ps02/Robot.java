package com.jaenyeong.swtest.exercise.ps02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Robot {
    /*
    ROBOT

    [문제]
    많은 공장에서 로봇이 이용되고 있다.
    우리 월드 공장의 로봇은 바라보는 방향으로 궤도를 따라 움직이며, 움직이는 방향은 동, 서, 남, 북 가운데 하나이다.
    로봇의 이동을 제어하는 명령어는 다음과 같이 두 가지이다.
    명령 1. Go k
    - k는 1, 2 또는 3일 수 있다. 현재 향하고 있는 방향으로 k칸 만큼 움직인다.
    명령 2. Turn dir
    - dir은 left 또는 right 이며, 각각 왼쪽 또는 오른쪽으로 90° 회전한다.
    공장 내 궤도가 설치되어 있는 상태가 아래와 같이 0과 1로 이루어진 직사각형 모양으로 로봇에게 입력된다.
    0은 궤도가 깔려 있어 로봇이 갈 수 있는 지점이고, 1은 궤도가 없어 로봇이 갈 수 없는 지점이다.
    로봇이 (4, 2) 지점에서 남쪽을 향하고 있을 때,
    이 로봇을 (2, 4) 지점에서 동쪽으로 향하도록 이동시키는 것은 아래와 같이 9번의 명령으로 가능하다.
    [robot.png]

    [입력]
    첫째 줄에 공장 내 궤도 설치 상태를 나타내는 직사각형의 세로 길이 M과 가로 길이 N이 빈칸을 사이에 두고 주어진다.
    이때 M과 N은 둘 다 100이하의 자연수이다.
    이어 M줄에 걸쳐 한 줄에 N개씩 각 지점의 궤도 설치 상태를 나타내는 숫자 0 또는 1이 빈칸을 사이에 두고 주어진다.
    다음 줄에는 로봇의 출발 지점의 위치 (행과 열의 번호)와 바라보는 방향이 빈칸을 사이에 두고 주어진다.
    마지막 줄에는 로봇의 도착 지점의 위치 (행과 열의 번호)와 바라보는 방향이 빈칸을 사이에 두고 주어진다.
    방향은 동쪽이 1, 서쪽이 2, 남쪽이 3, 북쪽이 4로 주어진다.
    출발지점에서 도착지점까지는 항상 이동이 가능하다.

    [출력]
    첫째 줄에 로봇을 도착 지점에 원하는 방향으로 이동시키는데 필요한 최소 명령 횟수를 출력한다.

    [예제 입력]
    5 6
    0 0 0 0 0 0
    0 1 1 0 1 0
    0 1 0 0 0 0
    0 0 1 1 1 0
    1 0 0 0 0 0
    4 2 3
    2 4 1
    [예제 출력]
    9

     */

    private static final Scanner SC = new Scanner(System.in);
    // 상, 우, 하, 좌
    private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static final int WALL = 1;

    public static void main(String[] args) {
        final int rowSize = SC.nextInt();
        final int colSize = SC.nextInt();

        final int[][] grid = new int[rowSize][colSize];

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                grid[row][col] = SC.nextInt();
            }
        }

        final RobotZone start = new RobotZone(SC.nextInt() - 1, SC.nextInt() - 1, getDir(SC.nextInt()), 0);
        final int destRow = SC.nextInt() - 1;
        final int destCol = SC.nextInt() - 1;
        final int destDir = getDir(SC.nextInt());

        SC.close();

        final Queue<RobotZone> movePlans = new LinkedList<>();
        movePlans.offer(start);

        final boolean[][][] visited = new boolean[rowSize][colSize][DIRECTIONS.length];
        visited[start.getRow()][start.getCol()][start.getDir()] = true;

        while (!movePlans.isEmpty()) {
            final RobotZone curRobot = movePlans.poll();

            // 해당 위치 좌표와 방향이 일치하는 경우
            if ((curRobot.getRow() == destRow) && (curRobot.getCol() == destCol) && (curRobot.getDir() == destDir)) {
                System.out.println(curRobot.getStep());
                return;
            }

            // 방향 회전
            turn(movePlans, visited, curRobot, rowSize, colSize);

            // 이동
            move(rowSize, colSize, grid, movePlans, visited, curRobot);
        }
    }

    private static int getDir(final int inputDir) {
        if (inputDir == 1) {
            return 1;
        }

        if (inputDir == 2) {
            return 3;
        }

        if (inputDir == 3) {
            return 2;
        }

        return 0;
    }

    private static boolean validateRange(final int rowSize, final int colSize, final int nextRow, final int nextCol) {
        return (0 > nextRow) || (nextRow >= rowSize) || (0 > nextCol) || (nextCol >= colSize);
    }

    private static void turn(final Queue<RobotZone> movePlans, final boolean[][][] visited,
                             final RobotZone curRobot, final int rowSize, final int colSize) {


        final int curDir = curRobot.getDir();

        final int leftTurnDir = (curDir - 1 < 0) ? 3 : (curDir - 1);
        turnOfferToMovePlan(movePlans, visited, curRobot, leftTurnDir);

        final int rightTurnDir = (curDir + 1 > 3) ? 0 : (curDir + 1);
        turnOfferToMovePlan(movePlans, visited, curRobot, rightTurnDir);
    }

    private static void turnOfferToMovePlan(final Queue<RobotZone> movePlans, final boolean[][][] visited,
                                            final RobotZone curRobot, final int nextTurn) {
        final int curRow = curRobot.getRow();
        final int curCol = curRobot.getCol();

        if (!visited[curRow][curCol][nextTurn]) {
            visited[curRobot.getRow()][curRobot.getCol()][nextTurn] = true;

            movePlans.offer(new RobotZone(curRow, curCol, nextTurn, curRobot.getStep() + 1));
        }
    }

    private static void move(final int rowSize, final int colSize, final int[][] grid, final Queue<RobotZone> movePlans,
                             final boolean[][][] visited, final RobotZone curRobot) {

        final int nextStep = curRobot.getStep() + 1;
        int curRow = curRobot.getRow();
        int curCol = curRobot.getCol();

        for (int i = 1; i <= 3; i++) {
            final int curDir = curRobot.getDir();

            final int nextRow = curRow + DIRECTIONS[curDir][0];
            final int nextCol = curCol + DIRECTIONS[curDir][1];

            if (validateRange(rowSize, colSize, nextRow, nextCol)) {
                continue;
            }

            if (visited[nextRow][nextCol][curDir] || grid[nextRow][nextCol] == WALL) {
                break;
            }

            visited[nextRow][nextCol][curDir] = true;
            movePlans.offer(new RobotZone(nextRow, nextCol, curDir, nextStep));

            curRow = nextRow;
            curCol = nextCol;
        }
    }
}

class RobotZone {
    private final int row;
    private final int col;
    private final int dir;
    private final int step;

    RobotZone(final int row, final int col, final int dir, final int step) {
        this.row = row;
        this.col = col;
        this.dir = dir;
        this.step = step;
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }

    int getDir() {
        return dir;
    }

    int getStep() {
        return step;
    }
}
