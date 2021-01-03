package com.jaenyeong.curriculum.lv19.ps02;

import java.util.*;

public class WormVirusBFS {
    /*
    웜 바이러스

    [문제]
    신종 바이러스인 웜 바이러스는 네트워크를 통해 전파된다.
    한 컴퓨터가 웜 바이러스에 걸리면 그 컴퓨터와 네트워크 상에서 연결되어 있는 모든 컴퓨터는 웜 바이러스에 걸리게 된다.
    예를 들어 7대의 컴퓨터가 < 그림 1 > 과 같이 네트워크 상에서 연결되어 있다고 하자.
    1번 컴퓨터가 웜 바이러스에 걸리면 웜 바이러스는 2번과 5번 컴퓨터를 거쳐 3번과 6번 컴퓨터까지 전파되어 2, 3, 5, 6 네 대의 컴퓨터는 웜 바이러스에 걸리게 된다.
    하지만 4번과 7번 컴퓨터는 1번 컴퓨터와 네트워크 상에서 연결되어 있지 않기 때문에 영향을 받지 않는다.
    [worm_virus.png]
    어느날 1번 컴퓨터가 웜 바이러스에 걸렸다.
    컴퓨터의 수와 네트워크 상에서 서로 연결되어 있는 정보가 주어질 때, 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 출력하는 프로그램을 작성하시오.

    [입력]
    첫째 줄에는 컴퓨터의 수 N이 주어진다.
    컴퓨터의 수는 100 이하이고 각 컴퓨터에는 1번부터 N번까지 차례대로 번호가 매겨진다.
    둘째 줄에는 네트워크상에서 직접 연결되어 있는 컴퓨터 쌍의 수 M이 주어진다.
    이어서 그 수만큼 한 줄에 한 쌍씩 네트워크 상에서 직접 연결되어 있는 컴퓨터의 번호 쌍이 주어진다.

    [출력]
    1번 컴퓨터가 웜 바이러스에 걸렸을 때, 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 첫째 줄에 출력한다.

    [예제 입력]
    7
    6
    1 2
    2 3
    1 5
    5 2
    5 6
    4 7
    [예제 출력]
    4

     */

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = SC.nextInt();
        final int m = SC.nextInt();

        final List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            final int aNode = SC.nextInt();
            final int bNode = SC.nextInt();

            graph.get(aNode).add(bNode);
            graph.get(bNode).add(aNode);
        }

        SC.close();

        int numberOfInfected = 0;
        final boolean[] infected = new boolean[n + 1];

        final Queue<Integer> queue = new LinkedList<>();
        infected[1] = true;
        queue.add(1);

        while (!queue.isEmpty()) {
            final int curNode = queue.poll();

            for (int nextNode : graph.get(curNode)) {
                if (infected[nextNode]) {
                    continue;
                }

                infected[nextNode] = true;
                queue.add(nextNode);
                numberOfInfected++;
            }
        }

        System.out.println(numberOfInfected);
    }
}
