package algorithm.boj;

import java.util.*;
public class Main_S5_1018_체스판다시칠하기_정태현_solved {
    //����� �� ���� ���̽��� �̷�����ִ�.
	static int check(int i, int j, int[][] arr, int[][] WorB) {
        int cnt = 0;

        for(int a = i; a < i+8; a++) //i, j������ 8ĭ��
            for(int b = j; b < j+8 ; b++)
                if(arr[a][b]==WorB[a-i][b-j]) cnt++; //WorB�� ó�� 0��°���� ���ƾ��ϴϱ�

        return cnt;    
    }

    static int min(int num1, int num2, int num3) {
    	int min = 200000;
    	if (num1 <= num2 && num1 <= num3) {
            min = num1;
        } else if (num2 <= num1 && num2 <= num3) {
            min = num2;
        } else {
            min = num3;
        }
    	return min;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int r = sc.nextInt();
        int c = sc.nextInt();
        int[][] arr = new int[r][c];
        int[][] checkW = new int[8][8];
        int[][] checkB = new int[8][8];
        int cnt = 10000;
        for(int i=0; i<r; i++) {
        	String s = sc.next();
        	for(int j=0; j<c; j++) {
        		char c1 = s.charAt(j);
        		if(c1=='W') arr[i][j] = 1;
        		else arr[i][j] = 0;
        	}
        }
        //System.out.println(Arrays.deepToString(arr));

        for(int i=0; i<8; i++) {
        	for(int j=0; j<8; j++) {
        		if((i+j)%2==0) checkW[i][j] = 1;
        		else checkW[i][j] = 0;
        	}
        }
        for(int i=0; i<8; i++) {
        	for(int j=0; j<8; j++) {
        		if((i+j)%2==0) checkB[i][j] = 0;
        		else checkB[i][j] = 1;
        	}
        }
        for(int i = 0 ; i+7 < r ; i++) //i+7�� r�̳� c������ �Ѿ�� 8*8�� �ȵ�
            for(int j = 0 ; j+7 < c ; j++) 
                cnt = min(cnt,check(i,j,arr,checkW),check(i,j,arr,checkB));


        System.out.println(cnt);
        
        sc.close();

    }    
}
