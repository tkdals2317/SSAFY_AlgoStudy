import java.util.Scanner;

public class Main_G5_18880_SocialDistancing1_변준형_X {
	public static void main(String[] args) {
		
		// your code goes here
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		String s1 = sc.nextLine();
		char[] s = s1.toCharArray();
		int startVal = 0, endVal = 0, count = 0;
		
		// count 0 in start.
		int i = 0;
		while (i < n && s[i] == '0')
			i++;
		startVal = i;
		
		// count 0 at the end.
		i = n - 1;
		while (i >= 0 && s[i] == '0') {
			i--;
			endVal++;
		}
		
		// count 0 in middle
		int c1 = 0, c2 = 0, temp = 0;
		for (int j = startVal; j <= i; j++) {
			if (s[j] == '0') {
				temp++;
			} else {
				if (temp >= c2) {
					c1 = c2;
					c2 = temp;
				} else if (temp >= c1) {
					c1 = temp;
				}
				temp = 0;
				//System.out.println("c1="+c1+" c2="+c2);
			}
		}
		
		// count minD for original array
		int minD = Integer.MAX_VALUE, x = 0, y = 0;
		temp = 0;
		for (int j = 0; j < n; j++) {
			if (s[j] == '1' && temp == 0) {
				x = j;
				temp = 1;
			} else if (s[j] == '1' && temp == 1) {
				y = j;
				int diff = y - x;
				if (minD > diff) {
					minD = diff;
				}
				x = j;
			}
		}
 		//System.out.println("start="+startVal+" end="+endVal+" c1="+c1+" c2="+c2 +" minD="+minD);
 		
		// get d
		int d = 0, t1 = 0, t2;
		
		// check for start c2
		t1 = startVal;
		if (c2 % 2 == 0)
			t2 = c2 / 2;
		else
			t2 = (c2 + 1) / 2;
		if (t1 < t2)
			d = t1;
		else
			d = t2;
		//System.out.println(d);
		
		// check for end c2
		t1 = endVal;
		if (t1 < t2)
			temp = t1;
		else
			temp = t2;
		if (temp > d)
			d = temp;
 		//System.out.println(temp);
 		
		// check for start end
		int j = 0, tt = 0;
		while (j < n) {
			if (s[j] == '1') {
				tt = 1;
				break;
			}
			j++;
		}
		if (tt == 1) {
			t1 = startVal;
			t2 = endVal;
			if (t1 < t2)
				temp = t1;
			else
				temp = t2;
			if (temp > d)
				d = temp;
			 //System.out.println(temp);
		}
		
		// check for c1 c2
		if (c2 % 2 == 0)
			t2 = c2 / 2;
		else
			t2 = (c2 + 1) / 2;
		if (c1 % 2 == 0)
			t1 = c1 / 2;
		else
			t1 = (c1 + 1) / 2;
		if (t1 < t2)
			temp = t1;
		else
			temp = t2;
		if (temp > d)
			d = temp;
// 		System.out.println(temp);
		
		// check for c2 c2
		t1 = (c2 + 1) / 3;
		if (t1 > d)
			d = t1;
		
		// check start start
		t1 = startVal / 2;
		if (t1 > d)
			d = t1;
		
		// check end end
		t1 = endVal / 2;
		if (t1 > d)
			d = t1;
		if (tt == 0) {
			t1 = startVal - 1;
			if (t1 > d)
				d = t1;
		}
// 		System.out.println("minD="+minD+" d="+d);
		if (minD < d)
			System.out.println(minD);
		else
			System.out.println(d);
	}
}
