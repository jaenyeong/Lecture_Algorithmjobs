package com.jaenyeong.curriculum.lv05.ps02;

import java.util.Scanner;

public class CombinationPascal {
    /*
    combination pascal

    [문제]
    n명의 사람중 m명을 순서에 상관없이 뽑는 경우의 수를 조합이라고 하며 nCm으로 나타낸다.
    이 조합은 파스칼의 삼각형과 아주 밀접한 관련이 있다고 한다.
    n과 m이 주어졌을때 nCm의 값을 출력하는 프로그램을 작성하시오.

    [입력]
    첫째 줄에 정수 n, m(0 ≤ m ≤ n ≤ 30)이 들어온다.

    [출력]
    첫째 줄에 nCm의 값을 출력한다.

    [예제 입력]
    5 2
    [예제 출력]
    10

     */

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = SC.nextInt();
        final int m = SC.nextInt();

        SC.close();

        final int[][] pascal = new int[n + 1][n + 1];

        pascal[0][0] = 1;

        for (int row = 1; row <= n; row++) {
            pascal[row][0] = pascal[row - 1][0];

            for (int col = 1; col <= row; col++) {
                if (col == row) {
                    pascal[row][col] = pascal[row - 1][col - 1];
                    continue;
                }

                final int first = pascal[row - 1][col - 1];
                final int second = pascal[row - 1][col];

                pascal[row][col] = (first + second);
            }
        }

        System.out.println(pascal[n][m]);
    }
}
