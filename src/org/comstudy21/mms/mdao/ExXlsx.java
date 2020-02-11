package org.comstudy21.mms.mdao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Vector;

import org.apache.poi.xssf.usermodel.*;
import org.comstudy21.mms.jdbc.JdbcUtil;

public class ExXlsx {
	public boolean exXlsx(String path) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Sheet1");
		XSSFRow row = null;
		XSSFCell cell = null;
		Connection conn = JdbcUtil.getConnection();
		MemberDao dao = new MemberDao(conn);
		Vector<Vector<String>> list = dao.selectAll();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				row = sheet.createRow(i);
				for (int j = 0; j < list.get(i).size(); j++) {
					cell = row.createCell(j);
					cell.setCellValue(list.get(i).get(j));
				}
			}
		}
		try {
			FileOutputStream fos = new FileOutputStream(path);
			try {
				workbook.write(fos);
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
