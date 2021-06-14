import java.util.*;
public class prg_완주하지못한선수_l1 {
	
	static String Solution(String[] participant, String[] completion) {
		 	Arrays.sort(participant); // 참가자 오름차순 정렬
			Arrays.sort(completion); // 완주자 오름차순 정렬
	      
			for(int i=0;i<completion.length;++i) {// 완주자 길이 만큼 비교
				if(!participant[i].equals(completion[i])) {// 다르면 바로 반환
					return participant[i];
				}
			}
	        return participant[participant.length-1];// 위에서 안걸린거면 마지막주자가 미완주
	}
	
	public static void main(String[] args) throws Exception {
		String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};
		String[] completion = {"josipa", "filipa", "marina", "nikola"};

		System.out.println(Solution(participant,completion));
	}

}
