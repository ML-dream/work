var product_id="";
var issue_num="";
var node_id="";
var father_id="";
var oelement = "";
var xmlHttp;

function createXMLHttpRequest() {
	    if (window.ActiveXObject) {
	        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	    } 
	    else if (window.XMLHttpRequest) {
	        xmlHttp = new XMLHttpRequest();
	    }
	}

function onStateChange(element) {
    if(xmlHttp.readyState == 4) {
        if(xmlHttp.status == 200) {
            showSelect(xmlHttp.responseXML);
        }
    }
}

function dochange(sta,element){
	switch(sta){
	case "10":
		 oelement = element;
		product_id  = document.getElementById("product_id").value;
		issue_num  = document.getElementById("issue_num").value;
		node_id  = document.getElementById("node_id").value;
		father_id  = document.getElementById("father_id").value;
		edtime=document.getElementById("partedtime").value;
		createXMLHttpRequest();
        var url = "DoNodeSvlt?sta="+sta+"&product_id="+product_id+"&issue_num="+issue_num+"&node_id="+node_id+"&father_id="+father_id+"&partedtime="+edtime;
        /*��׼��״̬�ı�ʱ����ҪΪreadyState����ָ���¼����������ô����������ִ��ݲ����ķ�����
        */
        xmlHttp.onreadystatechange = function(){onStateChange(oelement)};//,s_product_id,s_lot,s_sortie,s_issue
        xmlHttp.open("POST", url, true);
        xmlHttp.send(null);

		break;
	case "15":
		alert("���м��ͬ����Ƿ������´�ļƻ�");
		break;
	default:
		alert("ȷ��������ֹͣ������");
	}
}

function showSelect(xmlData){
	xelement = document.getElementById(oelement)
	 if(xmlData.documentElement.hasChildNodes()){
		 var time = xmlData.getElementsByTagName("STTIME");
		 xelement.value=time[0].childNodes[0].nodeValue;
	 }
}
	
	