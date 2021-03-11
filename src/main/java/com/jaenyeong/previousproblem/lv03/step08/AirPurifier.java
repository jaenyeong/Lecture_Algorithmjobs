package com.jaenyeong.previousproblem.lv03.step08;

import java.util.Scanner;

public class AirPurifier {
    /*
    공기 청정기 (시간 제한 3초)

    [문제]
    알고리즘잡스 강의실의 쾌적한 공기 상태를 보장하기 위해 공기청정기를 설치했다.
    어느날, 공기청정기가 제대로 동작하는지 궁금해진 영진은 현재 강의실의 공기상태를 알아내는 프로그램을 만들었다.
    편의상 강의실 내부는 격자판으로 그려지고, 각 칸에는 공기의 나쁨 정도를 숫자로 표현하도록 제작되었다.
    매초 확산이 먼저 일어나고 공기청정기가 작동한다 가정하자.
    [air_purifier_1.png]
    해당 프로그램에서 공기의 확산 특징은 다음과 같다.
    - 공기는 현재 위치에서 x, y 좌표와의 거리가 1이상 k 이하인 위치들로 확산된다.
      두 점 (x1, y1), (x2, y2)의 거리는 |x1 - x2| + |y1 - y2|로 구한다.
      만약 확산할 좌표가 존재하지 않거나, 공기청정기 위치일 경우 해당 위치로는 퍼지지 않는다.
    - 공기의 나쁨 정도가 2k2 + 2k + 1보다 작을 경우 확산이 일어나지 않는다.
    - 공기의 확산이 일어났을때, 퍼지는 나쁨 정도는 기준 위치에서 (2kk) + (2*k) + 1로 나눈 몫이다.
    - 확산을 일으킨 후의 기준 위치의 공기의 나쁨 정도는 (현재 공기의 나쁨 정도) - (퍼진 공기의 나쁨 정도)*(확산된 칸의 수)이다.
    - 하나의 칸에 두 공기가 확산되어 왔을 경우, 확산된 나쁨 정도를 합한다.
    - 공기는 1초에 한 번씩 확산이 된다.
    [air_purifier_2.png]
    공기청정기의 동작 방식 및 특징은 다음과 같다.
    - 공기청정기는 항상 강의실 오른쪽 벽면에 설치되어있고, 그 크기는 격자판 기준 가로 1칸, 세로 2칸을 차지한다.
    - 공기청정기는 총 2곳에서 바람을 내뿜고 있는데,
      세로 칸 중 윗 칸에서는 시계 방향으로 공기를 순환시키는 바람이 나오고,
      아랫칸에서는 반시계 방향으로 바람이 나온다.
    - 바람에 의해 공기가 이동하며, 격자판 기준 1초당 이동하는 칸 수는 1칸이다.
    - 바람의 시작점은 공기청정기의 위치이며, 바람은 벽면을 타고 이동한다.
    - 공기가 바람을 타고 공기청정기의 위치에 도착할 경우, 정화되어 다시 바람을 타고 나오게 된다.
      이때, 정화된 공기의 나쁨 정도는 0이다.
    [air_purifier_3.png]
    강의실 정보가 주어졌을때, S초가 지난 후 공기의 나쁨 정도를 모두 합한 값을 알아내보자.
    공기청정기는 항상 공기가 확산된 후에 동작한다.
    예를 들어, [그림 1]의 상황에서 1초가 지난 모습은 다음과 같다.
    [air_purifier_4.png]

    [입력]
    첫 번째 줄에 테스트 케이스의 개수 T가 주어진다.
    다음 줄부터 T개의 테스트 케이스에 대한 정보가 주어진다.
    각각의 테스트 케이스의 첫 번째 줄에 강의실 크기 R, C 와 시간 S, 퍼지는 거리 K 가 공백을 통해 구분하여 주어진다.
    두 번째 줄부터 R 개의 줄에 걸쳐 강의실 공기 정보가 주어진다.
    각 줄에는 C 개의 숫자가 공백을 통해 구분하여 주어지며, 각 숫자는 -1 이상 1000 이하의 정수이다.
    -1은 공기청정기를 뜻하며 세로로 인접하게 2개 주어진다.
    또한, 첫번째 행과 마지막 행에 -1이 존재할 수 없다.
    (5 <= R, C <= 100, 1 <= S <= 1000, 1 <= K <= 3)

    [출력]
    각 테스트 케이스에 대해 S초가 지난 후 공기의 나쁨 정도를 모두 합한 값을 출력한다.
    각 테스트 케이스의 출력 양식은 "#t r"이다.
    t는 테스트 케이스의 번호이며, 1부터 시작한다.
    r은 문제에 대한 결과값을 뜻한다.

    [예제 입력]
    2
    5 7 1 1
    0 9 0 8 0 0 0
    0 0 0 0 0 0 0
    0 0 0 0 11 19 -1
    0 17 14 0 13 0 -1
    0 0 0 1 0 0 16
    6 9 4 2
    15 10 0 0 3 0 18 0 14
    20 2 0 0 0 4 0 9 17
    0 0 0 0 0 0 14 12 0
    0 0 0 0 0 12 0 0 -1
    0 0 0 0 0 7 0 0 -1
    0 0 3 17 5 0 0 15 0
    [예제 출력]
    #1 95
    #2 153

     */

    private static final Scanner SC = new Scanner(System.in);
    private static final int AIR_PURIFIER = -1;

    public static void main(String[] args) {
        final int t = SC.nextInt();

        // 테스트 케이스 수 만큼 반복
        for (int i = 1; i <= t; i++) {
            final int rowSize = SC.nextInt();
            final int colSize = SC.nextInt();

            final int timeSecond = SC.nextInt();
            final int distLimit = SC.nextInt();

            // 강의실 정보를 입력받음
            final int[][] originRoom = inputRoomInfo(rowSize, colSize);

            // 정해진 작업(proliferation, ventilation)을 주어진 시간 만큼 반복
            for (int repeat = 0; repeat < timeSecond; repeat++) {
                // 복사 배열 초기화
                final int[][] copyRoom = new int[rowSize][colSize];
                for (int copyRow = 0; copyRow < rowSize; copyRow++) {
                    System.arraycopy(originRoom[copyRow], 0, copyRoom[copyRow], 0, colSize);
                }

                // 확산 먼저 발생 (배열 원소 값 계산)
                processProliferation(rowSize, colSize, distLimit, originRoom, copyRoom);

                // 공기 청정기 작동 (배열 원소 이동)
                // 공기 청정기 위치 찾기
                int topAirRow = -2;
                int bottomAirRow = -2;
                for (int row = 0; row < rowSize; row++) {
                    if (originRoom[row][colSize - 1] == AIR_PURIFIER) {
                        topAirRow = row;
                        bottomAirRow = topAirRow + 1;
                        break;
                    }
                }

                clockWise(colSize, originRoom, topAirRow);
                counterClockWise(rowSize, colSize, originRoom, bottomAirRow);
            }

            final int sum = getSum(rowSize, colSize, originRoom);

            System.out.println("#" + i + " " + sum);
        }

        SC.close();
    }

    private static int[][] inputRoomInfo(int rowSize, int colSize) {
        final int[][] room = new int[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                room[row][col] = SC.nextInt();
            }
        }

        return room;
    }

    private static void processProliferation(final int rowSize, final int colSize, final int distLimit,
                                             final int[][] originRoom, final int[][] copyRoom) {
        final int figure = getFigure(distLimit);

        // 각 위치마다 확산 처리
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                // 공기 청정기 위치인 경우 통과
                if (AIR_PURIFIER == originRoom[row][col]) {
                    continue;
                }

                if (originRoom[row][col] < figure) {
                    continue;
                }

                // 전염 수치 추출
                final int curFigure = originRoom[row][col] / figure;
                if (curFigure < 1) {
                    continue;
                }

                // 확산할 유효 범위 지정
                final int startRow = Math.max((row - distLimit), 0);
                final int endRow = Math.min((row + distLimit), (rowSize - 1));
                final int startCol = Math.max((col - distLimit), 0);
                final int endCol = Math.min((col + distLimit), (colSize - 1));

                int currentValue = originRoom[row][col];

                for (int nextRow = startRow; nextRow <= endRow; nextRow++) {
                    for (int nextCol = startCol; nextCol <= endCol; nextCol++) {
                        if (currentValue < curFigure) {
                            continue;
                        }

                        // 공기 청정기 위치 통과
                        if (AIR_PURIFIER == originRoom[nextRow][nextCol]) {
                            continue;
                        }

                        // 거리가 유효 거리보다 먼 경우 통과
                        final int dist = Math.abs(row - nextRow) + Math.abs(col - nextCol);
                        if (dist > distLimit) {
                            continue;
                        }

                        // 현재 위치 통과
                        if ((nextRow == row) && (nextCol == col)) {
                            continue;
                        }

                        copyRoom[nextRow][nextCol] += curFigure;
                        currentValue -= curFigure;
                    }
                }

                copyRoom[row][col] -= (originRoom[row][col] - currentValue);
            }
        }

        // copy
        for (int row = 0; row < rowSize; row++) {
            System.arraycopy(copyRoom[row], 0, originRoom[row], 0, colSize);
        }
    }

    private static int getFigure(final int k) {
        return (2 * (k * k)) + (2 * k) + 1;
    }

    private static void clockWise(final int colSize, final int[][] originRoom, final int topAirRow) {
        // right
        for (int row = topAirRow - 2; row >= 0; row--) {
            originRoom[row + 1][colSize - 1] = originRoom[row][colSize - 1];
        }
        // top
        System.arraycopy(originRoom[0], 0, originRoom[0], 1, colSize - 1);
        // left
        for (int row = 1; row <= topAirRow; row++) {
            originRoom[row - 1][0] = originRoom[row][0];
        }
        // bottom
        System.arraycopy(originRoom[topAirRow], 1, originRoom[topAirRow], 0, colSize - 2);

        originRoom[topAirRow][colSize - 2] = 0;
    }

    private static void counterClockWise(int rowSize, int colSize, int[][] originRoom, int bottomAirRow) {
        // right
        for (int row = (bottomAirRow + 2); row <= (rowSize - 1); row++) {
            originRoom[row - 1][colSize - 1] = originRoom[row][colSize - 1];
        }
        // bottom
        System.arraycopy(originRoom[rowSize - 1], 0, originRoom[rowSize - 1], 1, (colSize - 1));
        // left
        for (int row = (rowSize - 2); row >= bottomAirRow; row--) {
            originRoom[row + 1][0] = originRoom[row][0];
        }
        // top
        System.arraycopy(originRoom[bottomAirRow], 1, originRoom[bottomAirRow], 0, (colSize - 2));

        originRoom[bottomAirRow][colSize - 2] = 0;
    }

    private static int getSum(final int n, final int m, final int[][] originRoom) {
        int sum = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                final int data = originRoom[row][col];
                if (data > 0) {
                    sum += data;
                }
            }
        }
        return sum;
    }
}
