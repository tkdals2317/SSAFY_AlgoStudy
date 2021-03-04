import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main_bj_10814_나이순정렬2 {
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String [][]member = new String[N][2];
		for (int i = 0; i < N; i++) {
			member[i][0] = sc.next();
			member[i][1] = sc.next();
		}
		Arrays.sort(member, new Comparator<String[]>() {
			@Override
			public int compare(String[] o1, String[] o2) {
				int r = Integer.compare(Integer.parseInt(o1[0]), Integer.parseInt(o2[0]));
				return r;
			}
		});
		for(int i = 0; i <N ; i++) System.out.println(member[i][0] + " " + member[i][1]);
		
		sc.close();

	}

}
