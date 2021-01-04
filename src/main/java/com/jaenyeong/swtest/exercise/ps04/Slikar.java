package com.jaenyeong.swtest.exercise.ps04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Slikar {
    /*
    SLIKAR

    [문제]
    사악한 암흑의 군주 임정택은 드디어 원하던 마법 구슬을 손에 넣었고, 알랩숲에 홍수를 일으켰다!
    이 숲에 살고 있는 고슴도치는 지금 당장 비버의 굴로 가능한 빨리 돌아가 홍수를 피하려고 한다.
    알랩숲의 지도는 R행 C열로 구성되어 있다. 비어있는 곳은 '.'로 나타나 있고, 물이 차있는 지역은 '*', 돌은 'X'로 표현되어 있다.
    추가적으로, 비버의 굴은 'D'로, 고슴도치의 위치는 'S'로 표시되어 있다.
    매 분마다 고슴도치는 현재 있는 칸과 인접한 네 칸 중 하나로 이동할 수 있다. (위, 아래, 오른쪽, 왼쪽)
    물은 매 분마다 물이 있는 칸과 적어도 한 변을 공유하는 비어있는 모든 칸에 물이 차게 된다.
    물과 고슴도치는 돌을 통과할 수 없다.
    또, 고슴도치는 물로 차있는 구역으로 이동할 수 없고, 물은 비버의 소굴로 이동할 수 없다.
    알랩숲의 지도가 주어졌을 때, 고슴도치가 안전하게 비버의 굴로 도달하기 위해 필요한 최소 시간을 구하는 프로그램을 작성하시오.
    고슴도치는 물이 찰 예정인 칸으로 이동할 수 없음에 주의한다.

    [입력]
    첫째 줄에 50보다 작거나 같은 자연수 R과 C가 주어진다.
    다음 R개 줄에 걸쳐 C개의 문자가 주어진다.('.', '*', 'X', 'D' or 'S') 'D'와 'S'는 하나씩만 주어진다.

    [출력]
    첫째 줄에 고슴도치가 비버의 굴로 도달할 수 있는 가장 빠른 시간을 출력한다.
    만약, 안전하게 비버의 굴로 이동할 수 없을 경우, "KAKTUS"를 출력한다.

    [예제 입력]
    3 3
    D.*
    ...
    .S.
    [예제 출력]
    3

    [예제 입력]
    3 3
    D.*
    ...
    ..S
    [예제 출력]
    KAKTUS

    [예제 입력]
    3 6
    D...*.
    .X.X..
    ....S.
    [예제 출력]
    6

    [예제 입력]
    5 4
    .D.*
    ....
    ..X.
    S.*.
    ....
    [예제 출력]
    4

     */

    private static final Scanner SC = new Scanner(System.in);
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final char DESTINATION = 'D';
    private static final char START = 'S';
    private static final char WATER = '*';
    private static final char STONE = 'X';

    public static void main(String[] args) {
        final int rowSize = SC.nextInt();
        final int colSize = SC.nextInt();
        SC.nextLine();

        final char[][] map = new char[rowSize][colSize];
        final boolean[][] visited = new boolean[rowSize][colSize];
        final Queue<Flood> floods = new LinkedList<>();
        final Queue<Hedgehog> movesOfHedgehog = new LinkedList<>();

        final int[] destination = new int[2];

        for (int row = 0; row < rowSize; row++) {
            final char[] inputChars = SC.nextLine().toCharArray();
            for (int col = 0; col < inputChars.length; col++) {
                map[row][col] = inputChars[col];
                if (map[row][col] == WATER) {
                    floods.add(new Flood(row, col, 0));
                    continue;
                }

                if (map[row][col] == DESTINATION) {
                    destination[0] = row;
                    destination[1] = col;
                    continue;
                }

                if (map[row][col] == START) {
                    visited[row][col] = true;
                    movesOfHedgehog.add(new Hedgehog(row, col, 0));
                }
            }
        }

        SC.close();

        while (!movesOfHedgehog.isEmpty()) {
            final int floodsSize = floods.size();
            for (int i = 0; i < floodsSize; i++) {
                final Flood curFlood = floods.poll();
                assert curFlood != null;
                final int nextStep = curFlood.getStep() + 1;

                for (int[] dir : DIRECTIONS) {
                    final int nextRow = curFlood.getRow() + dir[0];
                    final int nextCol = curFlood.getCol() + dir[1];

                    if ((0 > nextRow) || (nextRow >= rowSize) || (0 > nextCol) || (nextCol >= colSize)) {
                        continue;
                    }

                    final char nextPosition = map[nextRow][nextCol];
                    if (nextPosition == DESTINATION || nextPosition == STONE || nextPosition == WATER) {
                        continue;
                    }

                    map[nextRow][nextCol] = WATER;
                    floods.offer(new Flood(nextRow, nextCol, nextStep));
                }
            }

            final int hedgehogSize = movesOfHedgehog.size();
            for (int i = 0; i < hedgehogSize; i++) {
                final Hedgehog curHed = movesOfHedgehog.poll();
                assert curHed != null;
                final int nextStep = curHed.getStep() + 1;

                for (int[] dir : DIRECTIONS) {
                    final int nextRow = curHed.getRow() + dir[0];
                    final int nextCol = curHed.getCol() + dir[1];

                    if ((0 > nextRow) || (nextRow >= rowSize) || (0 > nextCol) || (nextCol >= colSize)) {
                        continue;
                    }

                    final char nextPosition = map[nextRow][nextCol];
                    if (nextPosition == DESTINATION) {
                        System.out.println(nextStep);
                        return;
                    }

                    if (nextPosition == STONE || nextPosition == WATER || visited[nextRow][nextCol]) {
                        continue;
                    }

                    visited[nextRow][nextCol] = true;
                    movesOfHedgehog.offer(new Hedgehog(nextRow, nextCol, nextStep));
                }
            }
        }

        System.out.println("KAKTUS");
    }
}

class Flood {
    private final int row;
    private final int col;
    private final int step;

    Flood(final int row, final int col, final int step) {
        this.row = row;
        this.col = col;
        this.step = step;
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }

    int getStep() {
        return step;
    }
}

class Hedgehog {
    private final int row;
    private final int col;
    private final int step;

    Hedgehog(final int row, final int col, final int step) {
        this.row = row;
        this.col = col;
        this.step = step;
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }

    int getStep() {
        return step;
    }
}
