package com.jaenyeong.previousproblem.lv05.step05;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Push3 {
    /*
    밀기 알고리즘3

    [문제]
    위에서 볼 때 세로 길이 N, 가로 길이 M 인 창고에 택배를 쌓으려고 한다.
    편의상 택배는 가로, 세로, 높이 모두 1 이다. 가장 왼쪽 위 칸의 좌표를 (1, 1) 이라고 한다.
    총 q 번의 명령이 주어졌을때, 이를 처리한 결과를 출력하는 프로그램을 작성하시오.
    각 명령은 다음 중 하나이다.
    [1] 특정 위치의 가장 위에 택배를 c 개 올리는 명령
    [2] 특정 위치의 가장 아래에 택배를 c 개 끼워 넣는 명령
    [3] 특정 위치의 가장 위 택배를 c 개 제거하는 명령
    [4] 특정 위치의 가장 위 택배 무게를 출력하는 명령
    각 명령 형태는 다음과 같다.
    [1] 1 y x w c 가 명령으로 주어졌을 경우, (y, x) 위치의 가장 위에 무게 w 인 택배를 c 개 올린다.
    [2] 2 y x w c 가 명령으로 주어졌을 경우, (y, x) 위치의 가장 아래에 무게 w 인 택배를 c 개 끼워 넣는다.
    [3] 3 y x c 가 명령으로 주어졌을 경우, (y, x) 위치의 가장 위 택배를 제거한다.
    [4] 4 y x 가 명령으로 주어졌을 경우, (y, x) 위치의 가장 위 택배 무게를 출력한다.
    3 번, 4 번 명령에서 주어진 위치 (y, x) 에 택배가 없는 경우는 주어지지 않는다고 가정한다.

    [입력]
    첫 줄에 N, M, q 가 주어진다.
    두 번째 줄부터 q 줄에 걸쳐 명령이 주어진다.
    명령은 1 번부터 4 번까지 존재한다.
    명령의 번호를 t 라고 할 때, t 가 1 혹은 2 일 경우 t y x w c 의 형태로 주어진다.
    만약 t 가 3 일 경우 t y x c 로, 4 일 경우 t y x 의 형태로 주어진다.
    입력으로 주어지는 숫자는 모두 정수이다.
    (3 <= N, M, q <= 100, 1 <= t <= 4, 3 <= y <= N, 3 <= x <= M, 1 <= w <= 20, 1 <= c <= 10)

    [출력]
    각 명령에 대한 수행 결과를 한 줄에 하나씩 출력한다.

    [예제 입력]
    4 5 10
    2 1 1 1 1
    2 1 1 2 2
    4 1 1
    1 1 1 3 1
    4 1 1
    3 1 1 2
    4 1 1
    2 4 5 5 3
    1 4 5 9 2
    4 4 5
    [예제 출력]
    1
    3
    2
    9

    [예제 입력]
    3 4 11
    2 1 2 4 2
    1 1 2 3 3
    2 2 2 5 2
    4 1 2
    4 2 2
    3 1 2 1
    4 1 2
    2 1 2 9 1
    4 1 2
    3 1 2 4
    4 1 2
    [예제 출력]
    3
    5
    3
    3
    9

    [예제 입력]
    4 5 20
    2 1 2 9 2
    1 1 2 15 3
    4 1 2
    1 1 2 8 1
    2 1 2 6 2
    1 1 2 5 1
    2 1 2 3 3
    2 1 2 4 4
    2 2 1 5 2
    4 1 2
    3 1 2 3
    4 1 2
    3 1 2 6
    4 1 2
    3 1 2 1
    4 1 2
    3 1 2 2
    4 1 2
    3 1 2 1
    4 1 2
    [예제 출력]
    15
    5
    15
    3
    3
    4
    4

     */

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        final int rowSize = SC.nextInt();
        final int colSize = SC.nextInt();

        final int numberOfCommand = SC.nextInt();

        final BoxLoader[][] boxes = new BoxLoader[rowSize + 1][colSize + 1];

        for (int row = 1; row <= rowSize; row++) {
            for (int col = 1; col <= colSize; col++) {
                boxes[row][col] = new BoxLoader();
            }
        }

        for (int boxLoading = 0; boxLoading < numberOfCommand; boxLoading++) {
            final int command = SC.nextInt();
            final int row = SC.nextInt();
            final int col = SC.nextInt();

            if (command == 1 || command == 2) {
                final int weight = SC.nextInt();
                final int loopCount = SC.nextInt();

                if (command == 1) {
                    boxes[row][col].addTop(weight, loopCount);
                    continue;
                }

                boxes[row][col].addBottom(weight, loopCount);
                continue;
            }

            if (command == 3) {
                final int loopCount = SC.nextInt();
                boxes[row][col].pop(loopCount);
                continue;
            }

            System.out.println(boxes[row][col].getTopValue());
        }

        SC.close();
    }
}

class BoxLoader {
    private final Deque<Integer> boxes = new ArrayDeque<>();

    public void addTop(final int boxWeight, final int putCount) {
        for (int i = 0; i < putCount; i++) {
            if (boxes.size() > 100) {
                return;
            }

            boxes.add(boxWeight);
        }
    }

    public void addBottom(final int boxWeight, final int putCount) {
        for (int i = 0; i < putCount; i++) {
            if (boxes.size() > 100) {
                return;
            }

            boxes.push(boxWeight);
        }
    }

    public void pop(final int popCount) {
        for (int i = 0; i < popCount; i++) {
            if (boxes.size() <= 0) {
                return;
            }

            boxes.pollLast();
        }
    }

    public int getTopValue() {
        if (boxes.size() <= 0) {
            return 0;
        }

        return boxes.getLast();
    }
}
