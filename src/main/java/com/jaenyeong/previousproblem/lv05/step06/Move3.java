package com.jaenyeong.previousproblem.lv05.step06;

import java.util.*;

public class Move3 {
    /*
    이동 알고리즘3

    [문제]
    세로 길이 N, 가로 길이 M 인 2차원 창고에 k 개의 상자가 있다.
    이 창고에서는 매 초마다 상자를 옮기는데, i 번 상자는 1초에 di 방향으로 한 칸 씩 이동하고,
    창고의 끝에 도착하면 다음 이동 시 뒤로 돌아서 반대 방향으로 이동한다.
    매 초마다 1번 상자부터 k 번 상자까지 순서대로 이동하며,
    각 상자가 이동할 때 같은 위치에 있으면서 이동하는 상자보다 위에 쌓인 상자들은 함께 이동하게 된다.
    이동할 위치에 이미 상자가 있다면 이동할 상자들은 기존 있던 상자의 위에 쌓인다.
    t 초 후 각 상자의 위치를 1번 상자부터 출력하여라.
    이 배열의 왼쪽 위 좌표는 (1, 1) 이다.
    yi 는 i 번 사람의 세로 방향 위치, xi 는 i 번 사람의 가로 방향 위치를 의미한다.
    di 는 0, 1, 2, 3 중 하나로 주어진다.
    적힌 숫자 순서대로 상하좌우를 의미한다.

    [입력]
    첫 줄에 N, M, k, t 가 주어진다.
    두 번째 줄부터 k 줄에 걸쳐 yi, xi, di 가 주어진다.
    입력으로 주어지는 숫자는 모두 정수이다.
    (3 <= N, M, k, t <= 100, 1 <= yi <= N, 1 <= xi <= M, 0 <= di <= 3)

    [출력]
    k 줄에 걸쳐 각 상자의 t 초 후 위치를 출력한다.

    [예제 입력]
    4 5 3 3
    1 1 3
    1 2 1
    3 5 2
    [예제 출력]
    2 4
    4 2
    3 2

    [예제 입력]
    3 4 3 2
    1 3 0
    3 4 3
    2 1 3
    [예제 출력]
    3 2
    3 2
    2 3

    [예제 입력]
    4 6 3 3
    2 4 3
    4 4 2
    2 2 1
    [예제 출력]
    2 5
    4 1
    3 1

    [예제 입력]
    4 6 3 2
    2 4 3
    4 4 2
    2 2 1
    [예제 출력]
    2 6
    4 2
    4 2

     */

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        final int rowSize = SC.nextInt();
        final int colSize = SC.nextInt();

        final int numberOfBoxes = SC.nextInt();
        final int times = SC.nextInt();

        final Zone[][] warehouse = new Zone[rowSize + 1][colSize + 1];
        for (int row = 1; row <= rowSize; row++) {
            for (int col = 1; col <= colSize; col++) {
                warehouse[row][col] = new Zone();
            }
        }

        final ArrayList<Box> boxes = new ArrayList<>();

        for (int boxKey = 1; boxKey <= numberOfBoxes; boxKey++) {
            final int row = SC.nextInt();
            final int col = SC.nextInt();
            final int dir = SC.nextInt();
            final Box newBox = new Box(boxKey, row, col, dir);

            boxes.add(newBox);
            warehouse[row][col].addBox(newBox);
        }

        SC.close();

        for (int time = 0; time < times; time++) {
            for (Box targetBox : boxes) {
                final int curRow = targetBox.getRow();
                final int curCol = targetBox.getCol();
                targetBox.moveByTurn(rowSize, colSize);

                final int rowAfterMove = targetBox.getRow();
                final int colAfterMove = targetBox.getCol();
                warehouse[curRow][curCol].moveBoxesOverCurrentBox(rowAfterMove, colAfterMove, targetBox, warehouse[rowAfterMove][colAfterMove]);
            }
        }

        for (Box box : boxes) {
            System.out.println(box.getRow() + " " + box.getCol());
        }
    }
}

class Zone {
    private final Stack<Box> boxes = new Stack<>();

    Zone() {
    }

    public void moveBoxesOverCurrentBox(final int nextRow, final int nextCol, final Box targetBox, final Zone nextZone) {
        final Stack<Box> temp = new Stack<>();

        while (!boxes.isEmpty()) {
            final Box curBox = boxes.pop();

            curBox.move(nextRow, nextCol);
            temp.add(curBox);

            if (curBox.equals(targetBox)) {
                break;
            }
        }

        while (!temp.isEmpty()) {
            nextZone.addBox(temp.pop());
        }
    }

    public void addBox(final Box box) {
        boxes.add(box);
    }
}

class Box {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private final int key;
    private int row;
    private int col;
    private int direction;

    Box(final int boxKey, final int row, final int col, final int direction) {
        this.key = boxKey;
        this.row = row;
        this.col = col;
        this.direction = direction;
    }

    public void moveByTurn(final int rowSize, final int colSize) {
        int[] dir = DIRECTIONS[direction];

        int nextRow = row + dir[0];
        int nextCol = col + dir[1];

        if (0 >= nextRow || nextRow > rowSize || 0 >= nextCol || nextCol > colSize) {
            switch (direction) {
                case 0:
                    direction = 1;
                    break;
                case 1:
                    direction = 0;
                    break;
                case 2:
                    direction = 3;
                    break;
                case 3:
                    direction = 2;
                    break;
            }

            dir = DIRECTIONS[direction];
            nextRow = row + dir[0];
            nextCol = col + dir[1];
        }

        row = nextRow;
        col = nextCol;
    }

    public void move(final int nextRow, final int nextCol) {
        this.row = nextRow;
        this.col = nextCol;
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Box box = (Box) o;
        return key == box.key;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
