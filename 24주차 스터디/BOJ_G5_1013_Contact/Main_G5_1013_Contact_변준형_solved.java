import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main_G5_1013_Contact_변준형_solved {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
        String regex = "^(100+1+|01)+$";
        for(int i = 0;i<n;i++) {
            String line = br.readLine();
            // regex 정규 표현식으로 조건에 맞는지 판별
            boolean flag = Pattern.matches(regex, line);
            if(flag)
            	System.out.println("YES");
            else
            	System.out.println("NO");
        }
	}
}

//	^	문자열 시작
//	$	문자열 종료
//	정규 표현식 참고 사이트
//	https://coding-factory.tistory.com/529

//	NFA(Non-deterministic Finite Automata)
//	DFA(Deterministic Finite Automata)
//	NFA, DFA 설명 사이트
//	https://talkingaboutme.tistory.com/entry/Study-NFA-DFA
//	https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=noma000&logNo=220898432958