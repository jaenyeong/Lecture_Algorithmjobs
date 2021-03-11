package com.jaenyeong.previousproblem.lv05.step07;

import java.util.*;

public class Visitor {
    /*
    철새 (시간제한 1초)

    [문제]
    철새 도래지에 풍경 사진을 찍으러 온 민건은 해질녘 새들이 겹쳐서 나는 순간을 포착하고 싶다.
    철새들은 도래지 영역을 벗어나서 비행을 하지 않는다.
    만약, 비행하고 있는 방향에 나무가 있거나, 영역을 벗어나는 경우 반대 방향으로 다시 비행한다.
    만약 철새 도래지를 격자판으로 표현한다면, 철새들은 한 방향으로 1초에 한 칸씩 움직인다.
    [visitor_01.png]
    철새가 여러마리 있을때, 철새들이 움직이는 순서가 존재했다.
    편의상 가장 먼저 움직이는 철새의 번호를 1번이라고 부르겠다.
    1번 철새부터 차례대로 1칸씩 이동하며, 이동 중에 서로가 겹쳐질 수 있다.
    날고 있는 곳에 빛을 받는지 안받는지에 따라 겹치는 순서가 달라진다.
    만약 빛을 받고 있는 곳에서 겹쳐졌다면, 해당 칸에 도착한 순서대로 겹쳐진다.
    만약 빛을 받지 않고 있는 곳에서 겹쳐졌다면, 해당 칸에 도착한 역순으로 겹쳐진다.
    [visitor_02.png]
    [visitor_03.png]
    겹쳐진 상태에서 이동하게되면, 자신의 위에 존재하는 철새들이 함께 이동한다.
    [visitor_04.png]
    현재 철새 도래지의 상황이 주어졌을때, 몇 초를 기다려야 4마리 이상이 겹쳐진 모습을 찍을 수 있는지 알아보자.

    [입력]
    첫 번째 줄에 테스트 케이스의 개수 T가 주어진다.
    다음 줄부터 T개의 테스트 케이스에 대한 정보가 주어진다.
    각각의 테스트 케이스의 첫 번째 줄에 비행 영역의 크기 N, 철새의 수 K가 공백을 통해 구분하여 주어진다.
    두 번째 줄부터 N개의 줄에 걸쳐 영역 정보가 주어진다.
    각 줄에는 N개의 숫자가 공백을 통해 구분하여 주어지며, 각 숫자는 0부터 2까지의 정수이다.
    0은 빛이 없는 영역, 1은 빛이 있는 영역, 2는 나무를 뜻한다.
    다음 K개 줄에 걸쳐 철새의 위치 y, x와 철새가 보고 있는 방향 d가 공백을 통해 구분하여 주어진다.
    d는 0부터 3까지의 정수이며, 0은 상, 1은 하, 2는 좌, 3은 우를 뜻한다.
    철새의 초기위치는 모두 다르며, 나무가 있는 위치에는 철새가 존재할 수 없다.
    또한, 초기 철새 위치 상하좌우에는 나무가 존재하지 않는다.
    가장 왼쪽, 위의 좌표는 (0, 0)이다. (6 <= N <= 12, 6 <= K <= 10, 1 <= y, x < (N - 1))

    [출력]
    각 테스트 케이스에 대해 최소 4마리의 철새가 겹치는 시간을 출력한다.
    만약 기다린 시간이 1000을 초과할 경우 -1을 출력한다.
    각 테스트 케이스의 출력 양식은 "#t r"이다.
    t는 테스트 케이스의 번호이며, 1부터 시작한다.
    r은 문제에 대한 결과값을 뜻한다.

    [예제 입력]
    5
    7 6
    0 2 0 1 0 2 2
    0 2 1 0 1 1 1
    1 1 1 1 0 2 1
    1 1 1 2 1 1 1
    2 0 0 0 1 1 2
    1 0 1 1 0 1 2
    0 2 1 1 0 0 1
    6 3 1
    6 6 2
    2 3 2
    2 4 2
    2 2 1
    4 4 1
    7 7
    1 0 1 0 2 1 1
    2 1 0 2 1 1 1
    1 1 1 0 1 2 2
    0 1 0 0 0 0 1
    1 0 1 1 0 0 1
    1 0 2 2 0 1 1
    1 2 0 0 1 0 1
    2 1 0
    3 1 2
    4 1 2
    2 2 1
    3 4 2
    3 3 2
    4 5 2
    7 8
    1 1 1 1 1 1 0
    2 0 2 1 2 0 0
    1 1 0 0 1 1 2
    0 0 1 1 0 0 2
    1 1 0 0 2 1 1
    0 0 0 1 0 1 1
    0 1 2 0 0 0 1
    4 2 0
    5 5 1
    2 1 1
    3 2 2
    4 1 0
    5 3 1
    5 1 3
    3 1 2
    12 8
    0 0 2 1 1 2 2 0 2 2 1 0
    0 2 2 1 1 2 0 1 0 1 0 1
    0 0 1 0 1 1 0 1 0 1 2 0
    0 2 0 1 0 0 1 2 1 2 2 0
    0 0 0 0 0 1 1 1 1 0 1 0
    2 1 1 2 0 0 1 0 0 1 1 1
    2 1 1 1 0 2 0 0 1 0 1 1
    0 1 1 0 0 1 0 0 1 1 2 1
    2 1 1 0 1 1 1 0 1 2 1 0
    1 2 1 1 0 1 0 2 0 1 1 1
    0 0 1 2 0 1 2 0 2 0 2 1
    0 2 0 1 2 1 0 1 0 1 0 1
    9 4 3
    8 4 1
    2 6 3
    5 9 3
    5 10 2
    7 7 0
    9 5 0
    8 3 3
    10 7
    1 1 0 1 2 0 1 2 1 1
    2 0 1 0 1 1 1 0 2 0
    1 1 1 0 2 2 2 1 1 1
    0 0 0 2 0 1 2 2 1 0
    0 2 1 1 0 1 1 1 0 1
    1 2 0 2 1 1 1 1 1 0
    1 1 2 0 0 1 0 0 0 0
    1 1 1 0 0 1 0 1 2 0
    0 1 2 0 1 0 1 1 1 2
    0 1 2 1 0 2 0 1 0 0
    6 5 2
    7 4 0
    2 1 1
    5 7 0
    2 2 0
    5 6 0
    8 4 2
    [예제 출력]
    #1 11
    #2 3
    #3 1
    #4 38
    #5 -1

    [설명]
    예제 입력의 첫 번째 케이스에서 8초의 상황부터 이동하는 모습을 보면 다음과 같다.
    T=8
    [visitor_05.png]
    T=9, 1번 이동 후
    [visitor_06.png]
    T=9, 2번 이동 후
    [visitor_07.png]
    T=9, 3번 이동 후
    [visitor_08.png]
    T=9, 4번 이동 후
    [visitor_09.png]
    T=9, 5번 이동 후
    [visitor_10.png]
    T=9, 6번 이동 후
    [visitor_11.png]
    T=10, 1번 이동 후
    [visitor_12.png]
    T=10, 2번 이동 후
    [visitor_13.png]
    T=10, 3번 이동 후
    [visitor_14.png]
    T=10, 4번 이동 후
    [visitor_15.png]
    T=10, 5번 이동 후
    [visitor_16.png]
    T=10, 6번 이동 후
    [visitor_17.png]
    T=11, 1번 이동 후
    [visitor_18.png]

     */

    private static final Scanner SC = new Scanner(System.in);
    private static final int DARK_AREA = 0;
    private static final int BRIGHT_AREA = 1;
    private static final int TREE = 2;

    public static void main(String[] args) {
        final int testCase = SC.nextInt();

        for (int running = 1; running <= testCase; running++) {
            final int mapSize = SC.nextInt();
            final int numberOfBirds = SC.nextInt();

            final Area[][] area = initArea(mapSize);

            final List<Bird> birds = initBirds(numberOfBirds, area);

            flyTheBirds(running, mapSize, area, birds);
        }

        SC.close();
    }

    private static Area[][] initArea(final int mapSize) {
        final Area[][] area = new Area[mapSize][mapSize];
        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                final int type = SC.nextInt();

                bindingArea(area, row, col, type);
            }
        }
        return area;
    }

    private static void bindingArea(final Area[][] area, final int row, final int col, final int type) {
        switch (type) {
            case DARK_AREA:
                area[row][col] = new DarkArea();
                break;
            case BRIGHT_AREA:
                area[row][col] = new BrightArea();
                break;
            case TREE:
                // NOTHING
                break;
            default:
                throw new IllegalArgumentException("Not defined area type");
        }
    }

    private static List<Bird> initBirds(final int numberOfBirds, final Area[][] area) {
        final List<Bird> birds = new ArrayList<>();
        char birdId = 'a';

        for (int i = 1; i <= numberOfBirds; i++) {
            final char curId = birdId++;
            final int row = SC.nextInt();
            final int col = SC.nextInt();
            final int direction = SC.nextInt();

            final Bird bird = new Bird(curId, row, col, direction);

            birds.add(bird);
            area[row][col].add(bird);
        }
        return birds;
    }

    private static void flyTheBirds(final int running, final int gridSize, final Area[][] area, final List<Bird> birds) {
        for (int time = 1; time <= 1000; time++) {
            for (Bird flyingBird : birds) {
                final int curRow = flyingBird.getRow();
                final int curCol = flyingBird.getCol();

                flyingBird.flyByMyTurn(gridSize, area);
                final int rowAfterFly = flyingBird.getRow();
                final int colAfterFly = flyingBird.getCol();

                if (curRow == rowAfterFly && curCol == colAfterFly) {
                    continue;
                }

                final List<Bird> flyBirds = area[curRow][curCol].flyTheGroup(flyingBird);
                area[rowAfterFly][colAfterFly].add(flyBirds);

                if (checkNumberOfBirdsTheArea(running, area, birds, time)) return;
            }
        }

        System.out.println("#" + running + " -1");
    }

    private static boolean checkNumberOfBirdsTheArea(final int running, final Area[][] area, final List<Bird> birds, final int time) {
        for (Bird bird : birds) {
            final int row = bird.getRow();
            final int col = bird.getCol();

            if (area[row][col].birdsSizeIsGreaterThanFour()) {
                System.out.println("#" + running + " " + time);
                return true;
            }
        }
        return false;
    }
}

interface Area {
    int LIMIT_BIRDS = 4;

    boolean birdsSizeIsGreaterThanFour();

    void add(final Bird bird);

    void add(final List<Bird> birds);

    List<Bird> flyTheGroup(final Bird targetBird);
}

class BrightArea implements Area {
    private final Stack<Bird> birds = new Stack<>();

    @Override
    public boolean birdsSizeIsGreaterThanFour() {
        return this.birds.size() >= LIMIT_BIRDS;
    }

    @Override
    public void add(final Bird bird) {
        this.birds.push(bird);
    }

    @Override
    public void add(final List<Bird> comeInBirds) {
        Collections.reverse(comeInBirds);
        this.birds.addAll(comeInBirds);
    }

    @Override
    public List<Bird> flyTheGroup(final Bird targetBird) {
        final List<Bird> flyBirds = new ArrayList<>();

        while (!birds.isEmpty()) {
            final Bird flyBird = this.birds.pop();
            flyBird.flyByOtherBird(targetBird.getRow(), targetBird.getCol());
            flyBirds.add(flyBird);

            if (flyBird.equals(targetBird)) {
                break;
            }
        }

        return flyBirds;
    }
}

class DarkArea implements Area {
    private final Queue<Bird> birds = new LinkedList<>();

    @Override
    public boolean birdsSizeIsGreaterThanFour() {
        return birds.size() >= LIMIT_BIRDS;
    }

    @Override
    public void add(final Bird bird) {
        this.birds.offer(bird);
    }

    @Override
    public void add(final List<Bird> flyBirds) {
        this.birds.addAll(flyBirds);
    }

    @Override
    public List<Bird> flyTheGroup(final Bird targetBird) {
        final List<Bird> flyBirds = new ArrayList<>();

        while (!birds.isEmpty()) {
            final Bird flyBird = birds.poll();
            flyBird.flyByOtherBird(targetBird.getRow(), targetBird.getCol());
            flyBirds.add(flyBird);

            if (flyBird.equals(targetBird)) {
                break;
            }
        }

        return flyBirds;
    }
}

class Bird {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private char id;
    private int row;
    private int col;
    private int direction;

    Bird(final char id, final int row, final int col, final int direction) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.direction = direction;
    }

    void flyByMyTurn(final int gridSize, final Area[][] area) {
        int[] curDir = DIRECTIONS[direction];
        int nextRow = row + curDir[0];
        int nextCol = col + curDir[1];

        if (invalidRange(gridSize, nextRow, nextCol) || isTreeNextArea(area[nextRow][nextCol])) {
            turnDirection();

            curDir = DIRECTIONS[direction];
            nextRow = row + curDir[0];
            nextCol = col + curDir[1];

            if (invalidRange(gridSize, nextRow, nextCol) || isTreeNextArea(area[nextRow][nextCol])) {
                turnDirection();
                return;
            }
        }

        row = nextRow;
        col = nextCol;
    }

    private boolean invalidRange(final int gridSize, final int nextRow, final int nextCol) {
        return 0 > nextRow || nextRow >= gridSize || 0 > nextCol || nextCol >= gridSize;
    }

    private boolean isTreeNextArea(final Area nextArea) {
        return nextArea == null;
    }

    private void turnDirection() {
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
    }

    void flyByOtherBird(final int nextRow, final int nextCol) {
        this.row = nextRow;
        this.col = nextCol;
    }

    int getId() {
        return id;
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
        final Bird bird = (Bird) o;
        return getId() == bird.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
