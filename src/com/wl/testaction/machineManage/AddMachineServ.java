package com.wl.testaction.machineManage;

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

public class AddMachineServ extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String machineId = ChineseCode.toUTF8(request.getParameter("machineId").trim());
		String machineName = ChineseCode.toUTF8(request.getParameter("machineName").trim());
		String machineSpec = ChineseCode.toUTF8(request.getParameter("machineSpec").trim());
		String place = ChineseCode.toUTF8(request.getParameter("place").trim());
		String outCode = ChineseCode.toUTF8(request.getParameter("outCode").trim());
		
		String outDate = ChineseCode.toUTF8(request.getParameter("outDate").trim());
		String machNum = ChineseCode.toUTF8(request.getParameter("machNum").trim());
		String workRange = ChineseCode.toUTF8(request.getParameter("workRange").trim());
		String machType = ChineseCode.toUTF8(request.getParameter("machType").trim());
		String machModel = ChineseCode.toUTF8(request.getParameter("machModel").trim());
		
		String machStandard = ChineseCode.toUTF8(request.getParameter("machStandard").trim());
		String machManufacture = ChineseCode.toUTF8(request.getParameter("machManufacture").trim());
		String usedYears = ChineseCode.toUTF8(request.getParameter("usedYears").trim());
		String machPrice = ChineseCode.toUTF8(request.getParameter("machPrice").trim());
		String machOldRate = ChineseCode.toUTF8(request.getParameter("machOldRate").trim());
		
		String isKeyMach = ChineseCode.toUTF8(request.getParameter("isKeyMach").trim());
		String buyDate = ChineseCode.toUTF8(request.getParameter("buyDate").trim());
		String status = ChineseCode.toUTF8(request.getParameter("status").trim());
		String power = ChineseCode.toUTF8(request.getParameter("power").trim());
		String deptId = ChineseCode.toUTF8(request.getParameter("deptId"));
		
		String runDate = ChineseCode.toUTF8(request.getParameter("runDate"));
		String worker = ChineseCode.toUTF8(request.getParameter("worker"));
		String madeDate = ChineseCode.toUTF8(request.getParameter("madeDate"));
		String checkDate = ChineseCode.toUTF8(request.getParameter("checkDate"));
		String memo = ChineseCode.toUTF8(request.getParameter("memo"));
		String hourPercent = ChineseCode.toUTF8(request.getParameter("hourPercent"));
		String countPercent = ChineseCode.toUTF8(request.getParameter("countPercent"));
		
		String  sql = "insert into machinfo " +
				"(machineId,machineName,machineSpec,place,outCode," +
				"outDate,machNum,workRange,machType,machModel," +
				"machStandard,machManufacture,usedYears,machPrice,machOldRate," +
				"is_KeyMach,buyDate,status,power,dept_Id," +
				"runDate,worker,madeDate,checkTime,memo,hourPercent,countPercent)values('"+
				machineId+"','"+machineName+"','"+machineSpec+"','"+place+"','"+outCode+"'," +
				"to_date('"+outDate+"','yyyy-mm-dd,hh24:mi:ss'),'"+machNum+"','"+workRange+"','"+machType+"','"+machModel+"','"+
				machStandard+"','"+machManufacture+"','"+usedYears+"','"+machPrice+"','"+machOldRate+"','"+
				isKeyMach+"',to_date('"+buyDate+"','yyyy-mm-dd,hh24:mi:ss'),'"+status+"','"+power+"','"+deptId+"'," +
				"to_date('"+runDate+"','yyyy-mm-dd,hh24:mi:ss'),'"+worker+"',to_date('"+madeDate+"','yyyy-mm-dd,hh24:mi:ss'),to_date('"+checkDate+"','yyyy-mm-dd,hh24:mi:ss'),'"+memo+"','"+hourPercent+"','"+countPercent+"')";
		System.out.println("sql=="+sql);
		sql_data sqlData = new sql_data();
		try {
			sqlData.exeUpdateThrowExcep(sql);
			
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		} catch (SQLException e) {
			request.setAttribute("result", "failure");
			e.printStackTrace();
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		}
	}


}
