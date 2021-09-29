package PG;

import java.util.*;

public class Soulition_programmers_level3_다단계칫솔판매 {

	public static void main(String[] args) {

		String[] enroll = { "john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young" };
		String[] referral = { "-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward" };
		String[] seller = { "young", "john", "tod", "emily", "mary" };
		int[] amount = { 12, 4, 2, 5, 10 };

		/////////////////////////////////////////////////////

		Map<String, String> ref = new HashMap<String, String>();
		Map<String, Integer> idx = new HashMap<String, Integer>();
		int[] answer = new int[enroll.length];

		for (int i = 0; i < enroll.length; i++) {
			ref.put(enroll[i], referral[i]);
			idx.put(enroll[i], i);
		}

		for (int i = 0; i < seller.length; i++) {
			int price = 100 * amount[i];
			String cur = seller[i];

			while (!cur.equals("-")) {
				int nPrice = price/10;
				answer[idx.get(cur)] += (price - nPrice);

				cur = ref.get(cur);
				price/=10;
				if (price < 1) 
					break;
			}

		}
		
		for (int i : answer) {
			System.out.println(i);
		}
	}

}
