package com.jaenyeong.curriculum.lv10.ps02;

import java.util.Scanner;

public class TimesTableOfN {
    /*
    NN단표

    [문제]
    알랩이는 구구단표처럼 NN단표를 만들었다고 한다.
    NN단표는 2차원 배열의 모양으로 곱셈 1단부터 N단까지의 값들을 적어놓은 형태이다.
    NN단표의 배열을 A라고 했을 때, 배열의 들어가는 수 A[i][j] = i * j이다. (즉, 4행 7열에는 28, 7행 5열에는 35가 들어가 있다.)
    알랩이는 N단까지 나온 숫자들 중에서 K번째로 작은 수를 찾고 싶어한다.
    이때, 중복되는 여러 수들을 고려한다.
    즉 N*N개의 모든 수들 중에서 K번째 수를 구하는 것이다.

    [입력]
    첫째 줄에 배열의 크기 N이 주어진다.
    N은 100,000보다 작거나 같은 자연수이다.
    둘째 줄에 K가 주어진다.
    K는 N*N보다 작거나 같은 자연수이다.

    [출력]
    K번째 원소를 출력한다.

    [예제 입력]
    3
    7
    [예제 출력]
    6

     */

    private static final Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = SCAN.nextInt();
        final long k = SCAN.nextLong();
        SCAN.close();

        // 이진 탐색으로 원소를 선택하며 범위를 줄여 나가기
        System.out.println(binarySearch(n, k));
    }

    private static long binarySearch(final int n, final long k) {
        // start, end 정의
        // start : 항상 정답보다 작거나 같은 숫자
        // end   : 항상 정답보다 큰 숫자

        long start = 1;
        // End는 mid 값의 포함이 될 수 없기 때문에 End의 값을 (n * n)으로 초기화하면 탐색 시도를 하지 않음
        // 따라서 End를 ((n * n) + 1)로 초기화
        long end = (n * n) + 1;

        while ((start + 1) < end) {
            final long mid = ((start + end) / 2);

            // 선택된 원소가 몇 번째로 작은 수인지 구하기
            final long orderNumber = findWhatOrderTheNumber(n, mid);

            // 주어진 k와 해당 원소의 순서 비교
            // 없는 숫자를 찾을 수도 있기 때문에 같은 값이 나와도 범위를 좁히는 처리
            if (orderNumber <= k) {
                start = mid;
                continue;
            }

            end = mid;
        }

        return start;
    }

    private static long findWhatOrderTheNumber(final int n, final long key) {
        long orderCount = 0;

        // n 변수가 int 타입이기 때문에 i 변수를 long 타입으로 선언
        for (long i = 1; i <= n; i++) {
            // 해당 행의 마지막 값이 주어진 k보다 작은 경우
            if ((n * i) < key) {
                orderCount += n;
                continue;
            }

            // key가 해당 행으로 나눠 떨어지면 1 빼기
            if ((key % i) == 0) {
                orderCount += ((key / i) - 1);
                continue;
            }

            // 그 외
            orderCount += (key / i);
        }

        // 이 때 orderCount는 key보다 작은 숫자의 개수이기 때문에 1을 더하여 반환
        return orderCount + 1;
    }
}
