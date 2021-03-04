import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Scanner;

public class Solution_d2_1928_Base64_Decoder{
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		Decoder decoder = Base64.getDecoder();
		for (int tc = 0; tc < T; tc++) {
			String str = sc.next();
			byte[] decodeBytes = decoder.decode(str);
			System.out.println("#"+(tc+1)+" "+new String(decodeBytes,"UTF-8"));
		}
		sc.close();
	}
}
