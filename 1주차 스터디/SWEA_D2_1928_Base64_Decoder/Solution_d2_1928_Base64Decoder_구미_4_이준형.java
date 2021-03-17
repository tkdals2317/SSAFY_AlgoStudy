package swea;
import java.io.FileInputStream;
import java.util.*;
import java.util.Base64.Decoder;

public class Solution_d2_1928_Base64Decoder_구미_4_이준형 {

	public static void main(String[] args) throws Exception{

		System.setIn(new FileInputStream("res/input_d2_1928.txt"));
		Scanner sc=new Scanner(System.in);
		
		int k=sc.nextInt();
		for(int i=1;i<=k;i++) {
			String str=sc.next();
			Decoder decoder = Base64.getDecoder(); 
			byte[] decodedBytes = decoder.decode(str);
			System.out.print("#"+i+" ");
			System.out.println(new String(decodedBytes));
		}
		
		sc.close();
	}

}
