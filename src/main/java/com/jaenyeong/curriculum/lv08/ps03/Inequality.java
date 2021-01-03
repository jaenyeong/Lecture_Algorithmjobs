package com.jaenyeong.curriculum.lv08.ps03;

import java.util.Scanner;

public class Inequality {
    /*
    inequality

    [문제]
    두 종류의 부등호 기호 ‘<’와 ‘>’가 k 개 나열된 순서열 A가 있다.
    우리는 이 부등호 기호 앞뒤에 서로 다른 한 자릿수 숫자를 넣어서 모든 부등호 관계를 만족시키려고 한다.
    예를 들어, 제시된 부등호 순서열 A가 다음과 같다고 하자.
    A ⇒ < < < > < < > < >
    부등호 기호 앞뒤에 넣을 수 있는 숫자는 0부터 9까지의 정수이며 선택된 숫자는 모두 달라야 한다.
    아래는 부등호 순서열 A를 만족시키는 한 예이다.
    3 < 4 < 5 < 6 > 1 < 2 < 8 > 7 < 9 > 0
    이 상황에서 부등호 기호를 제거한 뒤, 숫자를 모두 붙이면 하나의 수를 만들 수 있는데 이 수를 주어진 부등호 관계를 만족시키는 정수라고 한다.
    그런데 주어진 부등호 관계를 만족하는 정수는 하나 이상 존재한다.
    예를 들어 3456128790 뿐만 아니라 5689023174도 아래와 같이 부등호 관계 A를 만족시킨다.
    5 < 6 < 8 < 9 > 0 < 2 < 3 > 1 < 7 > 4
    여러분은 제시된 k 개의 부등호 순서를 만족하는 (k+1) 자리의 정수 중에서 최대값과 최소값을 찾아야 한다.
    앞서 설명한 대로 각 부등호의 앞뒤에 들어가는 숫자는 { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 } 중에서 선택해야 하며 선택된 숫자는 모두 달라야 한다.
    프로그램의 실행시간은 0.5초를 넘을 수 없다.

    [입력]
    첫 줄에 부등호 문자의 개수를 나타내는 정수 가 주어진다.
    그 다음 줄에는 k 개의 부등호 기호가 하나의 공백을 두고 한 줄에 모두 제시된다. k 의 범위는 2 <= k <= 9이다.

    [출력]
    여러분은 제시된 부등호 관계를 만족하는 자리의 최대, 최소 정수를 첫째 줄과 둘째 줄에 각각 출력해야 한다.
    단 아래 예(1)과 같이 첫 자리가 0인 경우도 정수에 포함되어야 한다.
    모든 입력에 답은 항상 존재하며 출력 정수는 하나의 문자열이 되도록 해야 한다.

    [예제 입력]
    2
    < >
    [예제 출력]
    897
    021

    [예제 입력]
    9
    > < < < > > > < <
    [예제 출력]
    9567843012
    1023765489

     */

    private static final Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) {
        final int k = SCAN.nextInt();
        final char[] inequalities = new char[k];
        for (int i = 0; i < k; i++) {
            inequalities[i] = SCAN.next().charAt(0);
        }

        SCAN.close();

        final InequalityHandler inequalityHandler = new InequalityHandler(inequalities, k);

        inequalityHandler.getMaxNumber();
        inequalityHandler.getMinNumber();
    }
}

class InequalityHandler {
    private final char[] inequalities;
    private final int numberSize;
    private boolean isFindMinNumber;
    private boolean isFindMaxNumber;

    public InequalityHandler(char[] inequalities, int k) {
        this.inequalities = inequalities;
        this.numberSize = k + 1;
    }

    public void getMaxNumber() {
        final boolean[] isUseNumber = new boolean[10];
        final int[] resultNumber = new int[numberSize];
        getMax(0, isUseNumber, resultNumber);
    }

    private void getMax(final int curIdx, final boolean[] isUseNumber, final int[] resultNumber) {
        // 최댓값을 찾은 경우
        if (isFindMaxNumber) {
            return;
        }

        // 결과 처리
        if (resultProcessing(curIdx, resultNumber)) {
            // 최솟값 플래그 변경
            isFindMaxNumber = true;
            return;
        }

        // 0부터 9까지 반복
        for (int i = 9; i >= 0; i--) {
            // 해당 숫자 사용 여부 확인
            if (isUseNumber[i]) {
                continue;
            }

            // 유효하지 않은 부등식인 경우 통과
            if (invalidEquality(i, curIdx, resultNumber)) {
                continue;
            }

            // 해당 숫자를 사용처리
            isUseNumber[i] = true;
            // 현재 인덱스 자리에 해당 숫자 삽입
            resultNumber[curIdx] = i;

            // 재귀 호출
            getMax(curIdx + 1, isUseNumber, resultNumber);

            isUseNumber[i] = false;
            resultNumber[curIdx] = -1;
        }
    }

    private boolean resultProcessing(final int curIdx, final int[] resultNumber) {
        if (curIdx != numberSize) {
            return false;
        }

        // 결과 출력
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberSize; i++) {
            sb.append(resultNumber[i]);
        }
        System.out.println(sb.toString());

        return true;
    }

    private boolean invalidEquality(final int number, final int curIdx, final int[] resultNumber) {
        if (curIdx == 0) {
            return false;
        }

        final char inEquality = inequalities[curIdx - 1];
        final int previous = resultNumber[curIdx - 1];

        if (inEquality == '>') {
            return previous <= number;
        }

        // '<'
        return previous >= number;
    }

    public void getMinNumber() {
        final boolean[] isUseNumber = new boolean[10];
        final int[] resultNumber = new int[numberSize];
        getMinNumber(0, isUseNumber, resultNumber);
    }

    private void getMinNumber(final int curIdx, final boolean[] isUseNumber, final int[] resultNumber) {
        // 최솟값을 구한 경우
        if (isFindMinNumber) {
            return;
        }

        // 결과 처리
        if (resultProcessing(curIdx, resultNumber)) {
            // 최솟값 플래그 변경
            isFindMinNumber = true;
            return;
        }

        // 0부터 9까지 반복
        for (int i = 0; i < 10; i++) {
            // 해당 숫자 사용 여부 확인
            if (isUseNumber[i]) {
                continue;
            }

            // 유효하지 않은 부등식인 경우 통과
            if (invalidEquality(i, curIdx, resultNumber)) {
                continue;
            }

            // 해당 숫자를 사용처리
            isUseNumber[i] = true;
            // 현재 인덱스 자리에 해당 숫자 삽입
            resultNumber[curIdx] = i;

            // 재귀 호출
            getMinNumber(curIdx + 1, isUseNumber, resultNumber);

            isUseNumber[i] = false;
            resultNumber[curIdx] = -1;
        }
    }
}
