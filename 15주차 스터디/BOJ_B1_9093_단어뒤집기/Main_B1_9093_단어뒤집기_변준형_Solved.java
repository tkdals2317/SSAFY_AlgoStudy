import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B1_9093_단어뒤집기_변준형_Solved {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());					//	문장수
		while(n --> 0) {
			String temp = br.readLine();							//	문장 저장
			String[] sen = temp.split(" ");							//	단어로 저장 
			for(String s : sen) {
				StringBuilder sb = new StringBuilder(s);
				System.out.print(sb.reverse().toString() + " ");	//	단어마다 reverse해서 출력
			}
			System.out.println();
		}
	}
}
