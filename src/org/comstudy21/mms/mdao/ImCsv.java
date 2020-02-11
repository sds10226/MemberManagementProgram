package org.comstudy21.mms.mdao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.StringTokenizer;
import java.util.Vector;
import org.comstudy21.mms.jdbc.JdbcUtil;

public class ImCsv {
	Connection conn = JdbcUtil.getConnection();
	MemberDao dao = new MemberDao(conn);
	Vector<Vector<String>> list = new Vector<Vector<String>>();
	FileInputStream fis;

	public Vector<Vector<String>> csvRead(String path) throws FileNotFoundException {
		FileInputStream fis = null;
		fis = new FileInputStream(path);
		String rawdata = null;
		Vector<Vector<String>> list = new Vector<>();

		try {
			byte[] data = new byte[fis.available()];
			fis.read(data);
			fis.close();
			rawdata = new String(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringTokenizer tkrawdata = new StringTokenizer(rawdata, "\n");
		while (tkrawdata.hasMoreTokens()) {
			String tk1data = tkrawdata.nextToken();
			StringTokenizer tkdata = new StringTokenizer(tk1data, ",");
			Vector<String> rawVectorData = new Vector<String>();
			while (tkdata.hasMoreTokens()) {
				rawVectorData.add(tkdata.nextToken());
			}
			list.add(rawVectorData);
		}
		return list;
	}
}
