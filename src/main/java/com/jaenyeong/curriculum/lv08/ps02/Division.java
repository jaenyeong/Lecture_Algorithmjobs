package com.jaenyeong.curriculum.lv08.ps02;

import java.util.Scanner;
import java.util.StringJoiner;

public class Division {
    /*
    division

    [문제]
    임의의 자연수는 그보다 작은 자연수들의 합으로 표현될 수 있다.
    예를 들어 4의 경우,
    4
    = 3+1
    = 2+2
    = 2+1+1
    = 1+1+1+1
    위와 같은 방법으로 표현 될 수 있다.
    이 때 , 숫자의 구성이 같으면서 그 순서만이 다른 경우는 같은 경우로 생각하는데, 예를 들어 다음 세 가지 경우는 모두 같은 경우이다.
    2 + 1 + 1, 1 + 2 + 1 , 1 + 1 + 2
    자연수 n을 입력 받아 이를 n보다 작은 자연수들의 합으로 나타내는 방법을 모두 출력하는 프로그램을 재귀 호출을 사용하여 작성하시오.

    [입력]
    첫 줄에 2 이상 20 이하의 자연수 n이 주어진다.

    [출력]
    첫째 줄부터 모든 방법을 한 줄에 하나씩 출력한다.
    하나의 식 안에는 큰 숫자가 앞으로 오도록 하고, 전체적으로는 앞의 숫자가 큰 식이 먼저 출력되도록 한다.
    숫자와 더하기 사이에는 공백이 없다.
    그리고 마지막 줄에는 나누어진 자연수의 개수를 출력한다.

    [예제 입력]
    5
    [예제 출력]
    14+1
    3+2
    3+1+1
    2+2+1
    2+1+1+1
    1+1+1+1+1
    6

     */

    private static final Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = SCAN.nextInt();
        SCAN.close();

        final DivisionHandler divisionHandler = new DivisionHandler(n);
        System.out.println(divisionHandler.printResult());
    }
}

class DivisionHandler {
    private final int n;
    private final int[] printArr;
    private int count = 0;

    public DivisionHandler(int n) {
        this.n = n;
        this.printArr = new int[n];
    }

    public int printResult() {
        this.recursiveSolve(0, 0);
        return count;
    }

    private void recursiveSolve(final int currentIdx, final int currentSum) {
        // check base condition
        // 배열의 속한 값이 n(최종값)과 같은 경우
        if (currentSum == n) {
            final StringJoiner sj = new StringJoiner("+");
            for (int i = 0; i < n; i++) {
                // 더할 값이 없다면 탈출
                if (printArr[i] == 0) {
                    break;
                }

                sj.add(Integer.toString(printArr[i]));
            }

            // 완성된 식 출력
            System.out.println(sj.toString());

            // 유효한 식 중 중복되지 않는 경우의 수 카운트
            count++;
            return;
        }

        // 시작 값 초기화
        final int start = (currentIdx == 0) ? (n - 1) : (n - currentSum);
        // 시작값부터 1까지 감소하며 반복
        for (int value = start; value >= 1; value--) {
            // 현재 값이 그 전에 삽입된 값보다 큰 경우 통과
            if ((currentIdx > 0) && (printArr[currentIdx - 1] < value)) {
                continue;
            }
            // 배열 현재 인덱스 위치에 값 삽입
            printArr[currentIdx] = value;

            // 재귀 호출
            recursiveSolve((currentIdx + 1), (currentSum + value));
            // 다시 초기화
            printArr[currentIdx] = 0;
        }
    }
}
