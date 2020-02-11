package org.comstudy21.mms.mdao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyPic {
	FileInputStream fis;
	public CopyPic(String path, String fileName) throws FileNotFoundException {
		FileInputStream fis = null;
		fis = new FileInputStream(path);
		byte[] data=null;
		try {
			data = new byte[fis.available()];
			fis.read(data);
			fis.close();
		} catch (IOException e) {
			
		}
		
		FileOutputStream fos = new FileOutputStream("image/"+fileName);
		try {
			fos.write(data);
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}
	}
}
