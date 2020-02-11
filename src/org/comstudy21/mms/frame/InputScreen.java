package org.comstudy21.mms.frame;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.Connection;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.comstudy21.mms.jdbc.JdbcUtil;
import org.comstudy21.mms.mdao.CopyPic;
import org.comstudy21.mms.mdao.MemberDao;
import org.comstudy21.mms.mdto.MemberDto;

@SuppressWarnings("serial")
public class InputScreen extends JFrame implements ActionListener {
	Connection conn = JdbcUtil.getConnection();
	MemberDao dao = new MemberDao(conn);
	JPanel is = (JPanel) getContentPane();
	JPanel g1 = new JPanel();
	JPanel g2 = new JPanel();
	JPanel g3 = new JPanel();
	JPanel g4 = new JPanel();
	JPanel g5 = new JPanel();
	JPanel bp = new JPanel();
	JPanel jp = new JPanel();

	JLabel[] lb = new JLabel[9];
	public static final String[] istr = { "이름", "   나        이", "성별", "전화번호", "주소", "  이   메  일", "SNS ", "  생년월일",
			"  가   입  일" };
	JTextField[] tf = new JTextField[10];
	// -----파일선택-----
	JFileChooser fchooser = new JFileChooser();
	FileNameExtensionFilter extension = new FileNameExtensionFilter("JPG%GIF", "jpg", "gif");

	// -----일반버튼-----
	Button picture = new Button("사진첨부");
	Button inp = new Button("입력하기");
	Button clr = new Button("초기화");

	// -----라디오버튼-----
	ButtonGroup sgroup = new ButtonGroup();
	JRadioButton male = new JRadioButton("남성");
	JRadioButton female = new JRadioButton("여성");
	// ----- 전화번호 콤보박스(17)-----
	String[] firstPN = { "010", "031", "032", "033", "041", "042", "043", "044", "051", "052", "053", "054", "055",
			"061", "062", "063", "064" };
	JComboBox<String> localN = new JComboBox<String>(firstPN);
	// -----생년월일 콤보박스-----
	String[] ystr = new String[70];
	String[] mstr = new String[12];
	String[] dstr = new String[31];
	{
		for (int i = 0; i < 70; i++) {
			ystr[i] = 2020 - i + "";
		}
		for (int i = 0; i < 12; i++) {
			mstr[i] = i + 1 + "";
		}
		for (int i = 0; i < 31; i++) {
			dstr[i] = i + 1 + "";
		}
	}
	JComboBox<String> byear = new JComboBox<>(ystr);
	JComboBox<String> bmonth = new JComboBox<>(mstr);
	JComboBox<String> bday = new JComboBox<>(dstr);
	JComboBox<String> jyear = new JComboBox<>(ystr);
	JComboBox<String> jmonth = new JComboBox<>(mstr);
	JComboBox<String> jday = new JComboBox<>(dstr);

	public InputScreen() {
		for (int i = 0; i < istr.length; i++) {
			lb[i] = new JLabel(istr[i]);
		}
		for (int i = 0; i < istr.length + 1; i++) {
			tf[i] = new JTextField();
		}
		is.setLayout(new GridLayout(10, 1));
		TitledBorder ititled = new TitledBorder("입력");
		is.setBorder(ititled);
		// ---------------------------------------
		g1.setLayout(new FlowLayout(FlowLayout.LEFT));
		tf[0].setPreferredSize(new Dimension(100, 20));
		tf[1].setPreferredSize(new Dimension(50, 20));
		g1.add(lb[0]);
		g1.add(tf[0]);
		g1.add(lb[1]);
		g1.add(tf[1]);
		is.add(g1, -1);
		// -----------------------------------
		g2.setLayout(new FlowLayout(FlowLayout.LEFT));
		tf[2].setPreferredSize(new Dimension(35, 20));
		tf[3].setPreferredSize(new Dimension(35, 20));
		localN.setPreferredSize(new Dimension(50, 20));
		localN.setSelectedIndex(-1);
		localN.setLightWeightPopupEnabled(false);
		sgroup.add(male);
		sgroup.add(female);
		g2.add(lb[2]);
		g2.add(male);
		g2.add(female);
		g2.add(lb[3]);
		g2.add(localN);
		g2.add(new JLabel("-"));
		g2.add(tf[2]);
		g2.add(new JLabel("-"));
		g2.add(tf[3]);
		is.add(g2, -1);
		// -----------------------------------
		g3.setLayout(new FlowLayout(FlowLayout.LEFT));
		tf[4].setPreferredSize(new Dimension(100, 20));
		tf[5].setPreferredSize(new Dimension(145, 20));
		g3.add(lb[4]);
		g3.add(tf[4]);
		g3.add(lb[5]);
		g3.add(tf[5]);
		is.add(g3, -1);
		// -----------------------------------
		g4.setLayout(new FlowLayout(FlowLayout.LEFT));
		tf[6].setPreferredSize(new Dimension(100, 20));
		tf[7].setPreferredSize(new Dimension(100, 20));
		byear.setPreferredSize(new Dimension(55, 20));
		bmonth.setPreferredSize(new Dimension(40, 20));
		bday.setPreferredSize(new Dimension(40, 20));
		byear.setSelectedIndex(-1);
		bmonth.setSelectedIndex(-1);
		bday.setSelectedIndex(-1);
		byear.setLightWeightPopupEnabled(false);
		bmonth.setLightWeightPopupEnabled(false);
		bday.setLightWeightPopupEnabled(false);
		g4.add(lb[6]);
		g4.add(tf[6]);
		g4.add(lb[7]);
		g4.add(byear);
		g4.add(bmonth);
		g4.add(bday);
		is.add(g4, -1);
		// -----------------------------------
		g5.setLayout(new FlowLayout(FlowLayout.LEFT));
		tf[8].setPreferredSize(new Dimension(100, 20));
		tf[9].setPreferredSize(new Dimension(75, 20));
		picture.setPreferredSize(new Dimension(50, 20));
		picture.setFont(new Font("바탕체", Font.BOLD, 11));
		jyear.setPreferredSize(new Dimension(55, 20));
		jmonth.setPreferredSize(new Dimension(40, 20));
		jday.setPreferredSize(new Dimension(40, 20));
		jyear.setSelectedIndex(-1);
		jmonth.setSelectedIndex(-1);
		jday.setSelectedIndex(-1);
		jyear.setLightWeightPopupEnabled(false);
		jmonth.setLightWeightPopupEnabled(false);
		jday.setLightWeightPopupEnabled(false);
		g5.add(picture);
		g5.add(tf[9]);
		g5.add(lb[8]);
		g5.add(jyear);
		g5.add(jmonth);
		g5.add(jday);
		is.add(g5, -1);
		// -----------------------------------
		inp.addActionListener(this);
		clr.addActionListener(this);
		picture.addActionListener(this);
		// -----------------------------------
		is.add(inp, -1);
		is.add(clr, -1);
	}

	public void inputData() {
		String name = tf[0].getText();
		String age = tf[1].getText();
		String sex = "";
		if (male.isSelected()) {
			sex = male.getText();
		} else if (female.isSelected()) {
			sex = female.getText();
		}
		String phone = localN.getSelectedItem()+"-"+tf[2].getText()+"-"+tf[3].getText();
		String address = tf[4].getText();
		String email = tf[5].getText();
		String sns = tf[6].getText();
		String birth = byear.getSelectedItem() + "-" + bmonth.getSelectedItem() + "-" + bday.getSelectedItem();
		String joinDate = jyear.getSelectedItem() + "-" + jmonth.getSelectedItem() + "-" + jday.getSelectedItem();
		String picture = tf[9].getText();

		MemberDto dto = new MemberDto(0, name, age, sex, phone, address, email, sns, birth, joinDate, picture);

		dao.insert(dto);
		deletetf();
	}

	public void deletetf() {
		for (int i = 0; i < istr.length + 1; i++) {
			tf[i].setText("");
		}
		male.setSelected(false);
		female.setSelected(false);
		byear.setSelectedIndex(-1);
		bmonth.setSelectedIndex(-1);
		bday.setSelectedIndex(-1);
		jyear.setSelectedIndex(-1);
		jmonth.setSelectedIndex(-1);
		jday.setSelectedIndex(-1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String fileName = null;
		String path = null;
		if (inp == e.getSource()) {
			inputData();
		} else if (clr == e.getSource()) {
			deletetf();
		} else if (picture == e.getSource()) {
			JOptionPane.showMessageDialog(null, "이미지 파일은 200*200 사이즈의 이미지를 권장합니다 사이즈가 다를시 이미지가 다르게 나올수 있습니다.", "경 고", JOptionPane.WARNING_MESSAGE);
			fchooser.setFileFilter(extension);
			int ret = fchooser.showOpenDialog(null);
			if (ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경 고", JOptionPane.WARNING_MESSAGE);
			} else if (ret == JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "파일을 선택되었습니다.", "알 림", JOptionPane.INFORMATION_MESSAGE);
			}
			try {
				fileName = fchooser.getSelectedFile().getName();
				path = fchooser.getSelectedFile().getPath();
				tf[9].setText(fileName);
				new CopyPic(path, fileName);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
	}
}
}
