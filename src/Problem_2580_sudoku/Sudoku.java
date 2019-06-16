package Problem_2580_sudoku;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sudoku {
	
	private final String NUMBERS = "123456789";
	
	public String[][] getValues(){
		String[][] values = new String[9][9];
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				values[i][j] = NUMBERS;
			}
		}
		return values;
	}
	
	private int getStartSquareIndex(int index) {
		if(index < 3) {
			return 0;
		}else if(index < 6) {
			return 3;
		}else {
			return 6;
		}
	}
	
	// 같은 행에서 value 가 가능한 좌표들 조회 
	private List<String> getKeyListRowUnit(int row, int col, String value, String[][] values){
		List<String> keyList = new ArrayList();
		for(int i=0; i<9; i++) {
			if(i==col) continue;
			
			if(values[row][i].contains(value)) {
				keyList.add(row + "_" +i);
			}
		}
		return keyList;
	}
	
	// 같은 열에서 value 가 가능한 좌표들 조회 
	private List<String> getKeyListColUnit(int row, int col, String value, String[][] values){
		List<String> keyList = new ArrayList();
		for(int i=0; i<9; i++) {
			if(i==row) continue;
			
			if(values[i][col].contains(value)) {
				keyList.add(i + "_" +col);
			}
		}
		return keyList;
	}
	
	// 같은 사각형에서 value 가 가능한 좌표들 조회 
	private List<String> getKeyListSquareUnit(int row, int col, String value, String[][] values){
		List<String> keyList = new ArrayList();
		
		int startRow = this.getStartSquareIndex(row);
		int startCol = this.getStartSquareIndex(col);
		for(int i=startRow; i<startRow+3; i++) {
			for(int j=startCol; j<startCol+3; j++) {
				if(values[i][j].contains(value)) {
					keyList.add(i + "_" + j);
				}
			}
		}
		
		return keyList;
	}
	
	private boolean deleteValue(int row, int col, String value, String[][] values) {
		assert value.length() == 1;
		
		String candidates = values[row][col];
		
		// 이미 값을 지운 경우 
		if(!candidates.contains(value)) {
			return true;
		}
		
		// 지울수 있는 값이 없음 
		if(candidates.length() == 1) {
			return false;
		}
		values[row][col] = candidates.replace(value, "");
		candidates = values[row][col]; 
		
		if(candidates.length() == 1) {
			// 현재 값과 같은 단위에서 현재 값 제거 
			if(!this.deleteValueInUnits(row, col, candidates, values)) {
				return false;
			}
		}
		
		// 각 단위에서 value 가 가능한 값들이 있어야함
		List<List<String>> unit2dList = new ArrayList<List<String>>();
		// 같은 행 
		unit2dList.add(this.getKeyListRowUnit(row, col, value, values));
		// 같은 열 
		unit2dList.add(this.getKeyListColUnit(row, col, value, values));
		// 같은 사각형 
		unit2dList.add(this.getKeyListSquareUnit(row, col, value, values));
		
		for(List<String> unitKeyList : unit2dList) {
			if(unitKeyList.isEmpty()) { // 잘못됨 
				return false;
			}else if(unitKeyList.size() == 1) {
				// 현 단위에서 가능한 자리가 1개 
				String[] word = unitKeyList.get(0).split("_");
				int r = Integer.parseInt(word[0]);
				int c = Integer.parseInt(word[1]);
				
				this.setValue(r, c, value, values);
			}
		}
		
		return true;
	}
	
	private boolean deleteValueInUnits(int row, int col, String value, String[][] values) {
		// 같은 행, 열 에서 값제거
		for(int i=0; i<9; i++) {
			// 같은 행 
			if(i != col) {
				if(!this.deleteValue(row, i, value, values)) {
					return false;
				}	
			}

			// 같은 열
			if(i != row) {
				if(!this.deleteValue(i, col, value, values)) {
					return false;
				}
			}
		}
		
		// 같은 사각형에서 값제거 
		int startRow = this.getStartSquareIndex(row);
		int startCol = this.getStartSquareIndex(col);
		for(int i=startRow; i<startRow+3; i++) {
			for(int j=startCol; j<startCol+3; j++) {
				if(i == row && j == col) {
					continue;
				}
				
				if(!this.deleteValue(i, j, value, values)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean setValue(int row, int col, String value, String[][] values) {
		String candidates = values[row][col];
		
		for(int i=0; i<candidates.length(); i++) {
			String candidate = candidates.substring(i, i+1);
			
			if(value.equals(candidate)) {
				continue;
			}
			
			if(!this.deleteValue(row, col, candidate, values)) {
				return false;
			}
		}
		
		return true;
	}
	
	public String[][] search(String[][] values){
		if(checkValues(values)) {
			return values;
		}
		
		// 가장 후보들 수가 적은 곳 찾기
		int targetRow = -1, targetCol = -1;
		String targetValues = null;
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(values[i][j].length() == 1) continue;
				
				if(targetValues == null || values[i][j].length() < targetValues.length()) {
					targetRow = i;
					targetCol = j;
					targetValues = values[i][j];
				}
			}
		}
		
		String[][] result = this.copyValues(values);
		for(int i=0; i<targetValues.length(); i++) {
			String targetValue = targetValues.substring(i, i+1);
			
			String[][] copyValue = this.copyValues(values);
			
			// 값 설정 : 못만들면 다음 후보로 
			if(!this.setValue(targetRow, targetCol, targetValue, copyValue)) {
				continue;
			}
			
			result = this.search(copyValue);
			if(this.validate(result)) {
				return result;
			}
		}
		
		return result;
	}
	
	
	// 완성 여부 확인 
	private boolean checkValues(String[][] values) {
		int count = 0;
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(values[i][j].length() == 1) {
					count++;
				}
			}
		}
		return count == 81;
	}
	
	private String[][] copyValues(String[][] values){
		String[][] copy = new String[9][9];
		for(int i=0; i<9; i++) {
			System.arraycopy(values[i], 0, copy[i], 0, 9);
		}
		
		return copy;
	}
	
	// 실제로 스도쿠가 성립하는지 검사 
	public boolean validate(String[][] values) {
		for(int i=0; i<9; i++) {
			Set<Character> rowSet = new HashSet<Character>();
			Set<Character> colSet = new HashSet<Character>();
			
			for(int j=0; j<9; j++) {
				// 행 
				if(values[i][j].length() != 1) {
					return false;
				}
				rowSet.add(values[i][j].charAt(0));
				
				// 열 
				if(values[j][i].length() != 1) {
					return false;
				}
				colSet.add(values[j][i].charAt(0));
			}
			
			if(rowSet.size() != 9 || colSet.size() != 9) {
				return false;
			}
		}
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				int startRow = i *3;
				int startCol = j*3;
				
				Set<Character> valueSet = new HashSet<Character>();
				for(int r=startRow; r<startRow+3; r++) {
					for(int c=startCol; c<startCol+3; c++) {
						if(values[r][c].length() != 1) {
							return false;
						}
						
						valueSet.add(values[r][c].charAt(0));
					}
				}
				
				if(valueSet.size() != 9) {
					return false;
				}
			}
		}	
		
		return true;
	}

	private void print(String[][] values) {
		System.out.println("---------------------------");
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				String str = String.format("%9s", values[i][j]);
				System.out.print(str+"\t");
			}
			System.out.println();
		}
		System.out.println("---------------------------");
	}
	
	// 테스트 코드
	public static void main(String[] args) throws IOException {
		Sudoku sudoku = new Sudoku();
		
		String filePath = "/Users/gimgyeongho/eclipse-workspace/Algorithm/sudoku_input/hardest.txt";
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = null;
		while((line = br.readLine()) != null) {
			line = line.trim();
//			System.out.println(line);
			
			String[][] values = sudoku.getValues();
			
			int index = 0;
			for(char value : line.toCharArray()) {
				if(value >= '1' && value <= '9') {
					int rowIndex = (index) / 9;
					int colIndex = (index - rowIndex * 9) % 9;
					
					String valueStr = value + "";
					sudoku.setValue(rowIndex, colIndex, valueStr, values);
				}
				index++;
			}
//			sudoku.print(values);
			values = sudoku.search(values);		
//			sudoku.print(values);
			
			System.out.println(sudoku.validate(values));
		}

	}
}