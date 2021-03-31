import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SW역량_5656_벽돌깨기_변준형_solved {
 
    static class Block {
        int h;
        int w;
        int number;
        public Block(int h, int w, int number) {
            super();
            this.h = h;
            this.w = w;
            this.number = number;
        }
    }
     
    static int T, N, W, H, answer, allBlockCnt, tempBlockCnt;
    static boolean finish;
    static int[][] map, temp;
    static int[] HSize;
    static Queue<Block> queue = new LinkedList<>();
    static Queue<Integer> fall = new LinkedList<Integer>(); 
    static int[] order;
    static int[] dh= {1,-1,0,0};
    static int[] dw= {0,0,1,-1};
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
         
        T = Integer.parseInt(in.readLine());
         
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(in.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
             
            map = new int[H][W];
            temp = new int[H][W];
            order = new int[N];
            answer = Integer.MAX_VALUE;
            allBlockCnt=0;
             
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(in.readLine(), " ");
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] != 0) ++allBlockCnt; 
                }
            }
             
            nHr(0);
             
            System.out.println("#"+t+" "+answer);
        }
    }
 
    private static void nHr(int cnt) {
        if(cnt == N) {
            removeBlock();
            if(finish) {
                answer = 0;
                return;
            } else {
                answer = Math.min(answer, allBlockCnt - tempBlockCnt);
                return;
            }
        }
         
        if(answer == 0) return;
         
        for (int i = 0; i < W; i++) {
            order[cnt] = i;
            nHr(cnt+1);
        }
    }
 
    private static void removeBlock() {
        finish = false;
        copyMap();
        tempBlockCnt = 0;
         
        for (int a = 0; a < N; a++) {
            int curW = order[a];
             
            queue.clear();
            Block start = null;
            boolean goNext = false;
            for (int i = 0; i < H; i++) {
                if(temp[i][curW]!=0) {
                    start = new Block(i, curW, temp[i][curW]);
                    queue.offer(start);
                    break;
                }
                if(i==H-1 && temp[i][curW]==0) {
                    goNext = true;
                    break;
                }
            }
             
            if(goNext) continue;
            //벽돌 제거 
            while(!queue.isEmpty()) {
                Block curBlock = queue.poll();
                 
                if(curBlock.number==1) {
                    temp[curBlock.h][curBlock.w] = 0;
                    ++tempBlockCnt;
                } else {
                    for (int dir = 0; dir < 4; dir++) {
                        go(curBlock.h, curBlock.w, curBlock.number, dir);
                    }
                }
            }
             
            if(tempBlockCnt == allBlockCnt) {
                finish = true;
                return;
            }
             
            if(start.number == 1) continue;
            fall.clear();
            for (int i = 0; i < W; i++) {
                for (int j = H-1; j >= 0; j--) {
                    if(temp[j][i] != 0) fall.offer(temp[j][i]);
                }
                 
                int hIdx = H-1;
                while(!fall.isEmpty()) {
                    temp[hIdx--][i] = fall.poll();
                }
                while(hIdx>=0) {
                    temp[hIdx--][i] = 0; 
                }
            }
        }
    }
 
    private static void copyMap() {
        for (int i = 0; i < H; i++) {
            System.arraycopy(map[i], 0, temp[i], 0, W);
        }
    }
 
    private static void go(int h, int w, int number, int dir) {
        for (int i = 0; i < number; i++) {
            int nh = h + i*dh[dir];
            int nw = w + i*dw[dir];
             
            if(!check(nh,nw)) continue;
            if(temp[nh][nw] != 0 && temp[nh][nw] != 1) queue.offer(new Block(nh, nw, temp[nh][nw]));
            if(temp[nh][nw] != 0) {
                temp[nh][nw] = 0;
                ++tempBlockCnt;
            }
        }
    }
 
    private static boolean check(int h, int w) {
        return h>=0 && h<H && w>=0 && w<W;
    }
}