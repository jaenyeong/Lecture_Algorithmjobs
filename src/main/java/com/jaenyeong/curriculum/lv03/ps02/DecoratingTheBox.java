package com.jaenyeong.curriculum.lv03.ps02;

import java.util.Scanner;

public class DecoratingTheBox {
    /*
    상자 꾸미기

    [문제]
    면이 6개인 상자가 있다.
    이를 여러 가지 색종이를 붙여 꾸밀려고 하는데, 단 조건이 있다.
    인접한 면에 같은 색의 색종이를 붙이면 안 된다는 것이다.
    또한, 한 면에는 한 장의 색종이만 붙일 수 있다.
    여러 가지 색의 색종이들이 주어졌을 때, 조건을 만족하여 상자의 모든 면에 붙일 수 있는지 판별하는 프로그램을 작성하시오.

    [입력]
    첫째 줄에 색종이의 장수 N ( 1 <= N <= 1,000 ) 이 주어진다.
    둘째 줄에 각각의 색종이의 색깔을 나타내는 N개의 숫자가 주어진다.
    색깔은 양의 정수로 이루어져 있고, 1부터 N까지의 범위의 수이다.

    [출력]
    조건을 만족하면서 상자를 꾸밀 수 있으면 “YES”, 아니면 “NO”를 출력한다.

    [예제 입력]
    6
    1 2 1 2 1 3
    [예제 출력]
    NO

    [예제 입력]
    6
    1 2 3 1 2 3
    [예제 출력]
    YES

    [예제 입력]
    7
    1 1 1 2 2 3 3
    [예제 출력]
    YES

    [예제 입력]
    8
    1 2 2 2 1 1 1 3
    [예제 출력]
    NO

     */

    private static final Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = SCAN.nextInt();

        // 색종이 초기화
        final int[] colorPapers = new int[n + 1];
        for (int i = 0; i < n; i++) {
            colorPapers[SCAN.nextInt()]++;
        }

        SCAN.close();

        // 색종이 초기화
//        final int[] colorPapers = new int[n];
//        for (int i = 0; i < n; i++) {
//            colorPapers[i] = SCAN.nextInt();
//        }

        // 박스를 꾸밀 수 있는 지 유효성 검사
        System.out.println(validateDecoration(colorPapers, n) ? "YES" : "NO");
    }

    private static boolean validateDecoration(final int[] colorPapers, final int n) {
        // 주어진 색종이가 6장 이하인 경우
        if (n < 6) return false;

        // 색종이 색상 수 확인
        int countOfColor = 0;
        for (int paper = 1; paper <= n; paper++) {
            final int color = colorPapers[paper];
            countOfColor += Math.min(color, 2);
        }

        return countOfColor >= 6;
    }

//    private static boolean validateDecoration(final int[] colorPapers, final int n) {
//        // 주어진 색종이가 6장 이하인 경우
//        if (n < 6) return false;
//
//        // 색종이 색상 수 확인
//        final Set<Integer> colors = new HashSet<>();
//        for (int i = 0; i < n; i++) {
//            colors.add(colorPapers[i]);
//        }
//
//        // 색종이 색상 종류가 3가지 미만인 경우
//        if (colors.size() < 3) return false;
//
//        // 쉽게 구현하기 위해 인덱스와 색종이 색상을 동일하게 초기화
//        final int[] countOfColorPapers = new int[colors.size() + 1];
//        for (int i = 0; i < n; i++) {
//            countOfColorPapers[colorPapers[i]]++;
//        }
//
//        // 색종이 색상 종류가 3가지인 경우
//        if (colors.size() == 3) {
//            // 한 색상의 색종이가 2개 이상인 경우 카운트
//            int countOf2 = 0;
//            for (int color : countOfColorPapers) {
//                if (color >= 2) countOf2++;
//            }
//
//            return countOf2 >= 3;
//        }
//
//        // 색종이 색상 종류가 4가지인 경우
//        if (colors.size() == 4) {
//            // 한 색상의 색종이가 2개 이상인 경우 카운트
//            int countOf2 = 0;
//            for (int color : countOfColorPapers) {
//                if (color >= 2) countOf2++;
//            }
//
//            return countOf2 >= 2;
//        }
//
//        // 그 외
//        return true;
//    }
}
