package com.jaenyeong.curriculum.lv02.ps03;

import java.util.Scanner;

public class NumberPyramid {
    /*
    숫자 피라미드

    [문제]
    N과 시작 숫자 S가 주어지면 숫자 피라미드를 만드는 프로그램을 작성하시오.
    예를 들어, N이 5이고 S가 3 이라면, 그 숫자 피라미드는 다음과 같다.
        3
       456
      21987
     3456789
    987654321
    - 시작 숫자 S는 꼭대기부터 1씩 증가한다.
    - 시작 행의 번호가 1번이라고 했을때, 짝수번째 행은 왼쪽에서 오른쪽으로 1씩 증가하도록 적고, 홀수번째 행은 거꾸로 적는다.
    - 숫자가 만약 10이 될 경우, 1로 바꾸고 다시 증가한다.

    [입력]
    입력의 첫 번째 줄에 N과 시작 숫자 S가 주어진다. (1 <= N <= 100, 1 <= S <= 9)

    [출력]
    첫 번째 줄부터 숫자 피라미드를 출력한다. (각 줄에 존재하는 공백의 개수와 숫자의 개수를 정확하게 확인해주시바랍니다.)

    [예제 입력]
    5 3
    [예제 출력]
        3
       456
      21987
     3456789
    987654321

     */

    private static final Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = SCAN.nextInt();
        final int s = SCAN.nextInt();

        SCAN.close();

        // 출력할 숫자
        int currentNumber = s;
        // 출력할 공백 개수
        int emptyCount = n - 1;

        // 한 라인의 열 전체 개수
        final int totalSpaceOfLine = getCountOfPrint(n);

        for (int row = 1; row <= n; row++) {
            // 해당 라인에 출력될 문자열
            final StringBuilder lineNumberList = new StringBuilder();

            // 현재 가리키는 위치
            int curPosition = 0;

            // 앞에 공백 추가
            for (int i = 0; i < emptyCount; i++) {
                curPosition = addEmptySpace(lineNumberList, curPosition);
            }

            // 라인에 출력할 숫자 개수
            final int numberLimit = getCountOfPrint(row);

            // Print odd line
            if (row % 2 != 0) {
                int startNumber = currentNumber;

                for (int i = 1; i < numberLimit; i++) {
                    startNumber++;
                    if (startNumber == 10) startNumber = 1;
                }

                currentNumber = startNumber;

                for (int printPosition = 0; printPosition < numberLimit; printPosition++) {
                    lineNumberList.append(startNumber);

                    startNumber--;
                    if (startNumber == 0) startNumber = 9;

                    // 현재 가리키는 위치 증가
                    curPosition++;
                }

                currentNumber++;
            }
            // Print even line
            else {
                if (currentNumber == 10) currentNumber = 1;

                for (int printPosition = 0; printPosition < numberLimit; printPosition++) {
                    lineNumberList.append(currentNumber);

                    currentNumber++;
                    if (currentNumber == 10) currentNumber = 1;

                    // 현재 가리키는 위치 증가
                    curPosition++;
                }
            }

            // 뒤에 공백 추가
            for (int nextSpace = curPosition; nextSpace < totalSpaceOfLine; nextSpace++) {
                curPosition = addEmptySpace(lineNumberList, curPosition);
            }

            // 공백 개수 감소
            emptyCount--;

            System.out.println(lineNumberList.toString());
        }
    }

    private static int getCountOfPrint(final int n) {
        return (2 * n) - 1;
    }

    private static int addEmptySpace(final StringBuilder lineNumberList, int curPosition) {
        lineNumberList.append(" ");
        // 현재 출력되는 위치 값 증가
        curPosition++;
        return curPosition;
    }
}
