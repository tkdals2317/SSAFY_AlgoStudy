import java.util.*;
import java.io.*;
public class Main_G4_1744_수묶기_이상민_solved {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1744.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		//내림차순으로 정렬
		PriorityQueue<Integer> pque = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o2, o1);
			}			
		});
		//오름차순으로 정렬
		PriorityQueue<Integer> mque = new PriorityQueue<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
		});

		int zcnt = 0;
		
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num<0) {
				mque.offer(num);
			}else if(num>0){
				pque.offer(num);
			}else {
				zcnt++;
			}
		}
		//그리디하게 생각해보자
		//큰수끼리 곱하고 더한다면? 무조건 피연산자가 0또는 1이 아니라면 곱한게 더 값이 크다
		//정렬을 할까?
		//음수도 생각해야된다!
		//음수끼리는 절대값이 큰 수 끼리 곱하면 양수로 변환
		
		int result = 0; 
		//양수일 경우 
		while(pque.size()!=0) {
			//만약 큐에 하나 남았다면 곱할 피연산자가 하나 부족하므로 이 수는 더해주자
			if(pque.size()==1) {
				result+=pque.poll();
				break;
			}
			//피연산자 두개 꺼내기
			int num1 = pque.poll();
			int num2 = pque.poll();
			//만약 피연산자가 1이라면 곱하는 값보다 더하는 값이 더 큰 값이 나온다 ex) 1, 9 일때 1+9 = 10, 1*9 = 9
			if(num1==1||num2==1) {
				result += num1+num2;
			//위에 조건에 걸리지 않았다면 무조건 곱해주는 것이 더 값이 크다
			}else {
				result += num1*num2;
			}			
		}
		//음수 일 경우
		while(mque.size()!=0) {
			//음수 숫자가 하나 남았고 만약 0이 존재한다면 곱해서 음수를 없애 버리자
			if(mque.size()==1&&zcnt>0) {
				mque.poll();
				break;
			//음수 숫자가 하나 남았고 0이 없다면 그냥 더해주자	
			}else if(mque.size()==1) {
				result+=mque.poll();
				break;
			}
			//음수가 두개 이상 남아있다면 음수 두수를 곱해서 양수로 만들고 더해주자
			int num1 = mque.poll();
			int num2 = mque.poll();
	
			result += num1*num2;
		}
		System.out.println(result);
		br.close();
	}
}
