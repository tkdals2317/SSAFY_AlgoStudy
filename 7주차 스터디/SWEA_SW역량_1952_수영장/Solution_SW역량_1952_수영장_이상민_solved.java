import java.util.*;
import java.io.*;

public class Solution_SW역량_1952_수영장_이상민_solved {
	static int sol = Integer.MAX_VALUE;
	static int [] mPlan;
	static int [] monthPee;
	static int dayPrice;
	static int month1Price;
	static int month3Price;
	static int yearPrice;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_sw_1952.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			mPlan = new int[13];
			monthPee = new int[13];
			StringTokenizer st;
			//요금제 입력 받기
			st = new StringTokenizer(br.readLine()," ");
			dayPrice = Integer.parseInt(st.nextToken());
			month1Price = Integer.parseInt(st.nextToken());
			month3Price = Integer.parseInt(st.nextToken());
			yearPrice = Integer.parseInt(st.nextToken());
			//매달 수영 계획 입력 받기
			st = new StringTokenizer(br.readLine()," ");
			for (int i = 1; i <= 12; i++) {
				mPlan[i] = Integer.parseInt(st.nextToken());
			}
			sol = Integer.MAX_VALUE;
			comb(1,0);
			System.out.println("#"+tc+" "+sol);
		}
		br.close();
	}
	
	static void comb(int month, int total) {
		if(month>12) {
			//일년치요금제와 현재 12월 요금제의 최소값
			sol = Math.min(yearPrice, monthPee[12]);
			return;
		}
		//수영하는 날이 없으면 그 전달의 요금을 그대로 유지시킨제 다음 달로 이동
		if(mPlan[month]==0) {
			monthPee[month] = total;
			comb(month+1, total);
		}
		
		if(month>=1) {
			//현재달의 하루 이용권을 이용했을 시 금액
			int day = dayPrice*mPlan[month];
			//하루 이용권을 사용했을때보다 월 이용권을 사용했을 때 더 싸면
			if(day>month1Price) {
				//그 전달 누적 요금에서 월 이용권 금액 더하기
				monthPee[month] = monthPee[month-1]+month1Price;
			}else { //하루이용권이 더 싸다면
				//그  전달 누적 요금에서 하루 이용권 금액 더하기
				monthPee[month] = monthPee[month-1]+day;
			}
			//만약 앞에 달이 3개가 있다면 3달이용권 비교 가능하다
			if(month>=3) {
				//3달전 요금에 3달 이용권 사용할 시 금액 구하기
				int pmonth3 = month3Price+monthPee[month-3];
				//만약 3달이용 권을 사용하는 것이 현재 구해놓은 요금제를 사용한거보다 싸다면
				if(pmonth3<monthPee[month]) {
					//이번 달 요금 금액을 3달 전 누적금액에 3달 요금제 가격을 더한 것으로 갱신
					monthPee[month] = pmonth3;	
				}
			}
		}
		//다음달로 이동
		comb(month+1, monthPee[month]);
		return;
	}
}
