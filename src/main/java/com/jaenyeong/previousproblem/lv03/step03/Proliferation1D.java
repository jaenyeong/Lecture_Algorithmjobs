package com.jaenyeong.previousproblem.lv03.step03;

import java.util.Scanner;
import java.util.StringJoiner;

public class Proliferation1D {
    /*
    1차원 확산

    [문제]
    0 과 1 로 이루어진 길이가 N 인 수열이 주어진다.
    이 수열에서 1 과 인접한 0 을 모두 1 로 바꾼 수열을 출력하여라.
    [proliferation_1d.png]

    [입력]
    첫 줄에 N 이 주어진다.
    두 번째 줄에 0 과 1 로 이루어진 수열이 주어진다.
    (1 <= N <= 1,000)

    [출력]
    1 과 인접한 0 을 모두 1 로 바꾼 수열을 출력한다.

    [예제 입력]
    6
    1 0 0 0 1 0
    [예제 출력]
    1 1 0 1 1 1

    [예제 입력]
    6
    1 0 0 0 0 1
    [예제 출력]
    1 1 0 0 1 1

    [예제 입력]
    8
    0 1 0 0 1 0 0 1
    [예제 출력]
    1 1 1 1 1 1 1 1

    [예제 입력]
    10
    1 0 0 0 1 1 0 0 0 1
    [예제 출력]
    1 1 0 1 1 1 1 0 1 1

     */

    private static final Scanner SC = new Scanner(System.in);
    public static final int VIRUS = 1;

    public static void main(String[] args) {
        final int n = SC.nextInt();

        final int[] origin = new int[n];
        for (int i = 0; i < n; i++) {
            origin[i] = SC.nextInt();
        }

        SC.close();

        final int[] copy = new int[n];

        System.arraycopy(origin, 0, copy, 0, n);

        for (int i = 0; i < n; i++) {
            if (origin[i] != VIRUS) {
                continue;
            }

            if (i > 0) {
                copy[i - 1] = VIRUS;
            }

            if (i < (n - 1)) {
                copy[i + 1] = VIRUS;
            }
        }

        final StringJoiner sj = new StringJoiner(" ");
        for (int i = 0; i < n; i++) {
            sj.add(Integer.toString(copy[i]));
        }

        System.out.println(sj);
    }
}
