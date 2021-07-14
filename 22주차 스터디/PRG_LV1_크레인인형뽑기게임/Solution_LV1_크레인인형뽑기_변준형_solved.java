import java.util.*;

class Solution {
         int answer = 0;
        public int solution(int[][] board, int[] moves) {

            Stack<Integer> stack = new Stack<>(); // 1. Stack으로 장바구니를 만든다.

            int size = board.length; // 2. 인형뽑기의 depth를 구해온다.

            for(int i=0; i<moves.length; i++){ // 3. moves의 길이만큼 인형을 뽑아야 한다.

                int c = moves[i]-1; // 4. 배열 index의 시작은 0부터지만 moves는 1부터 시작하므로 -1을 해준다.

                for(int r=0; r<size; r++){ // 5. 인형뽑기의 위치를 찾기 위해 2번에서 구한 depth만큼 순환한다.
                    if(board[r][c] == 0) // 6-1. 0이라면 인형이 없는거니까 패스
                        continue;
                    else { // 6-2. 0이 아니라면 인형이 있는 경우이다.
                        basketReset(stack, board[r][c]); // 7. 1번에서 stack으로 만든 장바구니에 인형을 집어넣는 함수이다.
                        board[r][c] = 0; // 8. 인형 뽑았으니까 0으로 바꿔주자
                        break;
                    }
                }
            }

            return answer * 2;
        }

        public void basketReset(Stack<Integer> stack, int cur){

            if(stack.isEmpty()){
                stack.add(cur);
                return;
            }

            // 장바구니에 넣은 최상단의 인형과 지금 뽑은 인형이 같다면 두개다 없애주자.
            if(stack.peek() == cur){
                stack.pop();
                answer++;
                return;
            } else {
                stack.add(cur);
            }
        }
    }