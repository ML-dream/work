/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.wl.testaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.wl.forms.Item;
import com.wl.tools.Sqlhelper;

public class Add_itemsAction extends DispatchAction {

	public ActionForward add_items(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		Item item =(Item) form;
		System.out.println("进入add_items函数体");
		
		String itemid= request.getParameter("itemid");
		String itemtypeid= request.getParameter("itemtypeid");
		String itemname= request.getParameter("itemname");
		String itemdrawing= request.getParameter("itemdrawing");
		String materialmark= request.getParameter("materialmark");
		String itemattri= request.getParameter("itemattri");
		String itemspecification= request.getParameter("itemspecification");
		String itemmb= request.getParameter("itemmb");
		String itemstatus= request.getParameter("itemstatus");
		String unitm= request.getParameter("unitm");
		String lotsize= request.getParameter("lotsize");
		String ordermin= request.getParameter("ordermin");
		String leadtime= request.getParameter("leadtime");
		String varleadtime= request.getParameter("varleadtime");
		String abccode= request.getParameter("abccode");
		String LLc= request.getParameter("LLc");
		String safestock= request.getParameter("safestock");
		String stockhigh= request.getParameter("stockhigh");
		String stocklow= request.getParameter("stocklow");
		String mpsflag= request.getParameter("mpsflag");
		String phantomunit= request.getParameter("phantomunit");
		String itemunit= request.getParameter("itemunit");
		String weightunit= request.getParameter("weightunit");
		String yeild= request.getParameter("yeild");
		String ptype= request.getParameter("ptype");
		String purchaseunite= request.getParameter("purchaseunite");
		String planunite= request.getParameter("planunite");
		String subproduct= request.getParameter("subproduct");
		String assembleafterall= request.getParameter("assembleafterall");
		String costmethod= request.getParameter("costmethod");
		String daysmultiple= request.getParameter("daysmultiple");
		String backflash= request.getParameter("backflash");
		String itemprice= request.getParameter("itemprice");
		String priceunit= request.getParameter("priceunit");
		String currency= request.getParameter("currency");
		String rmbprice= request.getParameter("rmbprice");
		String extraA= request.getParameter("extraA");
		String extraB= request.getParameter("extraB");
		String dymtlmark= request.getParameter("dymtlmark");
		String memo= request.getParameter("memo");
		String mtlsort= request.getParameter("mtlsort");
		String itemA= request.getParameter("itemA");
		String itemB= request.getParameter("itemB");
		String itemC= request.getParameter("itemC");
		String itemD= request.getParameter("itemD");

		System.out.println(itemid);
		System.out.println(itemname);
		
		String sql =
	    "insert into item values('"+itemid+"','"+
	    itemtypeid+"','"+itemname+"','"+itemdrawing+"','"+materialmark+"','"+itemattri+"','"+
	    itemspecification+"','"+itemmb+"','"+itemstatus+"','"+unitm+"','"+lotsize+"','"+ordermin+"','"+
	    leadtime+"','"+varleadtime+"','"+abccode+"','"+LLc+"','"+safestock+"','"+stockhigh+"','"+
	    stocklow+"','"+mpsflag+"','"+phantomunit+"','"+itemunit+"','"+weightunit+"','"+yeild+"','"+
	    ptype+"','"+purchaseunite+"','"+planunite+"','"+subproduct+"','"+assembleafterall+"','"+
	    costmethod+"','"+daysmultiple+"','"+backflash+"','"+itemprice+"','"+priceunit+"','"+currency+"','"+
	    rmbprice+"','"+extraA+"','"+extraB+"','"+dymtlmark+"','"+memo+"','"+mtlsort+"','"+itemA+"','"+itemB+"','"+itemC+"','"+itemD+"')";

		System.out.println(sql);
		
		try {
			Sqlhelper.executeUpdate(sql, null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("添加items异常！！！");
			return mapping.findForward("err");
		}
		
		
		return mapping.findForward("ok");
	}
}