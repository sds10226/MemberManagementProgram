package org.comstudy21.mms.mdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.comstudy21.mms.jdbc.JdbcUtil;
import org.comstudy21.mms.mdto.MemberDto;

public class MemberDao {

	private static final String CRE_TAB = "create table MMP (mno number(4), name varchar2(20), age varchar2(20), sex varchar2(20), phone varchar2(20), address varchar2(30), email varchar2(20), sns varchar2(20), birth date, joinDate date, picture varchar2(20))";
	private static final String SELECT_ALL = "select * from MMP order by mno";
	private static final String DELETE_ALL = "delete from MMP";
	private static final String DELETE_SEQ = "drop sequence MMP_seq";
	private static final String RECRE_SEQ = "create sequence MMP_seq start with 1 increment by 1";
	private static final String DELETE_SEL = "delete from MMP where mno = ?";
	private static final String INSERT = "insert into MMP(mno, name, age, sex, phone, address, email, sns, birth, joinDate, picture) values(MMP_seq.nextval,?,?,?,?,?,?,?,?,?,?)";
	private static final String CSVINSERT = "insert into MMP(mno, name, age, sex, phone, address, email, sns, birth, joinDate, picture) values(?,?,?,?,?,?,?,?,?,?,?)";
	private static String MODIFY = "";
	private static String SELECT = "";
	
	Connection conn = JdbcUtil.getConnection();
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public String makeSelectQuery(String field) {
		return "select * from MMP where " + field + " =? order by mno";
	}

	public String makemodifyQuery(String field) {
		return "update MMP set " + field + " = ? where mno = ?";
	}
	
	public String makeAddSequenceQuery(String mno) {
		return "create sequence MMP_seq start with "+ mno +" increment by 1";
	}

	public MemberDao() {
		
	}

	public MemberDao(Connection conn) {
		this.conn = conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	public void createTable(){
		try {
			stmt = conn.createStatement();
			try {
				stmt.executeUpdate(SELECT_ALL);
			} catch (SQLSyntaxErrorException e) {
				int choice = JOptionPane.showOptionDialog(null, "DB에 테이블이 존재하지 않습니다! 새로 만드시겠습니까?", "테이블생성", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, "Yes");
				if(choice==0){
					int result = stmt.executeUpdate(CRE_TAB);
					stmt.executeUpdate(RECRE_SEQ);
					if(result==0){
						JOptionPane.showMessageDialog(null, "DB에 테이블 생성이 완료되었습니다.");
					}else{
						JOptionPane.showMessageDialog(null, "DB에 테이블 생성 되지 않았습니다. 다시 확인해주세요.");
						System.exit(0);
					}
				}else{
					System.exit(0);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtil.close(stmt);	
		}
	}

	public void insert(MemberDto dto) {
		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getAge());
			pstmt.setString(3, dto.getSex());
			pstmt.setString(4, dto.getPhone());
			pstmt.setString(5, dto.getAddress());
			pstmt.setString(6, dto.getEmail());
			pstmt.setString(7, dto.getSns());
			pstmt.setString(8, dto.getBirth());
			pstmt.setString(9, dto.getJoinDate());
			pstmt.setString(10, dto.getPicture());
			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				JOptionPane.showMessageDialog(null, "입력성공");
				conn.commit();
			} else {
				JOptionPane.showMessageDialog(null, "입력실패", "확인!", JOptionPane.ERROR_MESSAGE);
				conn.rollback();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void insertCsv(Vector<Vector<String>> list) {
		try {
			Vector<Vector<String>> rawdata = selectAll();
			if(rawdata.size()==0){
				int listS = list.size()-1;
				int lastN = Integer.parseInt(list.get(listS).get(0))+1;
				String query = makeAddSequenceQuery(lastN+"");
				stmt = conn.createStatement();
				stmt.executeQuery(DELETE_SEQ);
				stmt.executeQuery(query);
			}
			for(Vector<String> dto : list){
				int eqlcnt=0;
				int cnt = 0;
				for(int i=0; i<rawdata.size(); i++){
					if(dto.get(0).equals(rawdata.get(i).get(0))){
						eqlcnt++;
					}
				}
				if(eqlcnt==0){
					pstmt = conn.prepareStatement(CSVINSERT);
					int mno = Integer.parseInt(dto.get(0));
					String dtoE = dto.get(8).replace(" 00:00:00", "");
					String dtoN = dto.get(9).replace(" 00:00:00", "");
					pstmt.setInt(1, mno);
					pstmt.setString(2, dto.get(1));
					pstmt.setString(3, dto.get(2));
					pstmt.setString(4, dto.get(3));
					pstmt.setString(5, dto.get(4));
					pstmt.setString(6, dto.get(5));
					pstmt.setString(7, dto.get(6));
					pstmt.setString(8, dto.get(7));
					pstmt.setString(9, dtoE);
					pstmt.setString(10, dtoN);
					pstmt.setString(11, dto.get(10));
					cnt = pstmt.executeUpdate();
					if (cnt > 0) {
						conn.commit();
					} else {
						conn.rollback();
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public Vector<Vector<String>> select(String itemName, String val) {
		Vector<Vector<String>> list = new Vector<>();
		SELECT = makeSelectQuery(itemName);
		try {
			pstmt = conn.prepareStatement(SELECT);
			pstmt.setString(1, val);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setMno(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setAge(rs.getString(3));
				dto.setSex(rs.getString(4));
				dto.setPhone(rs.getString(5));
				dto.setAddress(rs.getString(6));
				dto.setEmail(rs.getString(7));
				dto.setSns(rs.getString(8));
				dto.setBirth(rs.getString(9));
				dto.setJoinDate(rs.getString(10));
				dto.setPicture(rs.getString(11));

				list.add(dto.getList());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public Vector<Vector<String>> selectAll() {
		Vector<Vector<String>> list = new Vector<>();
		try {
			stmt = conn.createStatement();
			try {
				rs = stmt.executeQuery(SELECT_ALL);
			} catch (SQLSyntaxErrorException e) {
				return null;
			}
			while (rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setMno(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setAge(rs.getString(3));
				dto.setSex(rs.getString(4));
				dto.setPhone(rs.getString(5));
				dto.setAddress(rs.getString(6));
				dto.setEmail(rs.getString(7));
				dto.setSns(rs.getString(8));
				dto.setBirth(rs.getString(9));
				dto.setJoinDate(rs.getString(10));
				dto.setPicture(rs.getString(11));
				list.add(dto.getList());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		return list;
	}

	public void modifyData(int no, String itemName, String val) {
		MODIFY = makemodifyQuery(itemName);
		try {
			pstmt = conn.prepareStatement(MODIFY);
			pstmt.setString(1, val);
			pstmt.setInt(2, no);
			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				JOptionPane.showMessageDialog(null, "수정성공");
				conn.commit();
			} else {
				JOptionPane.showMessageDialog(null, "수정실패", "확인!", JOptionPane.ERROR_MESSAGE);
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteData(int no) {
		try {
			pstmt = conn.prepareStatement(DELETE_SEL);
			pstmt.setInt(1, no);
			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				JOptionPane.showMessageDialog(null, "해당 번호 삭제성공");
				conn.commit();
			} else {
				JOptionPane.showMessageDialog(null, "해당 번호 삭제", "확인!", JOptionPane.ERROR_MESSAGE);
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void deleteAll() {
		try {
			Vector<Vector<String>> list = selectAll();
			stmt = conn.createStatement();
			int cnt = stmt.executeUpdate(DELETE_ALL);
			if (cnt > 0) {
				JOptionPane.showMessageDialog(null, "전체삭제성공");
				conn.commit();
			} else if(cnt==0&&list.size()!=0){
				JOptionPane.showMessageDialog(null, "전체삭제실패", "확인!", JOptionPane.ERROR_MESSAGE);
				conn.rollback();
			} else{}
			stmt.executeUpdate(DELETE_SEQ);
			stmt.executeUpdate(RECRE_SEQ);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
}
