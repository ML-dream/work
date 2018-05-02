package com.wl.testaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cfmes.util.sql_data;

import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class AddCustomerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String companyId = ChineseCode.toUTF8(request.getParameter("companyId").trim());
	    String companyName = ChineseCode.toUTF8(request.getParameter("companyName").trim());
	    String type = ChineseCode.toUTF8(request.getParameter("type").trim());
	    
	    String foundingTime = ChineseCode.toUTF8(request.getParameter("foundingTime").trim());
	    String header = ChineseCode.toUTF8(request.getParameter("header").trim());
	    String employeeNum = ChineseCode.toUTF8(request.getParameter("employeeNum").trim());
	    
	    String address = ChineseCode.toUTF8(request.getParameter("address").trim());
	    String postCode = ChineseCode.toUTF8(request.getParameter("postCode").trim());
	    String telephone = ChineseCode.toUTF8(request.getParameter("telephone").trim());
	    
	    String webAddress = ChineseCode.toUTF8(request.getParameter("webAddress"));
	    String business = ChineseCode.toUTF8(request.getParameter("business"));
	    String advise = ChineseCode.toUTF8(request.getParameter("advise"));
	    String connector = ChineseCode.toUTF8(request.getParameter("connector"));
	    String connectorTel = ChineseCode.toUTF8(request.getParameter("connectorTel"));
	    
	    String connector2 = ChineseCode.toUTF8(request.getParameter("connector2"));
	    String connector2Tel = ChineseCode.toUTF8(request.getParameter("connector2Tel"));
	    
	    String connector3 = ChineseCode.toUTF8(request.getParameter("connector3"));
	    String connector3Tel = ChineseCode.toUTF8(request.getParameter("connector3Tel"));
	    
	    String connector4 = ChineseCode.toUTF8(request.getParameter("connector4"));
	    String connector4Tel = ChineseCode.toUTF8(request.getParameter("connector4Tel"));
	    
		String  addCustomerSql = "insert into customer " +
				"(COMPANYID,COMPANYNAME,FOUNDEINGTIME,EMPLLOYEENUM,TYPE," +
				"ADDRESS,POSTCODE,TELEPHONE,WEBADDRESS,HEADER,BUSINESS," +
				"ADVISE,ISTOGETHER,connector,connectorTel,connector2,connector2Tel,connector3,connector3Tel,connector4,connector4Tel )values('"+
				companyId+"','"+companyName+"',to_date('"+foundingTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+employeeNum+"','"
				+type+"','"+address+"','"+postCode+"','"+telephone+"','"+
				webAddress+"','"+header+"','"+business+"','"+advise+"','Y','"+connector+"','"+connectorTel+"','"+connector2+"','"+connector2Tel+"','"+connector3+"','"+connector3Tel+"','"+connector4+"','"+connector4Tel+"')";
		System.out.println("addMachineSql=="+addCustomerSql);
		sql_data sqlData = new sql_data();
		try {
			sqlData.exeUpdateThrowExcep(addCustomerSql);
			//request.setAttribute("result", "sucess");
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			//request.getRequestDispatcher("customerManage/showCustomer.jsp").forward(request, response);
		} catch (SQLException e) {
			request.setAttribute("result", "failure");
			e.printStackTrace();
		}
		//this.getServletConfig().getServletContext().getRequestDispatcher("/UserManage.jsp").forward(request, response);
	}


}
