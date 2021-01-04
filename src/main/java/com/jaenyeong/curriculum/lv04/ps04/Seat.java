package com.jaenyeong.curriculum.lv04.ps04;

import java.util.Scanner;

public class Seat {
    /*
    seat

    [문제]
    어떤 공연장에는 가로로 R개, 세로로 C개의 좌석이 R×C 격자형으로 배치되어 있다.
    각 좌석의 번호는 해당 격자의 좌표 (x,y)로 표시된다.
    예를 들어보자.
    아래 그림은 가로 7개, 세로 6개 좌석으로 구성된 7×6격자형 좌석배치를 보여주고 있다.
    그림에서 각 단위 사각형은 개별 좌석을 나타내며, 그 안에 표시된 값 (x,y)는 해당 좌석의 번호를 나타낸다.
    가장 왼쪽 아래의 좌석번호는 (1,1)이며, 가장 오른쪽 위 좌석의 번호는 (7,6)이다.
    [seat_1.png]
    이 공연장에 입장하기 위하여 많은 사람이 대기줄에 서있다.
    기다리고 있는 사람들은 제일 앞에서부터 1, 2, 3, 4, . 순으로 대기번호표를 받았다.
    우리는 대기번호를 가진 사람들에 대하여 (1,1)위치 좌석부터 시작하여 시계방향으로 돌아가면서 비어있는 좌석에 관객을 순서대로 배정한다.
    이것을 좀 더 구체적으로 설명하면 다음과 같다.
    먼저 첫 번째 사람, 즉 대기번호 1인 사람은 자리 (1,1)에 배정한다.
    그 다음에는 위쪽 방향의 좌석으로 올라가면서 다음 사람들을 배정한다.
    만일 더 이상 위쪽 방향으로 빈 좌석이 없으면 오른쪽으로 가면서 배정한다.
    오른쪽에 더 이상 빈자리가 없으면 아래쪽으로 내려간다.
    그리고 아래쪽에 더 이상 자리가 없으면 왼쪽으로 가면서 남은 빈 좌석을 배정한다.
    이 후 왼쪽으로 더 이상의 빈 좌석이 없으면 다시 위쪽으로 배정하고, 이 과정을 모든 좌석이 배정될 때까지 반복한다.
    아래 그림은 7×6공연장에서 대기번호 1번부터 42번까지의 관객이 좌석에 배정된 결과를 보여주고 있다.
    [seat_2.png]
    여러분은 공연장의 크기를 나타내는 자연수 R과 C가 주어져 있을 때, 대기 순서가 K인 관객에게 배정될 좌석 번호 (x,y)를 찾는 프로그램을 작성해야 한다.

    [입력]
    첫 줄에는 공연장의 격자 크기를 나타내는 정수 R과 C가 하나의 공백을 사이에 두고 차례대로 주어진다.
    두 값의 범위는 5 ≤ R, C ≤ 1,000이다.
    그 다음 줄에는 어떤 관객의 대기번호 K가 주어진다. 단 1 <= K <= 100,000,000이다.

    [출력]
    입력으로 제시된 대기번호 K인 관객에게 배정될 좌석번호 (x,y)를 구해서 두 값, x와 y를 하나의 공백을 사이에 두고 출력해야 한다.
    만일 모든 좌석이 배정되어 해당 대기번호의 관객에게 좌석을 배정할 수 없는 경우에는 0(숫자 영)을 출력해야 한다.

    [예제 입력]
    7 6
    12
    [예제 출력]
    7 6

     */

    private static final Scanner SC = new Scanner(System.in);
    private static final int TAKE_A_SEAT = 1;

    public static void main(String[] args) {
        final int colSize = SC.nextInt();
        final int rowSize = SC.nextInt();

        final int[][] hall = new int[rowSize][colSize];

        int waitNumber = SC.nextInt();

        SC.close();

        final SeatPosition resultTicketing = ticketing(hall, rowSize, colSize, waitNumber);

        System.out.println(resultTicketing.isSeat() ? resultTicketing : 0);
    }

    private static SeatPosition ticketing(final int[][] hall, final int rowSize, final int colSize, int waitNumber) {
        int rowStart = 0;
        int rowEnd = rowSize - 1;

        int colStart = 0;
        int colEnd = colSize - 1;

        while ((rowStart <= rowEnd) && (colStart <= colEnd)) {
            // 위쪽 방향
            for (int row = rowStart; row <= rowEnd; row++) {
                hall[row][colStart] = TAKE_A_SEAT;
                waitNumber--;
                if (waitNumber <= 0) {
                    return new SeatPosition(true, row, colStart);
                }
            }
            colStart++;

            // 오른쪽 방향
            if (rowStart <= rowEnd) {
                for (int col = colStart; col <= colEnd; col++) {
                    hall[rowEnd][col] = TAKE_A_SEAT;
                    waitNumber--;
                    if (waitNumber <= 0) {
                        return new SeatPosition(true, rowEnd, col);
                    }
                }
            }
            rowEnd--;

            // 아래쪽 방향
            if (colStart <= colEnd) {
                for (int row = rowEnd; row >= rowStart; row--) {
                    hall[row][colEnd] = TAKE_A_SEAT;
                    waitNumber--;
                    if (waitNumber <= 0) {
                        return new SeatPosition(true, row, colEnd);
                    }
                }
            }
            colEnd--;

            // 왼쪽 방향
            if (rowStart <= rowEnd) {
                for (int col = colEnd; col >= colStart; col--) {
                    hall[rowStart][col] = TAKE_A_SEAT;
                    waitNumber--;
                    if (waitNumber <= 0) {
                        return new SeatPosition(true, rowStart, col);
                    }
                }
            }
            rowStart++;
        }

        return new SeatPosition(false, -1, -1);
    }
}

class SeatPosition {
    private final boolean seat;
    private final int row;
    private final int col;

    public SeatPosition(boolean seat, int row, int col) {
        this.seat = seat;
        this.row = row;
        this.col = col;
    }

    public boolean isSeat() {
        return seat;
    }

    @Override
    public String toString() {
        return (col + 1) + " " + (row + 1);
    }
}