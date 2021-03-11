package com.jaenyeong.previousproblem.lv04.step03;

import java.util.Scanner;

public class LargeMove1D {
    /*
    1차원 큰 이동

    [문제]
    1 번부터 n 번까지 총 n 개의 공간이 있는 1차원 배열의 x 번 위치에 사람이 있다.
    이 사람은 초기 방향 d 로 1 초에 한 칸 씩 이동하는데, 배열의 끝에 도착하면 다음 이동 시 뒤로 돌아서 반대 방향으로 이동한다.
    t 초 뒤 이 사람의 위치와 방향을 출력하여라.
    d 가 0 이면 좌표가 감소하는 방향, d 가 1이면 좌표가 증가하는 방향으로 이동한다.
    [large_move_1d.png]

    [입력]
    첫 줄에 정수 n, x, d, t 가 주어진다.
    (3 <= n <= 1,000,000,000, 1 <= x <= n, 0 <= d <= 1, 0 <= t <= 1,000,000,000)

    [출력]
    t 초 후 사람의 위치와 방향을 출력한다.

    [예제 입력]
    6 4 0 5
    [예제 출력]
    3 1

    [예제 입력]
    5131 555 0 35491
    [예제 출력]
    4158 1

    [예제 입력]
    21354 3124 0 861231
    [예제 출력]
    3989 1

    [예제 입력]
    463571372 78942314 1 1000000000
    [예제 출력]
    151799572 1

     */

    private static final Scanner SC = new Scanner(System.in);
    private static final int[] DIRECTIONS = {-1, 1};
    private static final int FIRST = 1;
    private static final int LEFT = 0;
    private static final int RIGHT = 1;

    public static void main(String[] args) {
        final int n = SC.nextInt();
        int currPosition = SC.nextInt();
        int dir = SC.nextInt();
        int time = SC.nextInt();

        SC.close();

        time %= (n * 2) - 2;

        while (true) {
            final int needToMoveStep = currPosition + (time * DIRECTIONS[dir]);

            if (FIRST <= needToMoveStep && needToMoveStep <= n) {
                break;
            }

            if (needToMoveStep < FIRST) {
                time -= (currPosition - 1);
                currPosition = FIRST;
                dir = RIGHT;

                continue;
            }

            if (needToMoveStep > n) {
                time -= (n - currPosition);
                currPosition = n;
                dir = LEFT;
            }
        }

        currPosition += (time * DIRECTIONS[dir]);

        System.out.printf("%d %d", currPosition, dir);
    }
}
