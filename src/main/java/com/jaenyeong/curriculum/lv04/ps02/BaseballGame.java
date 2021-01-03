package com.jaenyeong.curriculum.lv04.ps02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BaseballGame {
    /*
    baseball game

    [문제]
    정보문화진흥원 정보 영재 동아리에서 동아리 활동을 하던 영수와 민혁이는 쉬는 시간을 틈타 숫자야구 게임을 하기로 했다.
    영수는 1에서 9까지의 서로 다른 숫자 세 개로 구성된 세 자리 수를 마음속으로 생각한다.
    (예: 324) 민혁이는 1에서 9까지의 서로 다른 숫자 세 개로 구성된 세 자리 수를 영수에게 묻는다.
    (예: 123) 민혁이가 말한 세 자리 수에 있는 숫자들 중 하나가 영수의 세 자리 수의 동일한 자리에 위치하면 스트라이크 한 번으로 센다.
    숫자가 영수의 세 자리 수에 있긴 하나 다른 자리에 위치하면 볼 한 번으로 센다.
    예) 영수가 324를 갖고 있으면
    -. 429는 1 스트라이크 1 볼이다.
    -. 241은 0 스트라이크 2 볼이다.
    -. 924는 2 스트라이크 0 볼이다.
    영수는 민혁이가 말한 수가 몇 스트라이크 몇 볼인지를 답해준다.
    민혁이가 영수의 세 자리 수를 정확하게 맞추어 3 스트라이크가 되면 게임이 끝난다.
    아니라면 민혁이는 새로운 수를 생각해 다시 영수에게 묻는다.
    현재 민혁이와 영수는 게임을 하고 있는 도중에 있다.
    민혁이가 영수에게 어떤 수들을 물어보았는지, 그리고 각각의 물음에 영수가 어떤 대답을 했는지가 입력으로 주어진다.
    이 입력을 바탕으로 여러분은 영수가 생각하고 있을 가능성이 있는 수가 총 몇 개인지를 알아맞혀야 한다.
    아래와 같은 경우를 생각해보자.
    -. 민혁: 123
    -. 영수: 1 스트라이크 1 볼.
    -. 민혁: 356
    -. 영수: 1 스트라이크 0 볼.
    -. 민혁: 327
    -. 영수: 2 스트라이크 0 볼.
    -. 민혁: 489
    -. 영수: 0 스트라이크 1 볼.
    이 때 가능한 답은 324와 328, 이렇게 두 가지이다.
    영수는 동아리의 규율을 잘 따르는 착한 아이라 민혁이의 물음에 곧이곧대로 정직하게 답한다. 그러므로 영수의 답들에는 모순이 없다.
    민혁이의 물음들과 각각의 물음에 대한 영수의 답이 입력으로 주어질 때 영수가 생각하고 있을 가능성이 있는 답의 총 개수를 출력하는 프로그램을 작성하시오.

    [입력]
    첫첫째 줄에는 민혁이가 영수에게 몇 번이나 질문을 했는지를 나타내는 1 이상 100 이하의 자연수 N이 주어진다.
    이어지는 N개의 줄에는 각 줄마다 민혁이가 질문한 세 자리 수와 영수가 답한 스트라이크 개수를 나타내는 정수와 볼의 개수를 나타내는 정수,
    이렇게 총 세 개의 정수가 빈칸을 사이에 두고 주어진다.

    [출력]
    첫 줄에 영수가 생각하고 있을 가능성이 있는 답의 총 개수를 출력한다.

    [예제 입력]
    4
    123 1 1
    356 1 0
    327 2 0
    489 0 1
    [예제 출력]
    2

     */

    private static final Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = SCAN.nextInt();

        // 조건 입력
        final List<Score> inputs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            final int score = SCAN.nextInt();
            final int strike = SCAN.nextInt();
            final int ball = SCAN.nextInt();

            final int hundreds = (score / 100);
            final int tens = ((score / 10) % 10);
            final int units = (score % 10);

            inputs.add(new Score(hundreds, tens, units, strike, ball));
        }

        SCAN.close();

        int resultCount = 0;

        // 모든 숫자를 하나씩 대입해보며 비교
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                for (int k = 1; k < 10; k++) {

                    // 각 자리의 수는 모두 달라야 함
                    if ((i == j) || (j == k) || (i == k)) continue;

                    // 주어진 입력 조건과 비교하여 카운트
                    if (validateScore(inputs, i, j, k)) resultCount++;
                }
            }
        }

        System.out.println(resultCount);
    }

    private static boolean validateScore(final List<Score> inputs, final int i, final int j, final int k) {
        for (Score s : inputs) {
            final int strike = s.getStrike();
            final int ball = s.getBall();

            // 각 자리수 추출
            final int hundreds = s.getHundreds();
            final int tens = s.getTens();
            final int units = s.getUnits();

            int strikeCount = 0;
            int ballCount = 0;

            // 각 자릿수 별 스트라이크 비교
            if (hundreds == i) strikeCount++;
            if (tens == j) strikeCount++;
            if (units == k) strikeCount++;

            // 각 자릿수 별 볼 비교
            if ((hundreds == j) || (hundreds == k)) ballCount++;
            if ((tens == i) || (tens == k)) ballCount++;
            if ((units == i) || (units == j)) ballCount++;

            // 스트라이크와 볼 비교
            if ((strikeCount != strike) || (ballCount != ball)) return false;
        }

        return true;
    }
}

class Score {
    private final int hundreds;
    private final int tens;
    private final int units;
    private final int strike;
    private final int ball;

    public Score(int hundreds, int tens, int units, int strike, int ball) {
        this.hundreds = hundreds;
        this.tens = tens;
        this.units = units;
        this.strike = strike;
        this.ball = ball;
    }

    public int getHundreds() {
        return hundreds;
    }

    public int getTens() {
        return tens;
    }

    public int getUnits() {
        return units;
    }

    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }
}
