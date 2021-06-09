package Algorithm;

import java.util.*;

public class Soulition_programmers_level2_순위검색 {
	static HashMap<String, ArrayList<Integer>> infoMap = new HashMap<>();

	public static void main(String[] args) {

		String[] query = { "java and backend and junior and pizza 100",
				"python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
				"- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150" };
		String[] info = { "java backend junior pizza 150", "python frontend senior chicken 210",
				"python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80",
				"python backend senior chicken 50" };
		/////////////////////////////////////////////////////

		int[] answer = new int[query.length];

		for (int i = 0; i < info.length; i++) { // info 정보를 가지고 hashmap 생성
			String[] inf = info[i].split(" ");
			int score = Integer.parseInt(inf[4]);
			dfs(0, "", inf, score);
		}

		
		for (String key : infoMap.keySet()) // infoMap을 key값을 기준으로 정렬
			Collections.sort(infoMap.get(key));

		for (int i = 0; i < query.length; i++) {
			String queryKey = "";
			String[] qry = query[i].split(" "); //query 옵션
			int score = Integer.parseInt(qry[qry.length - 1]); // query 스코어

			for (int j = 0; j < qry.length - 1; j++) { // 주어진 query로부터 key 구함
				if (qry[j].equals("and")) continue;
				queryKey += qry[j];
			}

			if (infoMap.containsKey(queryKey)) { // query로부터 key를 가지고 hashMap에 들어있는 value 값 중 score와 같거나 큰 값을 이분탐색으로 찾음
				ArrayList<Integer> tmp = infoMap.get(queryKey);
				answer[i] = binarySearch(tmp, score);
			}
		}
		for (int i = 0; i < answer.length; i++)
			System.out.println(answer[i]);
//        return answer;
	}

	public static void dfs(int dep, String key, String[] inf, int score) {
		if (dep == 4) {
//        	System.out.println(key);
			ArrayList<Integer> list = new ArrayList<>();
			if (infoMap.containsKey(key)) // 만들어진 tmp와 동일한 key가 존재한다면 해당 key의 value 리스트를 가져옴
				list = infoMap.get(key);

			list.add(score); // value 값에 스코어 저장한 뒤 hashmap에 넣어줌
			infoMap.put(key, list);
			return;
		}
		dfs(dep + 1, key + inf[dep], inf, score); // 각각 option에 따른 경우의 수
		dfs(dep + 1, key + "-", inf, score); // option에 -가 들어가는 경우의 수
	}

	public static int binarySearch(ArrayList<Integer> arr, int score) { // 이분탐색
		int start = 0;
		int mid = 0;
		int end = arr.size();

		while (end > start) { // end가 start보다 같거나 작아지면, 그 값이 Lower Bound이므로 반복을 종료
			mid = (start + end) / 2;
			if (arr.get(mid) >= score) // 중간값이 원하는 값보다 크거나 같을 경우, 끝값을 중간값으로 설정하여 다시 탐색
				end = mid;
			else
				start = mid + 1; // 중간값이 원하는 값보다 작을 경우, 시작값을 중간값+1로 설정하여 다시 탐색
		}
		return arr.size() - start; // 이분탐색이 끝나면 start 값은 score의 값과 같거나 큰 원소의 위치가 반환
	}
}
