package com.jaenyeong.curriculum.lv08.ps01;

import java.util.Scanner;

public class FindPermutation {
    /*
    순열 구하기

    [문제]
    서로 다른 n개의 원소들 중에서 r개만을 뽑아 일렬로 나열하는 것을 순열이라 한다.
    예를 들어, 3개의 원소 a, b, c 중에서 2개만을 뽑아 나열하면 ab, ac, ba, bc, ca, cb 의 6가지 경우가 있다.
    n과 r이 주어질 때, n개의 소문자 중에서 r개만을 뽑아 나열하는 모든 경우를 출력하는 프로그램을 작성하시오.
    단, a부터 시작하여 연속으로 n개의 알파벳을 갖고 있다고 하자.

    [입력]
    첫 번째 줄에 n과 r이 주어진다. (1 <= n <= 10, 0 <= r <= min(n, 7))

    [출력]
    각 줄에 n개의 소문자 중에서 r개만을 뽑아 나열하는 경우를 사전순으로 나열한 결과를 출력한다.

    [예제 입력]
    4 2
    [예제 출력]
    ab
    ac
    ad
    ba
    bc
    bd
    ca
    cb
    cd
    da
    db
    dc

     */

    private static final Scanner SCAN = new Scanner(System.in);
    private static final int START_IDX = 0;

    public static void main(String[] args) {
        // 배열 사이즈와 순열을 추출시 원소 개수 입력
        final int n = SCAN.nextInt();
        final int r = SCAN.nextInt();
        SCAN.close();

        // 알파벳 배열 초기화
        final char[] alphabets = new char[n];
        final int a = 'a';
        for (int i = 0; i < n; i++) {
            alphabets[i] = (char) (i + a);
        }

        final Permutation per = new Permutation(alphabets, n, r);

        per.permutation(START_IDX);
    }
}

class Permutation {
    private final char[] alphabets;
    private final boolean[] useAlphabets;
    private final char[] permsAlpha;
    private final int n;
    private final int r;

    public Permutation(final char[] alphabets, final int n, final int r) {
        this.alphabets = alphabets;
        this.useAlphabets = new boolean[n];
        this.permsAlpha = new char[r];
        this.n = n;
        this.r = r;
    }

    public void permutation(final int current) {
        // 해당 순서까지 순열 조합이 완료되면 결과 처리
        if (current >= r) {
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < r; i++) {
                sb.append(permsAlpha[i]);
            }
            // 결과 출력
            System.out.println(sb.toString());
            return;
        }

        // 알파벳 배열 크기인 n만큼 반복
        for (int i = 0; i < n; i++) {
            // 해당 알파벳을 사용했는지 확인
            if (useAlphabets[i]) {
                continue;
            }

            // 사용하지 않은 알파벳을 사용처리 하고 해당 알파벳을 순열에 삽입
            useAlphabets[i] = true;
            permsAlpha[current] = alphabets[i];
            permutation(current + 1);

            permsAlpha[current] = 'A';
            useAlphabets[i] = false;
        }
    }
}
