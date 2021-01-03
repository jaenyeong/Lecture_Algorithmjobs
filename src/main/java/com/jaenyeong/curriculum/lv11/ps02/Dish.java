package com.jaenyeong.curriculum.lv11.ps02;

import java.util.*;

public class Dish {
    /*
    접시

    [문제]
    접시 a, b, c, d 가 있고, 알파벳 순으로 한쪽이 막혀 있는 세척기에 들어간다고 할 때,
    b a c d 순으로 꺼내기 위해서는 push, push, pop, pop, push, pop, push, pop을 하면 된다.
    세척기에서 꺼내는 순서가 주어질 때 그 동작을 출력하는 프로그램을 작성하시오.
    만약 주어진 순서대로 접시를 꺼낼 수 없다면 “impossible”을 출력한다.

    [입력]
    첫째 줄에 소문자 알파벳이 주어진다. 중복된 소문자 알파벳은 입력되지 않는다.
    알파벳 소문자는 26개이다.
    입력되는 알파벳의 길이는 최대 26 자리이다.

    [출력]
    접시를 꺼내는 것이 가능한 경우 push, pop의 순서를 출력한다.
    이것이 불가능하다면 impossible을 출력한다.

    [예제 입력]
    bacd
    [예제 출력]
    push
    push
    pop
    pop
    push
    pop
    push
    pop

    [예제 입력]
    dabc
    [예제 출력]
    impossible

     */

    private static final Scanner SC = new Scanner(System.in);
    private static final String FAIL = "impossible";

    public static void main(String[] args) {
        final char[] inputOrder = SC.next().toCharArray();
        SC.close();

        // 주어진 결과 순서
        final Queue<Character> givenOrderList = new LinkedList<>();
        for (char c : inputOrder) {
            givenOrderList.add(c);
        }

        // 처리 순서
        final List<String> commandHistory = new ArrayList<>();
        // 시뮬레이션할 스택
        final Stack<Character> dishes = new Stack<>();

        // 알파벳
        char letter = 'a';
        final int givenOrderSize = givenOrderList.size();

        while (!givenOrderList.isEmpty()) {
            if (letter > 'a' + givenOrderSize) {
                break;
            }

            final char order = givenOrderList.peek();

            if (!dishes.isEmpty()) {
                final char dish = dishes.peek();
                if (dish == order) {
                    dishes.pop();
                    givenOrderList.poll();
                    commandHistory.add("pop");
                    continue;
                }
            }

            dishes.push(letter++);
            commandHistory.add("push");

//            if (dish.isEmpty()) {
//                // 접시 넣기
//                dish.push(curLetter++);
//                commands.add("push");
//                continue;
//            }
//
//            if (dish.peek() == givenOrder.peek()) {
//                // 최상위 접시가 주어진 순서와 동일한 경우
//                givenOrder.poll();
//                // 접시 빼기
//                dish.pop();
//                // 커맨드 입력
//                commands.add("pop");
//                continue;
//            }
//
//            if (curLetter > 'a' + givenOrderSize) {
//                break;
//            }
//
//            // 접시 넣기
//            dish.push(curLetter++);
//            commands.add("push");
        }

        if (!dishes.isEmpty()) {
            System.out.println(FAIL);
            return;
        }

        commandHistory.forEach(System.out::println);
    }
}
