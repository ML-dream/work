<%@ page language="java" import="java.util.*,com.wl.forms.Employee" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <!-- miniui.js -->
      <script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>机床数据采集的实时显示</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body>
  	
  	<div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;"> 
	            <a class="mini-button" iconCls="icon-reload" plain="false" onclick="refresh()">刷新</a>
	            <!--
	            <a class="mini-button" iconCls="icon-remove" plain="false" onclick="getForm('0')">删除</a>     
	            <span class="separator"></span>             
	            <a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm('1')">保存</a>
	            <span class="separator"></span>             
	            <a class="mini-button" iconCls="icon-undo" plain="false" onclick="javascript:window.history.back(-1);">返回</a>  
	            -->                 
 </div>
	   <a class="mini-button" iconCls="icon-reload" plain="false" onclick="onclick()">点击</a>
	    <input id="piece_barcode"  name="piece_barcode" class="mini-textbox" required="true" 
	                	value="" onenter="loadgrid()"   vtype="rangeLength:15,18" style= "width:100%;"/>

	        <!-- <div id="grid1" class="mini-datagrid" style="width:100%;height:320px;"
				         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
				         url="dataFeedback" onrowdblclick="rowdblclick">
				        <div property="columns">
				            <div type="indexcolumn"></div>
				            <div name="action" width="50" headerAlign="center" align="center" renderer="onOperatePower"
                 				cellStyle="padding:0;">操作
            				</div>    
				            <div field="id" width="15%" headerAlign="center">操作系统</div>
				            <div field="sendTime" width="20%" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss">采集时间</div>
				            <div field="xaxisfeedspeed" width="15%" headerAlign="center">机床运行模式</div>机床运行模式
				            <div field="xaxiscoordinates" width="15%" headerAlign="center">X轴位置</div>
				            <div field="grade" width="20%" headerAlign="center">运行程序段名称</div>
				            
				            <div field="isReaded" width="15%" headerAlign="center" renderer="onGenderRenderer">X轴进给速度</div>
				            
				        </div>
				    </div> -->
				    
				    <div id="editform" class="form" >
        <input class="mini-hidden" name="id"/>
        <table style="">
            <tr>
            	<td style="width:80px;">机床       系统</td>
                <td style="width:60px;"><input  id="parm1" name="parm1" class="mini-textbox" allowInput="false" /></td>
               <!--  <td style="width:80px;">工序名称 </td>
                <td style="width:80px;"><input  name="fo_opname" class="mini-textbox" allowInput="false"/></td>
                <td style="width:80px;">工时定额 </td>
                <td style="width:80px;"><input id= "ratedtime" name="ratedtime" class="mini-textbox" allowInput="false" style="width:75px;"/>
                	<input name="num" class="mini-textbox" allowInput="false" style="visibility:hidden; width:1px;"  /></td> -->
            </tr>
            
            <tr>
               <td>X轴进给速度 </td>
                <td><input  id="parm2" name="parm2" class="mini-textbox" required="true" vtype="int"/></td>
                <!-- <td>合格数(*) </td>
                <td><input name="accept_num" class="mini-textbox" required="true" vtype="int"/></td>
                <td>不合格数 (*)</td>
                <td><input name="reject_num" class="mini-textbox"  value="0" required="true" vtype="int"/></td> -->
            </tr>
           
            <tr>
            	<td>X轴          位置</td>
            	<td><input style="" id="parm3" name="parm3" class="mini-textbox" vtype="float"/></td>
            	<!-- <td>备注 </td>
            	<td><input style="" name="remark" class="mini-textbox" /></td>
            	
            	<td style="visibility:hidden;">条形码号</td>
            	<td style="visibility:hidden;"><input id="Barcode" name="barcode" class="mini-textbox" /></td> -->
            </tr>
           <!--  <tr>
                <td style="text-align:right;padding-top:5px;padding-right:20px;" colspan="6">
                	
                    <a class="Update_Button" href="javascript:updateRow()">确定</a> 
                    <a class="Cancel_Button" href="javascript:cancelRow()">取消</a>
                </td>                
            </tr> -->
             <!-- <tr>
                <td style="text-align:right;padding-top:5px;padding-right:20px;" colspan="6">
                	 <a class="Update_Button" href="javascript:addEmp()">补充加工者信息</a> 
                </td>                
            </tr> -->
        </table>
    </div>
    
    
   <script>
   		mini.parse();
   		
   		/* var grid = mini.get("grid1");
	    grid.load(); */
	    
	    /*  $function(){
	     update();
         setInterval(update, 3000); 
	    }  */
	    update();
	     setInterval(update, 500); 
	    function onclick(){
            mini.get("piece_barcode").setValue("niuhfy");
            update();
	    }
	     update();
         setInterval(update, 3000);
	     function update(){
        	$.ajax({
        		type: "post",
        		url:"dataFeedback",
        		 /* data: { data: json },  */
    			success:function(text){
    				
				   /* alert("更新成功"); */
		           var msg=$.parseJSON(text);
		           mini.get("parm1").setValue(msg.data.id);
		           mini.get("parm2").setValue(msg.data.xaxisfeedspeed);
		           mini.get("parm3").setValue(msg.data.xaxiscoordinates);
    			    },
    			error : function() {
    			    /* alert("更新失败"); */
				}
        	});
		}
	    
	    
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" + e.row.id + "\',\'" + e.row.isReaded + "\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
	        str += "</span>";
	        return str;
	    }
	    
	    function ondEdit(id,isReaded){
	        window.location.href="NoticeDetailServlet?id=" + id+"&isReaded="+isReaded;
		}
   		
   		
   		function refresh(){
			
			window.location.href=window.location.href;
		}
		
   		function getForm(flag){
			var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
            	data.isAlive=flag;
                var params = eval("("+mini.encode(data)+")");
                var url = 'EditDeptSpecServlet';
   		        jQuery.post(url, params, callbackFun, 'json');
            }
   		}
   		
   		
   		function onIDCardsValidation(e) {
            if (e.isValid) {
                var pattern = /\d*/;
                if (e.value.length < 15 || e.value.length > 18 || pattern.test(e.value) == false) {
                    e.errorText = "必须输入15~18位数字";
                    e.isValid = false;
                }
            }
        }

   	

   		var Genders = [{ id: '0', text: '未读' }, { id: '1', text: '已读'}];
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
   </script>
  </body>
</html>
