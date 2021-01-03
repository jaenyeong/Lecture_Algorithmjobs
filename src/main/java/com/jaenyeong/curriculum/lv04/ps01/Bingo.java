package com.jaenyeong.curriculum.lv04.ps01;

import java.util.Scanner;

public class Bingo {
    /*
    color paper

    [문제]
    빙고 게임은 다음과 같은 방식으로 이루어진다.
    먼저 아래와 같이 25개의 칸으로 이루어진 빙고판에 1부터 25까지 자연수를 한 칸에 하나씩 쓴다.
    [bingo_1.png]
    다음은 사회자가 부르는 수를 차례로 지워나간다.
    예를 들어 5, 10, 7이 불렸다면 이 세 수를 지운 뒤 빙고판의 모습은 다음과 같다.
    [bingo_2.png]
    차례로 수를 지워가다가 같은 가로줄, 세로줄 또는 대각선 위에 있는 5개의 모든 수가 지워지는 경우 그 줄에 선을 긋는다.
    [bingo_3.png]
    이러한 선이 세 개 이상 그어지는 순간 "빙고"라고 외치는데, 가장 먼저 외치는 사람이 게임의 승자가 된다.
    [bingo_4.png]
    철수는 친구들과 빙고 게임을 하고 있다.
    철수가 빙고판에 쓴 수들과 사회자가 부르는 수의 순서가 주어질 때,
    사회자가 몇 번째 수를 부른 후 철수가 "빙고"를 외치게 되는지를 출력하는 프로그램을 작성하시오.

    [입력]
    첫째 줄부터 다섯째 줄까지 빙고판에 쓰여진 수가 가장 위 가로줄부터 차례대로 한 줄에 다섯 개씩 빈 칸을 사이에 두고 주어진다.
    여섯째 줄부터 열째 줄까지 사회자가 부르는 수가 차례대로 한 줄에 다섯 개씩 빈 칸을 사이에 두고 주어진다.
    빙고판에 쓰여진 수와 사회자가 부르는 수는 각각 1부터 25까지의 수가 한 번씩 사용된다.

    [출력]
    첫째 줄에 사회자가 몇 번째 수를 부른 후 철수가 "빙고"를 외치게 되는지 출력한다.

    [예제 입력]
    11 12 2 24 10
    16 1 13 3 25
    6 20 5 21 17
    19 4 8 14 9
    22 15 7 23 18
    5 10 7 16 2
    4 22 8 17 13
    3 18 1 6 25
    12 19 23 14 21
    11 24 9 20 15
    [예제 출력]
    15

     */

    private static final Scanner SCAN = new Scanner(System.in);
    private static final int BINGO_SIZE = 5;
    private static final int BINGO_ALL_SIZE = 25;
    private static final int BINGO_LIMIT = 3;
    private static final int SUCCESS = -1;

    public static void main(String[] args) {
        // 빙고판 초기화
        final int[][] bingo = new int[BINGO_SIZE][BINGO_SIZE];
        for (int i = 0; i < BINGO_SIZE; i++) {
            for (int j = 0; j < BINGO_SIZE; j++) {
                bingo[i][j] = SCAN.nextInt();
            }
        }

        // 숫자를 하나씩 총 25개를 부름
        // 순서를 위해 1부터 25까지 입력 받음
        int result = 0;
        for (int i = 1; i <= BINGO_ALL_SIZE; i++) {
            final int givenNumber = SCAN.nextInt();

            // 주어진 숫자로 빙고 확인 후 빙고 라인이 3개가 된 경우
            if (successBingo(bingo, givenNumber)) {
                result = i;
                break;
            }
        }

        SCAN.close();

        // 결과 출력
        System.out.println(result);
    }

    private static boolean successBingo(final int[][] bingo, final int givenNumber) {
        // 주어진 숫자를 빙고판에서 찾아 성공 처리
        findBingoNumber(bingo, givenNumber);

        return checkBingo(bingo);
    }

    private static void findBingoNumber(final int[][] bingo, final int givenNumber) {
        for (int row = 0; row < BINGO_SIZE; row++) {
            for (int col = 0; col < BINGO_SIZE; col++) {
                if (givenNumber == bingo[row][col]) {
                    // 해당 숫자 빙고 처리
                    bingo[row][col] = SUCCESS;
                    return;
                }
            }
        }
    }

    private static boolean checkBingo(final int[][] bingo) {
        int successLine = 0;
        // 가로, 세로 확인
        for (int first = 0; first < BINGO_SIZE; first++) {
            int rowSuccess = 0;
            int colSuccess = 0;

            for (int second = 0; second < BINGO_SIZE; second++) {
                if (bingo[first][second] == SUCCESS) rowSuccess++;
                if (bingo[second][first] == SUCCESS) colSuccess++;
            }

            if (rowSuccess == BINGO_SIZE) successLine++;
            if (colSuccess == BINGO_SIZE) successLine++;
        }

        // 대각선 확인
        if ((bingo[0][0] == SUCCESS) && (bingo[1][1] == SUCCESS)
            && (bingo[2][2] == SUCCESS) && (bingo[3][3] == SUCCESS) && (bingo[4][4] == SUCCESS)) {
            successLine++;
        }
        if ((bingo[0][4] == SUCCESS) && (bingo[1][3] == SUCCESS)
            && (bingo[2][2] == SUCCESS) && (bingo[3][1] == SUCCESS) && (bingo[4][0] == SUCCESS)) {
            successLine++;
        }

        return successLine >= BINGO_LIMIT;
    }
}
