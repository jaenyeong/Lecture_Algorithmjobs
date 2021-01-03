package com.jaenyeong.curriculum.lv03.ps08;

import java.util.Scanner;

public class ColorPaper {
    /*
    color paper

    [문제]
    평면에 색깔이 서로 다른 직사각형 모양의 색종이 N장이 하나씩 차례로 놓여진다.
    이 때 색종이가 비스듬하게 놓이는 경우는 없다.
    즉, 모든 색종이의 변은 서로 평행하거나, 서로 수직이거나 둘 중 하나이다.
    [color_paper_1.png]은 1번, 2번, 3번 세 장의 색종이가 순서대로 놓인 상태를 보여준다.
    여기에 그림-2에서 보인 것처럼 4번 색종이가 하나 더 놓이면 3번 색종이는 완전히 가려서 보이지 않게 된다.
    그리고, 1번 색종이와 2번 색종이는 부분적으로 가려 보이며, 4번 색종이는 완전히 보이게 된다.
    [color_paper_2.png]
    N장의 색종이가 주어진 위치에 차례로 놓일 경우, 각 색종이가 보이는 부분의 면적을 구하는 프로그램을 작성하시오.

    [입력]
    입력의 첫 번째 줄에는 색종이의 장수를 나타내는 정수 N (1 ≤ N ≤ 100)이 주어진다.
    이어서 N장의 색종이에 관한 입력이 각 색종이마다 한 줄씩 차례로 주어진다.
    색종이가 놓이는 평면은 가로 최대 101칸, 세로 최대 101칸으로 구성된 격자 모양이다.
    격자의 각 칸은 가로, 세로 길이가 1인 면적이 1인 정사각형이다.
    편의상 가로 6칸, 세로 6칸으로 이루어진 격자의 예를 들어 설명하면, 각 칸에 표시된 값 (a,b)는 해당 칸의 번호를 나타낸다.
    가장 왼쪽 아래의 칸은 (0,0) 가장 오른 쪽 위의 칸은 (5,5)이다.
    [color_paper_3.png]
    색종이가 놓인 상태는 가장 왼쪽 아래 칸의 번호와 너비, 높이를 나타내는 네 정수로 표현한다.
    예를 들어, 위 그림에서 회색으로 표시된 색종이는 (1,4)가 가장 왼쪽 아래에 있고 너비 3, 높이 2이므로 1 4 3 2로 표현한다.
    색종이가 격자 경계 밖으로 나가는 경우는 없다.

    [출력]
    입력에서 주어진 순서에 따라 N장의 색종이를 평면에 놓았을 때,
    입력에서 주어진 순서대로 각 색종이가 보이는 부분의 면적을 한 줄에 하나씩 하나의 정수로 출력한다.
    만약 색종이가 보이지 않는다면 정수 0을 출력한다.

    [예제 입력]
    2
    0 0 10 10
    2 2 6 6
    [예제 출력]
    64
    36

     */

    private static final Scanner SCAN = new Scanner(System.in);
    private static final int GRID_SIZE = 101;

    public static void main(String[] args) {
        final int[][] grid = new int[GRID_SIZE][GRID_SIZE];

        final int n = SCAN.nextInt();
        for (int i = 1; i <= n; i++) {
            // 시작 좌표 입력
            final int x = SCAN.nextInt();
            final int y = SCAN.nextInt();

            // 너비, 높이
            final int w = SCAN.nextInt();
            final int h = SCAN.nextInt();

            for (int row = x; row < (x + w); row++) {
                for (int col = y; col < (y + h); col++) {
                    grid[row][col] = i;
                }
            }
        }

        SCAN.close();

        // 결과 세기
        final int[] colors = new int[n + 1];
        // 색종이 수만큼 반복
        for (int i = 1; i <= n; i++) {

            // 2차원 배열의 격자 공간을 하나씩 순회
            for (int row = 0; row < GRID_SIZE; row++) {
                for (int col = 0; col < GRID_SIZE; col++) {
                    if (i == grid[row][col]) colors[i]++;
                }
            }
        }

        // 출력
        for (int i = 1; i <= n; i++) {
            System.out.println(colors[i]);
        }
    }
}
