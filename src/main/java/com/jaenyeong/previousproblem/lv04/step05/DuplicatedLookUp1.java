package com.jaenyeong.previousproblem.lv04.step05;

import java.util.Scanner;

public class DuplicatedLookUp1 {
    /*
    중복 조회1

    [문제]
    2차원 평면에 n 명의 사람이 있다.
    i 번 사람의 좌표는 두 자연수 yi, xi 로 표현되고, 같은 위치에 여러 사람이 존재할 수 있다.
    혼자 있는 사람의 수를 출력하여라.
    [duplicated_look_up_1.png]

    [입력]
    첫 줄에 n 이 주어진다.
    두 번째 줄부터 n 줄에 걸쳐 yi, xi 가 주어진다
    (1 <= n <= 100,000, 1 <= yi, xi <= 100)

    [출력]
    혼자 있는 사람의 수를 출력한다.

    [예제 입력]
    6
    1 1
    2 3
    2 2
    1 3
    1 1
    4 5
    [예제 출력]
    4

    [예제 입력]
    5
    1 1
    1 1
    1 1
    2 2
    3 3
    [예제 출력]
    2

    [예제 입력]
    5
    1 1
    1 2
    1 3
    1 1
    2 2
    [예제 출력]
    3

    [예제 입력]
    6
    1 1
    1 1
    1 1
    2 2
    3 3
    3 3
    [예제 출력]
    1

     */

    private static final Scanner SC = new Scanner(System.in);
    public static final int GRID_SIZE = 101;

    public static void main(String[] args) {
        final int numberOfPeople = SC.nextInt();

        final int[][] grid = new int[GRID_SIZE][GRID_SIZE];

        for (int i = 0; i < numberOfPeople; i++) {
            final int row = SC.nextInt();
            final int col = SC.nextInt();

            grid[row][col]++;
        }

        SC.close();

        int countAlonePeople = 0;

        for (int row = 1; row < GRID_SIZE; row++) {
            for (int col = 1; col < GRID_SIZE; col++) {
                if (grid[row][col] == 1) {
                    countAlonePeople++;
                }
            }
        }

        System.out.println(countAlonePeople);
    }
}
