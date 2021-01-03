package com.jaenyeong.curriculum.lv11.ps04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class ValueOfParenthesis {
    /*
    괄호의 값

    [문제]
    4개의 기호 '(', ')', '[', ']'를 이용해서 만들어지는 괄호열 중에서 올바른 괄호열이란 다음과 같이 정의된다.
    한 쌍의 괄호로만 이루어진 ‘()’와 ‘[]’는 올바른 괄호열이다.
    만일 X가 올바른 괄호열이면 ‘(X)’이나 ‘[X]’도 모두 올바른 괄호열이 된다.
    X와 Y 모두 올바른 괄호열이라면 이들을 결합한 XY도 올바른 괄호열이 된다.
    예를 들어 ‘(()[[]])’나 ‘(())[][]’ 는 올바른 괄호열이지만 ‘([)]’ 나 ‘(()()[]’ 은 모두 올바른 괄호열이 아니다.
    우리는 어떤 올바른 괄호열 X에 대하여 그 괄호열의 값(괄호값)을 아래와 같이 정의하고 값(X)로 표시한다.
    [1] ‘()’ 인 괄호열의 값은 2이다.
    [2] ‘[]’ 인 괄호열의 값은 3이다.
    [3] ‘(X)’ 의 괄호값은 2×값(X) 으로 계산된다.
    [4] ‘[X]’ 의 괄호값은 3×값(X) 으로 계산된다.
    [5] 올바른 괄호열 X와 Y가 결합된 XY의 괄호값은 값(XY)= 값(X)+값(Y) 로 계산된다.
    예를 들어 ‘(()[[]])([])’ 의 괄호값을 구해보자. ‘()[[]]’ 의 괄호값이 2 + 3×3=11 이므로 ‘(()[[ ]])’의 괄호값은 2×11=22 이다.
    그리고 ‘([])’의 값은 2×3=6 이므로 전체 괄호열의 값은 22 + 6 = 28 이다.
    여러분이 풀어야 할 문제는 주어진 괄호열을 읽고 그 괄호값을 앞에서 정의한대로 계산하여 출력하는 것이다.

    [입력]
    첫째 줄에 괄호열을 나타내는 문자열(스트링)이 주어진다.
    단 그 길이는 1 이상, 30 이하이다.

    [출력]
    첫째 줄에 그 괄호열의 값을 나타내는 정수를 출력한다.
    만일 입력이 올바르지 못한 괄호열이면 반드시 0을 출력해야 한다.

    [예제 입력]
    (()[[]])([])
    [예제 출력]
    28

     */

    private static final Scanner SC = new Scanner(System.in);
    private static final int OPEN_PARENTHESIS = -1; // '('
    private static final int OPEN_BRACKET = -2;     // '['

    public static void main(String[] args) {
        final String input = SC.next();
        SC.close();

        // 길이가 홀수면 종료
        if (input.length() % 2 != 0) {
            System.out.println(0);
            return;
        }

        // 주어진 괄호 리스트 초기화
        final Queue<Character> parentheses = new LinkedList<>();
        for (char c : input.toCharArray()) {
            parentheses.add(c);
        }

        // 시뮬레이션 스택 초기화
        final Stack<Integer> stack = new Stack<>();

        // 주어진 괄호를 모두 사용할 때까지 반복
        while (!parentheses.isEmpty()) {
            // 첫 괄호 뽑기
            final char curParenthesis = parentheses.poll();

            // 여는 괄호인 경우 바로 삽입
            if (curParenthesis == '(') {
                stack.push(OPEN_PARENTHESIS);
                continue;
            }
            if (curParenthesis == '[') {
                stack.push(OPEN_BRACKET);
                continue;
            }

            // 스택이 비어 있는 경우 종료
            if (stack.isEmpty()) {
                System.out.println(0);
                return;
            }

            int topValue = stack.pop();

            // 작은 괄호를 닫을 때
            if (curParenthesis == ')') {

                // 대괄호가 열려 있을 때
                if (topValue == OPEN_BRACKET) {
                    System.out.println(0);
                    return;
                }

                int score = 0;
                if (topValue == OPEN_PARENTHESIS) {
                    score = 2;
                    score = getPreviousValue(stack, score);

                } else { // 숫자일 때
                    score = topValue * 2;

                    if (stack.peek() == OPEN_BRACKET) {
                        System.out.println(0);
                        return;
                    }

                    if (!stack.isEmpty()) {
                        stack.pop();
                        score = getPreviousValue(stack, score);
                    }
                }

                // 연산한 값을 삽입
                stack.push(score);

                continue;
            }

            // 큰 괄호를 닫을 때
            // 작은 괄호가 열려 있을 때
            if (topValue == OPEN_PARENTHESIS) {
                System.out.println(0);
                return;
            }

            int score = 0;
            if (topValue == OPEN_BRACKET) {
                score = 3;
                score = getPreviousValue(stack, score);

            } else { // 숫자일 때
                score = topValue * 3;
                if (stack.peek() == OPEN_PARENTHESIS) {
                    System.out.println(0);
                    return;
                }

                if (!stack.isEmpty()) {
                    stack.pop();
                    score = getPreviousValue(stack, score);
                }
            }

            // 연산한 값을 삽입
            stack.push(score);
        }

        if (stack.size() != 1) {
            System.out.println(0);
            return;
        }

        System.out.println(stack.pop());
    }

    private static int getPreviousValue(final Stack<Integer> stack, int score) {
        if (!stack.isEmpty()) {
            final int previousValue = stack.peek();
            if ((previousValue != OPEN_PARENTHESIS) && (previousValue != OPEN_BRACKET)) {
                score += stack.pop();
            }
        }
        return score;
    }
}
