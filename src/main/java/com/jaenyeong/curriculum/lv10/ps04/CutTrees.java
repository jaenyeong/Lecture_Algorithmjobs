package com.jaenyeong.curriculum.lv10.ps04;

import java.util.Arrays;
import java.util.Scanner;

public class CutTrees {
    /*
    나무 자르기

    [문제]
    얼마전에 큰맘 먹고 새로운 절단기를 구입한 목수 윤성이는 나무 M미터가 필요하다.
    절단기는 다음과 같이 동작한다.
    먼저, 윤성이는 절단기에 높이 H를 지정해야 한다.
    높이를 지정하면 톱날이 땅으로부터 H미터 위로 올라간다.
    그 다음, 한 줄에 연속해있는 나무를 모두 절단해버린다.
    따라서, 높이가 H보다 큰 나무는 H 위의 부분이 잘릴 것이고, 낮은 나무는 잘리지 않을 것이다.
    예를 들어, 한 줄에 연속해있는 나무의 높이가 20, 15, 10, 17이라고 하자.
    상근이가 높이를 15로 지정했다면, 나무를 자른 뒤의 높이는 15, 15, 10, 15가 될 것이고,
    윤성이는 길이가 5인 나무와 2인 나무를 들고 집에 갈 것이다. (총 7미터를 집에 들고 간다)
    목수 윤성이는 과유불급을 좌우명으로 삼고 있기에 나무를 필요한 만큼만 가져가려고 한다.
    이때 적어도 M미터의 나무를 가져가기 위해서 절단기에 설정할 수 있는 높이의 최대값을 구하는 프로그램을 작성하라.

    [입력]
    첫째 줄에 나무의 수 N과 윤성이가 집으로 가져가려고 하는 나무의 길이 M이 주어진다. (1 <= N <= 1,000,000, 1 <= M <= 2,000,000,000)
    둘째 줄에는 나무의 높이가 주어진다.
    나무의 높이의 합은 항상 M을 넘기 때문에, 윤성이는 집에 필요한 나무를 항상 가져갈 수 있다.
    높이는 1,000,000,000보다 작거나 같은 자연수이다.

    [출력]
    적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최대값을 출력한다.

    [예제 입력]
    4 7
    20 15 10 17
    [예제 출력]
    15

     */

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        final int numberOfTree = SC.nextInt();
        final int needHeight = SC.nextInt();

        final long[] trees = new long[numberOfTree];
        for (int i = 0; i < numberOfTree; i++) {
            trees[i] = SC.nextInt();
        }

        SC.close();

        Arrays.sort(trees);

        long shortestHeight = 0;
        long longestHeight = trees[numberOfTree - 1];

        while (shortestHeight + 1 < longestHeight) {
            final long midHeight = (shortestHeight + longestHeight) / 2;

            final long heightSum = cuttingBy(midHeight, trees);

            if (heightSum >= needHeight) {
                shortestHeight = midHeight;
                continue;
            }

            longestHeight = midHeight;
        }

        System.out.println(shortestHeight);
    }

    private static long cuttingBy(final long midHeight, final long[] trees) {
        long heightSum = 0;
        for (long treeHeight : trees) {
            heightSum += Math.max(treeHeight - midHeight, 0);
        }
        return heightSum;
    }
}

