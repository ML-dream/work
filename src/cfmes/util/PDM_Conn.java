package cfmes.util;
import java.io.PrintWriter;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
public class PDM_Conn {
	private Connection conn=null;
    private DataSource source=null;
//    public Statement stmt = null;
    private PrintWriter out = null;
//    public PDM_Conn() {
//	    openConn();
//    }
//	�����ݿ�����
	  private void openConn() {
	    try {
	      if (conn == null) {
	    	  Context ic=new InitialContext();
			  source=(DataSource)ic.lookup("java:comp/env/jdbc/winddb");
			  conn =source.getConnection();
	      }
	    }
	    catch (SQLException ex) {
	    	out.print("���ݿ�winddb����ʧ��!");out.flush();out.close();
	      ex.printStackTrace();
	    }
	    catch (NamingException ex) {
	    	out.print("���ݿ�winddb����ʧ��!");out.flush();out.close();
	      ex.printStackTrace();
	    }
	    catch (Exception ex) {
	    	out.print("���ݿ�winddb����ʧ��!");out.flush();out.close();
	      ex.printStackTrace();
	    }
	  }
//�ر����ݿ�����
	  public void closeConn() {
		  try {
			  
		        if (conn != null && !conn.isClosed()) {
		            conn.close();
		            conn = null;
		        }
		    }
		    catch (SQLException ex) {
		    	out.print("�������ӳ�winddb�ر�ʧ�ܣ�");out.flush();out.close();
		      ex.printStackTrace();
		    }
	  }
	public ResultSet executeQuery(String sql) {
		ResultSet rs = null;
	    openConn();
		try {
			Statement stmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("PDM_Conn.executeQuery:" + e.getMessage());
		}
		return rs;
	}
}