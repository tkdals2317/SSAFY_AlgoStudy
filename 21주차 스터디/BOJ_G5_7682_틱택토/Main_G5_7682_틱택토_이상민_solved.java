import java.io.*;
import java.util.*;
public class Main_G5_7682_ƽ����_�̻��_solved {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_7682_ƽ����.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String status = "";
		StringBuilder sb = new StringBuilder();
		do {
			status = br.readLine();
			//�Է� ���� ����
			if(status.equals("end")) break;	
			sb.append(Solution(status)).append("\n");		
		}while(true);
		System.out.println(sb.toString());
		br.close();
	}
	static String Solution(String status) {
		char [][] map = new char[3][3];
		
		int xCnt = 0, oCnt = 0, dCnt = 0;
		
		for(int i = 0; i < status.length(); i++) {
			char c = status.charAt(i);
			map[i/3][i%3] = c;
			if(c=='X') xCnt++;
			else if(c=='O') oCnt++;
			else dCnt++;
		}
		//���� ���� ���� ������ �ִ� ���
		if(dCnt>0) {
			if(xCnt<oCnt&&Math.abs(xCnt-oCnt)>1) return "invalid";
			//O�� ���������� ���� ��� O�� �̰ܾ���
			if(xCnt==oCnt) {
				if(Check(map,'O')&&!Check(map,'X')) return "valid";
				else return "invalid";
			//X�� ���������� ���� ��� X�� �̰ܾ���
			}else if(xCnt-oCnt==1) {
				if(!Check(map,'O')&&Check(map,'X')) return "valid";
				else return "invalid";
			}
		//9ĭ�� ��� �� �� ���
		}else {
			//xCnt�� 5�� oCnt 4���� �ƴϰų�, O�� �̱�� invalid
			if(xCnt-oCnt!=1 || Check(map,'O')) return "invalid";
			return "valid";
		}

		return "invalid";
	}
	private static boolean Check(char[][] map, char c) {
		//���� �˻�
		for(int i = 0; i < 3; i++) {
			if(map[i][0]== c && map[i][1]== c && map[i][2]== c) return true;
		}
		//���� �˻�
		for(int i = 0; i < 3; i++) {
			if(map[0][i]== c && map[1][i]== c && map[2][i]== c) return true;
		}
		//�밢�� �˻�
		if(map[0][0]==c && map[1][1]==c && map[2][2]==c) return true;
		if(map[0][2]==c && map[1][1]==c && map[2][0]==c) return true;
		
		return false;
	}

}
