package com.jaenyeong.curriculum.lv14.ps02;

import java.util.Scanner;

public class GoodSeq {
    /*
    good seq

    [문제]
    숫자 1, 2, 3으로만 이루어지는 수열이 있다.
    임의의 길이의 인접한 두 개의 부분 수열이 동일한 것 이 있으면, 그 수열을 나쁜 수열이라고 부른다.
    그렇지 않은 수열은 좋은 수열이다.
    다음은 나쁜 수열의 예이다.
    33
    32121323
    123123213
    다음은 좋은 수열의 예이다.
    2
    32
    32123
    1232123
    길이가 N인 좋은 수열들을 N자리의 정수로 보아 그중 가장 작은 수를 나타내는 수열을 구하는 프로그램 을 작성하라.
    예를 들면, 1213121과 2123212는 모두 좋은 수열이지만 그 중에서 작은 수를 나타내는 수 열 1213121이다.

    [입력]
    입력은 숫자 N 하나로 이루어진다. N은 1 이상 80 이하이다.

    [출력]
    첫 번째 줄에 1, 2, 3으로만 이루어져 있는 길이가 N인 좋은 수열들 중에서 가장 작은 수를 나타내 는 수열만 출력한다.
    수열을 이루는 1, 2, 3들 사이에는 빈칸을 두지 않는다.

    [예제 입력]
    7
    [예제 출력]
    1213121

     */

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = SC.nextInt();
        SC.close();

        final BackTracking bt = new BackTracking(n);
        bt.recursiveSolve(0);
    }
}

class BackTracking {
    private static final int START = 1;
    private static final int END = 3;

    private final int givenDigit;
    private final int[] resultArr;
    private boolean finished;

    public BackTracking(int givenDigit) {
        this.givenDigit = givenDigit;
        this.resultArr = new int[givenDigit];
    }

    public void recursiveSolve(final int step) {
        if (finished) {
            return;
        }

        if (step >= givenDigit) {
            printResult();

            finished = true;
            return;
        }

        // 1 ~ 3까지 반복하며 각 자리의 직접 삽입하여 확인
        for (int i = START; i <= END; i++) {
            resultArr[step] = i;

            if (isDuplicateSubList(step)) {
                continue;
            }

            recursiveSolve(step + 1);
        }
    }

    private void printResult() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < givenDigit; i++) {
            sb.append(resultArr[i]);
        }

        System.out.println(sb);
    }

    private boolean isDuplicateSubList(final int step) {
        final int sizeLimit = (step + 1) / 2;
        for (int subListSize = 1; subListSize <= sizeLimit; subListSize++) {
            if (checkDuplicateEachSubList(step, subListSize)) {
                return true;
            }
        }

        return false;
    }

    private boolean checkDuplicateEachSubList(final int step, final int subListSize) {
        for (int i = 0; i < subListSize; i++) {
            if (resultArr[step - i] != resultArr[step - i - subListSize]) {
                return false;
            }
        }

        return true;
    }
}
