package com.jaenyeong.previousproblem.lv02.step05;

import java.util.Scanner;
import java.util.StringJoiner;

public class OneDimensionalLookUp {
    /*
    1차원 조회

    [문제]
    길이가 n인 수열이 있다.
    어떤 i번 인덱스와 j번 인덱스에 대해 i와 j의 차이가 1이면 "두 인덱스가 인접하다"고 표현한다.
    이 수열의 각 인덱스에 대해 인접한 인덱스에 자신과 같은 수가 있으면 1을, 없으면 0을 출력하여라.
    [one_dimensional_look_up.png]

    [입력]
    첫 줄에 수열의 길이 n이 주어진다.
    두 번째 줄에 수열을 구성하는 수 n개가 주어진다.
    (3 <= n, m <= 1,000, 1 <= 수열을 구성하는 수 <= n)

    [출력]
    첫 줄에 0번 인덱스부터 n-1번 인덱스까지 각 인덱스마다 인접한 인덱스에 같은 수가 있다면 1을, 없다면 0을 출력한다.

    [예제 입력]
    5
    2 3 1 1 4
    [예제 출력]
    0 0 1 1 0

    [예제 입력]
    5
    1 2 1 3 3
    [예제 출력]
    0 0 0 1 1

    [예제 입력]
    7
    2 2 2 3 3 3 4
    [예제 출력]
    1 1 1 1 1 1 0

    [예제 입력]
    11
    1 2 3 4 5 6 7 8 9 10 11
    [예제 출력]
    0 0 0 0 0 0 0 0 0 0 0

     */

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = SC.nextInt();

        final int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = SC.nextInt();
        }

        SC.close();

        final int[] searches = new int[n];

        for (int i = 0; i < (n - 1); i++) {
            if (numbers[i] == numbers[i + 1]) {
                searches[i] = 1;
                searches[i + 1] = 1;
            }
        }

        final StringJoiner sj = new StringJoiner(" ");
        for (int s : searches) {
            sj.add(Integer.toString(s));
        }

        System.out.println(sj);
    }
}
