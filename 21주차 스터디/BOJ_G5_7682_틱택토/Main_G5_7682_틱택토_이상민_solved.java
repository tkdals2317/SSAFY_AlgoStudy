import java.io.*;
import java.util.*;
public class Main_G5_7682_틱택토_이상민_solved {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_7682_틱택토.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String status = "";
		StringBuilder sb = new StringBuilder();
		do {
			status = br.readLine();
			//입력 종료 조건
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
		//아직 두지 않은 공간이 있는 경우
		if(dCnt>0) {
			if(xCnt<oCnt&&Math.abs(xCnt-oCnt)>1) return "invalid";
			//O가 마지막으로 놓은 경우 O가 이겨야함
			if(xCnt==oCnt) {
				if(Check(map,'O')&&!Check(map,'X')) return "valid";
				else return "invalid";
			//X가 마지막으로 놓은 경우 X가 이겨야함
			}else if(xCnt-oCnt==1) {
				if(!Check(map,'O')&&Check(map,'X')) return "valid";
				else return "invalid";
			}
		//9칸을 모두 다 둔 경우
		}else {
			//xCnt가 5개 oCnt 4개가 아니거나, O가 이기면 invalid
			if(xCnt-oCnt!=1 || Check(map,'O')) return "invalid";
			return "valid";
		}

		return "invalid";
	}
	private static boolean Check(char[][] map, char c) {
		//가로 검사
		for(int i = 0; i < 3; i++) {
			if(map[i][0]== c && map[i][1]== c && map[i][2]== c) return true;
		}
		//새로 검사
		for(int i = 0; i < 3; i++) {
			if(map[0][i]== c && map[1][i]== c && map[2][i]== c) return true;
		}
		//대각선 검사
		if(map[0][0]==c && map[1][1]==c && map[2][2]==c) return true;
		if(map[0][2]==c && map[1][1]==c && map[2][0]==c) return true;
		
		return false;
	}

}
