
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
  	 <a class="mini-button" iconCls="icon-save" plain="false"  onclick="lookMachineInfo()">查看设备详细记录</a>
  	 
  	 </div>
  <!-- 	<div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;"> 
	            <a class="mini-button" iconCls="icon-reload" plain="false" onclick="refresh()">刷新</a>
	            
	            <a class="mini-button" iconCls="icon-remove" plain="false" onclick="getForm('0')">删除</a>     
	            <span class="separator"></span>             
	            <a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm('1')">保存</a>
	            <span class="separator"></span>             
	            <a class="mini-button" iconCls="icon-undo" plain="false" onclick="javascript:window.history.back(-1);">返回</a>  
	                            
 </div> -->
	 <!--   <a class="mini-button" iconCls="icon-reload" plain="false" onclick="onclick()">点击</a>
	    <input id="piece_barcode"  name="piece_barcode" class="mini-textbox" required="true" 
	                	value="" onenter="loadgrid()"   vtype="rangeLength:15,18" style= "width:100%;"/> -->

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
				    
				<!--     <div id="editform" class="form" > -->
       <!--  <input class="mini-hidden" name="id"/>  -->
        <!-- <table style="">
            <tr>
            	<td style="width:80px;">机床       系统</td>
                <td style="width:60px;"><input  id="parm1" name="parm1" class="mini-textbox" allowInput="false" /></td>
                <td style="width:80px;">工序名称 </td>
                <td style="width:80px;"><input  name="fo_opname" class="mini-textbox" allowInput="false"/></td>
                <td style="width:80px;">工时定额 </td>
                <td style="width:80px;"><input id= "ratedtime" name="ratedtime" class="mini-textbox" allowInput="false" style="width:75px;"/>
                	<input name="num" class="mini-textbox" allowInput="false" style="visibility:hidden; width:1px;"  /></td>
            </tr>
            
            <tr>
               <td>X轴进给速度 </td>
                <td><input  id="parm2" name="parm2" class="mini-textbox" required="true" vtype="int"/></td>
                <td>合格数(*) </td>
                <td><input name="accept_num" class="mini-textbox" required="true" vtype="int"/></td>
                <td>不合格数 (*)</td>
                <td><input name="reject_num" class="mini-textbox"  value="0" required="true" vtype="int"/></td>
            </tr>
           
            <tr>
            	<td>X轴          位置</td>
            	<td><input style="" id="parm3" name="parm3" class="mini-textbox" vtype="float"/></td>
            	<td>备注 </td>
            	<td><input style="" name="remark" class="mini-textbox" /></td>
            	
            	<td style="visibility:hidden;">条形码号</td>
            	<td style="visibility:hidden;"><input id="Barcode" name="barcode" class="mini-textbox" /></td>
            </tr>
            <tr>
                <td style="text-align:right;padding-top:5px;padding-right:20px;" colspan="6">
                	
                    <a class="Update_Button" href="javascript:updateRow()">确定</a> 
                    <a class="Cancel_Button" href="javascript:cancelRow()">取消</a>
                </td>                
            </tr>
             <tr>
                <td style="text-align:right;padding-top:5px;padding-right:20px;" colspan="6">
                	 <a class="Update_Button" href="javascript:addEmp()">补充加工者信息</a> 
                </td>                
            </tr>
        </table> -->
 <!--    </div> -->
 
 
 
 <div id="add" style="background:#EFEFEF" >
		<form name="form1" id="form1" method="post" enctype="multipart/form-data">
			<table class="green_list_table" align="center" width="100%" border="1" style="word-break:break-all;border-collapse:collapse" bgcolor="#EFEFEF">
			<tr>
								<th>当前程序名称</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			        <th width="12%">当前运行程序号</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" style="background-color:blue" width="100%" style="background-color:blue" allowInput="false" vtype="float"/></td>
			    	<th width="12%">数控系统已运行时间</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" style="background-color:blue" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    			<th>数控系统已运行时间</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    
			        <th width="12%">电机温度</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    	<th width="12%">各轴实际进给速率</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    </tr>
			    <tr>
			    				<th>各轴实际进给倍率</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    
			        <th width="12%">当前刀具号</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    	<th width="12%">当前运行时间</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" allowInput="false"  width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    				<th>当前是否有待处理的报警</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox"  width="100%" allowInput="false"  vtype="float"/></td>
			    
			        <th width="12%">当前某通道的报警数</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    	<th width="12%">NC程序状态</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    </tr>
			    <tr>
			    				<th>当前运行程序状态</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox"  width="100%" allowInput="false"  vtype="float"/></td>
			    
			        <th width="12%">NC程序结束计数器</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    	<th width="12%">已加工的工件总数</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox"  allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    				<th>某个报警的消除方式</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox" allowInput="false" width="100%"  vtype="float"/></td>
			    
			        <th width="12%">刀具坐标</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    	<th width="12%">各轴的名称</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    				<th>某个报警的消除方式</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox" allowInput="false" width="100%"  vtype="float"/></td>
			    
			        <th width="12%">轴的加速度</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    	<th width="12%">轴的负载</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    				<th>某轴的功率</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox" allowInput="false" width="100%"  vtype="float"/></td>
			    
			        <th width="12%">轴的类型</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    	<th width="12%">数控系统类型</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    				<th>轴的状态</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox" allowInput="false" width="100%"  vtype="float"/></td>
			    
			        <th width="12%">转速的实际值</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    	<th width="12%">进给速率的单位</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    				<th>驱动实际电流值</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox" allowInput="false" width="100%"  vtype="float"/></td>
			    
			        <th width="12%">主轴恒定的切削速率</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    	<th width="12%">当前主轴的运行模式</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    				<th>主轴最大转速</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox" allowInput="false" width="100%"  vtype="float"/></td>
			    
			        <th width="12%">主轴最小转速</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    	<th width="12%">主轴当前状态/th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    				<th>主轴的实际转速</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox" allowInput="false" width="100%"  vtype="float"/></td>
			    
			        <th width="12%">主轴的实际转速</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    	<th width="12%">主轴的实际转速</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    				<th>主轴恒定的切削速度</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox" allowInput="false" width="100%"  vtype="float"/></td>
			    
			        <th width="12%">主轴转速期望值</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    	<th width="12%">主轴驱动负载</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    				<th>主轴名字</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox" allowInput="false" width="100%"  vtype="float"/></td>
			    
			        <th width="12%">主轴模式</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    	<th width="12%">当前主轴限速</th>
			    	<td><input id="operAidTime"  name="operAidTime" class= "mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			     <tr>
			    				<th>各轴功率</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox" allowInput="false" width="100%"  vtype="float"/></td>
			    
			        <th width="12%">PLC控制轴的状态 </th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    	<th width="12%">主轴倍率</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			     <tr>
			    				<th>NCU连接是否被激活 </th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox" allowInput="false" width="100%"  vtype="float"/></td>
			    
			        <th width="12%">Nck的工作模式</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    	<th width="12%">报警数量</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			</table>
		</form>
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
          /*   mini.get("piece_barcode").setValue("niuhfy"); */
            update();
	    }
	     update();
         setInterval(update, 3000);
	     function update(){
	    	 
	    	 var machineId = "<%=request.getParameter("machineId")%>";
	    	 
        	$.ajax({
        		type: "post",
        		url:"dataFeedback?machineId="+machineId,
        		 /* data: { data: json },  */
    			success:function(text){
    				
				   /* alert("更新成功"); */
		           var msg=$.parseJSON(text);
		           mini.get("ratedTime").setValue(msg.data.id);
		           mini.get("planTime").setValue(msg.data.xaxisfeedspeed);
		           mini.get("operAidTime").setValue(msg.data.xaxiscoordinates);
    			    },
    			error : function() {
    			    /* alert("更新失败"); */
				}
        	});
		}
	    
	    
	
	    
	    function ondEdit(id,isReaded){
	        window.location.href="NoticeDetailServlet?id=" + id+"&isReaded="+isReaded;
		}
   		
   		
   		function refresh(){
   			
			
			window.location.href=window.location.href;
		}
   		
		function lookMachineInfo(){
   			
			 var machineId = "<%=request.getParameter("machineId")%>";
			window.location.href="dataCollection/showData.jsp?machineId="+machineId;
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