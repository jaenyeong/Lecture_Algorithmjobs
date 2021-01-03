package com.jaenyeong.curriculum.lv15.ps01;

import java.util.Scanner;

public class MaxSumOfInARowNumber {
    /*
    연속 부분 최대

    [문제]
    N개의 정수가 주어질 때, 연속된 부분을 선택하여 합을 최대화 하는 프로그램을 작성하시오.
    예를 들어, 아래와 같이 8개의 숫자가 있을 경우, 색칠된 부분을 선택했을 때 그 합이 가장 최대가 된다.
    [max_sum_of_in_a_row_number.png]

    [입력]
    첫 번째 줄에 n이 주어진다. (1 <= n <= 100,000) 두 번째 줄에 n개의 정수가 주어진다.

    [출력]
    연속된 부분을 선택하였을 때의 최댓값을 출력한다.

    [예제 입력]
    8
    2 3 -5 8 -3 4 2 -9
    [예제 출력]
    11

    [예제 입력]
    5
    -1 -2 3 -2 4
    [예제 출력]
    5

     */

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = SC.nextInt();

        final int[] seq = new int[n];
        for (int i = 0; i < n; i++) {
            seq[i] = SC.nextInt();
        }

        SC.close();

        System.out.println(getMaxSum(seq, 0, (n - 1)));
    }

    private static int getMaxSum(final int[] seq, final int start, final int end) {
        if (start == end) {
            return seq[start];
        }

        final int mid = ((start + end) / 2);

        final int leftMax = getMaxSum(seq, start, mid);
        final int rightMax = getMaxSum(seq, mid + 1, end);
        final int midMax = getMidMaxSum(seq, start, end, mid);

        return Math.max(leftMax, Math.max(rightMax, midMax));
    }

    private static int getMidMaxSum(final int[] seq, final int start, final int end, final int mid) {
        final int leftResultMax = getLeftResultMax(seq, start, mid);
        final int rightResultMax = getRightResultMax(seq, mid, end);

        return leftResultMax + rightResultMax;
    }

    private static int getLeftResultMax(final int[] seq, final int start, final int mid) {
        int leftResultMax = seq[mid];
        int leftMaxSum = seq[mid];

        for (int i = (mid - 1); i >= start; i--) {
            leftMaxSum += seq[i];
            leftResultMax = Math.max(leftResultMax, leftMaxSum);
        }

        return leftResultMax;
    }

    private static int getRightResultMax(final int[] seq, final int mid, final int end) {
        int rightResultMax = seq[mid + 1];
        int rightMaxSum = seq[mid + 1];

        for (int i = (mid + 2); i <= end; i++) {
            rightMaxSum += seq[i];
            rightResultMax = Math.max(rightResultMax, rightMaxSum);
        }

        return rightResultMax;
    }
}
