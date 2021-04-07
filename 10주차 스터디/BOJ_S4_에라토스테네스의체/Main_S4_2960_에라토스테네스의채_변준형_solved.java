import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S4_2960_에라토스테네스의채_변준형_solved {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.valueOf(st.nextToken());
		int K = Integer.valueOf(st.nextToken());
		
		// defalut값 false이기때문에 0과 1에 따로 false 안함
		boolean[] Num = new boolean[N+1];
		
		//	2부터 입력받은 정수까지 true로 변환
		for (int i = 2; i <=N ; i++) {
			Num[i] = true;
		}
		//	k번째 지워지는 숫자를 찾는 변수
		int count = 0;
		
		for (int i = 2; i <= N; i++) {	//	2부터  N까지 
			for (int j = i; j <=N ; j+=i) {	//	배수로 검사 하기때문에 j+=i
				
				//	이미 검사가 되어 fals 값이 들어가 있으면 넘어감
				if(!Num[j])
					continue;
				//	아직 검사가 안된값이라면 false로 바꾸고 count값 증가
				Num[j] = false;
				count ++;
				//	count가 K와 같으면 출력
				if(count == K) {
					System.out.println(j);
					break;
				}
			}
		}
	}

}
