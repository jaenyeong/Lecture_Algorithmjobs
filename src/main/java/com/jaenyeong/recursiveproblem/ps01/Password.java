package com.jaenyeong.recursiveproblem.ps01;

import java.util.Arrays;
import java.util.Scanner;

public class Password {
    /*
    암호

    [문제]
    알고리즘랩스 왕국에서는 컴퓨터의 무분별한 사용을 막기 위해 컴퓨터에 암호를 걸어두었다.
    이 암호는 항상 5자리의 숫자로 구성되며, 각각의 숫자는 0부터 9까지가 가능하다.
    이 5자리의 암호는 날마다 달라진다.
    즉, 1일부터 30일까지의 날짜에 대하여 암호가 매일마다 달라진다.
    암호에는 규칙이 있는데, 암호가 되는 5자리 숫자의 각 자릿수를 모두 더한 후 K로 나누었을 때 그 나머지가 오늘의 날짜와 같아진다는 것이다.
    예를 들어, 오늘이 1일이고 K=10일 경우에는 00001, 00010, 00100, 01000, 10000, 12341 등의 암호가 모두 가능할 수 있는 것이다.
    오늘의 날짜 d와 숫자 K가 주어질 때, 암호가 될 수 있는 5자리 숫자의 경우의 수를 출력하는 프로그램을 작성하시오.

    [입력]
    첫 번째 줄에 날짜 d와 숫자 K가 주어진다. (1 <= d <= 30, 1 <= K <= 100)

    [출력]
    암호가 될 수 있는 5자리 숫자의 경우의 수를 출력한다.

    [예제 입력]
    4 10
    [예제 출력]
    10000

    [예제 입력]
    2 7
    [예제 출력]
    14271

     */

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        final int d = SC.nextInt();
        final int k = SC.nextInt();
        SC.close();

        final PasswordHandler pw = new PasswordHandler(d, k);
        pw.getPassword();
    }
}

class PasswordHandler {
    private static final int PASSWORD_DIGIT = 5;
    private final int[] curPassword = new int[PASSWORD_DIGIT];
    private final int day;
    private final int k;
    private int count;

    public PasswordHandler(int day, int k) {
        this.day = day;
        this.k = k;
        Arrays.fill(curPassword, -1);
    }

    public void getPassword() {
        getPassword(0, 0);
        System.out.println(count);
    }

    private void getPassword(final int step, final int sum) {
        if (step >= PASSWORD_DIGIT) {
            // 각 자릿수의 합을 K로 나눈 값이 주어진 일자와 같은 경우
            final int mod = sum % k;
            if (mod == day) {
                count++;
            }
            return;
        }

        for (int i = 0; i <= 9; i++) {
            curPassword[step] = i;

            getPassword(step + 1, sum + i);
        }
    }
}
