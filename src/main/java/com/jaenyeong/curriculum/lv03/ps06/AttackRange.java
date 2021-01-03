package com.jaenyeong.curriculum.lv03.ps06;

import java.util.Scanner;
import java.util.StringJoiner;

public class AttackRange {
    /*
    attack range

    [문제]
    윤성이는 어렸을 적부터 수없이 몰려오는 적으로부터 기지를 방어하는 디펜스 유형의 게임을 플레이하는 것을 좋아했다.
    그래서 간단한 디펜스 게임을 만들어 보려고 한다.
    당신은 윤성이를 도와, 디펜스 게임 내에서 플레이어가 설치하는 유닛의 사거리를 나타내는 기능을 구현하면 된다.

    [입력]
    입력 첫째 줄에는 디펜스 게임의 맵 크기 N이 주어딘다.
    맵은 N×N 크기의 2차원 형태이다. (N은 6이상 100이하의 짝수)
    둘째 줄에는 유닛이 설치될 위치 X, Y와 유닛의 사거리 R이 주어진다.
    X는 행의 번호, Y는 열의 번호를 의미한다. (X, Y는 1이상 N 이하의 자연수, R은 N/2 이하의 자연수)

    [출력]
    예제 출력과 같이 유닛의 사거리를 나타내어 출력한다. (유닛의 사거리가 아무리 길어도 맵을 벗어날 수는 없다.)

    [예제 입력]
    8
    4 5 3
    [예제 출력]
    0 0 0 0 3 0 0 0
    0 0 0 3 2 3 0 0
    0 0 3 2 1 2 3 0
    0 3 2 1 x 1 2 3
    0 0 3 2 1 2 3 0
    0 0 0 3 2 3 0 0
    0 0 0 0 3 0 0 0
    0 0 0 0 0 0 0 0

    [예제 입력]
    6
    2 3 3
    [예제 출력]
    3 2 1 2 3 0
    2 1 x 1 2 3
    3 2 1 2 3 0
    0 3 2 3 0 0
    0 0 3 0 0 0
    0 0 0 0 0 0

     */

    private static final Scanner SCAN = new Scanner(System.in);
    private static final int UNIT = -1;

    public static void main(String[] args) {
        final int n = SCAN.nextInt();

        final int x = SCAN.nextInt();
        final int y = SCAN.nextInt();
        final int r = SCAN.nextInt();

        SCAN.close();

        final int[][] map = new int[n][n];

        final int unitRow = x - 1;
        final int unitCol = y - 1;

        // 유닛을 주어진 위치에 삽입
        map[unitRow][unitCol] = UNIT;

        for (int row = (unitRow - r); row <= (unitRow + r); row++) {
            for (int col = (unitCol - r); col <= (unitCol + r); col++) {
                // 유효 범위 확인
                if ((row < 0) || (row >= n) || (col < 0) || (col >= n)) continue;

                // 현재 위치의 유닛이 있으면 통과
                final int currentZone = map[row][col];
                if (currentZone == UNIT) continue;

                // 사거리 확인
                final int diffRow = Math.abs(row - unitRow);
                final int diffCol = Math.abs(col - unitCol);

                final int diffRange = diffRow + diffCol;

                // 사거리가 주어진 사거리를 넘어선 곳이면 통과
                if (diffRange > r) continue;

                map[row][col] = diffRange;
            }
        }

        // 결과 출력
        printResult(n, map);
    }

    private static void printResult(final int n, final int[][] map) {
        for (int row = 0; row < n; row++) {
            StringJoiner sj = new StringJoiner(" ");
            for (int col = 0; col < n; col++) {
                final int el = map[row][col];
                if (el == UNIT) {
                    sj.add("x");
                    continue;
                }

                sj.add(Integer.toString(el));
            }
            System.out.println(sj);
        }
    }
}
