class Solution_LV2_거리두기확인하기_변준형_solved {
	static char[][] map;
	// 사람이면 안되는 곳
	static int[] x1 = { 1, -1, 0, 0 };
	static int[] y1 = { 0, 0, -1, 1 };
	// 사람일 수는 있지만 두 곳에 파티션이 필요한 곳
	static int[] x2 = { -1, -1, 1, 1 };
	static int[] y2 = { -1, 1, -1, 1 };
	// 사람일 수는 있지만 한 곳에 파티션이 필요한 곳
	static int[] x3 = { -2, 2, 0, 0 };
	static int[] y3 = { 0, 0, -2, 2 };
	static int answer;

	public int[] solution(String[][] places) {
		answer = 1;
		map = new char[5][5];
		int[] ans = new int[places.length]; // 문자열 배열
		String[] place;

		for (int a = 0; a < places.length; a++) {
			place = places[a];
			for (int i = 0; i < place.length; i++) {
				map[i] = place[i].toCharArray();
			}

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (map[i][j] == 'P') { // 사람이면 dfs시작
						dfs(i, j);
					}
				}
			}
			if (answer == 0) {
				ans[a] = answer; // 거리두기 실패
			} else {
				ans[a] = answer; // 거리두기 성공
			}
			answer = 1;
		}

		return ans;
	}

	public void dfs(int x, int y) {
		for (int i = 0; i < 4; i++) {					//	사람 상하좌우에 사람이 있는지 확인 있다면 실패 없으면 성공
			int nx = x + x1[i];
			int ny = y + y1[i];

			if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5)
				continue;
			if (map[nx][ny] == 'P')
				answer = 0;
		}

		for (int i = 0; i < 4; i++) {				
			int nx = x + x2[i];
			int ny = y + y2[i];

			if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5)
				continue;
			if (map[nx][ny] == 'P')		//	처음 사람 위치 상하좌우에 무조건 파티션이 있어야 한다 있으면 성공 없으면 실패
				check1(x, y, nx, ny);
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + x3[i];		
			int ny = y + y3[i];

			if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5)
				continue;
			if (map[nx][ny] == 'P')		//	처음 사람 위치와 다음 사람 위치 사이에 파티션이 있어야 한다 있으면 성공 없으면 실패
				check2(x, y, nx, ny);
		}
	}

	public void check1(int x, int y, int nx, int ny) {
		int newX = x - nx;
		int newY = y - ny;

		if (newX == 1 && newY == 1) {
			if (map[x - 1][y] != 'X' || map[x][y - 1] != 'X')
				answer = 0;
		} else if (newX == 1 && newY == -1) {
			if (map[x - 1][y] != 'X' || map[x][y + 1] != 'X')
				answer = 0;
		} else if (newX == -1 && newY == 1) {
			if (map[x][y - 1] != 'X' || map[x + 1][y] != 'X')
				answer = 0;
		} else {
			if (map[x][y + 1] != 'X' || map[x + 1][y] != 'X')
				answer = 0;
		}
	}

	public void check2(int x, int y, int nx, int ny) {
		int newX = x - nx;
		int newY = y - ny;

		if (newX == -2 && newY == 0) {
			if (map[x + 1][y] != 'X')
				answer = 0;
		} else if (newX == 2 && newY == 0) {
			if (map[x - 1][y] != 'X')
				answer = 0;
		} else if (newX == 0 && newY == -2) {
			if (map[x][y + 1] != 'X')
				answer = 0;
		} else {
			if (map[x][y - 1] != 'X')
				answer = 0;
		}
	}
}