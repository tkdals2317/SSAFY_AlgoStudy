package algorithm.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_S1_2447_별찍기10_정태현_solved {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		char[][] a = new char[N][N]; //�ð��ʰ�
//		String[][] a = new String[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				a[i][j] = ' ';
			}
		}
		
		divide(0, 0, N, a);
		
//		StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < N ; i++) {
//            for (int j = 0; j < N ; j++) {
//                sb.append(a[i][j]);
//            }
//            sb.append("\n");
//        }
        
//        System.out.println(sb.toString());
		for (int i = 0; i < N; i++) {
			System.out.println(a[i]);
		}
		
		
	}
	
	public static void divide(int si, int sj, int dN, char[][] a) {
		if(dN == 1) { //1ĭ�̴� ���� ��
			a[si][sj] = '*';
			return;
		}
		
		for (int i = 0; i < 3; i++) { //9���� ����
			for (int j = 0; j < 3; j++) {
				if(i==1 && j==1) continue; //�����̴ϱ� �״��
				else {
					divide(si+(i*dN/3), sj+(j*dN/3), dN/3, a);
					//0, 0, 9����Ʈ�� 9����ϸ�
					//0, 0, 3
					//0, 3, 3
					//0, 6, 3
					//...
				}
			}
		}
	}
}
