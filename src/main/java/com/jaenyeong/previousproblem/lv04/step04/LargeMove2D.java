package com.jaenyeong.previousproblem.lv04.step04;

import java.util.Scanner;

public class LargeMove2D {
    /*
    2차원 큰 이동

    [문제]
    세로 길이 N, 가로 길이 M 인 2 차원 배열의 (x, y) 위치에 사람이 있다.
    이 사람은 초기 방향 d 로 1 초에 한 칸 씩 이동하는데, 배열의 끝에 도착하면 다음 이동 시 뒤로 돌아서 반대 방향으로 이동한다.
    t 초 뒤 이 사람의 위치를 x, y 순서로 출력하여라.
    배열의 왼쪽 위 좌표는 (1, 1) 이고, 오른쪽 아래 좌표는 (M, N) 이다.
    d 는 0, 1, 2, 3 중 하나로 주어지는데, 순서대로 상하좌우를 의미한다.
    이 배열의 인덱스는 1부터 시작한다.
    [2차원_큰_이동.png]

    [입력]
    첫 줄에 정수 N, M, y, x, d, t 가 주어진다.
    (3 <= N, M <= 1,000,000,000, 1 <= y <= N, 1 <= x <= M, 0 <= d <= 3, 0 <= t <= 1,000,000,000)

    [출력]
    t 초 후 사람의 위치를 y, x 순서로 출력한다.

    [예제 입력]
    4 5 3 2 1 3
    [예제 출력]
    2 2

    [예제 입력]
    486123 97842 5465 2314 2 1678942
    [예제 출력]
    5465 84510

    [예제 입력]
    1894654 2313245 123456 576411 0 4346312
    [예제 출력]
    433552 576411

    [예제 입력]
    879546218 348978562 324567841 154875621 3 1000000000
    [예제 출력]
    324567841 241038625

     */

    private static final Scanner SC = new Scanner(System.in);
    private static final int MIN_SIZE = 1;
    // 순서대로 상하좌우
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int UP = 0;
    private static final int DOWN = 1;

    public static void main(String[] args) {
        final int rowSize = SC.nextInt();
        final int colSize = SC.nextInt();

        int curRow = SC.nextInt();
        int curCol = SC.nextInt();

        final int dir = SC.nextInt();
        int time = SC.nextInt();

        SC.close();

        if (dir == UP || dir == DOWN) {
            time = reduceTime(rowSize, time);
            curRow = move(curRow, time * DIRECTIONS[dir][0], rowSize);
            printResult(curRow, curCol);

            return;
        }

        time = reduceTime(colSize, time);
        curCol = move(curCol, time * DIRECTIONS[dir][1], colSize);
        printResult(curRow, curCol);
    }

    private static int reduceTime(final int size, final int time) {
        return time % ((size * 2) - 2);
    }

    private static int move(int curPosition, int haveToDist, final int maxSize) {
        while (true) {
            final int moveDist = (curPosition + haveToDist);

            if (MIN_SIZE <= moveDist && moveDist <= maxSize) {
                return moveDist;
            }

            if (moveDist < MIN_SIZE) {
                haveToDist += (curPosition - 1);
                curPosition = MIN_SIZE;
                haveToDist = changePositiveOrNegative(haveToDist);

                continue;
            }

            haveToDist -= (maxSize - curPosition);
            curPosition = maxSize;
            haveToDist = (changePositiveOrNegative(haveToDist));
        }
    }

    private static int changePositiveOrNegative(final int givenNumber) {
        return givenNumber * -1;
    }

    private static void printResult(final int curRow, final int curCol) {
        System.out.println(curRow + " " + curCol);
    }
}
