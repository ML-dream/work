var xmlData="";
var arra;

function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } 
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}

function EcPlan_Search(){
	var product_type =document.getElementById("product_type").value;
	var product_id   =document.getElementById("product_id").value;
	var issue_num    =document.getElementById("issue_num").value;
	var lot          =document.getElementById("lot").value;
	
	 createXMLHttpRequest();
     var url = "EcPlan_Search?product_type="+product_type+"&product_id="+product_id+"&issue_num="+issue_num+"&lot="+lot;
     
     xmlHttp.onreadystatechange = function(){onStateChanged()};
     xmlHttp.open("POST", url, true);
     xmlHttp.send(null);
}

function onStateChanged() {
    if(xmlHttp.readyState == 4) {
        if(xmlHttp.status == 200) {
            showSelected(xmlHttp.responseXML);
        }
    }
}

function showSelected(xmlData){
	if(xmlData==null){return;}
	var oElement = document.getElementById("ecplantable");
	var ecplandatas = xmlData.getElementsByTagName("ECPLANDATA");
	
	for(var j=oElement.rows.length-1;j>0;j--){//ɾ������¼��
		oElement.deleteRow(j);
	}
	
	for(var i=0;i<ecplandatas.length;i++){
		var orderid  =ecplandatas[i].childNodes[0].firstChild.nodeValue;
		var planid   =ecplandatas[i].childNodes[1].firstChild.nodeValue;
		var itemid   =ecplandatas[i].childNodes[2].firstChild.nodeValue;
		var fitemid  =ecplandatas[i].childNodes[3].firstChild.nodeValue;
		var qualityid=ecplandatas[i].childNodes[4].firstChild.nodeValue;
		var sttime   =ecplandatas[i].childNodes[5].firstChild.nodeValue;
		var edtime   =ecplandatas[i].childNodes[6].firstChild.nodeValue;
		var num      =ecplandatas[i].childNodes[7].firstChild.nodeValue;
		var ectype   =ecplandatas[i].childNodes[8].firstChild.nodeValue;
		var eccon    =ecplandatas[i].childNodes[9].firstChild.nodeValue;
		var ectime   =ecplandatas[i].childNodes[10].firstChild.nodeValue;
		
		 arra  = new Array();
			for(var m=0;m<15;m++){
					arra[m]=ecplandatas[i].childNodes[m].firstChild.nodeValue;
					
			}
		
		
		var newRow = oElement.insertRow();//������
		
		var newCelldo         = newRow.insertCell(); //�õ��հ׵�Ԫ��
		var newCellorderid    = newRow.insertCell();
		var newCellplanid     = newRow.insertCell();
		var newCellitemid     = newRow.insertCell();
		var newCellfitemid    = newRow.insertCell();
		var newCellqualityid  = newRow.insertCell();
		var newCellsttime     = newRow.insertCell();
		var newCelledtime     = newRow.insertCell();
		var newCellnum        = newRow.insertCell();
		var newCellectype     = newRow.insertCell();
		var newCelleccon      = newRow.insertCell();
		var newCellectime     = newRow.insertCell();
		
		newCelldo.innerHTML        = "<td align='center'><FONT color='#338800'><A onclick='del(arra)' style='cursor:hand'>[ɾ��]</A></FONT></td>";
		newCellorderid.innerHTML   = orderid;
		newCellplanid.innerHTML    = planid;
		newCellitemid.innerHTML    = itemid;
		newCellfitemid.innerHTML   = fitemid;
		newCellqualityid.innerHTML = qualityid;
		newCellsttime.innerHTML    = sttime;
		newCelledtime.innerHTML    = edtime;
		newCellnum.innerHTML       = num;
		newCellectype.innerHTML    = ectype;
		newCelleccon.innerHTML     = eccon;
		newCellectime.innerHTML    = ectime;
	
	/*if(i==ecebomdatas.length-1){
		var newRowrecord  = oElement.insertRow();
		var newCellrecord = newRowrecord.insertCell();
	    newCellrecord.colspan = 8;
		newCellrecord.innerHTML = "<td align=center colspan=8 >���м�¼����   ��ǰ  ҳ"+
		"<a onclick='firsrpg();' style='cursor:hand'>��һҳ</a><a onclick='lastpg();' style='cursor:hand'>��һҳ </a>"+
		"<a onclick='nextpg();' style='cursor:hand'>��һҳ</a>"+
		"<a onclick='finalpg();' style='cursor:hand'>���ҳ </a>"+
		"ֱ��<input type=image src='img/hand.gif' name='gotof' onclick='return chkdata();'>"+
		"<input type=text size=5 name=bm class=formcolor>ҳ</TD>"
		}*/
	}
}



