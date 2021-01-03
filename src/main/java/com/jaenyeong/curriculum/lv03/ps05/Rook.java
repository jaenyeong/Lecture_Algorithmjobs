package com.jaenyeong.curriculum.lv03.ps05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Rook {
    /*
    rook

    [문제]
    체스에서 룩이라는 기물은 막혀있지만 않으면 룩의 위치에서 같은 행, 같은 열에 해당하는 칸 중 하나로 한 번 이동할 수 있다.
    단, 특정 칸이 막혀있다면 그 칸에서부터 더 나아갈 수는 없다.
    만약 룩이 아래 그림과 같이 5행 4열에 존재하고 같은 행열에 기물이 없다면 5행이나 4열에 존재하는 칸 중 어디로든 갈 수 있다.
    예를 들어, 5행 2열 혹은 1행 4열로 움직일 수 있다.
    차례에 주어진 이동 횟수는 한 번이므로 이동이 완료되었다면 상대방의 차례로 넘어간다.
    [rook.png]
    체스는 킹만 잡히면 지게 되는 게임이다.
    그 중에서도 알랩이는 룩으로 인해 게임을 지는 것을 극도로 싫어한다!
    주어진 체스판의 상태에서 현재 차례가 상대의 차례일 때, 이번 차례에 알랩이의 킹이 상대방의 룩에게 잡힐 가능성이 있는지 알아보자.

    [입력]
    8줄에 걸쳐 8x8 체스판의 상태가 주어진다.
    이때 0은 기물이 없는 상태이고, 1은 알랩이의 킹을 의미하고, 2는 상대의 룩을 의미하며 3은 그 외 다른 기물들을 의미한다.
    (킹은 하나만 존재하며, 상대의 룩은 최대 2개까지 있을 수 있다. 그 외 기물들은 최대 29개까지 있을 수 있다.)

    [출력]
    킹이 룩에게 잡힐 가능성이 있으면 1, 아니면 0을 출력한다.

    [예제 입력]
    0 3 0 0 0 0 0 0
    3 1 0 0 0 0 2 0
    0 3 0 0 0 0 0 0
    0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0
    [예제 출력]
    1

    [예제 입력]
    0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0
    0 3 3 0 0 0 0 0
    3 0 1 0 3 0 2 0
    0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0
    0 0 0 0 0 0 0 0
    [예제 출력]
    0

     */

    private static final Scanner SCAN = new Scanner(System.in);
    private static final int CHESS_SIZE = 8;
    private static final int EMPTY = 0;
    private static final int MY_KING = 1;
    private static final int YOUR_ROOK = 2;
    // 상하좌우
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        final int[][] chess = new int[CHESS_SIZE][CHESS_SIZE];

        // 상대의 룩은 최대 2개
        final List<RookPosition> yourRooks = new ArrayList<>();

        // 체스판 입력 받기
        inputChess(chess, yourRooks);

        SCAN.close();

        final boolean result = findMyKingByYourRook(chess, yourRooks);

        System.out.println(result ? 1 : 0);
    }

    private static void inputChess(final int[][] chess, final List<RookPosition> yourRooks) {
        for (int row = 0; row < CHESS_SIZE; row++) {
            for (int col = 0; col < CHESS_SIZE; col++) {
                final int chessPiece = SCAN.nextInt();
                chess[row][col] = chessPiece;

                // 상대방 룩의 위치를 캐싱(저장)
                if (chessPiece == YOUR_ROOK) {
                    yourRooks.add(new RookPosition(row, col));
                }
            }
        }
    }

    private static boolean findMyKingByYourRook(final int[][] chess, final List<RookPosition> yourRooks) {
        // 상대방 룩의 위치를 기준으로 행, 열을 모두 탐색하며 나의 왕을 찾음
        for (RookPosition rook : yourRooks) {
            final int rookRow = rook.getRow();
            final int rookCol = rook.getCol();

            // 해당 위치로부터 상하좌우 순으로 반복
            for (int[] dir : DIRECTIONS) {
                int nextRow = rookRow + dir[0];
                int nextCol = rookCol + dir[1];

                // 해당 방향으로 계속 이동
                while (true) {
                    // 다음 지역이 유효하지 않은 범위면 탈출
                    if ((nextRow < 0) || (nextRow >= CHESS_SIZE) || (nextCol < 0) || (nextCol >= CHESS_SIZE)) {
                        break;
                    }

                    // 다음 지역으로 이동
                    final int nextPosition = chess[nextRow][nextCol];

                    // 내 왕을 찾은 경우
                    if (nextPosition == MY_KING) return true;

                    // 빈 공간이 아닌 다른 기물을 만난 경우
                    if (nextPosition != EMPTY) break;

                    nextRow += dir[0];
                    nextCol += dir[1];
                }
            }
        }

        return false;
    }
}

class RookPosition {
    private final int row;
    private final int col;

    public RookPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
