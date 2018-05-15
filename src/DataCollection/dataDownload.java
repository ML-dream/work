package DataCollection;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cfmes.util.sql_data;

import com.wl.forms.Notice;
import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.forms.dataCollectionTable;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;
import com.wl.tools.UUIDHexGenerator;

public class dataDownload extends HttpServlet {
	private static final long serialVersionUID = 8211416997978746631L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 1;
	    String orderStr = "sendTime";
	    String json ="";
	    
	    //orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    //pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    //countPerPage = Integer.parseInt(request.getParameter("pageSize"));

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
//	    HttpSession session = request.getSession();
//	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
//	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();	
	    
	    String totalCountSql = "select count(*) from datacollection01  ";
//	    String[] paramss = {createPerson};
		try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
			
			String noticeWillSql ="select t.* from datacollection01 t order by tydate";
			List<dataCollectionTable> notices = new ArrayList<dataCollectionTable>();
			try {
				notices = Sqlhelper.exeQueryList(noticeWillSql,null, dataCollectionTable.class);
			} catch (Exception e) {
				System.out.print("关闭的语句");
			}
		json = PluSoft.Utils.JSON.Encode(notices);
		/*for (int i = 0; i < json.length(); i++) {
			
		}
	    */
//		json = json.substring(1, json.length()-1);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		
		

		String file = "C:\\Users\\戴志强\\Desktop\\01.txt";

		FileOutputStream writerStream = new FileOutputStream(file);    

		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8")); 

		writer.write(json);
		writer.close(); 
		
		response.setCharacterEncoding("UTF-8");
		String json1 ="{\"url\":\""+file+"\"}";
		response.getWriter().append(json1).flush();
		System.out.println(json1);
	}
}
