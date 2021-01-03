package com.jaenyeong.curriculum.lv02.ps01;

import java.util.Scanner;

public class CardGame {
    /*
    card game

    [문제]
    두 사람 A와 B는 1부터 10까지의 숫자가 하나씩 적힌 열 장의 카드로 ‘게임’을 한다.
    게임은 총 열 번의 ‘라운드’로 구성되고, 각 라운드 마다 자신이 가지고 있는 카드 중 하나를 제시하고, 한 번 제 시한 카드는 버린다.
    게임 승패는 다음과 같이 결정된다.
    [1] 각 라운드는 더 높은 숫자를 제시한 사람이 승리하고, 제시한 숫자가 같은 경우는 비긴다.
    [2] 열 번의 라운드에서 더 많은 라운드를 승리한 사람이 게임을 승리하고, 승리한 라운드 횟수 가 동일한 경우 비긴다.
    다음은 게임의 한 예로, 각 라운드마다 A와 B가 제시한 카드의 숫자와 각 라운드의 승자를 보여준다. (비긴 라운드는 D로 표시함)
    [카드게임.png]
    A는 5번의 라운드에서 승리하고 B는 4번의 라운 드에서 승리하였으므로, 이 게임은 A가 승리한다.
    라운드 순서대로 A와 B가 제시한 카드의 숫자가 주어졌을 때, 게임의 승자를 판단하는 프로그램을 작성하시오.

    [입력]
    다음 정보가 표준 입력으로 주어진다.
    첫 번째 줄 에는 A가 제시한 카드의 숫자 10개가 라운드 순서대로 주어지고, 두 번째 줄에는 B가 제시한 카 드의 숫자 10개가 라운드 순서대로 주어진다.

    [출력]
    다음 정보를 표준 출력으로 출력한다.
    게임의 승 패가 결정되는 경우 승리한 사람을 출력하고, 비 기는 경우에는 D를 출력한다.

    [예제 입력]
    6 7 5 1 4 10 2 3 8 9
    1 10 2 9 4 8 3 7 5 6
    [예제 출력]
    A

    [예제 입력]
    1 2 3 4 5 6 7 8 9 10
    5 4 3 2 1 10 9 8 7 6
    [예제 출력]
    D

     */

    private static final Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) {
        final int[] aCards = new int[10];
        final int[] bCards = new int[10];

        // 초기화
        for (int i = 0; i < 10; i++) {
            aCards[i] = SCAN.nextInt();
        }
        for (int i = 0; i < 10; i++) {
            bCards[i] = SCAN.nextInt();
        }

        SCAN.close();

        // 매 라운드(10) 카드 숫자 크기 비교
        int aWinCount = 0;
        int bWinCount = 0;
        for (int i = 0; i < 10; i++) {
            final int aCard = aCards[i];
            final int bCard = bCards[i];

            if (aCard > bCard) {
                aWinCount++;
                continue;
            }

            if (bCard > aCard) {
                bWinCount++;
            }
        }

        System.out.println((aWinCount > bWinCount) ? 'A' : (aWinCount == bWinCount) ? 'D' : 'B');
    }
}
