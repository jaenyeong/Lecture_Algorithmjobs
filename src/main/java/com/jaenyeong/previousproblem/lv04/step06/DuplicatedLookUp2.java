package com.jaenyeong.previousproblem.lv04.step06;

import java.util.Scanner;

public class DuplicatedLookUp2 {
    /*
    중복 조회2

    [문제]
    2차원 평면에 n 명의 사람이 있다.
    i 번 사람의 좌표는 두 자연수 yi, xi 로 표현되고, 같은 위치에 여러 사람이 존재할 수 있다.
    1 번 사람부터 n 번 사람까지 순서대로 좌표가 주어진다.
    각 좌표에 있는 사람의 번호의 합을 출력하여라.
    단, 같은 위치에 두 명 이상의 사람이 있을 경우 가장 작은 번호만 더한다.
    [중복_조회2.png]

    [입력]
    첫 줄에 n 이 주어진다.
    두 번째 줄부터 n 줄에 걸쳐 yi, xi 가 주어진다
    (1 <= n <= 100,000, 1 <= yi, xi <= 100)

    [출력]
    조건에 맞는 번호의 합을 출력한다.

    [예제 입력]
    6
    1 1
    2 3
    2 2
    1 3
    1 1
    4 5
    [예제 출력]
    16

    [예제 입력]
    5
    1 1
    1 1
    1 1
    2 2
    3 3
    [예제 출력]
    10

    [예제 입력]
    5
    1 1
    1 2
    1 3
    1 1
    2 2
    [예제 출력]
    11

    [예제 입력]
    6
    1 1
    1 1
    1 1
    2 2
    3 3
    3 3
    [예제 출력]
    10

     */

    private static final Scanner SC = new Scanner(System.in);
    public static final int GRID_SIZE = 101;

    public static void main(String[] args) {
        final int numberOfPeople = SC.nextInt();

        final int[][] grid = new int[GRID_SIZE][GRID_SIZE];

        for (int man = 1; man <= numberOfPeople; man++) {
            final int row = SC.nextInt();
            final int col = SC.nextInt();

            if (grid[row][col] <= 0) {
                grid[row][col] = man;
            }
        }

        SC.close();

        int sum = 0;
        for (int row = 1; row < GRID_SIZE; row++) {
            for (int col = 1; col < GRID_SIZE; col++) {
                if (grid[row][col] > 0) {
                    sum += grid[row][col];
                }
            }
        }

        System.out.println(sum);
    }
}
