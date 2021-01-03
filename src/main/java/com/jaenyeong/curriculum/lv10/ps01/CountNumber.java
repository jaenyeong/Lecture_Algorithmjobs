package com.jaenyeong.curriculum.lv10.ps01;

import java.util.Scanner;

public class CountNumber {
    /*
    숫자 개수 세기

    [문제]
    n개의 숫자가 주어지고, q개의 질문이 주어진다.
    각각의 질문은 n개의 숫자 중에서 특정 숫자가 몇개나 있는지를 묻는다.
    q개의 질문에 모두 답하는 프로그램을 작성하시오.

    [입력]
    첫 번째 줄에 숫자의 개수 n, 그리고 질문의 개수 q가 주어진다. (1 <= n <= 100,000, 1 <= q <= 100,000)
    두 번째 줄에 n개의 숫자가 주어진다.
    세 번째 줄에 q개의 질문이 주어진다.
    주어지는 q개의 질문에 해당하는 숫자 범위는 100,000,000이하이다.

    [출력]
    각 질문에 대하여 숫자의 개수를 한 줄에 하나씩 출력한다.

    [예제 입력]
    10 4
    1 3 4 3 2 3 1 2 5 10
    1 3 9 10
    [예제 출력]
    2
    3
    0
    1

     */

    private static final Scanner SCAN = new Scanner(System.in);
    private static final int NOT_FOUND = -1;

    public static void main(String[] args) {
        final int n = SCAN.nextInt();
        final int q = SCAN.nextInt();

        // 배열 초기화
        final int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = SCAN.nextInt();
        }

        // Question 초기화
        final int[] questions = new int[q];
        for (int i = 0; i < q; i++) {
            questions[i] = SCAN.nextInt();
        }

        SCAN.close();

        // Quick Sort
        quickSort(numbers, 0, numbers.length - 1);

        for (int target : questions) {
            // 최솟값 인덱스 구함
            final int minIdx = searchMinIndex(numbers, n, target);
            if (minIdx == NOT_FOUND) {
                System.out.println(0);
                continue;
            }

            // 최댓값 인덱스 구함
            final int maxIdx = searchMaxIndex(numbers, n, target);

            // 찾은 인덱스들을 뺄셈연산하여 길이(개수) 확인
            System.out.println((maxIdx - minIdx) + 1);
        }
    }

    private static void quickSort(final int[] numbers, final int start, final int end) {
        // 범위안에 원소 개수가 1이하인 경우
        if (start >= end) {
            return;
        }

        // 변수 초기화
        final int pivot = start;
        int left = start + 1;
        int right = end;

        // 반복하며 해당 값을 찾음
        while (left <= right) {
            // 왼쪽 (인덱스 범위 내에서 피벗 값보다 작은 값들만 찾음)
            while ((left <= end) && (numbers[left] <= numbers[pivot])) {
                left++;
            }

            // 오른쪽 (인덱스 범위 내에서 피벗 값보다 큰 값들만 찾음)
            while ((right > start) && (numbers[pivot] <= numbers[right])) {
                right--;
            }

            // 해당 범위가 교차한 경우
            // 오른쪽 인덱스와 피벗의 값을 맞교환
            if (left > right) {
                final int pivotValue = numbers[pivot];
                numbers[pivot] = numbers[right];
                numbers[right] = pivotValue;
                continue;
            }

            // 교차하지 않은 경우
            // 왼쪽 인덱스와 오른쪽 인덱스에 해당 값을 맞교환
            final int temp = numbers[left];
            numbers[left] = numbers[right];
            numbers[right] = temp;
        }

        // 왼쪽 영역 정렬 (재귀호출)
        quickSort(numbers, start, right - 1);
        // 오른쪽 영역 정렬 (재귀호출)
        quickSort(numbers, right + 1, end);
    }

    private static int searchMinIndex(final int[] numbers, final int n, final int target) {
        final int begin = 0;
        final int last = n - 1;

        // 해당 영역에 값이 없는 경우
        if (numbers[begin] > target) return NOT_FOUND;
        if (numbers[last] < target) return NOT_FOUND;

        // 맨 처음 값이 타겟가 동일한 경우
        if (numbers[begin] == target) return begin;

        // start, end 정의
        // start : target 보다 작은 값
        // end   : target 보다 크거나 같은 값
        int start = 0;
        int end = last;

        while ((start + 1) < end) {
            final int mid = (start + end) / 2;

            if (numbers[mid] < target) {
                start = mid;
                continue;
            }

            end = mid;
        }

        // END는 target 보다 항상 크거나 같은 값이기 때문에
        if (numbers[end] == target) return end;

        return NOT_FOUND;
    }

    private static int searchMaxIndex(final int[] numbers, final int n, final int target) {
        final int begin = 0;
        final int last = n - 1;

        // 해당 영역에 값이 없는 경우
        if (numbers[begin] > target) return NOT_FOUND;
        if (numbers[last] < target) return NOT_FOUND;

        // 맨 마지막 값이 타겟가 동일한 경우
        if (numbers[last] == target) return last;

        // start, end 정의
        // start : target 보다 작거나 같은 값
        // end   : target 보다 큰 값
        int start = 0;
        int end = last;

        while ((start + 1) < end) {
            final int mid = (start + end) / 2;

            if (numbers[mid] <= target) {
                start = mid;
                continue;
            }

            end = mid;
        }

        if (numbers[start] == target) return start;

        return NOT_FOUND;
    }
}
