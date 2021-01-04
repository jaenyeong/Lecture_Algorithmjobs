package com.jaenyeong.previousproblem.lv04.step07;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MoveAlgorithm {
    /*
    이동 알고리즘2

    [문제]
    세로 길이 N, 가로 길이 M 인 2 차원 배열에 k 명의 사람이 있다.
    i 번 사람은 1 초에 di 방향으로 fi 만큼 이동하고, 배열의 끝에 도착하면 다음 이동 시 뒤로 돌아서 반대 방향으로 이동한다.
    t 초 후 각 사람의 위치를 1 번 사람부터 출력하여라.
    이 배열의 왼쪽 위 좌표는 (1, 1) 이고, 오른쪽 아래 좌표는 (M, N) 이다.
    xi 는 i 번 사람의 가로 방향 위치, yi 는 i 번 사람의 세로 방향 위치를 의미한다.
    di 는 0, 1, 2, 3 중 하나로 주어지는데, 순서대로 상하좌우를 의미한다.
    [이동_알고리즘2.png]

    [입력]
    첫 줄에 N, M, k, t 가 주어진다.
    두 번째 줄 부터 k 줄에 걸쳐 yi, xi, di, fi 가 주어진다.
    (3 <= N, M <= 1,000,000,000, 1 <= t, k <= 100, 1 <= yi <= N, 1 <= xi <= M, 0 <= di <= 3, 1 <= fi <= 1,000,000,000)

    [출력]
    k 줄에 걸쳐 각 사람의 t 초 후 위치를 y, x 순서로 출력한다.

    [예제 입력]
    4 5 3 2
    1 1 3 1
    2 2 1 2
    3 5 2 3
    [예제 출력]
    1 3
    2 2
    3 3

    [예제 입력]
    34615 23432 3 435
    4531 23431 0 783
    4897 21541 1 3288
    34600 4897 3 23464
    [예제 출력]
    10066 23431
    18613 21541
    34600 4181

    [예제 입력]
    3461542 2318432 3 1000000
    457831 233431 0 78353
    4897 21541 1 3288
    34600 4897 3 23464
    [예제 출력]
    2899907 233431
    459055 21541
    34600 1483177

    [예제 입력]
    3461542 2318432 3 43345
    457831 233431 0 78353
    4897 21541 1 3288
    34600 4897 3 23464
    [예제 출력]
    3442776 233431
    2861467 21541
    34600 1579199

     */

    private static final Scanner SC = new Scanner(System.in);
    static final int UP = 0;
    static final int DOWN = 1;

    public static void main(String[] args) {
        final int rowSize = SC.nextInt();
        final int colSize = SC.nextInt();

        final int numberOfPeople = SC.nextInt();
        final int times = SC.nextInt();

        final List<Man> mans = new ArrayList<>();

        for (int man = 0; man < numberOfPeople; man++) {
            final int row = SC.nextInt();
            final int col = SC.nextInt();
            final int direction = SC.nextInt();
            long step = SC.nextLong() * times;

            step = (direction == UP || direction == DOWN) ? reduceStep(rowSize, step) : reduceStep(colSize, step);

            mans.add(new Man(rowSize, colSize, row, col, direction, step));
        }

        SC.close();

        for (Man man : mans) {
            man.move();
            man.print();
        }
    }

    private static long reduceStep(final long size, final long step) {
        return Math.toIntExact(step % ((size * 2) - 2));
    }
}

class Man {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int MIN_SIZE = 1;
    private static final int UP = 0;
    private static final int DOWN = 1;

    private final int rowSize;
    private final int colSize;
    private long row;
    private long col;
    private final int direction;
    private final long canStep;

    Man(final int rowSize, final int colSize, final int row, final int col, final int direction, final long canStep) {
        this.rowSize = rowSize;
        this.colSize = colSize;
        this.row = row;
        this.col = col;
        this.direction = direction;
        this.canStep = canStep;
    }

    void move() {
        if (direction == UP || direction == DOWN) {
            row = move(row, canStep * DIRECTIONS[direction][0], rowSize);
            return;
        }

        col = move(col, canStep * DIRECTIONS[direction][1], colSize);
    }

    private long move(long curPosition, long haveToMoveDist, final int maxSize) {
        while (true) {
            final long afterMovePosition = curPosition + haveToMoveDist;

            if (MIN_SIZE <= afterMovePosition && afterMovePosition <= maxSize) {
                return afterMovePosition;
            }

            if (MIN_SIZE > afterMovePosition) {
                haveToMoveDist += (curPosition - 1);
                curPosition = 1;
                haveToMoveDist = changePositiveOrNegative(haveToMoveDist);
                continue;
            }

            haveToMoveDist -= (maxSize - curPosition);
            curPosition = maxSize;
            haveToMoveDist = changePositiveOrNegative(haveToMoveDist);
        }
    }

    private long changePositiveOrNegative(final long givenNumber) {
        return givenNumber * -1;
    }

    public void print() {
        System.out.println(row + " " + col);
    }
}
