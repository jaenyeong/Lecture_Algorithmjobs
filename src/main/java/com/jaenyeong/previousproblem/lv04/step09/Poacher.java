package com.jaenyeong.previousproblem.lv04.step09;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Poacher {
    /*
    밀렵꾼

    [문제]
    밀렵꾼은 야간에 멧돼지 포획에 나섰다.
    밀렵꾼이 있는 장소를 R*C의 격자판으로 표현할 수 있다.
    각 격자칸에 멧돼지 정보가 주어지며, 밀렵꾼은 (R + 1, 0) 부터 (R + 1, C)까지 1초에 한 칸씩 움직인다.
    움직일 때마다 각 col 을 손전등으로 확인하고 눈 앞에 보이는 멧돼지 한 마리를 포획한다.
    [poacher_01.png]
    멧돼지의 정보는 총 5가지로 이루어져있다.
    멧돼지의 위치 y, x 와 멧돼지가 달리고 있는 방향 d, 달리는 속력 f, 멧돼지의 무게 w 이다.
    멧돼지는 바라보는 방향 d를 향해 초당 f 칸 만큼 이동한다.
    멧돼지가 이동한 후 같은 격자 칸에 2마리 이상의 멧돼지가 있을 경우 가장 무게가 많이 나가는 멧돼지가 다른 멧돼지들을 먹는다.
    <poacher_01.png>의 멧돼지들의 정보는 <poacher_02.png>에서 각 멧돼지 아래에 멧돼지 구분자, 속력, 크기 순서대로 적혀있다.
    <poacher_03.png>은 <poacher_02.png>에서 1초가 지난 뒤 모습이다.
    멧돼지가 이동한 <poacher_03.png> 모습 직후 밀렵꾼이 한 칸 이동하여 손전등을 비춰서 멧돼지를 확인한다.
    보이는 멧돼지가 없기때문에 1초 후 포획되는 멧돼지는 없다.
    [poacher_02.png]
    [poacher_03.png]
    [poacher_04.png]
    [poacher_05.png]
    [poacher_06.png]
    [poacher_07.png]
    [poacher_08.png]
    [poacher_09.png]
    [poacher_10.png]
    포획된 멧돼지의 무게 합은 10이다.

    [입력]
    첫 번째 줄에 테스트 케이스의 개수 T가 주어진다.
    다음 줄부터 T개의 테스트 케이스에 대한 정보가 주어진다.
    각각의 테스트 케이스의 첫 번째 줄에 장소의 크기 R, C 와 멧돼지의 마릿수 M 이 공백을 통해 구분하여 주어진다.
    두 번째 줄부터 M 개의 줄에 걸쳐 멧돼지 정보가 공백을 통해 구분하여 주어진다.
    멧돼지의 위치 y, x 와 멧돼지가 달리고 있는 방향 d, 달리는 속력 f, 멧돼지의 무게 w 이다.
    초기 멧돼지의 위치는 서로 겹치지 않으며, 같은 무게를 가진 멧돼지는 없다.
    멧돼지가 달리고 있는 방향은 1부터 4까지의 정수이며, 숫자 순서대로 상하좌우를 의미한다.
    (3 <= R, C <= 100, 1 <= M <= (R * C), 1 <= y <= R, 1 <= x <= C, 1 <= d <= 4, 0 <= f <= 1000, 1 <= w <= 10000)

    [출력]
    각 테스트 케이스에 대해 포획된 멧돼지의 무게 합을 출력한다.
    각 테스트 케이스의 출력 양식은 "#t r"이다.
    t는 테스트 케이스의 번호이며, 1부터 시작한다.
    r은 문제에 대한 결과값을 뜻한다.

    [예제 입력]
    5
    5 7 6
    1 6 3 3 9
    2 1 4 8 3
    2 4 3 7 5
    3 7 1 4 1
    4 3 2 10 4
    4 5 4 5 2
    9 3 4
    2 2 1 0 6
    5 2 4 15 7
    8 3 4 10 1
    6 1 1 16 5
    8 5 4
    3 5 2 4 6
    2 3 4 0 2
    8 5 4 8 7
    2 4 4 15 9
    5 9 15
    1 4 1 15 94
    4 7 2 4 32
    5 2 3 11 85
    4 4 2 18 33
    2 8 4 14 87
    1 7 3 14 100
    4 8 1 10 63
    1 6 2 0 4
    1 1 2 19 95
    4 3 2 17 19
    5 9 1 12 17
    4 9 3 0 60
    3 6 2 1 23
    2 7 4 0 58
    1 9 4 3 53
    7 9 8
    4 3 2 2 1
    7 8 1 5 47
    3 7 2 18 61
    2 6 2 19 14
    1 3 1 6 93
    3 2 3 13 65
    5 3 4 16 69
    6 8 2 11 54
    [예제 출력]
    #1 10
    #2 8
    #3 16
    #4 498
    #5 287

     */

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        final int times = SC.nextInt();

        for (int time = 1; time <= times; time++) {
            final int rowSize = SC.nextInt();
            final int colSize = SC.nextInt();
            final int numberOfBoars = SC.nextInt();

            Boar.setSize(rowSize, colSize);

            // 멧돼지 삽입
            final Queue<Boar> boars = new LinkedList<>();
            final Boar[][] boarsPosition = new Boar[rowSize + 1][colSize + 1];

            char id = 'a';

            for (int boar = 0; boar < numberOfBoars; boar++) {
                final int row = SC.nextInt();
                final int col = SC.nextInt();
                final int direction = SC.nextInt();
                final int speed = SC.nextInt();
                final int weight = SC.nextInt();

                final Boar newBoar = new Boar(id++, row, col, direction, speed, weight);

                boars.add(newBoar);
                boarsPosition[row][col] = newBoar;
            }

            int sumBoarWeight = 0;

            // 이동, 포획
            for (int capture = 1; capture <= colSize; capture++) {
                final Boar[][] copyPosition = new Boar[rowSize + 1][colSize + 1];

                final int boarsCount = boars.size();
                for (int i = 0; i < boarsCount; i++) {
                    final Boar boar = boars.poll();

                    assert boar != null;
                    boarsPosition[boar.getRow()][boar.getCol()] = null;

                    boar.move();

                    final Boar originPosition = copyPosition[boar.getRow()][boar.getCol()];
                    if (originPosition != null) {
                        if (originPosition.getWeight() >= boar.getWeight()) {
                            continue;
                        }

                        boars.remove(originPosition);
                    }

                    copyPosition[boar.getRow()][boar.getCol()] = boar;
                    boars.offer(boar);
                }

                for (int row = 1; row <= rowSize; row++) {
                    for (int col = 1; col <= colSize; col++) {
                        if (copyPosition[row][col] != null) {
                            boarsPosition[row][col] = copyPosition[row][col];
                        }
                    }
                }

                // 멧돼지 무게 합산
                for (int row = rowSize; row >= 1; row--) {
                    if (boarsPosition[row][capture] != null) {
                        final Boar beCaughtBoar = boarsPosition[row][capture];
                        sumBoarWeight += beCaughtBoar.getWeight();
                        boars.remove(beCaughtBoar);
                        break;
                    }
                }
            }

            System.out.println("#" + time + " " + sumBoarWeight);
        }

        SC.close();
    }
}

class Boar {
    private static final int[][] DIRECTIONS = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int UP = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private static final int RIGHT = 4;
    private static final int FIRST_POSITION = 1;
    private static int rowSize;
    private static int colSize;

    private final char id;
    private int row;
    private int col;
    private int direction;
    private final int speed;
    private final int weight;

    Boar(final char id, final int row, final int col, final int direction, final int speed, final int weight) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.direction = direction;
        this.speed = (direction == UP || direction == DOWN) ? reduceStep(rowSize, speed) : reduceStep(colSize, speed);
        this.weight = weight;
    }

    private int reduceStep(final int size, final int speed) {
        return speed % ((size * 2) - 2);
    }

    public static void setSize(final int givenRowSize, final int givenColSize) {
        rowSize = givenRowSize;
        colSize = givenColSize;
    }

    public void move() {
        if (direction == UP || direction == DOWN) {
            row = run(row, speed * DIRECTIONS[direction][0], rowSize);
            return;
        }

        col = run(col, speed * DIRECTIONS[direction][1], colSize);
    }

    private int run(int curPosition, int haveToMoveDist, final int maxSize) {
        while (true) {
            final int nextPosition = curPosition + haveToMoveDist;

            if (FIRST_POSITION <= nextPosition && nextPosition <= maxSize) {
                return nextPosition;
            }

            if (FIRST_POSITION > nextPosition) {
                haveToMoveDist += (curPosition - 1);
                curPosition = FIRST_POSITION;
                haveToMoveDist *= -1;
                direction = (direction == UP) ? DOWN : RIGHT;
                continue;
            }

            haveToMoveDist -= (maxSize - curPosition);
            curPosition = maxSize;
            haveToMoveDist *= -1;
            direction = (direction == DOWN) ? UP : LEFT;
        }
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }

    int getWeight() {
        return weight;
    }
}
