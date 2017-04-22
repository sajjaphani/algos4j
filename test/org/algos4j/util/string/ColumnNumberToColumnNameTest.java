package org.algos4j.util.string;

import org.algos4j.util.StringUtil;

/**
 * Given a column number, this tests finding the column name in an Excel sheet.
 * 
 * @author psajja
 *
 */
public class ColumnNumberToColumnNameTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int columnNumber = 700;
		System.out.println("Number: " + columnNumber + ", Name: " + StringUtil.getExcelColumnName(columnNumber));
		
		int columnNumber1 = 1700;
		System.out.println("Number: " + columnNumber1 + ", Name: " + StringUtil.getExcelColumnName(columnNumber1));
		
		int columnNumber2 = Integer.MAX_VALUE;
		System.out.println("Number: " + columnNumber2 + ", Name: " + StringUtil.getExcelColumnName(columnNumber2));
	}

}
