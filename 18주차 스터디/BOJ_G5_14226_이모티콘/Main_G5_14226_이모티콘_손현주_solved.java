import java.io.*;
import java.util.*;

public class bj_14226_G5 {

	static class Emoji {
		int screen, clipboard, time;

		Emoji(int screen, int clipboard, int time) {
			this.screen = screen;
			this.clipboard = clipboard;
			this.time = time;
		}
	}

	static int N;
	static boolean visited[][] = new boolean[2001][2001];//S가1000인경우2000

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		find();
	}

	static void find() {
		Queue<Emoji> q = new LinkedList<>();
		q.add(new Emoji(1, 0, 0));
		visited[1][0] = true;// 첫스타트위치
		while (!q.isEmpty()) {// bfs
			Emoji now = q.poll();
			if(now.screen==N) {// 개수만큼 화면에 다찍히면
				System.out.println(now.time);//출력
				break;
			}
			// 1 . 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
			q.add(new Emoji(now.screen, now.screen, now.time + 1));
			// 2 . 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
			int nextScreen = now.screen + now.clipboard;
			if (!visited[nextScreen][now.clipboard] && 
				now.clipboard > 0 && nextScreen <= N) {
				q.add(new Emoji(nextScreen,now.clipboard,now.time+1));
				visited[nextScreen][now.clipboard]=true;
			}
			// 3 . 화면에 있는 이모티콘 중 하나를 삭제한다.
			if(now.screen > 0 && !visited[now.screen-1][now.clipboard]) {
				q.offer(new Emoji(now.screen-1,now.clipboard, now.time+1));
				visited[now.screen-1][now.clipboard] = true;
			}
		}
	}

}
