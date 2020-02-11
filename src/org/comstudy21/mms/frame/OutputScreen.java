package org.comstudy21.mms.frame;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.comstudy21.mms.jdbc.JdbcUtil;
import org.comstudy21.mms.mdao.ExCsv;
import org.comstudy21.mms.mdao.ExXlsx;
import org.comstudy21.mms.mdao.ImCsv;
import org.comstudy21.mms.mdao.ImXlsx;
import org.comstudy21.mms.mdao.MemberDao;

@SuppressWarnings("serial")
public class OutputScreen extends JPanel implements ActionListener {
	Connection conn = JdbcUtil.getConnection();;
	static ListScreen ls = new ListScreen();
	MemberDao dao = new MemberDao(conn);
	JPanel os = new JPanel();

	Button allPrint = new Button("��ü���");
	Button csvEx = new Button("CSV ���Ϸ� ��������");
	Button csvIm = new Button("CSV ���� ��������");
	Button excelEx = new Button("Excel ���Ϸ� ��������");
	Button excelIm = new Button("Excel ���� ��������");

	ExCsv ec = new ExCsv();
	ExXlsx ex = new ExXlsx();
	ImCsv ic = new ImCsv();
	ImXlsx ix = new ImXlsx();
	JFileChooser fchooser = new JFileChooser();
	FileNameExtensionFilter csvextension = new FileNameExtensionFilter("CSV", "csv");
	FileNameExtensionFilter excelextension = new FileNameExtensionFilter("Xlsx", "xlsx");
	Vector<Vector<String>> list;

	public OutputScreen() {
		os.setLayout(new GridLayout(10, 1));
		TitledBorder otlb = new TitledBorder("���");
		os.setBorder(otlb);
		allPrint.addActionListener(this);
		csvEx.addActionListener(this);
		csvIm.addActionListener(this);
		excelEx.addActionListener(this);
		excelIm.addActionListener(this);
		os.setPreferredSize(new Dimension(300, 500));
		os.add(allPrint);
		os.add(csvEx);
		os.add(csvIm);
		os.add(excelEx);
		os.add(excelIm);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String pathName = null;
		if (allPrint == e.getSource()) {
			ls.changeTableData();
		} else if (csvEx == e.getSource()) {
			fchooser.setFileFilter(csvextension);
			int ret = fchooser.showSaveDialog(null);
			if (ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "������ �� �����̸��� �Է����� �ʾҽ��ϴ�.", "�� ��", JOptionPane.WARNING_MESSAGE);
			} else if (ret == JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "�ش� ��� �� �����̸����� �����մϴ�.", "�� ��", JOptionPane.INFORMATION_MESSAGE);
				pathName = fchooser.getSelectedFile().getPath();
				pathName += ".csv";
			}
			if(pathName==null){
				return;
			}

			boolean tf;
			try {
				tf = ec.csvWrite(pathName);

				if (tf) {
					JOptionPane.showMessageDialog(null, "���Ϸ�  �������� ����!!", "�� ��", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "���Ϸ� �������� ����!!", "�� ��", JOptionPane.WARNING_MESSAGE);
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (csvIm == e.getSource()) {
			if(MMFrame.lg.choice==0){
				JOptionPane.showMessageDialog(null, "�����ڰ� �ƴϸ� ����� ����Ҽ� �����ϴ�.");
				return;
			}
			fchooser.setFileFilter(csvextension);
			int ret = fchooser.showOpenDialog(null);
			if (ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�!", "�� ��", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (ret == JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "������ ���õǾ����ϴ�.", "�� ��", JOptionPane.INFORMATION_MESSAGE);
				pathName = fchooser.getSelectedFile().getPath();
			}
			

			try {
				list = ic.csvRead(pathName);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ls.showTableImData(list);
		} else if (excelEx == e.getSource()) {
			fchooser.setFileFilter(excelextension);
			int ret = fchooser.showSaveDialog(null);
			if (ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "������ �� �����̸��� �Է����� �ʾҽ��ϴ�.", "�� ��", JOptionPane.WARNING_MESSAGE);
			} else if (ret == JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "�ش� ��� �� �����̸����� �����մϴ�.", "�� ��", JOptionPane.INFORMATION_MESSAGE);
				pathName = fchooser.getSelectedFile().getPath();
				pathName += ".xlsx";
			}
			
			if(pathName==null){
				return;
			}

			boolean tf = ex.exXlsx(pathName);
			if (tf) {
				JOptionPane.showMessageDialog(null, "���Ϸ�  �������� ����!!", "�� ��", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "���Ϸ� �������� ����!!", "�� ��", JOptionPane.WARNING_MESSAGE);
			}

		} else if (excelIm == e.getSource()) {
			if(MMFrame.lg.choice==0){
				JOptionPane.showMessageDialog(null, "�����ڰ� �ƴϸ� ����� ����Ҽ� �����ϴ�.");
				return;
			}
			fchooser.setFileFilter(excelextension);
			int ret = fchooser.showOpenDialog(null);
			if (ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�!", "�� ��", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (ret == JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "������ ���õǾ����ϴ�.", "�� ��", JOptionPane.INFORMATION_MESSAGE);
				pathName = fchooser.getSelectedFile().getPath();
			}
			try {
				list = ix.imXlsx(pathName);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ls.showTableImData(list);
		}
	}
}
