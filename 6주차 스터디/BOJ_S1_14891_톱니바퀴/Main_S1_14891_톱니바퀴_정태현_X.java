package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_S1_14891_톱니바퀴_정태현_X {
    static String[] gears = new String[5];
    static int[] score = {0, 1, 2, 4, 8};
    static int k;

    static void rotate(int idx, int dir, boolean l, boolean r) {
        // 현재 기어의 왼쪽, 오른쪽 기어 idx
        int leftIdx = idx - 1;
        int rightIdx = idx + 1;
        
        if (l && leftIdx >= 1 && gears[idx].charAt(6) != gears[leftIdx].charAt(2)) {
            rotate(leftIdx, -dir, true, false);
        }

//        if () {
//            
//        }
    }

    static String clockRotate(String state, int dir) {
		return state;
        
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        gears[1] = br.readLine();
        gears[2] = br.readLine();
        gears[3] = br.readLine();
        gears[4] = br.readLine();

        k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            rotate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), true, true);
        }

        int answer = 0;

        for (int i = 1; i <= 4; i++) {
            if (gears[i].charAt(0) == '1') {
                answer += score[i];
            }
        }

        System.out.println(answer);
    }
}
