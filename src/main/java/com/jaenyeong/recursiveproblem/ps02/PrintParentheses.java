package com.jaenyeong.recursiveproblem.ps02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrintParentheses {
    /*
    암호

    [문제]
    자연수 N이 주어질 때, 길이 N의 가능한 모든 "옳은 괄호 쌍"을 출력하는 프로그램을 작성하시오.
    예를 들어, 길이 6의 가능한 "옳은 괄호 쌍"은 다음과 같다.
    ((())) (()()) (())() ()(()) ()()()

    [입력]
    첫 번째 줄에 자연수 N이 주어진다. (2 <= N <= 16) N은 항상 짝수로 주어진다.

    [출력]
    가능한 모든 "옳은 괄호 쌍"을 출력한다.
    출력을 할 때는 여는 괄호가 먼저 나오는 괄호 문자열을 우선으로 출력한다.
    예를 들어, (())은 ()() 보다 먼저 출력되어야 한다.
    (())이 ()()보다 여는 괄호가 더 먼저 나오기 때문이다.
    즉, (())의 여는 괄호는 첫번째와 두번째에 나오며,
    ()()의 여는 괄호는 첫번째와 세번째에 나오므로 (())의 여는 괄호가 더 먼저 나온다고 할 수 있다.

    [예제 입력]
    6
    [예제 출력]
    ((()))
    (()())
    (())()
    ()(())
    ()()()

     */

    private static final Scanner SC = new Scanner(System.in);
    private static final String EMPTY_VALUE = "";

    public static void main(String[] args) {
        final int n = SC.nextInt();
        SC.close();

        solve(n / 2);
    }

    private static void solve(final int pairsCount) {
        final List<String> parentheses = new ArrayList<>();

        dfs(parentheses, pairsCount, pairsCount, EMPTY_VALUE);

        for (String s : parentheses) {
            System.out.println(s);
        }
    }

    private static void dfs(final List<String> parentheses, final int open, final int close, final String currStr) {
        if ((0 > open) || (open > close)) {
            return;
        }

        if ((open == 0) && (close == 0)) {
            parentheses.add(currStr);
            return;
        }

        // open parenthesis "("
        dfs(parentheses, open - 1, close, currStr + "(");
        // close parenthesis ")"
        dfs(parentheses, open, close - 1, currStr + ")");
    }
}
