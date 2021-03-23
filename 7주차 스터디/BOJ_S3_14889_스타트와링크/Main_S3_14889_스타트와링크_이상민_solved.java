import java.util.*;
import java.io.*;

public class Main_S3_14889_스타트와링크_이상민_solved {
	static int N;
	static int [][] arr;
	static int [] pick;
	static boolean [] v;
	static int min;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14981.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		int half = N/2; //두 팀으로 나뉘므로 조합시 절반으로 팀을 나누기 위한 변수
		v = new boolean[N];
		min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}	
		comb(0,0,half);
		System.out.println(min);
		br.close();
	}
	static void comb(int cnt, int start, int half){
		//두 팀으로 나눠진다면
		if(cnt==half){
			//두 팀의 차이 최솟값일 시 최솟값 갱신
			min = Math.min(min, check());
		}
		for (int i = start; i < N; i++) {
			v[i] = true; 
			comb(cnt+1, i+1, half);
			v[i] = false;
		}
	}
	static int check() {
		ArrayList<Integer> start = new ArrayList<Integer>();
		ArrayList<Integer> link = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			//v[i]가 true이면 start팀에 배정
			if(v[i]) start.add(i);
			//v[i]가 false이면 link팀에 배정
			else link.add(i);
		}
		
		int startScore = 0;
		int lickScore = 0;
		for (int i = 0; i < start.size(); i++) {
			for (int j = i; j < start.size(); j++) {
				//start팀의 능력치 합 구하기 
				startScore += arr[start.get(i)][start.get(j)];
				startScore += arr[start.get(j)][start.get(i)];
				//link팀의 능력치 합 구하기
				lickScore += arr[link.get(i)][link.get(j)];
				lickScore += arr[link.get(j)][link.get(i)];		
			}
		}
		//두 팀 능력치 합의 차이를 절대값으로 반환
		return Math.abs((startScore-lickScore));
	}
}
