package com.jaenyeong.previousproblem.lv03.step05;

import java.util.Scanner;
import java.util.StringJoiner;

public class ProliferationNSpace {
    /*
    N칸 확산

    [문제]
    세로 길이 N, 가로 길이 M 인 2차원 배열이 주어진다.
    이 배열의 각 칸에 들어있는 수는 1 초에 한 번 씩 자기 자신을 복사해서 x, y좌표와의 거리가 1이상 k이하인 칸들에 더해준다.
    두 점 (x1, y1), (x2, y2)의 거리는 |x1 - x2| + |y1 - y2|로 구한다.
    t 초가 지난 후 배열의 상태를 출력하여라.
    중간 계산 과정과 최종 결과의 모든 수는 int 로 표현 가능함이 보장된다.
    [N칸_확산.png]

    [입력]
    첫 줄에 N, M, k 가 주어진다.
    두 번째 줄부터 N 줄에 걸쳐 배열의 값이 주어진다.
    마지막 줄에 t 가 주어진다.
    (1 <= N, M <= 5, 1 <= k <= 4, 1 <= 배열의 값 <= 10, 1 <= t <= 3)

    [출력]
    t 초 후 배열을 출력한다.

    [예제 입력]
    3 4 2
    5 1 3 4
    2 5 1 2
    7 8 3 1
    2
    [예제 출력]
    172 212 204 133
    206 279 257 171
    177 232 217 138

    [예제 입력]
    3 3 1
    2 4 2
    4 2 4
    2 4 2
    3
    [예제 출력]
    126 166 126
    166 250 166
    126 166 126

    [예제 입력]
    2 5 2
    1 0 1 0 1
    0 1 0 1 0
    5
    [예제 출력]
    4693 6930 7976 6930 4693
    4689 6931 7968 6931 4689

    [예제 입력]
    3 4 1
    7 8 1 2
    6 9 7 5
    8 8 3 8
    10
    [예제 출력]
    4381720 6997542 6881404 4197186
    6212937 9918239 9758514 5949716
    4402841 7031994 6916199 4218862

     */

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        final int n = SC.nextInt();
        final int m = SC.nextInt();
        final int k = SC.nextInt();

        final int[][] origin = new int[n][m];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                origin[row][col] = SC.nextInt();
            }
        }

        final int t = SC.nextInt();

        SC.close();

        for (int time = 0; time < t; time++) {

            final int[][] copy = new int[n][m];
            for (int row = 0; row < n; row++) {
                System.arraycopy(origin[row], 0, copy[row], 0, m);
            }

            // 각 위치 순회
            for (int curRow = 0; curRow < n; curRow++) {
                for (int curCol = 0; curCol < m; curCol++) {

                    // 해당 위치의 값
                    final int curValue = origin[curRow][curCol];

                    // 유효 범위 설정
                    int rowStart = Math.max(curRow - k, 0);
                    int rowEnd = Math.min((curRow + k), (n - 1));

                    int colStart = Math.max((curCol - k), 0);
                    int colEnd = Math.min((curCol + k), (m - 1));

                    for (int r = rowStart; r <= rowEnd; r++) {
                        for (int c = colStart; c <= colEnd; c++) {
                            // 현재위치와의 거리값
                            final int dist = Math.abs(curRow - r) + Math.abs(curCol - c);

                            if (dist > k) {
                                continue;
                            }

                            if ((curRow == r) && (curCol == c)) {
                                continue;
                            }

                            copy[r][c] += curValue;
                        }
                    }

                }
            }

            // copy
            for (int row = 0; row < n; row++) {
                System.arraycopy(copy[row], 0, origin[row], 0, m);
            }
        }

        for (int row = 0; row < n; row++) {
            final StringJoiner sj = new StringJoiner(" ");
            for (int col = 0; col < m; col++) {
                sj.add(Integer.toString(origin[row][col]));
            }
            System.out.println(sj);
        }
    }
}
