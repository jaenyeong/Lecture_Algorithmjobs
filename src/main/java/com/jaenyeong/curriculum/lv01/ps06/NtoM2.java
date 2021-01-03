package com.jaenyeong.curriculum.lv01.ps06;

import java.util.Scanner;
import java.util.StringJoiner;

public class NtoM2 {
    /*
    N to M 2

    [문제]
    N부터 M까지 출력하는 프로그램을 작성해보자.

    [입력]
    첫째 줄에 자연수 N과 자연수 M이 공백을 가지고 주어진다. (N <= M <= 1,000)

    [출력]
    첫째 줄에 N부터 M까지의 자연수를 공백을 사이에 두고 차례대로 출력한다. (단, 한 줄에 최대 8개씩 출력해야 한다.)

    [예제 입력]
    1 7
    [예제 출력]
    1 2 3 4 5 6 7

    [예제 입력]
    500 512
    [예제 출력]
    500 501 502 503 504 505 506 507
    508 509 510 511 512

     */

    private static final Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = SCAN.nextInt();
        final int m = SCAN.nextInt();
        SCAN.close();

        StringJoiner sj = new StringJoiner(" ");
        int count = 0;
        for (int i = n; i <= m; i++) {
            if (count == 8) {
                System.out.println(sj.toString());
                sj = new StringJoiner(" ");
                count = 0;
            }

            sj.add(Integer.toString(i));
            count++;
        }

        if (sj.length() > 0) System.out.println(sj);
    }
}
