import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_bj_2852_NBA농구_정소영_solved {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int a = 0, b = 0;
		int atime = 0, btime = 0;
		int ntime = 0;

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int team = Integer.parseInt(st.nextToken());
			String str = st.nextToken();
			//System.out.println("str : "+str+", "+str.length());
			
			int tm = Integer.parseInt(str.substring(0, 2));
			int tn = Integer.parseInt(str.substring(3, 5));
			//System.out.println(tm+" : "+tn);

			int gtime = tm * 60 + tn;

			if (a > b) {
				atime += (gtime - ntime);
			} else if (a < b) {
				btime += (gtime - ntime);
			}

			if (team == 1)
				a++;
			else
				b++;
			ntime = gtime;
		}

		if (a > b) {
			atime += (48 * 60 - ntime);
		} else if (a < b) {
			btime += (48 * 60 - ntime);
		}

		System.out.println(change(atime));
		System.out.println(change(btime));

		br.close();
	}

	private static String change(int time) {

		int mm = time / 60;
		int ss = time % 60;

		String result = "";

		if (mm < 10) {
			result += "0";
		}
		result += mm;
		
		result += ":";
		
		if (ss < 10) {
			result += "0";
		}
		
		result += ss;

		return result;
	}

}
