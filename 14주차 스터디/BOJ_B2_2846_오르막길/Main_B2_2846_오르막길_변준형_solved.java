import java.util.Scanner;

public class Main_B2_2846_오르막길_변준형_solved {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int arr[] = new int[n];			//	오르막길 크기
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		int sum = 0;					//	합
		int result = 0;					//	결과
		for (int i = 0; i < n-1; i++) {
			if(arr[i]<arr[i+1])			//	앞에가 뒤보다 크면 빼서 합
				sum += (arr[i+1]-arr[i]);
			else
				sum = 0;				//	최대값 비교
			result = Math.max(result, sum);
		}
		System.out.println(result);
	}
}
