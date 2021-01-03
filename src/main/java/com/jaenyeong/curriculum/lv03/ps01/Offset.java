package com.jaenyeong.curriculum.lv03.ps01;

import java.util.Scanner;
import java.util.StringJoiner;

public class Offset {
    /*
    offset

    [문제]
    5x5 2차원 배열이 주어질 때 어떤 원소가 상하좌우에 있는 원소보다 작을 때 해당 위치에 * 을 표시하는 프로그램을 작성하시오.
    경계선에 있는 수는 상하좌우 중 존재하는 원소만을 비교한다.

    [입력]
    5x5 행렬의 정보가 25 개의 수로 주어진다.
    각 수는 0 에서 9 사이 수이다.

    [출력]
    *를 포함한 행렬을 출력예의 형식으로 출력한다.

    [예제 입력]
    3 4 1 4 9
    2 9 4 5 8
    9 0 8 2 1
    7 0 2 8 4
    2 7 2 1 4
    [예제 출력]
    3 4 * 4 9
    * 9 4 5 8
    9 0 8 2 *
    7 0 2 8 4
    * 7 2 * 4

     */

    private static final Scanner SCAN = new Scanner(System.in);
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {

        // Please Enter Your Code Here
        final int[][] inputs = new int[5][5];
        final char[][] results = new char[5][5];

        // 입력 받기
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                inputs[row][col] = SCAN.nextInt();
                results[row][col] = (char) (inputs[row][col] + '0');
            }
        }

        SCAN.close();

        // 해당 원소가 주변의 원소보다 작은지 확인
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {

                final int current = inputs[row][col];
                boolean smallCheck = true;

                for (int[] dir : DIRECTIONS) {
                    final int nextRow = row + dir[0];
                    final int nextCol = col + dir[1];

                    // 유효 범위를 벗어난 경우
                    if ((0 > nextRow) || (nextRow >= 5) || (0 > nextCol) || (nextCol >= 5)) continue;

                    final int nextValue = inputs[nextRow][nextCol];

                    if (current >= nextValue) {
                        smallCheck = false;
                        break;
                    }
                }

                if (smallCheck) results[row][col] = '*';
            }
        }

        // 출력
        for (int row = 0; row < 5; row++) {
            StringJoiner sj = new StringJoiner(" ");
            for (int col = 0; col < 5; col++) {
                sj.add(String.valueOf(results[row][col]));
            }
            System.out.println(sj.toString());
        }
    }
}
