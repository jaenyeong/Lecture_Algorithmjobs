package com.jaenyeong.previousproblem.lv03.step01;

import java.util.Scanner;
import java.util.StringJoiner;

public class BrushUpPush {
    /*
    밀기 복습

    [문제]
    길이가 m인 수열이 n개 존재한다.
    n, m, q와 이 수열들의 초기 상태가 0번 수열부터 n-1번 수열까지 순서대로 주어진다.
    각 질문은 세 정수 f, x, y로 주어지는데,
    x가 1이면 f번 수열을 오른쪽으로 y칸 밀어낸 결과를,
    x가 2면 f번 수열을 왼쪽으로 y칸 밀어낸 결과를 출력하여라.
    단, 각 수열의 모양은 다음 그림처럼 원형이라서 n-1번 인덱스의 다음 칸은 0번이다.
    각 질문에 의해 밀린 수열은 원래대로 복구하지 않는다.
    즉, 같은 수열을 여러 번 밀게 된다면 이전에 밀린 상태에서 추가로 밀어서 출력한다.
    [brush_up_push.png]

    [입력]
    첫 줄에 수열의 수 n과 수열의 길이 m, 질문의 수 q가 주어진다.
    두 번째 줄부터 n개의 줄에 걸쳐 각 수열을 구성하는 수 m개가 주어진다.
    세 번째 줄부터 q개의 줄에 걸쳐 f, x, y가 주어진다.
    (3 <= n, m <= 1,000, 1 <= q <= 10   0, 0 <= f <= (n - 1), 1 <= 수열을 구성하는 수, y <= m, 1 <= x <= 2)

    [출력]
    q줄에 각 질문에 대한 답을 출력한다.

    [예제 입력]
    4 5 3
    2 3 1 5 4
    1 3 5 4 2
    4 5 1 2 3
    1 3 5 2 4
    0 1 2
    0 2 1
    1 2 3
    [예제 출력]
    5 4 2 3 1
    4 2 3 1 5
    4 2 1 3 5

     */

    private static final Scanner SC = new Scanner(System.in);
    private static final int PUSH_RIGHT = 1;

    public static void main(String[] args) {
        final int theNumberOfArr = SC.nextInt();
        final int arrSize = SC.nextInt();
        final int commands = SC.nextInt();

        final int[][] seq = new int[theNumberOfArr][arrSize];

        for (int i = 0; i < theNumberOfArr; i++) {
            for (int j = 0; j < arrSize; j++) {
                seq[i][j] = SC.nextInt();
            }
        }

        for (int c = 0; c < commands; c++) {
            final int numOfArr = SC.nextInt();
            final int direction = SC.nextInt();
            final int numOfPush = SC.nextInt();

            final int[] target = seq[numOfArr];
            for (int push = 0; push < numOfPush; push++) {

                if (direction == PUSH_RIGHT) {
                    pushRight(target);
                } else {
                    pushLeft(target);
                }
            }

            final StringJoiner sj = new StringJoiner(" ");
            for (int v : target) {
                sj.add(Integer.toString(v));
            }
            System.out.println(sj);
        }

        SC.close();
    }

    private static void pushRight(final int[] numbers) {
        final int limit = numbers.length;
        final int lastIdx = limit - 1;
        final int temp = numbers[lastIdx];

        System.arraycopy(numbers, 0, numbers, 1, lastIdx);

        numbers[0] = temp;
    }

    private static void pushLeft(final int[] numbers) {
        final int limit = numbers.length;
        final int lastIdx = limit - 1;
        final int temp = numbers[0];

        if (lastIdx >= 0) System.arraycopy(numbers, 1, numbers, 0, lastIdx);

        numbers[lastIdx] = temp;
    }
}
