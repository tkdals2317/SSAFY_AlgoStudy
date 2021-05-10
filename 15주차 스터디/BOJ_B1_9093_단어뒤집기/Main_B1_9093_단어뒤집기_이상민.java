import java.util.*;
import java.io.*;
public class Main_B1_9093_단어뒤집기_이상민 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_9093.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			String str = br.readLine(); 
			StringTokenizer st = new StringTokenizer(str," ");
			//hasMoreElements() 스트링토크나이저의 토큰이 남아 있는지 확인
			//st.countToken()으로 for문 돌리면 안됌! 스택과 비슷한 원리
			while(st.hasMoreElements()) {
				//띄어쓰기 단위로 분리한 단어
				String token = st.nextToken();
				//System.out.println(token);
				//역순으로 스트링빌더에 append
				for (int k = token.length()-1; k >=0 ; k--) {
					sb.append(token.charAt(k));
				}
				//한개의 단어 처리가 끝난 후 띄어쓰기 append
				sb.append(" ");
			}
			//한개의 문장 처리가 끝난 후 개행 append
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}
