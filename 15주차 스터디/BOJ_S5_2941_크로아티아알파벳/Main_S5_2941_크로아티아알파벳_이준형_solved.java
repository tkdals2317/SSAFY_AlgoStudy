import java.io.*;
import java.util.*;

public class Main_bj_2941_크로아티아알파벳_구미_4_이준형 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] bae = { "c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=" };

		String str = br.readLine();

		int count = 0; // 크로아티아수 세기

		for (int i = 0; i < str.length();) {	//문자 길이 만큼
			boolean findflag=false;
			for (int j = 0; j < bae.length; j++) {	//단어 배열만큼
				boolean flag = true;
				for (int k = 0; k < bae[j].length(); k++) {	//배열의 단어 길이 만큼
					if (i + k >= str.length()) {	//범위 나가는거 체크
						flag = false;
						continue;
					}
					if (bae[j].charAt(k) != str.charAt(i + k))
						flag = false;
				}
				if (flag == true) {	//단어가 일치 하면
					i += (bae[j].length());
					count++;
					findflag=true;
					break;
				}
			}
			if (findflag == false) {	//못찾으면 단어 하나 증가
				i++;
				count++;
			}

		}

		System.out.println(count);

		br.close();
	}

}
