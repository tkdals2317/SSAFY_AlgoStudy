import java.util.*;
import java.io.*;
class Solution_LV2_단체사진찍기_이상민_solved {
    //  A,   C,   F,     J,     M,   N,    R,    T
    // 어피치, 콘, 프로도, 제이지, 무지, 네오, 라이언, 튜브
    // 네오는 프로도와 나란히 서기를 원함 
    // 튜브와 라이언은 세 칸 이상 떨어지기를 원함    
    static int totalCnt = 0;
    public int solution(int n, String[] data) {
        // n : 조건의 개수, data : 조건
        char [] input = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        boolean [] visited = new boolean[8];
        StringBuilder sb = new StringBuilder();
        //전역변수 안에서 초기화 안해주면 틀린다!
        totalCnt = 0;
        //순열 만들기
        perm(0, input, visited, sb, data);
        return totalCnt;
    }
    static void perm(int cnt, char[] input, boolean[] visited, StringBuilder sb, String[] data) {
        //기저 조건
		if (cnt == input.length) {
            //순열이 완성되면 조건에 맞는지 검사 조건에 맞으면 totalCnt 증가
			if(check(sb, data)) totalCnt++;
			return;
		}
        //순열 코드
		for (int i = 0; i < input.length; i++) {
			if (visited[i]) continue;
			sb.append(input[i]);
			visited[i] = true;
			perm(cnt + 1, input, visited, sb, data);
			visited[i] = false;
			sb.deleteCharAt(cnt);
		}
	}

	static boolean check(StringBuilder sb, String[] data) {		
		for(String d : data) {
            //연산자
			char oper = d.charAt(3);
			//거리
            int dist = d.charAt(4) - '0';
			//첫번째 캐릭터의 인덱스
            int idx1 = sb.indexOf(d.charAt(0)+"");
			//두번째 캐릭터의 인덱스
            int idx2 = sb.indexOf( d.charAt(2)+"");
			//두 캐릭터 사이의 거리
            int diff = Math.abs(idx1-idx2);
            //연산자의 반대일 때 false를 반환
			if(oper=='=') {
                //차이가 0일때 나란히 서는 거이므로 1을 빼준다
				if(diff-1!=dist) return false; 
			}else if(oper=='<') {
				if(diff-1>=dist)  return false;
			}else {
				if(diff-1<=dist) return false;
			}
		}
        //위 조건을 통과했다면 합격
		return true;
	}
}