import java.util.*;
import java.io.*;
public class Main_G5_5430_AC_이상민_solved {
	//함수 R은 배열에 있는 숫자의 순서를 뒤집는 함수이고
	//D는 첫 번째 숫자를 버리는 함수이다. 배열이 비어있는데 D를 사용한 경우에는 에러가 발생한다.
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_5430.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int i=0; i<T; i++) {
			String order = br.readLine();
			int N = Integer.parseInt(br.readLine());
			
			String str = br.readLine();
			solution(order, str, N);
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void solution(String order, String str, int N) {
		//System.out.println(order);
		//숫자만 뽑아내기
		StringTokenizer st = new StringTokenizer(str, "[],");
		
		//덱에 집어 넣어주기
		Deque<Integer> deque = new ArrayDeque<Integer>();
		for(int i=0; i<N; i++) {
			deque.offer(Integer.parseInt(st.nextToken())); 
		}
		//System.out.println(deque);
		//int Rcnt=0;
		boolean isReverse = false;
		for(int i=0; i<order.length(); i++) {
			//R이 나올때마다 바꾸면 시간초과 
			if(order.charAt(i)=='R') {
				//Rcnt++;
				isReverse = !isReverse;
			}else {
				//배열에 아무것도 없는 데 D함수를 호출한 경우
				if(deque.size()==0) {
					sb.append("error").append("\n");
					return;
					//return "error";
				}
				//if(Rcnt%2==1) {
				//	deque.pollLast();		
				//}else{
				//	deque.pollFirst();
				//}
				//지금 현 상태가 뒤집힌 상태라면
				if(isReverse) {
					//덱의 마지막 원소 삭제
					deque.pollLast();
				//지금 현 상태가 뒤집히지 않은 상태라면	
				}else {
					//덱의 첫번째 원소 삭제
					deque.pollFirst();
				}
			}
		}
		int length = deque.size();
		sb.append("[");
		if(length>0) {
			//뒤집힌 상태라면
			if(isReverse) {
				for(int j=0; j<length;j++) {
					//스트링빌더에 덱의 원소를 뒤에서부터 넣어준다
					sb.append(deque.pollLast());
					//마지막 원소일때까지 , 붙혀주기
					if(!deque.isEmpty()) sb.append(",");
				}
			//뒤집히지 않은 상태라면
			}else {
				for(int j=0; j<length;j++) {
					//스트링빌더에 덱의 원소 앞에서부터 넣어준다
					sb.append(deque.pollFirst());
					if(!deque.isEmpty()) sb.append(",");
				}
			}
		}
		sb.append("]").append("\n");
		return;
	}
}
