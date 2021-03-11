package com.jaenyeong.swtest.problem.ps01;

import java.util.*;

public class RoboticVacuumCleaner {
    /*
    로봇 청소기 (시간제한 7초)

    [문제]
    민건이는 원룸 청소하기 너무 귀찮아서 로봇청소기를 하나 구매했다.
    로봇 청소기는 원룸 바닥을 돌아다니며, 열심히 바닥에 있는 먼지를 청소한다.
    돈이 없는 민건이는 값싼 로봇 청소기를 구매하였다.
    해당 로봇 청소기는 원룸 구조를 파악하여 똑똑하게 이동하는 것이 아닌 그냥 벽에 부딪히면 튕겨나온 방향으로 움직이는 무식한(?) 청소기이다.
    벽이나 구조물을 만나기 전까지 계속해서 직진만 하며, 벽이나 구조물에 부딪히는 순간 방향을 틀어서 움직인다.
    예를 들어, 벽이나 구조물에 부딪히면 다음과 같이 직각 혹은 반대 방향으로 움직인다.
    [robotic_vacuum_cleaner_1.png]
    구조물은 회전이 가능하며, 현재 <robotic_vacuum_cleaner_1.png>의 왼편에 나오는 구조물의 번호는 1번이다.
    해당 구조물을 시계 방향으로 90° 회전시킬시 2번, 180° - 3번, 270° - 4번, 360°는 구조물 1번과 동일하므로 1번이다.
    <robotic_vacuum_cleaner_1.png>의 오른편에 나오는 구조물은 5번으로 붙혔다.
    그림으로 표현하면 다음과 같다.
    [robotic_vacuum_cleaner_2.png]
    무식한(?) 청소기답게 현재 높이보다 낮은 턱에 빠지면 더 이상 움직일 수 없어서 꺼내줘야한다.
    낮은 턱의 경우 집 구조 상 어디든 존재하고 원룸 구조도에 “-1”로 표기한다.
    또한 특이하게 다른 원룸과 달리 바닥에 최대 5쌍의 워프 장치가 있는데,
    워프 장치 한 쪽으로 갈 경우 다른 한 쪽으로 이동하게 된다.(그래서 바닥에 물건을 떨어뜨리면 찾기가 쉽지않다.)
    이때 워프 장치를 통과한 후 진행방향은 워프 진입 직전의 진행 방향과 동일하다.
    워프를 통과한 후 이동은 반드시 일어난다.
    워프 통과 후 바로 워프를 탈 수는 없다.
    워프 장치는 번호는 6번부터 10번까지 존재하고, 원룸 구조도에 무조건 한 쌍으로 표기되어있다.
    [robotic_vacuum_cleaner_3.png]
    [robotic_vacuum_cleaner_4.png]
    [robotic_vacuum_cleaner_5.png]
    [robotic_vacuum_cleaner_6.png]
    [robotic_vacuum_cleaner_7.png]
    <robotic_vacuum_cleaner_4.png>와 같은 원룸 구조도가 주어졌을 때, 로봇청소기의 이동 사례를 살펴보면 다음과 같다.
    <robotic_vacuum_cleaner_5.png>의 경우 시작지점으로 다시 돌아와서 이동이 종료되었고,
    <robotic_vacuum_cleaner_6.png>의 경우 낮은 턱에 빠져버려서 이동이 종료되었다.
    <robotic_vacuum_cleaner_7.png>의 경우 벽에 부딪혀 튕겨져나와, <robotic_vacuum_cleaner_5.png> 와 동일하게 시작지점으로 돌아와서 이동이 종료되었다.
    <robotic_vacuum_cleaner_5.png>의 이동한 칸의 수는 6 이고, <robotic_vacuum_cleaner_6.png>의 이동한 칸의 수는 9 이다.
    <robotic_vacuum_cleaner_7.png>의 이동한 칸의 수는 4 이다.
    시작 위치는 구조물, 워프 장치, 낮은 턱이 될 수 없다.
    그 이외의 모든 공간에서는 청소를 시작할 수 있다.
    원룸 구조는 N x N m^2 이며, 로봇 청소기는 한 번에 한 칸씩 움직일 수 있다.
    다시 말하면, 1m^2 씩 움직일 수 있다.
    로봇 청소기가 출발한 위치로 다시 돌아오고, 시작한 방향과 동일한 방향을 보고 있을때 작동을 멈춘다.
    혹은 청소 진행 도중에 낮은 턱에 빠져버릴 경우 그대로 작동을 중지한다.
    현재 민건이는 로봇청소기가 얼마나 열심히 일하는지 알고싶다.
    원룸 구조도가 주어지고, 로봇 청소기가 임의의 위치에서 임의의 방향을 보고 이동을 시작하여,
    끝나기까지 한 번이라도 지나간 칸의 수를 D 라고 하였을 때,
    D 의 최댓값을 구해보자! (단, D의 경로에는 낮은 턱이 포함되지 않는다.)

    [입력]
    첫 번째 줄에 테스트 케이스의 개수 T(5 <= T <= 50) 가 주어진다.
    각 테스트 케이스의 첫째 줄에 N(5 <= N <= 100) 이 주어지고, N 개의 줄에 걸쳐 N 개의 숫자가 공백을 통해 구분하여 입력된다.
    각각의 입력되는 숫자는 -1 부터 10까지의 정수이다.
    워프 장치는 최대 5쌍 존재한다.
    낮은 턱은 최대 5개 존재할 수 있으며, 최소 1개 존재한다.

    [출력]
    각 테스트 케이스에 해당하는 결과값을 “#t result” 포맷으로 출력한다. (t는 1부터 T까지의 정수이다)

    [예제 입력]
    4
    5
    0 4 0 0 0
    0 0 3 6 0
    0 2 0 0 5
    0 0 -1 0 0
    0 6 0 1 0
    8
    0 0 1 1 0 0 0 4
    0 0 0 0 0 0 5 0
    0 0 0 3 0 0 0 0
    2 0 0 4 3 4 0 0
    0 0 0 0 0 0 0 0
    0 0 2 0 0 5 0 0
    0 0 5 0 3 0 0 0
    0 0 4 0 0 3 1 0
    4
    1 1 1 1
    1 1 -1 1
    1 -1 0 -1
    1 1 -1 1
    4
    0 0 0 4
    0 0 4 0
    2 -1 5 0
    6 1 5 6
    [예제 출력]
    #1 9
    #2 11
    #3 1
    #4 8

     */

    private static final Scanner SC = new Scanner(System.in);
    private static final int EMPTY = 0;
    private static final int POTHOLE = -1;
    private static final int DEGREE_OF_0 = 1;
    private static final int DEGREE_OF_90 = 2;
    private static final int DEGREE_OF_180 = 3;
    private static final int DEGREE_OF_270 = 4;
    private static final int WALL = 5;
    private static Map<Integer, List<Warp>> warps;

    public static void main(String[] args) {
        final int testCase = scanInputInt();

        for (int numberOfTest = 1; numberOfTest <= testCase; numberOfTest++) {

            // 워프 위치 맵 초기화
            initWarps();

            // 방 정보 입력
            final int roomSize = scanInputInt();
            final int[][] room = scanRoom(roomSize);

            // 청소 시작
            final int maxTheNumberCleanZone = cleanRoom(roomSize, room);

            // 결과 출력
            System.out.println("#" + numberOfTest + " " + maxTheNumberCleanZone);
        }

        SC.close();
    }

    private static void initWarps() {
        warps = null;
        warps = new HashMap<>();
        warps.put(6, new ArrayList<>());
        warps.put(7, new ArrayList<>());
        warps.put(8, new ArrayList<>());
        warps.put(9, new ArrayList<>());
        warps.put(10, new ArrayList<>());
    }

    private static int scanInputInt() {
        return SC.nextInt();
    }

    private static int[][] scanRoom(final int roomSize) {
        final int limitSize = roomSize + 2;
        final int[][] room = new int[limitSize][limitSize];

        for (int row = 0; row < limitSize; row++) {
            for (int col = 0; col < limitSize; col++) {
                room[row][col] = WALL;
            }
        }

        for (int row = 1; row <= roomSize; row++) {
            for (int col = 1; col <= roomSize; col++) {
                final int zone = scanInputInt();

                room[row][col] = zone;
                if (6 <= zone && zone <= 10) {
                    warps.get(zone).add(new Warp(row, col));
                }
            }
        }

        return room;
    }

    private static int cleanRoom(final int roomSize, final int[][] room) {
        // 턴별로 청소를 수행한 영역 중 가장 큰 값
        int maxCleanZone = 0;

        // 방의 타일마다 순회하며 청소 시뮬레이션
        for (int row = 1; row <= roomSize; row++) {
            for (int col = 1; col <= roomSize; col++) {

                // 구조물, 턱, 워프의 경우 시작위치가 될 수 없음
                if (room[row][col] != EMPTY) {
                    continue;
                }

                maxCleanZone = Math.max(maxCleanZone, cleanRoomFromStarting(room, row, col));
            }
        }

        return maxCleanZone;
    }

    private static int cleanRoomFromStarting(final int[][] room, final int startRow, final int startCol) {
        final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        final int up = 0;
        final int down = 1;
        final int left = 2;
        final int right = 3;

        // 해당 위치에서 4방향 중 가장 많이 청소한 영역 수
        int maxCleanZone = 0;

        for (int startDirection = 0; startDirection < 4; startDirection++) {
            final boolean[][] clean = new boolean[room.length][room.length];

            // 시작 위치 청소 처리
            int numberCleanZone = 1;
            clean[startRow][startCol] = true;

            int nextRow = startRow + directions[startDirection][0];
            int nextCol = startCol + directions[startDirection][1];

            // 청소 중 방향
            int runningDirection = startDirection;

            // 다음 위치가 구멍에 빠질 때까지
            while (room[nextRow][nextCol] != POTHOLE) {

                // 시작위치의 시작방향으로 도착한 경우
                if (startRow == nextRow && startCol == nextCol && runningDirection == startDirection) {
                    break;
                }

                final int nextZone = room[nextRow][nextCol];

                // 빈 영역에 도착한 경우
                if (nextZone == EMPTY) {
                    // 청소 처리
                    if (!clean[nextRow][nextCol]) {
                        clean[nextRow][nextCol] = true;
                        numberCleanZone++;
                    }

                    // 다음 영역 이동
                    nextRow += directions[runningDirection][0];
                    nextCol += directions[runningDirection][1];

                    continue;
                }

                // 벽과 부딪히는 경우
                if (nextZone == WALL) {
                    switch (runningDirection) {
                        case up:
                            runningDirection = down;
                            break;
                        case down:
                            runningDirection = up;
                            break;
                        case left:
                            runningDirection = right;
                            break;
                        case right:
                            runningDirection = left;
                            break;
                        default:
                            throw new RuntimeException("Error can not go direction");
                    }

                    nextRow += directions[runningDirection][0];
                    nextCol += directions[runningDirection][1];

                    continue;
                }

                // 회전되는 구조물과 부딪히는 경우
                if (EMPTY < nextZone && nextZone < WALL) {
                    switch (runningDirection) {
                        case up:
                            if (nextZone == DEGREE_OF_0 || nextZone == DEGREE_OF_90) {
                                runningDirection = down;
                                nextRow += directions[runningDirection][0];
                                nextCol += directions[runningDirection][1];
                                continue;
                            }

                            if (!clean[nextRow][nextCol]) {
                                clean[nextRow][nextCol] = true;
                                numberCleanZone++;
                            }

                            if (nextZone == DEGREE_OF_180) {
                                runningDirection = right;
                            } else {
                                runningDirection = left;
                            }

                            nextRow += directions[runningDirection][0];
                            nextCol += directions[runningDirection][1];

                            break;

                        case down:
                            if (nextZone == DEGREE_OF_180 || nextZone == DEGREE_OF_270) {
                                runningDirection = up;
                                nextRow += directions[runningDirection][0];
                                nextCol += directions[runningDirection][1];
                                continue;
                            }

                            if (!clean[nextRow][nextCol]) {
                                clean[nextRow][nextCol] = true;
                                numberCleanZone++;
                            }

                            if (nextZone == DEGREE_OF_0) {
                                runningDirection = left;
                            } else {
                                runningDirection = right;
                            }

                            nextRow += directions[runningDirection][0];
                            nextCol += directions[runningDirection][1];

                            break;

                        case left:
                            if (nextZone == DEGREE_OF_0 || nextZone == DEGREE_OF_270) {
                                runningDirection = right;
                                nextRow += directions[runningDirection][0];
                                nextCol += directions[runningDirection][1];
                                continue;
                            }

                            if (!clean[nextRow][nextCol]) {
                                clean[nextRow][nextCol] = true;
                                numberCleanZone++;
                            }

                            if (nextZone == DEGREE_OF_90) {
                                runningDirection = up;
                            } else {
                                runningDirection = down;
                            }

                            nextRow += directions[runningDirection][0];
                            nextCol += directions[runningDirection][1];

                            break;

                        case right:
                            if (nextZone == DEGREE_OF_90 || nextZone == DEGREE_OF_180) {
                                runningDirection = left;
                                nextRow += directions[runningDirection][0];
                                nextCol += directions[runningDirection][1];
                                continue;
                            }

                            if (!clean[nextRow][nextCol]) {
                                clean[nextRow][nextCol] = true;
                                numberCleanZone++;
                            }

                            if (nextZone == DEGREE_OF_0) {
                                runningDirection = up;
                            } else {
                                runningDirection = down;
                            }

                            nextRow += directions[runningDirection][0];
                            nextCol += directions[runningDirection][1];

                            break;

                        default:
                            throw new RuntimeException("Error can not go direction");
                    }

                    continue;
                }

                // 워프에 도착한 경우
                if (!clean[nextRow][nextCol]) {
                    clean[nextRow][nextCol] = true;
                    numberCleanZone++;
                }

                final List<Warp> warps = RoboticVacuumCleaner.warps.get(nextZone);
                for (Warp warp : warps) {
                    final int warpRow = warp.getRow();
                    final int warpCol = warp.getCol();

                    if (warpRow != nextRow || warpCol != nextCol) {
                        nextRow = warpRow;
                        nextCol = warpCol;

                        if (!clean[nextRow][nextCol]) {
                            clean[nextRow][nextCol] = true;
                            numberCleanZone++;
                        }

                        nextRow += directions[runningDirection][0];
                        nextCol += directions[runningDirection][1];
                        break;
                    }
                }
            }

            maxCleanZone = Math.max(maxCleanZone, numberCleanZone);
        }

        return maxCleanZone;
    }
}

class Warp {
    private final int row;
    private final int col;

    Warp(final int row, final int col) {
        this.row = row;
        this.col = col;
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }
}
