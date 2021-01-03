package com.jaenyeong.curriculum.lv08.ps04;

import java.util.Scanner;

public class Tobin {
    /*
    tobin

    [문제]
    두 정수 n, k를 입력받아 k개의 1을 가진 n자리 이진 패턴을 출력하는 프로그램을 작성하세요.

    [입력]
    두 정수 n, k가 입력으로 주어진다. (0 < n <= 30, 0 <= k < 8, n >= k)

    [출력]
    결과를 내림차순으로 출력한다.

    [예제 입력]
    2 1
    [예제 출력]
    10
    01

    [예제 입력]
    2 0
    [예제 출력]
    00

    [예제 입력]
    4 2
    [예제 출력]
    1100
    1010
    1001
    0110
    0101
    0011

     */

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = SC.nextInt();
        final int k = SC.nextInt();
        SC.close();

        final BinaryPermutation bp = new BinaryPermutation(n, k);
        bp.perm(0, 0);
    }
}

class BinaryPermutation {
    private final int n;
    private final int k;
    private final int[] binaryNumbers;

    public BinaryPermutation(int n, int k) {
        this.n = n;
        this.k = k;
        this.binaryNumbers = new int[n];
    }

    public void perm(final int step, final int oneCount) {
        if (step == n) {
            if (oneCount != k) {
                return;
            }

            final StringBuilder sb = new StringBuilder();
            for (int b : binaryNumbers) {
                sb.append(b);
            }
            System.out.println(sb);
            return;
        }

        for (int i = 1; i >= 0; i--) {
            if ((oneCount == k) && (i == 1)) {
                continue;
            }

            this.binaryNumbers[step] = i;
            perm(step + 1, (i == 1) ? oneCount + 1 : oneCount);
            this.binaryNumbers[step] = -1;
        }
    }
}
