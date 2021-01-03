package com.jaenyeong.curriculum.lv18.ps03;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class NumberingOfAptComplex {
    /*
    단지 번호 붙이기

    [문제]
    [numbering_of_apt_complex.png - 그림 1]과 같이 정사각형 모양의 지도가 있다.
    1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다.
    철수는 이 지도를 가지고 연결된 집들의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다.
    여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다.
    대각선상에 집이 있는 경우는 연결된 것이 아니다.
    [numbering_of_apt_complex.png - 그림 2]는 [numbering_of_apt_complex.png - 그림 1]을 단지별로 번호를 붙인 것이다.
    지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.

    [입력]
    첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고, 그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.

    [출력]
    첫 번째 줄에는 총 단지수를 출력하시오.
    그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.

    [예제 입력]
    7
    0110100
    0110101
    1110101
    0000111
    0100000
    0111110
    0111000
    [예제 출력]
    3
    7
    8
    9

     */

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = SC.nextInt();

        final int[][] grid = new int[n][n];

        for (int i = 0; i < n; i++) {
            final List<Integer> inputNumbers = Arrays.stream(SC.next().split(""))
                .map(Integer::parseInt)
                .collect(toList());

            for (int j = 0; j < n; j++) {
                grid[i][j] = inputNumbers.get(j);
            }
        }

        SC.close();

        final NumberingOfAptComplexHandler complex = new NumberingOfAptComplexHandler(n, grid);
        complex.solve();
    }
}

class NumberingOfAptComplexHandler {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int ROW = 0;
    private static final int COL = 1;
    private static final int EMPTY = 0;
    private final int n;
    private final int[][] originGrid;
    private final boolean[][] visited;

    public NumberingOfAptComplexHandler(final int n, final int[][] grid) {
        this.n = n;
        this.originGrid = grid;
        this.visited = new boolean[n][n];
    }

    public void solve() {
        int numbering = 0;
        final List<Integer> numbers = new ArrayList<>();

        // 배열의 모든 위치를 순회하며 확인
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                // 이미 방문한 경우 통과
                if (visited[row][col]) {
                    continue;
                }

                // 빈 공간이면 통과
                if (originGrid[row][col] == EMPTY) {
                    continue;
                }

                numbering++;
                final int numberOfAptComplex = dfsSolve(row, col);
                numbers.add(numberOfAptComplex);
            }
        }

        Collections.sort(numbers);

        System.out.println(numbering);
        numbers.forEach(System.out::println);
    }

    private int dfsSolve(final int row, final int col) {
        visited[row][col] = true;
        int numberOfAptComplex = 1;

        for (int[] dir : DIRECTIONS) {
            final int nextRow = row + dir[ROW];
            final int nextCol = col + dir[COL];

            // 유효 범위를 벗어난 경우
            if ((0 > nextRow) || (nextRow >= n) || (0 > nextCol) || (nextCol >= n)) {
                continue;
            }

            // 방문한 적이 있거나, 빈 공간인 경우
            if (visited[nextRow][nextCol] || originGrid[nextRow][nextCol] == EMPTY) {
                continue;
            }

            numberOfAptComplex += dfsSolve(nextRow, nextCol);
        }

        return numberOfAptComplex;
    }
}
