import java.util.Scanner;

public class Main_10814_나이순정렬 {
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int [][]memberAge = new int[N][2];
		String []memberName = new String [N];
		for (int i = 0; i < N; i++) {
			memberAge[i][0] = i;
			memberAge[i][1] = sc.nextInt();
			memberName[i] = sc.next();
		}

		for (int i = 0; i < N-1; i++) {
			for(int j = i+1; j<N; j++) {
				if(memberAge[i][1] > memberAge[j][1]){
					int temp1 = memberAge[j][0];
					memberAge[j][0] = memberAge[i][0];
					memberAge[i][0] = temp1;
					
					int temp2 = memberAge[j][1];
					memberAge[j][1] = memberAge[i][1];
					memberAge[i][1] = temp2;
						
					String temp3 = memberName[j];
					memberName[j] = memberName[i];
					memberName[i] = temp3;
				}
			}
		}

		for (int i = 0; i < N-1; i++) {
			if(memberAge[i][1]==memberAge[i+1][1]) {
				if(memberAge[i][0]>memberAge[i+1][0]) {
					int temp1 = memberAge[i+1][0];
					memberAge[i+1][0] = memberAge[i][0];
					memberAge[i][0] = temp1;
					
					int temp2 = memberAge[i+1][1];
					memberAge[i+1][1] = memberAge[i][1];
					memberAge[i][1] = temp2;
						
					String temp3 = memberName[i+1];
					memberName[i+1] = memberName[i];
					memberName[i] = temp3;
				}
			}
		}

		for(int i = 0; i < N ; i++) {
			System.out.println(memberAge[i][1] + " " + memberName[i]);
		}
		sc.close();
	}

}
