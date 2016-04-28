package kr.co.hucloud.utilities.excel.read;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.hucloud.utilities.excel.option.ReadOption;
import kr.co.hucloud.utilities.excel.read.util.CellRef;
import kr.co.hucloud.utilities.excel.read.util.FileType;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelRead {
	
	public static List<Map<String, String>> read(ReadOption readOption) {
		
		Workbook wb = FileType.getWorkbook(readOption.getFilePath());
		Sheet sheet = wb.getSheetAt(0);
		
		int numOfRows = sheet.getPhysicalNumberOfRows();
		int numOfCells = 0;
		
		Row row = null;
		Cell cell = null;
		
		String cellName = "";
		
		Map<String, String> map = null;
		List<Map<String, String>> result = new ArrayList<Map<String, String>>(); 
		
		for(int rowIndex = readOption.getStartRow() - 1; rowIndex < numOfRows; rowIndex++) {
			
			row = sheet.getRow(rowIndex);
			
			if(row != null) {
				numOfCells = row.getPhysicalNumberOfCells();
				
				map = new HashMap<String, String>();
				
				for(int cellIndex = 0; cellIndex < numOfCells; cellIndex++) {
					
					cell = row.getCell(cellIndex);
					cellName = CellRef.getName(cell, cellIndex);
					
					if( !readOption.getOutputColumns().contains(cellName) ) {
						continue;
					}
					
					map.put(cellName, CellRef.getValue(cell));
				}
				
				result.add(map);
				
			}
			
		}
		
		return result;
		
	}
	
	
	
	public static void main(String[] args) {
		
		ReadOption ro = new ReadOption();
		ro.setFilePath("/Users/jangminchang/Downloads/4.-한국관광공사-예산계획안참고자료.xlsx");
		ro.setOutputColumns("A", "B");
		ro.setStartRow(1);
		
		List<Map<String, String>> result = ExcelRead.read(ro);
		
		for(Map<String, String> map : result) {
			System.out.println(map.get("B"));
		}
	}
	
}
