package com.jaenyeong.previousproblem.lv01.step08;

import java.util.*;

public class RouletteAndRobot {
    /*
    회전판과 로봇

    [문제]
    가로 N칸, 세로 M칸으로 이루어진 격자판이 주어지고, 그 중 한 개의 칸에 로봇이 존재한다.
    로봇은 동(E), 서(W), 남(S), 북(N) 4 방향으로 움직일 수 있다.
    각 칸에는 로봇이 얻을 수 있는 점수가 적혀있거나 장애물이 존재한다.
    점수는 자연수로 주어지며, 장애물은 -1로 주어진다.
    로봇은 움직일 때 마다 자신이 위치해 있는 곳의 점수를 얻게 되고, 해당 칸의 값은 0으로 바뀌게 된다.
    로봇의 초기 위치에는 장애물이 존재하지 않으며, 항상 초기 위치에 있는 점수를 얻으며 움직이기 시작한다.
    성우는 총 L회 로봇을 움직일 수 있으며, 각 회마다 성우는 로봇이 이동할 방향과 크기를 정해줄 수 있다.
    예를 들어, 한 회에 로봇을 서쪽으로 3칸 움직일 수 있다.
    이렇게 로봇이 이동하는 동안 방문하는 칸에 있는 점수를 얻게 된다.
    단, 이동 중에 장애물을 만나거나 격자판의 모서리에 닿게 되면 로봇이 그 즉시 이동을 멈추고 다음 명령을 수행한다.
    [회전판과로봇_1.png]
    이동 중 장애물을 만나거나 모서리 혹은 벽면에 닿은 경우 - 빨간 화살표
    로봇을 움직이는 명령이 너무 단조롭다고 느낀 성우는, 로봇이 이동하는 크기를 조금 더 복잡하게 정해보고자 한다.
    로봇이 움직이는 크기는 회전판을 돌림으로써 정해진다.
    회전판은 K개의 칸으로 이루어져 있으며, 각 칸에는 자연수가 적혀있다.
    성우는 각 회마다 판을 한 번 회전시키고, 회전 결과 한 개의 숫자가 선택된다.
    이 숫자만큼 로봇이 이동하게 된다.
    그리고 회전판은 최초인 경우에는 지정된 기준에 따르고, 여러번 돌린 경우에는 직전 회전 결과를 기준으로 회전판을 돌린다.
    [회전판과로봇_2.png]
    성우는 판을 시계방향 혹은 반시계방향으로 판을 회전시킬 수 있다.
    또한, 성우는 힘조절을 완벽하게 하기 때문에 판을 회전시킬 때에는 정확히 특정 칸 수만큼만 회전시킬 수 있다.
    예를 들어, 크기 5인 회전판이 있고, 이는 위의 그림과 같다고 하자.
    이 상황에서 성우가 회전판을 시계방향으로 회전시키면서 정확히 7칸만큼만 움직였다고 하자.
    그러면 화살표는 0 → 1 → 3 → 5 → 2 → 0 → 1 순서대로 숫자를 가리키므로, 회전판은 마지막으로 숫자 1을 가리킨다.
    따라서 로봇이 이동하는 크기는 1이 된다.
    성우는 회전판과 함께 최종적으로 다음과 같은 명령을 내린다.
    이 명령은 (이동 방향, 회전 방향, 회전시키는 칸 수) 로 주어진다.
    회전 방향의 경우, 1은 시계방향을 나타내고 2는 반시계방향을 나타낸다.
    다음은 이 명령의 예를 나타낸다.
    N 1 2 : 회전판을 시계 방향으로 회전하며 2칸만큼 움직이고, 가리키는 숫자만큼 로봇을 북쪽으로 이동시킨다.
    E 2 8 : 회전판을 시계 반대방향으로 회전하며 8칸만큼 움직이고, 가리키는 숫자만큼 로봇을 동쪽으로 이동시킨다.
    예를 들어, 지도와 회전판의 초기 상태가 위의 그림과 같고, 로봇이 (2, 5)에 위치해있는 경우에 “N 1 3” 의 명령을 내렸다고 하자.
    그러면 성우는 시계방향으로 판을 회전시키면서 정확히 3칸을 움직이게 되므로, 회전판은 0 → 1 → 3 의 순서대로 숫자를 가리키게 된다.
    따라서 북쪽으로 3만큼 움직이므로, 로봇의 최종 위치는 (2, 2)가 된다.
    성우가 로봇을 L회 움직일 때, 로봇이 얻는 총 점수와 로봇의 최종위치 (x’, y’)를 출력하는 프로그램을 작성하시오.

    [입력]
    첫째 줄에는 테스트 케이스 T (1 <= T <= 10)가 주어지고,
    그 다음 줄에 도로의 가로의 크기 N과 세로의 크기 M,
    그리고 로봇의 현재 위치 X, Y 가 빈칸을 사이에 두고 주어진다. (1 <= N, M, X, Y <= 500)
    다음 M개의 줄에 대하여 판의 상태가 주어진다.
    각 칸에는 점수 혹은 장애물이 있으며, 점수는 200이하의 자연수로 주어지고 장애물은 -1로 주어진다.
    그 후 다음 줄에 회전판에 존재하는 칸의 개수 K(1 <= K <= 8) 가 주어지고,
    다음 줄에 회전판의 값들이 K개의 1000이하 자연수로 주어진다.
    숫자는 회전판의 시계방향으로 주어지며, 회전판은 초기에 가장 앞의 숫자를 가리키고 있다.
    그 다음 줄에 성우가 로봇을 움직이는 횟수 L(1 <= L <= 500) 이 주어지고, 이어 L개의 줄에 걸쳐 로봇을 움직이는 명령이 주어진다.
    각 명령은 이동할 방향, 회전 방향, 회전시키는 칸 수로 이루어져 있다.
    회전 방향의 경우, 시계 방향은 1로 주어지며 반시계방향은 2로 주어진다.
    회전시키는 칸 수의 경우 100 이하의 자연수이다.

    [출력]
    T개의 테스트 케이스 각각에 대하여 로봇의 최종 위치와 점수를 출력한다.
    각 줄은 “#x”, 로봇이 얻은 점수, 그리고 로봇의 최종 위치(x’, y’)를 각각 공백을 두고 출력한다.
    단, x는 1부터 시작하는 테스트 케이스의 번호를 나타낸다.

    [예제 입력]
    2
    5 6 1 2
    0 1 1 2 3
    2 5 -1 2 0
    10 29 17 -1 3
    5 4 7 9 12
    11 19 -1 12 4
    7 8 9 1 3
    3
    2 4 1
    3
    S 1 2
    N 2 1
    E 2 5
    5 6 2 5
    0 1 1 2 3
    2 5 -1 2 0
    10 29 17 -1 3
    5 4 7 9 12
    11 19 -1 12 4
    7 8 9 1 3
    5
    2 5 3 1 0
    5
    S 1 3
    N 1 4
    W 2 1
    N 1 7
    E 1 1
    [예제 출력]
    #1 54 2 5
    #2 65 2 2

    [설명]
    [회전판과로봇_3.png]
    로봇은 (1, 2) 에서부터 출발하여 그림과 같이 움직인다.
    따라서 얻게 되는 점수는 54이며, 마지막 로봇의 위치는 (2, 5)이다.

     */

    private static final Scanner SCAN = new Scanner(System.in);
    private static final int WALL = -1;

    public static void main(String[] args) {
        // 이동 방향 초기화
        final Map<Character, int[]> direction = new HashMap<>();
        direction.put('E', new int[]{0, 1});
        direction.put('W', new int[]{0, -1});
        direction.put('S', new int[]{1, 0});
        direction.put('N', new int[]{-1, 0});

        // 테스트 케이스 개수 입력
        final int t = SCAN.nextInt();

        // 테스트 케이스만큼 반복
        for (int testCase = 1; testCase <= t; testCase++) {
            // 2차원 배열 크기
            final int colSize = SCAN.nextInt();
            final int rowSize = SCAN.nextInt();
            // 로봇의 시작 위치 초기화
            int robotCol = SCAN.nextInt() - 1;
            int robotRow = SCAN.nextInt() - 1;

            // 2차원 배열 초기화
            final int[][] grid = initGridArr(colSize, rowSize);

            // 룰렛 초기화
            final Roulette roulette = initRoulette(SCAN.nextInt());

            // 로봇이 이동하며 획득할 점수 합산 변수
            int score = grid[robotRow][robotCol];
            // 시작지점 점수를 획득 후 시작지점 점수 초기화
            grid[robotRow][robotCol] = 0;

            // 주어진 수만큼 룰렛을 굴림
            final int turn = SCAN.nextInt();
            for (int move = 0; move < turn; move++) {
                // 방향을 문자열로 입력
                final char gridDirection = SCAN.next().charAt(0);
                // 룰렛 방향 입력
                final int rDir = SCAN.nextInt();
                // 룰렛 이동횟수 입력
                final int rCount = SCAN.nextInt();

                // 룰렛을 이동시켜 로봇의 이동거리 획득
                final int moveDist = roulette.turnRoulette(rDir, rCount);
                // 해당 방향
                final int[] dir = direction.get(gridDirection);

                // 룰렛에서 나온 숫자만큼 로봇을 이동
                for (int i = 0; i < moveDist; i++) {
                    final int nextRow = robotRow + dir[0];
                    final int nextCol = robotCol + dir[1];

                    // 다음 이동할 지역의 유효성 판단
                    if (validateGridRange(colSize, rowSize, nextRow, nextCol)) {
                        break;
                    }

                    // 벽을 만난 경우 이동 중지
                    final int nextZone = grid[nextRow][nextCol];
                    if (nextZone == WALL) {
                        break;
                    }

                    robotRow = nextRow;
                    robotCol = nextCol;
                    // 해당 지역의 점수를 합산
                    score += grid[robotRow][robotCol];

                    // 지나온 지점은 점수 0으로 초기화
                    grid[robotRow][robotCol] = 0;
                }
            }

            // 결과 출력 : '#' + testCase + ' ' + score + ' ' + col + ' ' + row
            System.out.println(getResultFormat(testCase, score, robotCol + 1, robotRow + 1));
        }

        SCAN.close();
    }

    private static int[][] initGridArr(final int colSize, final int rowSize) {
        final int[][] grid = new int[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                grid[row][col] = SCAN.nextInt();
            }
        }
        return grid;
    }

    private static Roulette initRoulette(final int k) {
        final int[] roul = new int[k];
        for (int num = 0; num < k; num++) {
            roul[num] = SCAN.nextInt();
        }

        return new Roulette(roul);
    }

    private static boolean validateGridRange(final int colSize, final int rowSize, final int nextRow, final int nextCol) {
        return (0 > nextRow) || (nextRow >= rowSize) || (0 > nextCol) || (nextCol >= colSize);
    }

    private static String getResultFormat(final int testCase, final int score, final int robotCol, final int robotRow) {
        final StringJoiner sj = new StringJoiner(" ");
        sj.add("#" + testCase);
        sj.add(Integer.toString(score));
        sj.add(Integer.toString(robotCol));
        sj.add(Integer.toString(robotRow));

        return sj.toString();
    }
}

class Roulette {
    private final int[] roulette;
    private final int gridSize;
    private final int gridLastIdx;
    private static final int CLOCK_WISE = 1;
    private int currentIdx = 0;

    public Roulette(final int[] roulette) {
        this.roulette = roulette;
        gridSize = roulette.length;
        gridLastIdx = (gridSize - 1);
    }

    public int turnRoulette(final int dir, final int moveCount) {
        if (dir == CLOCK_WISE) {
            return turnClockwise(moveCount);
        }

        return turnCounterClockwise(moveCount);
    }

    private int turnClockwise(final int moveCount) {
        for (int move = 0; move < moveCount; move++) {
            currentIdx--;
            if (currentIdx == -1) {
                currentIdx = gridLastIdx;
            }
        }

        return roulette[currentIdx];
    }

    private int turnCounterClockwise(final int moveCount) {
        for (int move = 0; move < moveCount; move++) {
            currentIdx++;
            if (currentIdx == gridSize) {
                currentIdx = 0;
            }
        }

        return roulette[currentIdx];
    }
}
