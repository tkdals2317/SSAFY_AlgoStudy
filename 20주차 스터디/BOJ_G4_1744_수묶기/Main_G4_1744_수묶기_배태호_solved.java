package boj;
import java.io.*;
import java.util.Arrays;

public class Soulition_BOJ_1744_수묶기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ans=0, cnt=-1;
		int n = Integer.parseInt(br.readLine());
		int [] num= new int[n];
		
		for(int i=0; i<n; i++)
			num[i]= Integer.parseInt(br.readLine());
		Arrays.sort(num);
		
		for(int i= n-1; i>=0; i--) {
			if(num[i]<=0) {
				cnt=i;
				break;
			}
			if (num[i] > 1) 
				if (i > 0 && num[i - 1] > 1) {
					ans += num[i - 1] * num[i];
					i--;
				} else ans += num[i];
			else if(num[i] == 1) ans++;
		}
		
		for(int i=0; i<=cnt; i++) 
			if(i<cnt && num[i+1]*num[i]>=0) {
				ans+=num[i+1]*num[i];
				i++;
			}
			else ans+=num[i];
		
		System.out.println(ans);
	}
}