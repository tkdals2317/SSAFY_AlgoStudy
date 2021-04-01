import java.util.*;
import java.io.*;
public class Solution_d4_7792_반장선출_이상민_solved {
	static class student implements Comparable<student>{
		String name;
		int alphaCnt;
		public student(String name, int alphaCnt) {
			super();
			this.name = name;
			this.alphaCnt = alphaCnt;
		}
		//알파벳의 개수 큰 순으로 정렬 후 같은 경우 사전순으로 정렬
		@Override
		public int compareTo(student o) {
			int diff = o.alphaCnt-this.alphaCnt;
			if(diff==0) {
				diff = this.name.compareTo(o.name);
			}
			return diff;
		}
	}
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_d4_7792.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			student [] sArr = new student[N];

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				// 알파벳을 세기 위한 Set 생성(중복 제거의 효과)
				Set<Character> aCntSet = new HashSet<Character>();
				for (int j = 0; j < str.length(); j++) {
					//공백을 제외하고 Set에 추가 !이거 안하면 테케 통과못함!
					if(str.charAt(j)!=' ') aCntSet.add(str.charAt(j));
				}
				System.out.println(aCntSet);
				sArr[i] = new student(str, aCntSet.size());
				System.out.println(sArr[i].name);
				System.out.println(sArr[i].alphaCnt);
			}
			Arrays.sort(sArr);
			System.out.println("#"+tc+" "+sArr[0].name);
		}
		
		br.close();
	}

}
