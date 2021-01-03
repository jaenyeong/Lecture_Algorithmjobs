package com.jaenyeong.curriculum.lv01.ps02;

import java.util.Scanner;

public class Yutnori {
    /*
    윷놀이

    [문제]
    첫째 줄부터 셋째 줄까지 각 줄에 각각 한 번 던진 윷짝들의 상태를 나타내는 네 개의 정수(0 또는 1)가 빈칸을 사이에 두고 주어진다.

    [입력]
    첫째 줄부터 셋째 줄까지 각 줄에 각각 한 번 던진 윷짝들의 상태를 나타내는 네 개의 정수(0 또는 1)가 빈칸을 사이에 두고 주어진다.

    [출력]
    첫째 줄부터 셋째 줄까지 한 줄에 하나씩 결과를 도는 A, 개는 B, 걸은 C, 윷은 D, 모는 E로 출력 한다.

    [예제 입력]
    0 1 0 1
    1 1 1 0
    0 0 1 1
    [예제 출력]
    B
    A
    B

     */

    private static final Scanner SCAN = new Scanner(System.in);

    private static final int FRONT = 0; // 배 - 평평한 면

    public static void main(String[] args) {
        for (int question = 0; question < 3; question++) {
            int frontScore = 0;
            for (int yut = 0; yut < 4; yut++) {
                final int input = SCAN.nextInt();
                if (input == FRONT) frontScore++;
            }

            switch (frontScore) {
                case 1: // 도 (배 1개) - A
                    System.out.println("A");
                    break;
                case 2: // 개 (배 2개) - B
                    System.out.println("B");
                    break;
                case 3: // 걸 (배 3개) - C
                    System.out.println("C");
                    break;
                case 4: // 윷 (배 4개) - D
                    System.out.println("D");
                    break;
                default: // 모 (배 0개) - E
                    System.out.println("E");
            }
        }
    }
}
