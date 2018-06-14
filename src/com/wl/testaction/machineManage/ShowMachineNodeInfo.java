package com.wl.testaction.machineManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Machine;
import com.wl.forms.StockInfo;
import com.wl.tools.Sqlhelper;

public class ShowMachineNodeInfo extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public ShowMachineNodeInfo() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		int pageNo=0;
	    int countPerPage=20;
	    int totalCount = 0;
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    String machineId=request.getParameter("machineId");
	    String itemName=request.getParameter("itemName");
	    String totalCountSql="";
	    String sql="";
	    if(machineId.length()!=0 && itemName.equals("")){
	    	totalCountSql ="select count(*) from MACHINEINFO_TIME where machineId = '"+machineId+"'";
		    sql= "select rownum,c.* from (select * from MACHINEINFO_TIME a left join fo_detail b on a.fo_id=b.fo_id  where a.machineId = '"+machineId+"' order by orderId desc ,machineTime) c " + 
		    		"where rownum between "+ ((pageNo-1)*countPerPage+1)+ " and "+ (countPerPage*pageNo);
	    }else if(machineId.equals("")&&itemName.equals("")) {
	    	/*totalCountSql ="select count(*) from stock where warehouse_id='"+machineId+"' and item_name like '%"+itemName+"%'";*/
		   
	    	totalCountSql="select count(*) from MACHINEINFO_TIME ";
		    sql= "select rownum,c.* from (select * from MACHINEINFO_TIME a left join fo_detail b on a.fo_id=b.fo_id  order by orderId desc ,machineTime) c " + 
		    		"where rownum between "+ ((pageNo-1)*countPerPage+1)+ " and "+ (countPerPage*pageNo);
	    }else if(machineId.length()!=0 && itemName.length()!=0){
	    	totalCountSql="select count(*) from MACHINEINFO_TIME where machineId = '" + machineId +"'  andmachineTime like '%"+ itemName+ "%'";
		    sql= "select rownum,c.* from (select * from MACHINEINFO_TIME a   left join fo_detail b on a.fo_id=b.fo_id  where machineTime like  '%"+ itemName+ "%' and machineId='"+ machineId+"' order by orderId desc ,machineTime) c " + 
		    		"where rownum between "+ ((pageNo-1)*countPerPage+1)+ " and "+ (countPerPage*pageNo);
	    	
	    }else if( machineId.equals("") && itemName.length()!=0){
	    	
	    	totalCountSql="select count(*) from MACHINEINFO_TIME where machineTime like '%"+ itemName+ "%'";
		    sql= "select rownum,c.* from (select * from MACHINEINFO_TIME a   left join fo_detail b on a.fo_id=b.fo_id  where machineTime like  '%"+ itemName+ "%'  order by orderId desc ,machineTime) c " + 
		    		"where rownum between "+ ((pageNo-1)*countPerPage+1)+ " and "+ (countPerPage*pageNo);
	    }
	    
	    try {
		    totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 
		 List<Machine> resultList = new ArrayList<Machine>();
		 try{
	     resultList=Sqlhelper.exeQueryList(sql, null, Machine.class);
	    
	  	}catch(Exception e){
	  	e.printStackTrace();
	  	}
	  	String json = PluSoft.Utils.JSON.Encode(resultList);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		//response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
		
	}

}
