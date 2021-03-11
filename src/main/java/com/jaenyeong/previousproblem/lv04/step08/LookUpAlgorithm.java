package com.jaenyeong.previousproblem.lv04.step08;

import java.util.Scanner;

public class LookUpAlgorithm {
    /*
    조회 알고리즘2

    [문제]
    2차원 배열에 n 명의 사람이 있다.
    i 번 사람에 대한 정보는 현재 위치 yi, xi 와 나이 agei 가 있다.
    t 초 동안 1 초에 한 번 씩 k 명의 새로운 사람들이 배열 안으로 들어온다.
    그 후 만약 어떤 위치에 두 명 이상의 사람이 있을 경우 가장 나이가 많은 사람만 남고, 나머지는 배열 범위 밖으로 나가버린다.
    가장 나이가 많은 사람이 둘 이상일 경우 둘 중 아무나 한 명이 남게 된다.
    초기 시각이 0 초 라고 할 때, 1 초부터 t 초까지 각 시각별로 배열 안에 있는 사람들의 나이 합을 출력하여라.
    [look_up_2.png]

    [입력]
    첫 줄에 n, k, t 가 주어진다.
    두 번째 줄부터 n 줄에 걸쳐 0 초에 배열 내에 존재하는 사람들의 정보가 주어진다.
    n + 2 번째 줄 부터 k * t 줄에 걸쳐 새로 들어오는 사람들의 정보가 주어진다.
    (1 <= n <= 10,000, 1 <= k <= 10, 1 <= t <= 1,000, 1 <= yi, xi, agei <= 1,000)

    [출력]
    t 줄에 걸쳐 각 시각별로 배열 안에 있는 사람들의 나이 합을 출력한다.

    [예제 입력]
    6 3 2
    1 1 5
    2 3 6
    2 2 3
    1 3 4
    1 1 7
    4 5 2
    4 1 5
    4 1 6
    4 1 9
    2 2 1
    2 3 6
    1 1 9
    [예제 출력]
    31
    33

    [예제 입력]
    6 3 4
    1 1 1
    1 2 2
    1 3 3
    2 1 4
    2 2 5
    2 3 6
    3 1 7
    3 2 8
    3 3 9
    1 1 10
    2 2 11
    3 3 12
    1 1 5
    2 2 5
    3 3 5
    1 2 12
    2 1 14
    3 2 18
    [예제 출력]
    45
    63
    63
    93

     */

    private static final Scanner SC = new Scanner(System.in);
    private static final int SIZE = 1_001;

    public static void main(String[] args) {
        final int numberOfPeople = SC.nextInt();

        final int numberOfNewPeople = SC.nextInt();
        final int times = SC.nextInt();

        final int[][] ages = new int[SIZE][SIZE];

        for (int man = 0; man < numberOfPeople; man++) {
            inputMan(ages);
        }

        for (int time = 0; time < times; time++) {
            for (int newMan = 0; newMan < numberOfNewPeople; newMan++) {
                inputMan(ages);
            }

            printAgeSum(ages);
        }

        SC.close();
    }

    private static void inputMan(final int[][] ages) {
        final int row = SC.nextInt();
        final int col = SC.nextInt();
        final int age = SC.nextInt();

        ages[row][col] = Math.max(ages[row][col], age);
    }

    private static void printAgeSum(final int[][] ages) {
        int sumAge = 0;
        for (int row = 1; row < SIZE; row++) {
            for (int col = 1; col < SIZE; col++) {
                sumAge += ages[row][col];
            }
        }

        System.out.println(sumAge);
    }
}
