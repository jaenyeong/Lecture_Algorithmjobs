package com.jaenyeong.curriculum.lv03.ps07;

import java.util.Scanner;

public class ClassPresident {
    /*
    class president

    [문제]
    오민식 선생님은 올해 형택초등학교 6학년 1반 담임을 맡게 되었다.
    오민식 선생님은 우선 임시로 반장을 정하고 학생들이 서로 친숙해진 후에 정식으로 선거를 통해 반장을 선출하려고 한다.
    그는 자기반 학생 중에서 1학년부터 5학년까지 지내오면서 한번이라도 같은 반이었던 사람이 가장 많은 학생을 임시 반장으로 정하려 한다.
    그래서 오민식 선생님은 각 학생들이 1학년부터 5학년까지 몇 반에 속했었는지를 나타내는 표를 만들었다.
    예를 들어 학생 수가 5명일 때의 표를 살펴보자.
    [class_president.png]
    위 경우에 4번 학생을 보면 3번 학생과 2학년 때 같은 반이었고,
    3번 학생 및 5번 학생과 3학년 때 같은 반이었으며,
    2번 학생과는 4학년 때 같은 반이었음을 알 수 있다.
    그러므로 이 학급에서 4번 학생과 한번이라도 같은 반이었던 사람은 2번 학생, 3번 학생과 5번 학생으로 모두 3명이다.
    이 예에서 4번 학생이 전체 학생 중에서 같은 반이었던 학생 수가 제일 많으므로 임시 반장이 된다.
    각 학생들이 1학년부터 5학년까지 속했던 반이 주어질 때, 임시 반장을 정하는 프로그램을 작성하시오.

    [입력]
    첫째 줄에는 반의 학생 수를 나타내는 정수가 주어진다.
    학생 수는 3 이상 1000 이하이다.
    둘째 줄부터는 1번 학생부터 차례대로 각 줄마다 1학년부터 5학년까지 몇 반에 속했었는지를 나타내는 5개의 정수가 빈칸 하나를 사이에 두고 주어진다.
    주어지는 정수는 모두 1 이상 9 이하의 정수이다.

    [출력]
    첫 줄에 임시 반장으로 정해진 학생의 번호를 출력한다.
    단, 임시 반장이 될 수 있는 학생이 여러 명인 경우에는 그 중 가장 작은 번호만 출력한다.

    [예제 입력]
    5
    2 3 1 7 3
    4 1 9 6 8
    5 5 2 4 4
    6 5 2 6 7
    8 4 2 2 2
    [예제 출력]
    4

     */

    private static final Scanner SCAN = new Scanner(System.in);
    private static final int GRADE_LIMIT = 5;

    public static void main(String[] args) {
        final int n = SCAN.nextInt();

        // 학생 학년별 반 테이블 초기화
        final int[][] classTable = new int[n][GRADE_LIMIT];
        for (int student = 0; student < n; student++) {
            for (int grade = 0; grade < GRADE_LIMIT; grade++) {
                classTable[student][grade] = SCAN.nextInt();
            }
        }

        SCAN.close();

        // 같은 반 이었던 적이 있던 경우 확인
        final int[] sameClass = new int[n];

        // 학생들 비교
        for (int first = 0; first < n; first++) {
            // 같은 반 횟수 체크
            int same = 0;
            for (int second = 0; second < n; second++) {
                // 동일 학생인 경우 통과
                if (first == second) continue;

                // 두 학생의 학년별 반 비교
                if (compareGrade(classTable[first], classTable[second])) same++;
            }
            sameClass[first] = same;
        }

        // 결과 출력
        printResult(n, sameClass);
    }

    private static boolean compareGrade(final int[] student1, final int[] student2) {
        for (int grade = 0; grade < GRADE_LIMIT; grade++) {
            final int grade1 = student1[grade];
            final int grade2 = student2[grade];

            // 같은반이었던 적이 있으면 true 반환
            if (grade1 == grade2) {
                return true;
            }
        }

        return false;
    }

    private static void printResult(final int n, final int[] sameClass) {
        int studentIdx = 0;
        int mostStudent = 0;
        for (int i = 0; i < n; i++) {
            if (mostStudent < sameClass[i]) {
                mostStudent = sameClass[i];
                studentIdx = i;
            }
        }

        System.out.println(studentIdx + 1);
    }
}
