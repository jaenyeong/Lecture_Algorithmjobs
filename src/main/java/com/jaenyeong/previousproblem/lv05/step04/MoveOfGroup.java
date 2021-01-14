package com.jaenyeong.previousproblem.lv05.step04;

import java.util.*;

public class MoveOfGroup {
    /*
    단체 이동

    [문제]
    세로 길이 N, 가로 길이 M인 2차원 배열에 k명의 사람이 있다.
    i번 사람은 1초에 di 방향으로 한 칸 씩 이동하고, 배열의 끝에 도착하면 다음 이동 시 뒤로 돌아서 반대 방향으로 이동한다.
    매 초마다 1번 사람부터 k번 사람까지 순서대로 이동하며, 각 사람이 이동할 때 같은 위치에 있는 다른 사람들도 함께 이동한다.
    t초 후 각 사람의 위치를 1번 사람부터 k번 사람까지 출력하여라.
    이 배열의 왼쪽 위 좌표는 (1, 1) 이다.
    yi는 i번 사람의 세로 방향 위치, xi 는 i번 사람의 가로 방향 위치를 의미한다.
    di는 0, 1, 2, 3 중 하나로 주어진다.
    적힌 숫자 순서대로 상하좌우를 의미한다.

    [입력]
    첫 줄에 N, M, k, t가 주어진다.
    두 번째 줄부터 k줄에 걸쳐 yi, xi, di, fi가 주어진다.
    (3 <= N, M, k, t <= 100, 1 <= yi <= N, 1 <= xi <= M, 0 <= di <= 3)

    [출력]
    k줄에 걸쳐 각 사람의 t초 후 위치를 y, x 순서로 출력한다.

    [예제 입력]
    4 5 3 3
    1 1 3
    1 2 1
    3 5 2
    [예제 출력]
    4 3
    4 3
    4 3

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
    3 1
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

    [설명]
    아래는 입출력 예 1번의 상황이다.
    [단체_이동.png]

     */

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        final int rowSize = SC.nextInt();
        final int colSize = SC.nextInt();

        final int numberOfPeople = SC.nextInt();
        final int times = SC.nextInt();

        final People[][] area = new People[rowSize + 1][colSize + 1];
        for (int row = 1; row <= rowSize; row++) {
            for (int col = 1; col <= colSize; col++) {
                area[row][col] = new People();
            }
        }

        final List<Man> mans = new ArrayList<>();

        for (int i = 0; i < numberOfPeople; i++) {
            final int row = SC.nextInt();
            final int col = SC.nextInt();
            final int direction = SC.nextInt();

            final Man man = new Man(row, col, direction);
            mans.add(man);

            area[row][col].add(man);
        }

        SC.close();

        for (int time = 0; time < times; time++) {
            for (Man man : mans) {
                final int curRow = man.getRow();
                final int curCol = man.getCol();

                man.move(rowSize, colSize);
                area[curRow][curCol].remove(man);

                final int nextRow = man.getRow();
                final int nextCol = man.getCol();
                area[nextRow][nextCol].add(man);

                area[curRow][curCol].move(nextRow, nextCol, area[nextRow][nextCol]);
            }
        }

        for (Man man : mans) {
            System.out.println(man.getRow() + " " + man.getCol());
        }
    }
}

class People {
    private final Queue<Man> people;

    People() {
        this.people = new LinkedList<>();
    }

    public void add(final Man man) {
        if (people.contains(man)) {
            return;
        }

        people.add(man);
    }

    public void move(final int row, final int col, final People otherGroup) {
        while (!people.isEmpty()) {
            final Man nextMan = people.poll();

            nextMan.setRow(row);
            nextMan.setCol(col);
            otherGroup.add(nextMan);
        }
    }

    public void remove(final Man man) {
        people.remove(man);
    }
}

class Man {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private int row;
    private int col;
    private int direction;

    Man(final int row, final int col, final int direction) {
        this.row = row;
        this.col = col;
        this.direction = direction;
    }

    public void move(final int rowSize, final int colSize) {
        int nextRow = row + DIRECTIONS[direction][0];
        int nextCol = col + DIRECTIONS[direction][1];

        if (1 > nextRow || nextRow > rowSize || 1 > nextCol || nextCol > colSize) {

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

            nextRow = row + DIRECTIONS[direction][0];
            nextCol = col + DIRECTIONS[direction][1];
        }

        row = nextRow;
        col = nextCol;
    }

    int getRow() {
        return row;
    }

    int getCol() {
        return col;
    }

    void setRow(final int row) {
        this.row = row;
    }

    void setCol(final int col) {
        this.col = col;
    }
}
