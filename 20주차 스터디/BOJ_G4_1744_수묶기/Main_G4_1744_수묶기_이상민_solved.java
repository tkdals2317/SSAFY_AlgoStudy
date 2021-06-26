import java.util.*;
import java.io.*;
public class Main_G4_1744_수묶기_이상민 {
	static int N;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1744.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ArrayList<Integer> pnums = new ArrayList<Integer>();
		ArrayList<Integer> mnums = new ArrayList<Integer>();
		ArrayList<Integer> zeros = new ArrayList<Integer>();
		int pcnt = 0, mcnt = 0, zcnt = 0;
		
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num<0) {
				mnums.add(num);
				mcnt++;
			}else if(num>0){
				pnums.add(num);
				pcnt++;
			}else {
				zeros.add(num);
				zcnt++;
			}
		}
		//그리디하게 생각해보자
		//큰수끼리 곱하고 더한다면? 무조건 피연산자가 0이 아니라면 곱한게 더 값이 크다
		//정렬을 할까?
		//음수도 생각해야된다!
		//음수끼리는 절대값이 큰 수 끼리 곱하면 양수로 변환
		
		Collections.sort(pnums, Collections.reverseOrder());
		Collections.sort(mnums);
		
		Queue<Integer> pque = new LinkedList<Integer>();
		Queue<Integer> mque = new LinkedList<Integer>();
		Queue<Integer> zque = new LinkedList<Integer>();
		for(int i = 0; i<pcnt ; i++) {
			pque.offer(pnums.get(i));
		}
		for(int i = 0; i<mcnt ; i++) {
			mque.offer(mnums.get(i));
		}	
		for(int i = 0; i<zcnt ; i++) {
			zque.offer(zeros.get(i));
		}
		
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
			if(mque.size()==1&&zque.size()>0) {
				result+=mque.poll()*zque.poll();
				break;
			}else if(mque.size()==1) {
				result+=mque.poll();
				break;
			}
			int num1 = mque.poll();
			int num2 = mque.poll();
	
			result += num1*num2;
		}
		System.out.println(result);
		br.close();
	}
}
