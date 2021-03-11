package com.jaenyeong.curriculum.lv06.ps01;

import java.util.Scanner;

public class SubtractionOfLargeDigits {
    /*
    큰 자릿수 뺄셈

    [문제]
    두 자연수가 주어질 때 그들의 뺄셈을 계산하는 프로그램을 작성하시오.
    각 수는 1 이상 10의 100승 미만의 범위를 가진다.

    [입력]
    첫 번째 줄과 두 번째 줄에 각각 하나의 자연수가 주어진다.

    [출력]
    첫 번째 줄에 뺄셈의 결과를 출력한다.

    [예제 입력]
    1962831868293922
    2528838232
    [예제 출력]
    1962829339455690

    [예제 입력]
    18592918185
    2195812983912895799129
    [예제 출력]
    -2195812983894302880944

     */

    private static final Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) {
        // BigInteger 사용
//        final BigInteger first = new BigInteger(SCAN.nextLine());
//        final BigInteger second = new BigInteger(SCAN.nextLine());
//
//        SCAN.close();
//
//        System.out.println(first.subtract(second));

        // 배열을 사용하여 직접 연산
        final String s1 = SCAN.nextLine().trim();
        final String s2 = SCAN.nextLine().trim();
        SCAN.close();

        if (s1.equals(s2)) {
            System.out.println(0);
            return;
        }

        new CompareNumbers(s1, s2).compareNumbers();
    }

    static class CompareNumbers {
        private final String s1;
        private final String s2;
        private final boolean isS1BiggerThanS2;
        private final int maxSize;

        CompareNumbers(final String s1, final String s2) {
            this.s1 = s1;
            this.s2 = s2;
            this.isS1BiggerThanS2 = isS1BiggerThanS2();
            this.maxSize = isS1BiggerThanS2 ? s1.length() : s2.length();
        }

        private boolean isS1BiggerThanS2() {
            if (s1.length() > s2.length()) {
                return true;
            }

            if (s2.length() > s1.length()) {
                return false;
            }

            for (int i = 0; i < s1.length(); i++) {
                final int s1Number = s1.charAt(i) - '0';
                final int s2Number = s2.charAt(i) - '0';

                if (s1Number > s2Number) {
                    return true;
                }

                if (s2Number > s1Number) {
                    return false;
                }
            }

            throw new IllegalArgumentException("Why?");
        }

        public void compareNumbers() {
            final int[] bigNumber = new int[maxSize];
            final int[] smallNumber = new int[maxSize];

            if (isS1BiggerThanS2) {
                initNumber(bigNumber, s1);
                initNumber(smallNumber, s2);
            } else {
                initNumber(smallNumber, s1);
                initNumber(bigNumber, s2);
            }

            print(calculate(bigNumber, smallNumber));
        }

        private void initNumber(final int[] numbers, final String givenString) {
            final int numbersLastIdx = numbers.length - 1;
            final int givenStringLastIdx = givenString.length() - 1;

            for (int i = 0; i <= givenStringLastIdx; i++) {
                numbers[numbersLastIdx - i] = givenString.charAt(givenStringLastIdx - i) - '0';
            }
        }

        private int[] calculate(final int[] bigNumber, final int[] smallNumber) {
            final int[] result = new int[maxSize];

            for (int i = maxSize - 1; i >= 0; i--) {
                int bigOnes = bigNumber[i];
                int smallOnes = smallNumber[i];

                if (bigOnes < smallOnes) {
                    bigNumber[i - 1]--;
                    bigOnes += 10;
                }

                result[i] = bigOnes - smallOnes;
            }

            return result;
        }

        private void print(final int[] resultNumber) {
            final StringBuilder result = new StringBuilder();
            if (!isS1BiggerThanS2) {
                result.append("-");
            }

            int idx = 0;
            while (idx < maxSize) {
                if (resultNumber[idx] == 0) {
                    idx++;
                    continue;
                }

                while (idx < maxSize) {
                    result.append(resultNumber[idx++]);
                }
            }

            System.out.println(result.toString());
        }
    }
}
