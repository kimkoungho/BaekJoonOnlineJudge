import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.awt.Point;


public class Problem_13460 {
	
	public static int[] mx = {0, 0, -1, 1};
	public static int[] my = {-1, 1, 0, 0};
	
	public static boolean [][]visit;
	
	public static boolean move(char[][] map, Point ball, Point target, int d) {
		switch(d) {
		case 1://up
			for(int y=ball.y; y>0; y--) {
				if(ball.x == target.x && y == target.y) 
					return true;
				else if(map[y][ball.x] != '.') {
					ball.setLocation(ball.x, y+1);
					return false;
				}
			}
		case 2://down
			for(int y=ball.y; y<map.length-1; y++) {
				if(ball.x == target.x && y == target.y)
					return true;
				else if(map[y][ball.x] != '.') {
					ball.setLocation(ball.x, y-1);
					return false;
				}
			}
		case 3://left
			for(int x=ball.x; x>0; x--) {
				if(x == target.x && ball.y == target.y)
					return true;
				else if(map[ball.y][x] != '.') {
					ball.setLocation(x+1, ball.y);
					return false;
				}
			}
		case 4://right
			for(int x=ball.x; x<map.length-1; x++) {
				if(x == target.x && ball.y == target.y)
					return true;
				else if(map[ball.y][x]!='.') {
					ball.setLocation(x-1, ball.y);
					return false;
				}
			}
		}
		
		return false;
	}
	
	public static int start(char[][] map, Point red, Point blue, Point target) {
		
		// up, down, left, right
	
		
		return 0;
	}
	
	public static int solve(char[][] map, Point red, Point blue, Point target, int count) {
		System.out.println(red);
		showArray(map);
		
		
		// 기저 사례 1 : red = target
		//if(target.equals(red)) return 0;
		// 기저 사례 2 : blue = target
		//if(target.equals(blue)) return -1;
		
		// 기저 사례 3 : count > 10
		if(count > 10) return -1;

		boolean redHole = false, blueHole = false;
		
		int ret = 11;
		// 기울임 : 한 쪽으로 갈 수 있는 끝까지 움직임
		// 기울이는 방향 -> 위, 아래, 왼, 오른 
		for(int d=0; d<4; d++) {
			int redX = red.x, redY = red.y;
			int blueX = blue.x, blueY = blue.y;
			
			map[red.y][red.x] = '.';
			map[blue.y][blue.x] = '.';
			
			int sw = 0;
			while(!visit[redY+my[d]][redX+mx[d]]) {
				if(target.y == blueY && target.x == blueX) {// 
					blueHole = true;
					break;
				}else if(target.y == redY && target.x == redX) {
					redHole = true;
				}
				
				if(map[redY+my[d]][redX+mx[d]]=='.' && !redHole) {
					redX += mx[d];
					redY += my[d];
					visit[redY][redX] = true;
					sw++;
				}else {
					
				}
				
				if(map[blueY+my[d]][blueX+mx[d]]=='.' && !blueHole) {
					blueX += mx[d];
					blueY += my[d];
					sw++;
				}else {
					
				}
			}
			
//			System.out.println(redY + ", "+ redX);
//			System.out.println(blueY+"," +blueX);
//			System.out.println(redHole + "  "+blueHole);
			if(blueHole)
				return -1;
			else if(redHole)
				return 1;
			
			if(sw == 0) continue;
			
			// 예외 처리
			if(redX == blueX && redY == blueY) {
				//원래 위치를 비교해서 처리
				switch(d) {
				case 0: //up
					if(red.y < blue.y) blueY++;
					else redY++;
				case 1://down
					if(red.y < blue.y) redY--;
					else blueY--;
				case 2://left
					if(red.x < blue.x) blueX++;
					else redX++;
				case 3://right
					if(red.x < blue.x) redX--;
					else blueX--;
				}
			}else {
				red.x = redX;
				red.y = redY;
				blue.x = blueX;
				blue.y = blueY;
			}
			
			map[redY][redX] = 'R';
			map[blueY][blueX] = 'B';
			
			System.out.println(d);
			int subRet = solve(map, red, blue, target, count+1);
			if(subRet == -1)
				continue;
			else
				ret = Math.min(ret, subRet+1);
		}
		
		return ret > 10 ? -1 : ret;
	}
	
	
	
	/*
5 5
#####
#..B#
#.#.#
#RO.#
#####
1

7 7
#######
#...RB#
#.#####
#.....#
#####.#
#O....#
#######
5

7 7
#######
#..R#B#
#.#####
#.....#
#####.#
#O....#
#######
5

10 10
##########
#R#...##B#
#...#.##.#
#####.##.#
#......#.#
#.######.#
#.#....#.#
#.#.#.#..#
#...#.O#.#
##########
-1

3 7
#######
#R.O.B#
#######
1

10 10
##########
#R#...##B#
#...#.##.#
#####.##.#
#......#.#
#.######.#
#.#....#.#
#.#.##...#
#O..#....#
##########
7

3 10
##########
#.O....RB#
##########
-1
	 */

	public static void showArray(char[][] arr) {
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++)
				System.out.print(arr[i][j]+"\t");
			System.out.println();
		}
		System.out.println();
	}
	public static void main(String[] args) {
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			String line = br.readLine();
			int N = Integer.parseInt(line.split(" ")[0]);
			int M = Integer.parseInt(line.split(" ")[1]);
			
			Point target = null, red = null, blue = null;
		
			visit = new boolean[N][M];
			char[][]map = new char[N][M];
			for(int i=0; i<N; i++) {
				line = br.readLine();
				map[i] = line.toCharArray();
				for(int j=0; j<M; j++) {
					switch(map[i][j]) {
					case '#': visit[i][j] = true;break;
					case 'O': target = new Point(j, i); map[i][j] = '.'; break;
					case 'R': red = new Point(j, i); visit[i][j] = true; break;
					case 'B': blue = new Point(j, i); break;
					}
				}
			}
			
			//System.out.println(solve(map, red, blue, target, 0));
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
