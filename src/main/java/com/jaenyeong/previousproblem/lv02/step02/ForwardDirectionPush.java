package com.jaenyeong.previousproblem.lv02.step02;

import java.util.Scanner;
import java.util.StringJoiner;

public class ForwardDirectionPush {
    /*
    정방향 밀기

    [문제]
    길이가 n인 수열이 있다.
    이 수열의 모든 수가 오른쪽으로 한 칸 씩 밀린 결과를 출력하여라.
    단, 이 수열의 모양은 다음 그림처럼 원형이라서 n-1번 인덱스의 다음 칸은 0번이다.
    즉, n-1번 값은 0번으로 이동한다.
    [forward_direction_push.png]

    [입력]
    첫 줄에 수열의 길이 n이 주어진다.
    두 번째 줄에 수열을 구성하는 수 n 개가 주어진다.
    (3 <= n <= 1000, 1 <= 수열을 구성하는 수 <= n)

    [출력]
    첫 줄에 밀린 후의 수열을 출력한다.

    [예제 입력]
    5
    2 3 1 5 4
    [예제 출력]
    4 2 3 1 5

    [예제 입력]
    5
    1 3 5 4 2
    [예제 출력]
    2 1 3 5 4

    [예제 입력]
    7
    3 7 4 5 2 6 1
    [예제 출력]
    1 3 7 4 5 2 6

    [예제 입력]
    11
    1 9 8 4 5 2 3 7 6 11 10
    [예제 출력]
    10 1 9 8 4 5 2 3 7 6 11

     */

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = SC.nextInt();
        final int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = SC.nextInt();
        }

        SC.close();

        forwardDirectionPush(numbers);
        final StringJoiner sj = new StringJoiner(" ");
        for (int number : numbers) {
            sj.add(Integer.toString(number));
        }
        System.out.println(sj.toString());
    }

    private static void forwardDirectionPush(final int[] numbers) {
        final int lastIdx = numbers.length - 1;
        final int temp = numbers[lastIdx];

        System.arraycopy(numbers, 0, numbers, 1, lastIdx);

        numbers[0] = temp;
    }
}
