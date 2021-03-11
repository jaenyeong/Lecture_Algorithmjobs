package com.jaenyeong.previousproblem.lv02.step08;

import java.util.Scanner;

public class SpinningTower {
    /*
    회전탑

    [문제]
    N개의 층에 각각 M개의 숫자가 쓰여있다.
    회전탑은 층에 존재하는 숫자를 회전시킬 수 있다.
    회전 후 인접한 부분의 숫자가 같을 경우 회전판에서 숫자를 지운다.
    [spinning_tower_1.png]
    회전탑의 회전은 시계 방향 혹은 반시계 방향으로 가능하다.
    한 번 회전시 회전되는 층은 다수가 될 수 있다.
    회전시켜야할 층의 번호와 배수관계에 있는 회전판들은 모두 회전된다.
    또한, 회전은 동시에 일어난다.
    층을 회전한 뒤 회전한 어떠한 층에서도 지워지는 숫자가 없을 경우,
    모든 층에 존재하는 숫자들의 평균을 구해 각각의 숫자와 비교한다. (평균값의 소숫점 아래는 버린다)
    만약 평균보다 크면 -1을, 평균보다 작으면 +1을 더해준다.
    앞서 말한 처리를 한 다음엔 다시 제거가 일어나는 것이 아닌 다음 회전이 일어난다.
    [spinning_tower_2.png]
    [spinning_tower_3.png]
    회전탑의 정보와 회전시키는 정보가 주어졌을때, 회전을 마친 뒤 탑에 남아있는 숫자들의 합을 구해보자.

    [입력]
    첫 번째 줄에 테스트 케이스의 개수 T가 주어진다.
    다음 줄부터 T개의 테스트 케이스에 대한 정보가 주어진다.
    각각의 테스트 케이스의 첫 번째 줄에 회전판의 개수 N,
    각각의 회전판에 쓰여진 숫자 개수 M,
    회전 횟수 K가 공백을 통해 구분하여 주어진다.
    두 번째 줄부터 N개의 줄에 걸쳐 1층부터 N층까지 각 층의 회전판에 쓰여진 숫자가 공백을 통해 구분하여 주어진다.
    주어진 순서대로 인접해있으며, 첫 번째 숫자가 12시 방향의 바로 오른쪽에 적힌다.
    회전판에 적히는 숫자는 100을 넘지않는 자연수다.
    바로 다음 줄부터 K개의 줄에 걸쳐 회전 정보가 공백을 통해 구분하여 주어진다.
    각 줄에 주어지는 회전 정보는 회전시킬 층의 번호 a, 방향 d, 회전시킬 칸 수 c 순서로 주어진다.
    회전 방향은 0과 1로 주어지며, 0은 시계 방향, 1은 반시계 방향이다. (3 <= N, M <= 50, 1 <= K <= 50, 1 <= a <= N, 1 <= c <= 50)

    [출력]
    각 테스트 케이스에 대해 회전을 마친 뒤 회전탑에 남은 숫자들의 합을 출력한다.
    각 테스트 케이스의 출력 양식은 "#t r"이다. t는 테스트 케이스의 번호이며, 1부터 시작한다.
    r은 문제에 대한 결과값을 뜻한다.

    [예제 입력]
    5
    4 5 2
    4 3 6 1 3
    3 2 6 3 2
    6 5 1 9 7
    2 9 8 3 8
    2 0 1
    1 0 1
    4 5 2
    4 3 6 1 3
    3 2 6 3 2
    6 5 1 9 7
    2 9 8 3 8
    2 1 2
    1 0 1
    3 7 5
    7 4 9 2 9 5 7
    9 3 5 6 5 9 3
    4 4 3 9 7 4 2
    3 1 8
    2 1 1
    1 0 6
    1 1 7
    3 0 8
    9 5 4
    10 7 9 9 7
    5 2 8 2 3
    7 4 9 10 4
    9 6 2 4 4
    9 10 10 5 5
    8 5 4 2 4
    9 5 4 10 6
    8 6 8 9 10
    10 9 2 8 9
    7 1 10
    5 1 2
    2 0 9
    4 0 8
    7 7 1
    1 1 2 6 2 7 6
    4 9 9 7 9 1 1
    4 8 3 5 6 9 5
    9 1 2 6 1 6 4
    7 1 2 2 1 6 2
    5 10 1 3 8 7 9
    3 10 2 9 3 2 2
    6 0 10
    [예제 출력]
    #1 76
    #2 74
    #3 49
    #4 133
    #5 173

     */

    private static final Scanner SC = new Scanner(System.in);
    private static final int EMPTY = -1;
    // 시계방향
    private static final int CLOCK_WISE = 0;
    private static final int[] NEXT_ROW = {-1, 1, 0, 0};
    private static final int[] NEXT_COL = {0, 0, -1, 1};

    public static void main(String[] args) {
        final int t = SC.nextInt();

        for (int testCase = 1; testCase <= t; testCase++) {
            final int n = SC.nextInt(); // 회전탑 총 층수
            final int m = SC.nextInt(); // 각 회전판의 숫자 개수
            final int k = SC.nextInt(); // 회전판을 회전하는 횟수

            // 회전탑 입력
            final int[][] tower = new int[n + 1][m];
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j < m; j++) {
                    tower[i][j] = SC.nextInt();
                }
            }

            // 주어진 수만큼 탑의 회전판 회전
            for (int i = 0; i < k; i++) {
                final int startFloor = SC.nextInt(); // 회전시킬 층
                final int direction = SC.nextInt();  // 회전시킬 방향
                final int move = SC.nextInt();       // 회전 칸 수

                turn(tower, n, direction, startFloor, move);

                // 인접한 칸의 같은 숫자가 있다면 제거
                boolean deleteSameNumber = isDeleteSameNumber(n, m, tower);

                // 값을 지운 경우
                if (deleteSameNumber) {
                    continue;
                }

                // 타워 내 모든 숫자의 값을 합산하여 평균 획득
                final int sum = getSumTower(n, m, tower);
                final int avg = (sum / getValidNumberCount(n, m, tower));

                // 모든 값을 평균을 기준으로 작으면 1을 더하고 크면 1을 뺌
                plusAndMinusTheNumber(n, m, tower, avg);
            }

            // 남은 숫자들의 합 출력 (예 : #1 76)
            System.out.println("#" + testCase + " " + getSumTower(n, m, tower));
        }

        SC.close();
    }

    private static void turn(final int[][] tower, final int n, final int direction, final int start, final int move) {
        // 해당 층부터 꼭대기 층까지 반복
        for (int i = start; i <= n; i++) {
            // 해당 층의 배수가 되는 층만 회전
            if ((i % start) != 0) {
                continue;
            }

            // 주어진 수만큼 회전
            for (int turn = 0; turn < move; turn++) {
                if (direction == CLOCK_WISE) {
                    turnClockWise(tower[i]);
                    continue;
                }

                turnCounterClockWise(tower[i]);
            }
        }
    }

    private static void turnClockWise(final int[] floor) {
        final int floorSize = floor.length;

        final int lastIdx = floorSize - 1;
        final int temp = floor[lastIdx];

        System.arraycopy(floor, 0, floor, 1, lastIdx);
        floor[0] = temp;
    }

    private static void turnCounterClockWise(final int[] floor) {
        final int floorSize = floor.length;

        final int lastIdx = floorSize - 1;
        final int temp = floor[0];

        System.arraycopy(floor, 1, floor, 0, lastIdx);
        floor[lastIdx] = temp;
    }

    private static boolean isDeleteSameNumber(final int n, final int m, final int[][] tower) {
        boolean deleteSameNumber = false;

        final boolean[][] remove = new boolean[n + 1][m];

        // 인접 숫자 체크하여 같은 숫자가 있다면 지우고 반환
        for (int row = 1; row <= n; row++) {
            for (int col = 0; col < m; col++) {
                if (tower[row][col] == EMPTY) {
                    continue;
                }

                // 상하좌우 확인
                for (int dir = 0; dir < 4; dir++) {
                    int nextRow = row + NEXT_ROW[dir];
                    int nextCol = col + NEXT_COL[dir];

                    if ((0 >= nextRow) || (nextRow > n)) {
                        continue;
                    }

                    // 각 회전판은 둥글기 때문에 유효 범위를 벗어난 경우 연결 처리
                    if (nextCol < 0) {
                        nextCol = (m - 1);
                    }
                    if (nextCol >= m) {
                        nextCol = 0;
                    }

                    if (tower[row][col] == tower[nextRow][nextCol]) {
                        remove[row][col] = true;
                        deleteSameNumber = true;
                        break;
                    }
                }
            }
        }

        for (int row = 1; row <= n; row++) {
            for (int col = 0; col < m; col++) {
                if (remove[row][col]) {
                    tower[row][col] = EMPTY;
                }
            }
        }

        return deleteSameNumber;
    }

    private static int getSumTower(final int n, final int m, final int[][] tower) {
        int sum = 0;
        for (int row = 1; row <= n; row++) {
            for (int col = 0; col < m; col++) {
                if (tower[row][col] <= 0) {
                    continue;
                }
                sum += tower[row][col];
            }
        }
        return sum;
    }

    private static int getValidNumberCount(final int n, final int m, final int[][] tower) {
        int count = 0;
        for (int row = 1; row <= n; row++) {
            for (int col = 0; col < m; col++) {
                if (tower[row][col] != EMPTY) {
                    count++;
                }
            }
        }

        return count;
    }

    private static void plusAndMinusTheNumber(final int n, final int m, final int[][] tower, final int avg) {
        for (int row = 1; row <= n; row++) {
            for (int col = 0; col < m; col++) {
                if (tower[row][col] == EMPTY) {
                    continue;
                }

                if (avg < tower[row][col]) {
                    tower[row][col]--;
                    continue;
                }

                if (avg > tower[row][col]) {
                    tower[row][col]++;
                }
            }
        }
    }
}
