package org.comstudy21.mms.mdao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Vector;

import org.comstudy21.mms.jdbc.JdbcUtil;

public class ExCsv {
	Connection conn = JdbcUtil.getConnection();
	MemberDao dao = new MemberDao(conn);
	Vector<Vector<String>> list = null;

	public boolean csvWrite(String path) throws FileNotFoundException {
		String data = "";
		list = dao.selectAll();
		for (int i = 0; i < list.size(); i++) {

			for (int j = 0; j < list.get(0).size(); j++) {
				if(j==0){
					data += list.get(i).get(j);	
				}else{
					data += "," + list.get(i).get(j);
				}
			}
			data += "\n";
		}

		FileOutputStream fos = new FileOutputStream(path);
		try {
			fos.write(data.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

}
