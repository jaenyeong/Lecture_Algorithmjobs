package com.jaenyeong.curriculum.lv11.ps01;

import java.util.Scanner;

public class PS01StackImplementation {
    /*
    스택 구현하기

    [문제]
    이 문제에서는 스택을 구현한다.
    스택은 다음 세 개의 연산을 지원한다.
    Push X : 스택에 정수 X를 push한다. 만약 스택이 꽉 차서 push를 할 수 없다면, “Overflow”를 출력한다.
    Pop : 스택에서 정수 하나를 pop한다. 만약 스택이 비어있어서 pop을 할 수 없다면, “Underflow”를 출력한다.
    Top : 스택의 top에 있는 정수를 출력한다. 만약 스택이 비어있다면 “NULL”을 출력한다.
    크기가 n인 스택에 m개의 연산을 하는 프로그램을 작성하시오.
    입력의 편의를 위해서 Push는 “1”, Pop은 “2”, Top은 “3”으로 표현한다.

    [입력]
    첫째 줄에 스택의 크기 n, 연산의 개수 m이 주어진다. (1 <= n <= 100, 1 <= m <= 1,000)
    두 번째 줄부터 연산이 주어진다.
    1은 Push, 2는 Pop, 3은 Top 연산을 의미한다.

    [출력]
    연산의 결과를 출력한다.

    [예제 입력]
    4 10
    1 1
    1 2
    1 3
    2
    3
    1 4
    1 5
    3
    1 6
    3
    [예제 출력]
    2
    5
    Overflow
    5

    [예제 입력]
    4 11
    1 1
    1 2
    1 4
    3
    2
    3
    2
    3
    2
    3
    2
    [예제 출력]
    4
    2
    1
    NULL
    Underflow

     */

    private static final Scanner SC = new Scanner(System.in);
    private static final int PUSH = 1;
    private static final int POP = 2;
    private static final int INVALID_VALUE = -1;

    public static void main(String[] args) {
        final int n = SC.nextInt(); // 스택의 크기
        final int m = SC.nextInt(); // 연산 횟수

        final TestStack ts = new TestStack(n);

        // 입력 연산 실행
        for (int i = 0; i < m; i++) {
            final int command = SC.nextInt();
            if (command == PUSH) {
                final int value = SC.nextInt();

                ts.push(value);
                continue;
            }

            if (command == POP) {
                ts.pop();
                continue;
            }

            int topValue = ts.top();
            if (topValue != INVALID_VALUE) {
                System.out.println(topValue);
            }
        }

        SC.close();
    }
}

class TestStack {
    private static final int INVALID_VALUE = -1;
    private final int[] intStack;
    private final int limit;
    private int size;
    private int top;

    public TestStack(int limit) {
        this.intStack = new int[limit];
        this.limit = limit;
        this.size = 0;
        this.top = INVALID_VALUE;
    }

    public void push(Integer i) {
        if (this.limit <= this.size) {
            System.out.println("Overflow");
            return;
        }

        this.top++;
        intStack[this.top] = i;
        size++;
    }

    public void pop() {
        if (this.top <= INVALID_VALUE) {
            System.out.println("Underflow");
            return;
        }

        intStack[this.top] = 0;
        this.top--;
        this.size--;
    }

    public Integer top() {
        if (this.top <= INVALID_VALUE) {
            System.out.println("NULL");
            return INVALID_VALUE;
        }

        return intStack[this.top];
    }
}
