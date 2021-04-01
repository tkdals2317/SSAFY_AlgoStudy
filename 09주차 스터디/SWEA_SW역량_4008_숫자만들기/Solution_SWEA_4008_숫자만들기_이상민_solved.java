import java.util.*;
import java.io.*;

public class Solution_SWEA_4008_숫자만들기_이상민_solved {
	static int N; //숫자의 개수
	static int plus, minus, mul, div; //각 연산자의 사용가능한 횟수
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int [] input;
	static char [] pick; // 0:plus 1:minus 2:mul 3:div
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_swea_4008.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			//구현 시작
			//숫자 개수 입력
			N = Integer.parseInt(br.readLine());
			input = new int[N];
			//input = new ArrayDeque<Integer>();
			pick = new char[N-1];
			//연산자 개수 입력
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			plus = Integer.parseInt(st.nextToken());
			minus = Integer.parseInt(st.nextToken());
			mul = Integer.parseInt(st.nextToken());
			div = Integer.parseInt(st.nextToken());

			//연산에 사용될 숫자 입력
			st = new StringTokenizer(br.readLine()," ");
			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			perm(0,0,0,0,0);
			System.out.println("#"+tc+" "+(max-min));
		}
		br.close();
	}
	//선택된 연산자의 개수를 파라미터로 넘겨줘서 몇번 소모 했는지 체크
	static void perm(int cnt, int pCnt, int mCnt, int mulCnt, int dCnt) {
		if(cnt==N-1) {
			int res = calc();
			//최소값, 최대값 갱신
			max = Math.max(max, res);
			min = Math.min(min, res);
			return;
		}
		//중복 순열(연산자의 갯수만 있다면 중복해서 선택 할 수 있으므로, visisted로 방문체크를 하지 않음)
		//대신 연산자의 개수를 카운트하여 조건을 검사 해준다
		for (int i = 0; i < 4; i++) {
			if(i==0){ //plus 선택
				if(pCnt>=plus) continue;
				pick[cnt] = '+';
				perm(cnt+1, pCnt+1, mCnt, mulCnt, dCnt);
			}else if(i==1){ //minus 선택
				if(mCnt>=minus) continue;
				pick[cnt] = '-';
				perm(cnt+1, pCnt, mCnt+1, mulCnt, dCnt);
			}else if(i==2) { //mul 선택
				if(mulCnt>=mul) continue;
				pick[cnt] = '*';
				perm(cnt+1, pCnt, mCnt, mulCnt+1, dCnt);
			}else if(i==3) { //div 선택
				if(dCnt>=div) continue;
				pick[cnt] = '/';
				perm(cnt+1, pCnt, mCnt, mulCnt, dCnt+1);
			}
		}
	}
	static int calc() {
		//연산 결과를 저장할 큐
		ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
		//큐에 입력받은 숫자로 초기화
		for (int i = 0; i < N; i++) {
			dq.offer(input[i]);
		}
		for (int i = 0; i < N-1; i++) {
			//맨 앞에 두 개의 숫자를 꺼냄
			int a = dq.poll();
			int b = dq.poll();
			//연산 후 결과를 다시 큐의 맨앞에 넣어줌 -> 왼쪽에서 오른쪽으로 가는 연산순서를 위해
			if(pick[i]=='+') {
				dq.addFirst(a+b);
			}else if(pick[i]=='-') {
				dq.addFirst(a-b);
			}else if(pick[i]=='*') {
				dq.addFirst(a*b);
			}else if(pick[i]=='/') {
				dq.addFirst(a/b);
			}		
		}
		//마지막에 남은 숫자가 최종적으로 계산된 결과 반환
		return dq.poll();
	}
}
