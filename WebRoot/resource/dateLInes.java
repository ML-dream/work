package yuYue;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import beans.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;



public class dateLInes extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			Calendar calendar2 = Calendar.getInstance();
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
			calendar2.add(Calendar.DATE, 3);
			String three_days_after = sdf2.format(calendar2.getTime());
			System.out.println(three_days_after);
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
	        System.out.println(df.format(calendar2.getTime()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
			
	        ArrayList<dateContent> dateContentList = new ArrayList<dateContent>();
	        
	        for(int i=0;i<10;i++){
	        	
	        	dateContent dateContent = new dateContent();
	        	calendar2.add(Calendar.DATE, i);
	        	dateContent.setDate(sdf2.format(calendar2.getTime()));
	        	dateContent.setIsSelected(false);
	        	dateContent.setIsToday(false);
	        	dateContent.setWeek(calendar2.get(Calendar.DAY_OF_WEEK));
	        	dateContentList.add(dateContent);
	        }
	        jsonBean jsonBean =new jsonBean();
	        
	        jsonBean.setContent(dateContentList);
	        jsonBean.setMsg("�����ɹ�");
	        jsonBean.setNo(2000);
	        jsonBean.setNow(df.format(calendar2.getTime()));
	        
	       // JSONObject json = JSONObject.fromObject(jsonBean);
	        JSONObject json=  JSONObject.fromObject(jsonBean);
	        String json1 = json.toString();
			//String json = PluSoft.Utils.JSON.Encode(notices);
			//json = json.substring(1, json.length()-1);
			//json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json1).flush();
			System.out.println(json1);
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
