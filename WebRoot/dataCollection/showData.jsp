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
   
    <title>已发通知</title>
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

	        <div id="grid1" class="mini-datagrid" style="width:100%;height:320px;"
				         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
				         url="dataFeedback01" onrowdblclick="rowdblclick">
				        <div property="columns">
				            <div type="indexcolumn"></div>
				            <div name="action" width="50" headerAlign="center" align="center" renderer="onOperatePower"
                 				cellStyle="padding:0;">操作
            				</div>    
				            <div field="contentTitle" width="25%" headerAlign="center">通知标题</div>
				            <div field="xaxisfeedspeed" width="15%" headerAlign="center">发送人</div>
				            <div field="xaxiscoordinates" width="15%" headerAlign="center">接收人</div>
				            <div field="id" width="15%" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss">发送时间</div>
				            <div field="time" width="15%" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss">查看时间</div>
				            <div field="grade" width="10%" headerAlign="center">通知等级</div>
				            <div field="isReaded" width="15%" headerAlign="center" renderer="onGenderRenderer">通知状态</div>
				            
				        </div>
				    </div>
   <script>
   		mini.parse();
   		var grid = mini.get("grid1");
	    grid.load();
	    
	    
	    
	    function ondEdit(id,isReaded){
	        window.location.href="NoticeDetailServlet?id=" + id+"&isReaded="+isReaded;
		}
   		
	    refresh();
	    setInterval(refresh, 6000);
   		
   		function refresh(){
   			var grid = mini.get("grid1");
			grid.load();
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
   		
   		
   		

   	
   		
   </script>
  </body>
</html>
