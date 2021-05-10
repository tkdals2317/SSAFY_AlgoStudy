import java.util.*;
import java.io.*;
public class Main_S5_2941_크로아티아알파벳_이상민 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		//크로아티아 글자라면 글자수가 1개인 글자로 변경
		str = str.replace("c=", "1");
		str = str.replace("c-", "1");
		str = str.replace("dz=", "1");
		str = str.replace("d-", "1");
		str = str.replace("lj", "1");
		str = str.replace("nj", "1");
		str = str.replace("s=", "1");
		str = str.replace("z=", "1");
		//전체 문장의 길이 출력
		System.out.println(str.length());
		br.close();
	}

}
