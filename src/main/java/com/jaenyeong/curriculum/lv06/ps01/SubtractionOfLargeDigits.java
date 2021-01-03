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
        final String s1 = SCAN.next();
        final String s2 = SCAN.next();
        SCAN.close();

        final CompareValue cv = new CompareValue(s1, s2);
        cv.execute();
    }
}

class CompareValue {
    private static final int S1_IS_SMALL = -1;
    private static final int EQUAL_SIZE = 0;
    private static final int S2_IS_SMALL = 1;
    private final String s1;
    private final String s2;
    private final int s1Size;
    private final int s2Size;

    public CompareValue(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        this.s1Size = s1.length();
        this.s2Size = s2.length();
    }

    public void execute() {
        // 대소 비교 (입력 받은 순서대로 넘김)
        final int sizeResult = compareSize(s1, s2);

        // 값이 같으면 0을 반환하고 종료
        if (sizeResult == EQUAL_SIZE) {
            System.out.println(0);
            return;
        }

        // 배열 마지막 자리부터 값 삽입
        final int bigNumberSize = (sizeResult == S2_IS_SMALL) ? s1Size : s2Size;
        final int[] bigArr = (sizeResult == S2_IS_SMALL)
            ? initArr(bigNumberSize, s1, s1Size)
            : initArr(bigNumberSize, s2, s2Size);
        final int[] smallArr = (sizeResult == S2_IS_SMALL)
            ? initArr(bigNumberSize, s2, s2Size)
            : initArr(bigNumberSize, s1, s1Size);

        // 뺄셈 연산 (각 자리에 따라 대소비교 하며 연산)
        final int[] arrResult = subtract(bigNumberSize, bigArr, smallArr);

        // 결과 출력
        printResult(sizeResult, bigNumberSize, arrResult);
    }

    private int compareSize(final String firstStr, final String secondStr) {
        // s1 작으면 -1, 같으면 0, s2 작으면 1 반환

        // 길이가 다를 때
        if (s1Size > s2Size) {
            return S2_IS_SMALL;
        }

        if (s1Size < s2Size) {
            return S1_IS_SMALL;
        }

        // 길이가 같을 때
        for (int i = 0; i < s1Size; i++) {
            final int i1 = Integer.parseInt(String.valueOf(firstStr.charAt(i)));
            final int i2 = Integer.parseInt(String.valueOf(secondStr.charAt(i)));

            if (i1 > i2) {
                return S2_IS_SMALL;
            }

            if (i1 < i2) {
                return S1_IS_SMALL;
            }
        }

        // 값이 같을 때
        return EQUAL_SIZE;
    }

    private int[] initArr(final int bigNumberSize, final String str, final int strSize) {
        final int[] arr = new int[bigNumberSize];

        for (int i = 0; i < strSize; i++) {
            arr[bigNumberSize - i - 1] = (str.charAt(strSize - i - 1) - '0');
        }

        return arr;
    }

    private int[] subtract(final int bigNumberSize, final int[] bigArr, final int[] smallArr) {
        final int[] arrResult = new int[bigNumberSize];
        for (int i = (bigNumberSize - 1); i >= 0; i--) {
            int bigDigit = bigArr[i];
            int smallDigit = smallArr[i];

            if (bigDigit < smallDigit) {
                bigArr[i - 1]--;
                bigDigit += 10;
            }

            arrResult[i] = (bigDigit - smallDigit);
        }
        return arrResult;
    }

    private void printResult(final int sizeResult, final int bigNumberSize, final int[] arrResult) {
        final StringBuilder sb = new StringBuilder();
        if (sizeResult == S1_IS_SMALL) {
            sb.append("-");
        }

        int idx = 0;
        while (idx < bigNumberSize) {
            if (arrResult[idx] == 0) {
                idx++;
                continue;
            }

            for (; idx < bigNumberSize; idx++) {
                sb.append(arrResult[idx]);
            }
        }

        System.out.println(sb.toString());
    }
}
