package com.jaenyeong.curriculum.lv02.ps05;

import java.util.Scanner;

public class CalculateScore {
    /*
    점수 계산

    [문제]
    OX 문제는 맞거나 틀린 두 경우의 답을 가지는 문제를 말한다.
    여러 개의 OX 문제로 만들어진 시험에서 연속적으로 답을 맞히는 경우에는 가산점을 주기 위해서 다음과 같이 점수 계산을 하기로 하였다.
    1번 문제가 맞는 경우에는 1점으로 계산한다.
    앞의 문제에 대해서는 답을 틀리다가 답이 맞는 처음 문제는 1점으로 계산한다.
    또한, 연속으로 문제의 답이 맞는 경우에서 두 번째 문제는 2점, 세 번째 문제는 3점, ..., K번째 문제는 K점으로 계산한다.
    틀린 문제는 0점으로 계산한다.
    예를 들어, 아래와 같이 10 개의 OX 문제에서
    답이 맞은 문제의 경우에는 1로 표시하고, 틀린 경우에는 0으로 표시하였을 때,
    점수 계산은 아래 표와 같이 계산되어, 총 점수는 1+1+2+3+1+2=10 점이다.
    [점수계산.png]

    [입력]
    첫째 줄부터 아홉 번째 줄까지 한 줄에 하나의 자연수가 주어진다.
    주어지는 자연수는 10,000,000 보다 작다.

    [출력]
    첫째 줄에 두 번째 최솟값을 출력하고, 둘째 줄에 두 번째 최솟값이 몇 번째 수인지를 출력한다.

    [예제 입력]
    3
    29
    38
    12
    57
    74
    40
    85
    61
    [예제 출력]
    12
    4

     */

    private static final Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = SCAN.nextInt();
        final boolean[] scores = new boolean[n];

        for (int i = 0; i < n; i++) {
            final int input = SCAN.nextInt();
            scores[i] = input == 1;
        }

        SCAN.close();

        int totalScore = 0;
        int addPoint = 0;

        for (boolean score : scores) {
            if (score) {
                addPoint++;
                totalScore += addPoint;
                continue;
            }

            addPoint = 0;
        }

        System.out.println(totalScore);
    }
}
