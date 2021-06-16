import java.util.*;
import java.io.*;
/***
 * 1. 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
 * 2. 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
 * 3. 화면에 있는 이모티콘 중 하나를 삭제한다.
 *  
 *  위 동작들은 모두 1초씩 소요
 *  
 *  클립보드에 이모티콘을 복사하면 이전에 클립보드에 있던 내용은 덮어쓰기가 된다. 
 *  클립보드가 비어있는 상태에는 붙여넣기를 할 수 없으며, 일부만 클립보드에 복사할 수는 없다.
 *  화면에 이모티콘을 붙여넣기 하면, 클립보드에 있는 이모티콘의 개수가 화면에 추가된다.
 */
public class Main_G5_14226_이모티콘_이상민 {
	static int S;
	static int min = Integer.MAX_VALUE;
	static int clipBoard = 0;
	static int [][] dp;
	
	static class Order{
		String type;
		int length;
		int clipboard;
		int time;
		public Order(String type, int length,  int clipboard, int time) {
			super();
			this.type = type;
			this.length = length;
			this.clipboard = clipboard;
			this.time = time;
		}
		
		@Override
		public String toString() {
			return "Order [type=" + type + ", length=" + length + ", clipboard=" + clipboard + ", time=" + time + "]";
		}

	}
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input_bj_2573.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = Integer.parseInt(br.readLine());
		dp = new int[2002][2002];
		for (int i = 0; i < 2002; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		//Arrays.fill(dp, Integer.MAX_VALUE);
		ArrayDeque<Order> queue = new ArrayDeque<Order>();
		queue.offer(new Order("처음", 1, 0, 0));
		while(!queue.isEmpty()) {
			Order current = queue.poll();
			//System.out.println(current);
			if(current.length==S) {
				System.out.println(current.time);
				break;
			}
			// 1. 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
			// dp에 저장된 시간보다 소요된 시간이 짧은 경우에만 큐에 삽입
			// 중복방지!
			if(dp[current.length][current.length] > current.time+1) {
				queue.offer(new Order("복사", current.length, current.length, current.time+1));			
				//dp배열 갱신
				dp[current.length][current.length] = current.time+1;
			}
			// 2. 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
			// 클립보드에 이모티콘이 있고 dp에 저장된 시간보다 소요된 시간이 짧은 경우에만 큐에 삽입
			if(current.clipboard>0&&dp[current.length+current.clipboard][current.clipboard]>current.time+1) {
				queue.offer(new Order("붙여넣기",current.length+current.clipboard, current.clipboard, current.time+1));
				//dp배열 갱신
				dp[current.length+current.clipboard][current.clipboard] = current.time+1;
			}
			// 3. 화면에 있는 이모티콘 중 하나를 삭제한다.
			// 화면에 이모티콘이 있고 dp에 저장된 시간보다 소요된 시간이 짧은 경우에만 큐에 삽입
			if(current.length>0&&dp[current.length-1][current.clipboard]>current.time+1) {
				queue.offer(new Order("한글자 삭제",current.length-1, current.clipboard, current.time+1));
				//dp배열 갱신
				dp[current.length-1][current.clipboard]= current.time+1;
			}
		}
		
		br.close();
	}
}
