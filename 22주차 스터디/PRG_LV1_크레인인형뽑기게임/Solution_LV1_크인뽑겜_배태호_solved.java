import java.util.*;
class Solution {
    public int solution(int[][] board, int[] moves) {
        Stack<Integer> st= new Stack<>();
        int ans=0;
		for(int i=0; i<moves.length; i++) 
			for(int j=0; j<board.length; j++) 
				if(board[j][moves[i]-1]!=0 ) {
					st.push(board[j][moves[i]-1]);
					board[j][moves[i]-1]=0;
					if(st.size()>1&&st.get(st.size()-1)==st.get(st.size()-2)) {
						ans+=2;
						st.pop();
						st.pop();
					}
					break;
				}
        return ans;
    }
}