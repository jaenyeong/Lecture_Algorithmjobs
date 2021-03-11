package com.jaenyeong.previousproblem.lv05.step03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Move2 {
    /*
    이동 알고리즘2 (easy)

    [문제]
    세로 길이 N, 가로 길이 M인 2차원 배열에 k명의 사람이 있다.
    i번 사람은 1초에 di 방향으로 fi 만큼 이동하고,
    만약 이동하려는 위치가 배열의 범위 밖이라면 배열을 나가기 직전의 위치에서 멈춘다.
    t초 후 각 사람의 위치를 1번 사람부터 출력하여라.
    이 배열의 왼쪽 위 좌표는 (1, 1)이다.
    xi는 i번 사람의 가로 방향 위치, yi는 i번 사람의 세로 방향 위치를 의미한다.
    di는 0, 1, 2, 3 중 하나로 주어지는데, 순서대로 상하좌우를 의미한다.
    [move_2.png]

    [입력]
    첫 줄에 N, M, k, t가 주어진다.
    두 번째 줄부터 k줄에 걸쳐 yi, xi, di, fi가 주어진다.
    (3 <= N, M <= 100, 1 <= t, k <= 100, 1 <= yi <= N, 1 <= xi <= M, 0 <= di <= 3, 1 <= fi <= 100)

    [출력]
    k줄에 걸쳐 각 사람의 t초 후 위치를 y, x 순서로 출력한다.

    [예제 입력]
    4 5 3 2
    1 1 3 1
    2 2 1 2
    3 5 2 3
    [예제 출력]
    1 3
    4 2
    3 1

    [예제 입력]
    9 11 3 4
    4 2 2 20
    5 3 1 15
    8 5 3 2
    [예제 출력]
    4 1
    9 3
    8 11

    [예제 입력]
    25 31 3 2
    4 2 2 20
    5 3 1 15
    8 5 3 2
    [예제 출력]
    4 1
    25 3
    8 9

    [예제 입력]
    25 31 3 6
    4 2 2 20
    5 3 1 15
    8 5 3 2
    [예제 출력]
    4 1
    25 3
    8 17

     */

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        final int rowSize = SC.nextInt();
        final int colSize = SC.nextInt();

        final int numberOfPeople = SC.nextInt();
        final int times = SC.nextInt();

        final List<Man> mans = new ArrayList<>();

        for (int i = 0; i < numberOfPeople; i++) {
            final int row = SC.nextInt();
            final int col = SC.nextInt();

            final int dir = SC.nextInt();
            final int distance = SC.nextInt();
            mans.add(new Man(row, col, dir, distance));
        }

        SC.close();

        for (Man man : mans) {
            man.move(times, rowSize, colSize);
        }

        for (Man man : mans) {
            System.out.println(man.getRow() + " " + man.getCol());
        }
    }
}

class Man {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int row;
    private int col;
    private final int dir;
    private final int distance;

    Man(final int row, final int col, final int dir, final int distance) {
        this.row = row;
        this.col = col;
        this.dir = dir;
        this.distance = distance;
    }

    public void move(final int times, final int rowSize, final int colSize) {
        final int[] direction = DIRECTIONS[dir];

        for (int time = 0; time < times; time++) {
            for (int dist = 0; dist < distance; dist++) {

                final int nextRow = row + direction[0];
                final int nextCol = col + direction[1];

                if (1 > nextRow || nextRow > rowSize || 1 > nextCol || nextCol > colSize) {
                    return;
                }

                row = nextRow;
                col = nextCol;
            }
        }
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }
}
