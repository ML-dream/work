package cfmes.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cfmes.bean.partplanbean;
import cfmes.util.DealString;

public class DoPartPlan extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		DealString ds = new DealString();
		partplanbean ppbean =new partplanbean();
		
		/*ȡ������ƻ�������Ĳ���ֵ*/
		String plan_id = ds.toGBK(request.getParameter("plan_id2"));
		String plan_time = ds.toGBK(request.getParameter("do_time"));
		String plan_peo = ds.toGBK(request.getParameter("plan_peo2"));
		String part_id = ds.toGBK(request.getParameter("node_id"));
		String father_id = ds.toGBK(request.getParameter("father_id"));
		String part_num = ds.toGBK(request.getParameter("partnum"));
		String order_id = ds.toGBK(request.getParameter("order_id2"));
		String plan_bgtime = ds.toGBK(request.getParameter("plansttime"));
		String plan_edtime = ds.toGBK(request.getParameter("planedtime"));
		String quality_id = ds.toGBK(request.getParameter("quality_id2"));
		String product_id = ds.toGBK(request.getParameter("product_id2"));
		String issue_num = ds.toGBK(request.getParameter("issue_num2"));
		String part_status = ds.toGBK(request.getParameter("part_status"));
		String do_type = ds.toGBK(request.getParameter("do_type"));
		part_status = do_type;
		
		/**����Щ����ֵ�����ϣ�����档��ϣ��ͨ��Ӧ���ڲ���ֵ�ڶ�Ĳ�������**/
		Hashtable ht = new Hashtable();
	    ht.put("plan_id",plan_id );
	    ht.put("plan_time",plan_time );
	    ht.put("plan_peo",plan_peo );
	    ht.put("part_id",part_id );
	    ht.put("father_id",father_id );
	    ht.put("part_num",part_num );
	    ht.put("order_id",order_id );
	    ht.put("plan_bgtime",plan_bgtime );
	    ht.put("plan_edtime",plan_edtime );
	    ht.put("quality_id",quality_id );
	    ht.put("issue_num",issue_num );
	    ht.put("product_id",product_id );
	    ht.put("part_status",part_status );
	    ht.put("do_type",do_type );
	    
	    /**ѡ��ƻ��Ĳ������ͣ����ơ�ֹͣ��ɾ��**/
	    if(do_type.equals("10")){
	    ppbean.EditPartPlan(ht);
	    }else if(do_type.equals("15")){
	    if(ppbean.isdeliveredplan(ht)){
	    	out.print("<script>alert('����������´������ƻ�����飡');</script>");
	    }else{
	    	ppbean.EditPartPlan(ht);
	    }
	    }else if(do_type.equals("20")){
	    	ppbean.EditPartPlan(ht);
	    }else if(do_type.equals("25")){
	    	ppbean.delplan(ht);
	    }else{}
		out.flush();
		out.close();
	}

}
