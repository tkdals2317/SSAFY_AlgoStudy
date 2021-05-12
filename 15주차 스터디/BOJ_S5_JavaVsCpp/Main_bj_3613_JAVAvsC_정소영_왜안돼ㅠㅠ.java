import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main_bj_3613_JAVAvsC_정소영_왜안돼ㅠㅠ {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		char[] str = br.readLine().toCharArray();
		int len = str.length;
		// 대문자 65~90, 소문자 97~122
		// _ : 95
		
		// javaIdentifier <-> c_identifier
		
		int c = 0;	// 모드 설정
		int startIndex = 0;
		if(len>=4 && str[0]=='j' && str[1]=='a' && str[2]=='v' &&str[3]=='a') {	// 자바 언어
			bw.write("c_");
			startIndex = 4;
			
		} else if(len>=2 && str[0]=='c' && str[1]=='_') {	// c언어
			bw.write("java");
			startIndex = 1;	// '_'를 판단해야 다음 소문자를 대문자로 바꿈
			c = 1;
		} else {	// 아직모름
			c = 2;
		}
		
		
		
		for (int l = startIndex; l < len; l++) {
			
			if(c==1) {	// c++ -> 자바 => 소문자와 _만 있어야 함
				if(str[l]>96 && str[l]<123) bw.append(str[l]);
				else if(str[l]=='_' && l+1<len && str[l+1]>96 && str[l+1]<123) { // c++	<- '_'가 있을 경우
					bw.append( (char) (str[++l]-32)); 
				}
				 else { // 다른 점이 올 경우 
						System.out.println("\"Error!\"");
						System.exit(0);
					}
			} else if(c==0){	// 자바 -> c++ => 소문자와 대문자만 있어야 함
				if(str[l]>96 && str[l]<123) bw.append(str[l]);	// 소문자일 경우 그냥 삽입
				else if(str[l]>64 && str[l]<91) {	// java <- 대문자가 있을 경우
					if(l+1<len && str[l]>96 && str[l]<123) {	// 대문자 다음에 대문자가 또 있으면
						System.out.println("\"Error!\"");
						System.exit(0);
					}
					bw.append('_');
					bw.append((char) (str[l]+32)); 
				}else {
					System.out.println("\"Error!\"");
					System.exit(0);
				}
			} else {	// 모드를 못 정했을 때 
				if(str[l]>96 && str[l]<123) bw.append(str[l]);
				else if(str[l]>64 && str[l]<91) {	// java <- 대문자가 있을 경우
					bw.append('_');
					bw.append((char) (str[l]+32)); 
					c=0;
				}else if(str[l]=='_' && l+1<len) { // c++	<- '_'가 있을 경우
					bw.append( (char) (str[++l]-32)); 
					c=1;
				}else {
					System.out.println("\"Error!\"");
					System.exit(0);
				}
			}
			
			
//			if(str[l]>96 && str[l]<132) bw.append(str[l]);	// 소문자일 경우 그냥 삽입
//			else if(str[l]=='_' && l+1<len) { // c++	<- '_'가 있을 경우
//				bw.append( (char) (str[++l]-32)); 
//			} else if(str[l]>64 && str[l]<91) {	// java <- 대문자가 있을 경우
//				bw.append('_');
//				bw.append((char) (str[l]+32)); 
//			} else {
//				System.out.println("\"Error!\"");
//				System.exit(0);
//			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

}
