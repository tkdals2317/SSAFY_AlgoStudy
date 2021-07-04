package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Soulition_BOJ_13335_트럭 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); //4
		int w = Integer.parseInt(st.nextToken()); //2
		int L = Integer.parseInt(st.nextToken()); //10
		int[] truck_weights = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			truck_weights[i]=Integer.parseInt(st.nextToken());
		int answer = 0, cur = 0;
		Queue<Integer> onBridge = new LinkedList<Integer>();
		int endTime[] = new int[n];

		while (true) {
			if (!onBridge.isEmpty() && endTime[onBridge.peek()] == answer) 
				L += truck_weights[onBridge.poll()];

			if (cur < n && truck_weights[cur] <= L) {
				onBridge.offer(cur);
				endTime[cur] = answer + w;
				L -= truck_weights[cur];
				cur++;
			}
			answer++;
			if (onBridge.isEmpty())
				break;
		}
		System.out.println(answer);

	}

}
/*

4 2 10
7 4 5 6
*/