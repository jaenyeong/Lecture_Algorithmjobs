package com.jaenyeong.curriculum.lv10.ps03;

import java.util.Scanner;

public class BinarySearch {
    /*
    이진탐색

    [문제]
    n개의 오름차순으로 정렬된 숫자가 주어지고, 그 후 q개의 질문이 주어진다.
    각각의 질문은 특정 숫자가 n개의 숫자 내에 존재하는지를 판별하는 것이다.
    모든 q개의 질문에 대하여 답을 하는 프로그램을 작성하시오.

    [입력]
    첫 번째 줄에 숫자의 개수 n, 그리고 질문의 개수 q가 주어진다. (1 ≤ n ≤ 100,000, 1 ≤ q ≤ 100,000)
    이는 오름차순으로 정렬되어 주어진다.
    두 번째 줄에 n개의 숫자가 주어진다.
    세 번째 줄에 q개의 질문이 주어진다.
    각 수는 21억보다 작은 정수다.

    [출력]
    각 질문에 대하여 숫자가 존재하면 YES, 아니면 NO를 한 줄에 하나씩 출력한다.

    [예제 입력]
    10 4
    1 2 4 8 10 11 12 14 15 19
    4 5 8 17
    [예제 출력]
    YES
    NO
    YES
    NO

     */

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = SC.nextInt();
        final int k = SC.nextInt();

        final int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = SC.nextInt();
        }

        final BinarySearchHandler bs = new BinarySearchHandler(n, numbers);

        for (int i = 0; i < k; i++) {
            final int query = SC.nextInt();

            System.out.println((bs.find(query) ? "YES" : "NO"));
        }

        SC.close();
    }
}

class BinarySearchHandler {
    private final int n;
    private final int[] numbers;

    public BinarySearchHandler(int n, int[] numbers) {
        this.n = n;
        this.numbers = numbers;
    }

    public boolean find(final int target) {
        int start = 0;
        int end = n - 1;

        if (numbers[start] > target) return false;
        if (numbers[end] < target) return false;

        while (start + 1 < end) {
            final int mid = ((start + end) / 2);

            if (numbers[mid] == target) return true;

            if (numbers[mid] < target) {
                start = mid;
                continue;
            }

            end = mid;
        }

        if (numbers[start] == target) return true;

        return numbers[end] == target;
    }
}
