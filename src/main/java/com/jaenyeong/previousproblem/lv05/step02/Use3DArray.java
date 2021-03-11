package com.jaenyeong.previousproblem.lv05.step02;

import java.util.*;

public class Use3DArray {
    /*
    3차원 배열 사용

    [문제]
    위에서 볼 때 세로 길이 N, 가로 길이 M 인 창고에 택배를 쌓으려고 한다.
    편의상 택배는 가로, 세로, 높이 모두 1 이다.
    가장 왼쪽 위 칸의 좌표를 (1, 1) 이라고 한다.
    총 q 번의 명령이 주어졌을때, 이를 처리한 결과를 출력하는 프로그램을 작성하시오.
    각 명령은 다음 중 하나이다.
    [1] 특정 위치의 가장 위에 택배를 하나 올리는 명령
    [2] 특정 위치의 가장 아래에 택배를 하나 끼워 넣는 명령
    [3] 특정 위치의 가장 위 택배를 제거하는 명령
    [4] 특정 위치의 가장 위 택배 무게를 출력하는 명령
    각 명령 형태는 다음과 같다.
    [1] 1 y x w 가 명령으로 주어졌을 경우, (y, x) 위치의 가장 위에 무게 w 인 택배를 하나 올린다.
    [2] 2 y x w 가 명령으로 주어졌을 경우, (y, x) 위치의 가장 아래에 무게 w 인 택배를 하나 끼워 넣는다.
    [3] 3 y x 가 명령으로 주어졌을 경우, (y, x) 위치의 가장 위 택배를 제거한다.
    [4] 4 y x 가 명령으로 주어졌을 경우, (y, x) 위치의 가장 위 택배 무게를 출력한다.
    3번, 4번 명령에서 주어진 위치(y, x)에 택배가 없는 경우는 주어지지 않는다고 가정한다.

    [입력]
    첫 줄에 N, M, q 가 주어진다.
    두 번째 줄부터 q 줄에 걸쳐 명령이 주어진다.
    명령은 1번부터 4번까지 존재한다.
    명령의 번호를 t라고 할 때, t가 1 혹은 2일 경우 t y x w 의 형태로 주어진다.
    만약 t가 3 혹은 4일 경우 t y x 의 형태로 주어진다.
    입력으로 주어지는 숫자는 모두 정수이다.
    (3 <= N, M, q <= 100, 1 <= t <= 4, 3 <= y <= N, 3 <= x <= M, 1 <= w <= 20)

    [출력]
    각 명령에 대한 수행 결과를 한 줄에 하나씩 출력한다.

    [예제 입력]
    4 5 10
    2 1 1 1
    2 1 1 2
    4 1 1
    1 1 1 3
    4 1 1
    2 4 5 5
    1 4 5 9
    1 4 5 8
    3 4 5
    4 4 5
    [예제 출력]
    1
    3
    9

    [예제 입력]
    3 4 9
    2 1 2 4
    1 1 2 3
    2 2 2 5
    4 1 2
    4 2 2
    3 1 2
    4 1 2
    2 1 2 9
    4 1 2
    [예제 출력]
    3
    5
    4
    4

    [예제 입력]
    4 5 20
    2 1 2 9
    1 1 2 15
    4 1 2
    1 1 2 8
    2 1 2 6
    1 1 2 5
    2 1 2 3
    2 1 2 4
    2 2 1 5
    4 1 2
    3 1 2
    4 1 2
    3 1 2
    4 1 2
    3 1 2
    4 1 2
    3 1 2
    4 1 2
    3 1 2
    4 1 2
    [예제 출력]
    15
    5
    8
    15
    9
    6
    3

    [설명]
    아래는 입출력 예 1 번에 대한 그림이다.
    [use_3d_array.png]

     */

    private static final Scanner SC = new Scanner(System.in);
    private static final int PUSH_TOP = 1;
    private static final int PUSH_BOTTOM = 2;
    private static final int POP_TOP = 3;

    public static void main(String[] args) {
        final int rowSize = SC.nextInt();
        final int colSize = SC.nextInt();
        final int times = SC.nextInt();

        final Space[][] spaces = new Space[rowSize + 1][colSize + 1];

        for (int row = 1; row <= rowSize; row++) {
            for (int col = 1; col <= colSize; col++) {
                spaces[row][col] = new Space();
            }
        }

        for (int time = 0; time < times; time++) {
            final int command = SC.nextInt();
            final int row = SC.nextInt();
            final int col = SC.nextInt();

            if (command == PUSH_TOP || command == PUSH_BOTTOM) {
                final int weight = SC.nextInt();

                if (command == PUSH_TOP) {
                    spaces[row][col].pushTop(weight);
                    continue;
                }

                spaces[row][col].pushBottom(weight);
                continue;
            }

            if (command == POP_TOP) {
                spaces[row][col].popTop();
                continue;
            }

            System.out.println(spaces[row][col].getLast());
        }

        SC.close();
    }
}

class Space {
    private final Deque<Integer> boxes;

    Space() {
        this.boxes = new ArrayDeque<>();
    }

    public void pushTop(final int height) {
        boxes.add(height);
    }

    public void pushBottom(final int height) {
        boxes.push(height);
    }

    public void popTop() {
        boxes.pollLast();
    }

    public int getLast() {
        if (boxes.size() <= 0) {
            return 0;
        }

        return boxes.getLast();
    }
}
