class Solution{
    public String solution(String new_id){
        String answer = new_id;

        answer = answer
                // 1. 소문자로 치환
                .toLowerCase()
                // 2. 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.) 제외하고 모든 문자 제거
                .replaceAll("[^\\.\\-_0-9a-z]", "")
                // 3. 마침표(.)가 2번 이상 연속된 부분 하나로 치환
                .replaceAll("[.]+", ".");

        // 4. 마침표(.)가 처음이나 끝에 위치한다면 제거
        if(answer.startsWith(".")) answer = answer.substring(1);
        if(answer.endsWith(".")) answer = answer.substring(0, answer.length() - 1);

        // 5. 빈 문자열이라면 "a" 대입
        if(answer.isEmpty()) answer = "a";

        // 6. 16자 이상이면 첫 15개의 문자를 제외한 나머지 제거, 제거 후 마침표(.)가 마지막 위치한다면 마침표 제거
        if(answer.length() >= 16)
            answer = answer.substring(0, 15).endsWith(".") ?answer.substring(0, 14) :
                            answer.substring(0, 15);

        // 7. 길이가 2자 이하라면 마지막 문자를 길이가 3이 될 때까지 반복해서 끝에 추가
        if(answer.length() <= 2) {
            char last = answer.charAt(answer.length() - 1);
            while(answer.length() < 3) {
                answer += last;
            }
        }

        return answer;
    }
}