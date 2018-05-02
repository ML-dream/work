package com.wl.testaction.orderManage;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.wl.tools.StringUtil;
import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class OrderListServlet extends HttpServlet {

	private static final long serialVersionUID = -3196500925146346715L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = "ORDER_DATE";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    String orderId=StringUtil.isNullOrEmpty(request.getParameter("orderId"))?"":request.getParameter("orderId");
	    String customer=StringUtil.isNullOrEmpty(request.getParameter("customer"))?"":request.getParameter("customer");
//	    String companyId=request.getParameter("companyId");
	   
	    
//	    HttpSession session = request.getSession();
//		String userId = ((User)session.getAttribute("user")).getUserId();
//		String staffCode =  ((User)session.getAttribute("user")).getStaffCode();
		
	    String totalCountSql = "select count(*) from orders where order_status<11 and order_id like '%"+orderId+"%' and customer like '%"+customer+"%'";
//	    String[] params1 = {staffCode};
	    
//	    xiem 是否查询未交付完成订单  3表示全部订单
	    String orderMode = StringUtil.isNullOrEmpty(request.getParameter("orderMode"))?"3":request.getParameter("orderMode");
	    if(orderMode.equals("1")){
	    	totalCountSql = "select count(*) from unpayedOrderId d" +
	    			"       left join orders t on t.order_id= d.orderId " +
	    			"where order_status<11 and order_id like '%"+orderId+"%' and customer like '%"+customer+"%'";
	    }
	    try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    
		
	    String OrderSql= "select ORDER_ID orderId,DEPT_USER deptUser,ORDER_DATE orderDate,ENDTIME,CUSTOMER,ORDER_STATUS orderStatus,C.COMPANYNAME companyName,D.deptname,B.connector,B.connectorTel " +
	    	"from (select A.*,ROWNUM row_num from (select EM.* from orders EM where order_status<11 and order_id like '%"+orderId+"%' and customer like '%"+customer+"%' order by "+orderStr+" desc) " +
	    	"A where ROWNUM<="+(countPerPage*pageNo)+" order by "+orderStr+" desc) B " +
	    	"left join customer C on B.CUSTOMER=C.COMPANYID " +
	    	"left join dept D on B.DEPT_USER=D.deptid " +
	    	"where row_num>="+((pageNo-1)*countPerPage+1)+" order by "+orderStr+" desc";
	    
	    if(orderMode.equals("1")){
	    	OrderSql= "select ORDER_ID orderId,DEPT_USER deptUser,ORDER_DATE orderDate,ENDTIME,CUSTOMER,ORDER_STATUS orderStatus,C.COMPANYNAME companyName,D.deptname,B.connector,B.connectorTel " +
		    	"from (select A.*,ROWNUM row_num from (" +
		    	"select t.* from unpayedOrderId d" +
	    			"       left join orders t on t.order_id= d.orderId " +
	    			"where order_status<11 and order_id like '%"+orderId+"%' and customer like '%"+customer+"%' " +
		    	" order by "+orderStr+" desc) " +
		    	"A where ROWNUM<="+(countPerPage*pageNo)+" order by "+orderStr+" desc) B " +
		    	"left join customer C on B.CUSTOMER=C.COMPANYID " +
		    	"left join dept D on B.DEPT_USER=D.deptid " +
		    	"where row_num>="+((pageNo-1)*countPerPage+1)+" order by "+orderStr+" desc";
	    }
	    
//	    String[] params = {staffCode};
	    List<Order> orderList = new ArrayList<Order>();
	    
	    try {
			orderList = Sqlhelper.exeQueryList(OrderSql, null, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    String json = PluSoft.Utils.JSON.Encode(orderList);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	    
	
//	    System.out.println("OrderSql=="+OrderSql);
//	    
//	    ResultSet OrderRs =null;
//		try{
//			OrderRs = Sqlhelper.executeQuery(OrderSql, null);
//			List<Order> orderList = new ArrayList<Order>();
//			try {
//				while (OrderRs.next()) {
//					Order order = new Order();
//					order.setOrderId(OrderRs.getString(1));
//					order.setDeptUser(OrderRs.getString(2));
//					order.setOrderDate(OrderRs.getString(3));
//					order.setEndTime(OrderRs.getString(4));
//					order.setCustomer(OrderRs.getString(5));
//					order.setOrderStatus(OrderRs.getString(6));
//					order.setCompanyName(OrderRs.getString(7));
//					order.setDeptName(OrderRs.getString(8));
//					order.setConnector(OrderRs.getString(9));
//					order.setConnectorTel(OrderRs.getString(10));
//					
//					orderList.add(order);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			String json = PluSoft.Utils.JSON.Encode(orderList);
//			json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().append(json).flush();
//			System.out.println(json);
//		}catch(Exception e){
//		}  finally{
//			try {
//				if(OrderRs!=null){
//					OrderRs.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













