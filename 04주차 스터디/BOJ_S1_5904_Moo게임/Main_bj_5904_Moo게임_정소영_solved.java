import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/5904
// 1. m, o를 배열에다 다 저장? 너무 길다
// 2. 재귀를 이용하자

public class Main_bj_5904_Moo게임_정소영_solved {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		len = length(n);
		if (len > 3) {
			moo(n);
		}
		System.out.println(res);
	}

	// 3 -> 10 -> 25 -> 56....
	static int index = 0;
	static int len = 3;
	static char res;

	private static int length(int n) {	// 처음에 해당 위치가 속해있을 배열의 길이를 구한다. 
		if (n == 1) {
			res = 'm';
		} else if (n <= 3) {
			res = 'o';
		} else {
			while (n > len) {
				index++;
				len = len * 2 + 3 + index;
			}
		}
		return len;
	}

	private static void moo(int n) {


		int bef = (len - 3 - index) / 2; // 이전 index에서의 길이
		
		if(n<=bef) {	// 앞쪽
			len = len - bef - (3 + index); // len 길이 이전 index로 줄이기
			index--;
			moo(n);
		} else if(n<=len-bef) {	// 중간쪽
			if (n == bef+1) {
				res = 'm';
			} else {
				res = 'o';
			}
			return;
		} else {	// 뒤쪽
			len = len - bef - (3 + index); // len 길이 이전 index로 줄이기
			n = n - bef - (3 + index); // n 위치도 맞게 조정
			index--;
			moo(n);
		}
		
	}

}
