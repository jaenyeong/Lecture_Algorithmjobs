package com.jaenyeong.previousproblem.lv04.step02;

import java.util.Scanner;

public class NPeopleMove {
    /*
    N명 이동

    [문제]
    세로 길이 N, 가로 길이 M 인 2차원 배열에 k 명의 사람이 있다.
    i 번 사람은 1 초에 di 방향으로 fi 만큼 이동하고, 만약 이동하려는 위치가 배열의 범위 밖이라면 배열을 나가기 직전의 위치에서 멈춘다.
    t 초 후 각 사람의 위치를 1 번 사람부터 출력하여라.
    이 배열의 왼쪽 위 좌표는 (1, 1) 이고, 오른쪽 아래 좌표는 (M, N) 이다.
    yi 는 i 번 사람의 세로 방향 위치, xi 는 i 번 사람의 가로 방향 위치를 의미한다.
    di 는 0, 1, 2, 3 중 하나로 주어지는데, 순서대로 상하좌우를 의미한다.

    [입력]
    첫 줄에 정수 N, M, k, t 가 주어진다.
    두 번째 줄부터 k 줄에 걸쳐 정수 yi, xi, di, fi 가 주어진다.
    (3 <= N, M, k, t <= 100, 1 <= yi <= N, 1 <= xi <= M, 0 <= di <= 3, 1 <= fi <= 100)

    [출력]
    k 줄에 걸쳐 각 사람의 t 초 후 위치를 y, x 순으로 출력한다.

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

    [설명]
    아래는 예 1 번에 대한 그림이다.
    [n_people_move.png]

     */

    private static final Scanner SC = new Scanner(System.in);
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int ROW = 0;
    private static final int COL = 1;

    public static void main(String[] args) {
        final int n = SC.nextInt();
        final int m = SC.nextInt();
        final int k = SC.nextInt();
        final int t = SC.nextInt();

        for (int man = 0; man < k; man++) {
            int currRow = SC.nextInt();
            int currCol = SC.nextInt();
            final int direction = SC.nextInt();
            final int numberOfMove = SC.nextInt();

            for (int time = 0; time < t; time++) {
                for (int move = 0; move < numberOfMove; move++) {
                    int nextRow = currRow + DIRECTIONS[direction][ROW];
                    int nextCol = currCol + DIRECTIONS[direction][COL];

                    if ((0 >= nextRow) || (nextRow > n) || (0 >= nextCol) || (nextCol > m)) {
                        break;
                    }

                    currRow = nextRow;
                    currCol = nextCol;
                }
            }

            System.out.printf("%d %d\n", currRow, currCol);
        }

        SC.close();
    }
}
