package org.comstudy21.mms.mdao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Vector;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.comstudy21.mms.jdbc.JdbcUtil;

public class ImXlsx {
	Connection conn = JdbcUtil.getConnection();
	MemberDao dao = new MemberDao(conn);
	Vector<Vector<String>> list = new Vector<Vector<String>>();
	FileInputStream fis;

	public Vector<Vector<String>> imXlsx(String path) throws FileNotFoundException {
		Vector<Vector<String>> list = new Vector<>();
		FileInputStream fis = new FileInputStream(path);
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int maxrow = sheet.getPhysicalNumberOfRows();
			if (maxrow != 0) {
				for (int i = 0; i < maxrow; i++) {
					if(sheet.getRow(i)==null){
						break;
					}
					XSSFRow row = sheet.getRow(i);
					Vector<String> rowdata = new Vector<>();
					int maxcell = row.getPhysicalNumberOfCells();
					if (maxcell != 0) {
						for (int j = 0; j < maxcell; j++) {
							XSSFCell cell = row.getCell(j);
							String data = cell + "";
							rowdata.add(data);
						}
					}
					list.add(rowdata);
				}
			}
			fis.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return list;
	}
}
