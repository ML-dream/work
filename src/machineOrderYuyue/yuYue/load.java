package machineOrderYuyue.yuYue;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.wl.forms.dataCollectionTable;
import com.wl.tools.Sqlhelper;

import machineOrderYuyue.beans.bookingInfoBean;
import machineOrderYuyue.beans.contentBean;
import machineOrderYuyue.beans.jsonBean;
import machineOrderYuyue.beans.loadJson;
import machineOrderYuyue.beans.machineBean;
import machineOrderYuyue.beans.timeLinesBean;

public class load extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String deptId1 = request.getParameter("id");
		String date = request.getParameter("date");
		
		int deptId = Integer.parseInt(deptId1);
		
		List<String> machineNames = new ArrayList<String>();
		List<Integer> machineIds = new ArrayList<Integer>();
		
		if(deptId1.equals("551")){
			machineNames.add("数控电火花成形机床");
			machineNames.add("数控低速走丝电火花线切割机床");
			machineNames.add("数控高速成型磨床");
			machineNames.add("超精密成形平面磨床");				
			machineNames.add("数控车床");
			machineNames.add("CNC雕刻机");
			machineIds.add(5511);
			machineIds.add(5512);
			machineIds.add(5513);
			machineIds.add(5514);
			machineIds.add(5515);
			machineIds.add(5516);
			
		}else{
			
			
			machineNames.add("车铣复合加工中心");
			machineNames.add("超高速磨床");
			machineNames.add("超声辅助高速加工中心");
			machineNames.add("高速五坐标加工中心");				
			machineNames.add("加工中心");
			machineIds.add(5501);
			machineIds.add(5502);
			machineIds.add(5503);
			machineIds.add(5504);
			machineIds.add(5505);
			
		}
		
		Calendar calendar2 = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      
		//此处是最外层的Bean的构造
		loadJson loadJson =new loadJson();
		
		loadJson.setMsg("操作成功");
		loadJson.setNo(2000);
		
		loadJson.setNow(df.format(calendar2.getTime()));
	
		
		
		//接下来开始contentBean的构造
		contentBean contentBean = new contentBean();
		
		contentBean.setSelectedDate(date);
		contentBean.setDeptId(deptId);
		contentBean.setDeptName(deptId>550 ? "实验室103":"实验室101");
		
		
		
		//接下来开始写循环，对于车间的机床
		List<timeLinesBean> timeLinesList = new ArrayList<timeLinesBean>();
		
		
		for(int i = 0;i<14;i++){
			
			String srcStart = String.valueOf(i+8); 
			String srcEnd = String.valueOf(i+9); 
			String srcPattern = "HH"; 
			String outPattern = "HH:mm"; 
			String outStart = null;
			try {
				outStart = new SimpleDateFormat(outPattern).format(new SimpleDateFormat(srcPattern).parse(srcStart));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String outEnd = null;
			try {
				outEnd = new SimpleDateFormat(outPattern).format(new SimpleDateFormat(srcPattern).parse(srcEnd));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			timeLinesBean timeLineBean =new timeLinesBean(); 
			
			timeLineBean.setStartTime(outStart);
			timeLineBean.setEndTime(outEnd);
			
			timeLinesList.add(timeLineBean);
			
			
		}
		
		contentBean.setTimeLines(timeLinesList);
		
		
		
		List<machineBean> machinesList = new ArrayList<machineBean>();
		
		
		
		
		//for (Integer machineId : machineIds) {
	      for(int i=0;i<machineIds.size();i++){
	    	  
	    	int machineId = machineIds.get(i);  
	    	
	    	machineBean machineBean = new machineBean();
	        
			machineBean.setMachineId(machineId);
	        machineBean.setCreateTime(df.format(calendar2.getTime()));//后续再加上，不过这个没什么用
	        machineBean.setDescription("暂时没有任何说明");
	        machineBean.setIsDeleted(0);
	        machineBean.setMachineName(machineNames.get(i));
	        machineBean.setSequence(i+1);
	        machineBean.setState(1);//应该没什么用！！！！也可以有用，，前面进行判断，然后锁住所有的！
	        String deptMachineYearNo = machineId+"-"+date+"-";
	        machineBean.setDeptMachineYearNo(deptMachineYearNo);
			
	        //for(int i=0)//此处不需要循环，直接查询出来就是list的结构，直接就可以了
	        
	        List<bookingInfoBean> sessionsList = new ArrayList<bookingInfoBean>();
	        String bookingInfoSql ="SELECT a.*,B.* FROM (select t.*from bookingInfo t  where time_YMD = '"+date+"' and machineId = '"+machineId+"') B right join bookingInfosecond a on B.time_md = a. timeline order by a.timeline";
	        try {
				sessionsList = Sqlhelper.exeQueryList(bookingInfoSql,null,bookingInfoBean.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
	        
	        machineBean.setSessionsList(sessionsList);
			
	        machinesList.add(machineBean);
	        }
		
		contentBean.setMachines(machinesList);
		
		loadJson.setContent(contentBean);//此处需要传入一个contentbean
		
		
		response.setContentType("application/json;charset=utf-8");
		  
        //JSONObject json = JSONObject.fromObject(loadJson);
       // JSONObject json=  JSONObject.fromObject(jsonBean);
        //String json1 = json.toString();
        String json = PluSoft.Utils.JSON.Encode(loadJson);
        //response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
		//json = json.substring(1, json.length()-1);
		//json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		/*response.setCharacterEncoding("UTF-8");*/
		/*response.getWriter().append(json1).flush();
		System.out.println(json1);*/
	}

}
