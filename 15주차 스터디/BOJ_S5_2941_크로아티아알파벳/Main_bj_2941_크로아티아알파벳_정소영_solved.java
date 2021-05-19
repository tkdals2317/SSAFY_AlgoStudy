import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_bj_2941_크로아티아알파벳_정소영_solved {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		
		System.out.println(count(str));
		
		
		br.close();
	}

	private static int count(String str) {
		int cnt = 0;
		char[] c = str.toCharArray();
		
		for(int i=0; i<c.length; i++) {
			if((c[i]==99&&c[i+1]==61) || (c[i]==99&&c[i+1]==45)) {
					cnt++;
					i=i+1;
			}else if(c[i]==100&&c[i+1]==122&&c[i+2]==61){	
					cnt++;
					i=i+2;
			}else if(c[i]==100&&c[i+1]==45) {
				cnt++;
				i=i+1;
			}else if(c[i]==108&&c[i+1]==106) {
				cnt++;
				i=i+1;
			}else if(c[i]==110&&c[i+1]==106) {
				cnt++;
				i=i+1;
			}else if(c[i]==115&&c[i+1]==61) {
				cnt++;
				i=i+1;
			}else if(c[i]==122&&c[i+1]==61) {
				cnt++;
				i=i+1;
			}else {
				cnt++;
			}
		}
		
		
		return cnt;
	}

}
